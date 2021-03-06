/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */
package com.sampas.socbs.core.data.arcsde.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import org.geotools.data.DataSourceException;
import com.esri.sde.sdk.client.SeException;
import com.esri.sde.sdk.client.SeQuery;
import com.esri.sde.sdk.client.SeRasterConstraint;
import com.esri.sde.sdk.client.SeRasterTile;
import com.esri.sde.sdk.client.SeRow;
import com.esri.sde.sdk.client.SeSqlConstruct;


@SuppressWarnings("unchecked")
public class ArcSDERasterReader extends ImageReader {

    private static final boolean DEBUG = false;

    private static final ArrayList supportedImageTypes;
    static {
        supportedImageTypes = new ArrayList();
        supportedImageTypes.add(ImageTypeSpecifier
                .createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB));
    }

    private final Logger LOGGER = org.geotools.util.logging.Logging.getLogger(this.getClass()
            .toString());

    private final ArcSDEPyramid _rasterPyramid;

    private final Dimension _tileSize;

    private final String _rasterTable, _rasterColumn;

    public ArcSDERasterReader(ArcSDERasterReaderSpi parent, ArcSDEPyramid rasterPyramid,
            String rasterTable, String rasterColumn) {
        super(parent);
        _tileSize = rasterPyramid.getTileDimension();
        _rasterPyramid = rasterPyramid;
        _rasterTable = rasterTable;
        _rasterColumn = rasterColumn;
    }

    //@Override
    public int getHeight(int imageIndex) throws IOException {
        return _rasterPyramid.getPyramidLevel(imageIndex).size.height;
    }

    //@Override
    public int getWidth(int imageIndex) throws IOException {
        return _rasterPyramid.getPyramidLevel(imageIndex).size.width;
    }

    //@Override
    public Iterator getImageTypes(int imageIndex) throws IOException {
        return supportedImageTypes.iterator();
    }

    //@Override
    public int getNumImages(boolean allowSearch) throws IOException {
        return _rasterPyramid.getNumLevels();
    }

    /**
     * This method is THREADSAFE.
     * 
     * Reads from its configured ArcSDE Raster "row" (one row in a raster table
     * corresponds to one ArcSDERasterReader) in the manner described by the
     * supplied ImageReadParam.
     * 
     * @param param
     *            This must be an ArcSDERasterImageReadParam, containing a
     *            valid, live SeConnection through which this reader will "suck"
     *            its raster data from SDE.
     * @param imageIndex
     *            This parameter specifies which image pyramid level (if there's
     *            more than one) to read from. If there's only one pyramid
     *            level, this value should be 0.
     * 
     * @throws IOException
     */
    //@Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {

        // we only read from ArcSDERasterImageReadParams.
        if (!(param instanceof ArcSDERasterImageReadParam)) {
            throw new IllegalArgumentException(
                    "read() must be called with an ArcSDERasterReadImageParam, not a "
                            + param.getClass());
        }
        final ArcSDERasterImageReadParam sdeirp = (ArcSDERasterImageReadParam) param;

        // double-check that all the required info is present.
        if (sdeirp.getSourceBands() == null) {
            throw new IllegalArgumentException(
                    "You must provide source bands to the ArcSDERasterReader via param.setSourceBands()");
        }
        if (sdeirp.getConnection() == null) {
            throw new IllegalArgumentException(
                    "You must provide a connection to the ArcSDERasterReader via the param.setConnection() method.");
        }
        if (sdeirp.getBandMapper() == null) {
            throw new IllegalArgumentException(
                    "You must provide a hashmap bandmapper to the ArcSDERasterReader via the param.setBandMapper() method");
        }

        // start collecting our background information for doing the read.

        // the source region is the actual pixel extent of this particar pyramid
        // layer
        final Rectangle sourceRegion = param.getSourceRegion();

        final ArcSDEPyramidLevel curLevel = _rasterPyramid.getPyramidLevel(imageIndex);

        // if we're reading "off the top" or "off the left" side of the image,
        // then there's some "blank" part of
        // the returned image that we need to leave blank. This offset tells us
        // where to start writing into the
        // destination image in that case. (Note, if we're reading "off the
        // bottom" or "off the right" side of
        // the image, this doesn't affect us as we just stop writing at the end
        // of the tile and the rest is
        // left blank automatically).
        final Point destOffset = param.getDestinationOffset() == null ? new Point(0, 0) : param
                .getDestinationOffset();
        if (curLevel.getXOffset() != 0) {
            sourceRegion.x += curLevel.getXOffset();
        }
        if (curLevel.getYOffset() != 0) {
            if (LOGGER.isLoggable(Level.FINER))
                LOGGER.finer("y-axis is offset by " + curLevel.getYOffset()
                        + " at SDE pyramid level " + curLevel.getLevel());
            sourceRegion.y += curLevel.getYOffset();
        }

        // the destination image for our eventual raster read. Can be provided
        // (if the given one is non-null)
        // or we can be expected to generate it (if the given one is null)
        BufferedImage destination = param.getDestination();
        if (destination == null && (destOffset.x == 0 && destOffset.y == 0)) {
            destination = new BufferedImage(sourceRegion.width, sourceRegion.height,
                    BufferedImage.TYPE_INT_ARGB);
        } else if (destination == null) {
            final int imageWidth = destOffset.x + sourceRegion.width;
            final int imageHeight = destOffset.y + sourceRegion.height;
            destination = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        } else if (!(destOffset.x == 0 && destOffset.y == 0)) {
            int destWidth = sourceRegion.width, destHeight = sourceRegion.height;
            if (destOffset.x + sourceRegion.width > destination.getWidth())
                destWidth = destination.getWidth() - destOffset.x;
            if (destOffset.y + sourceRegion.height > destination.getHeight())
                destHeight = destination.getHeight() - destOffset.y;
            destination = destination
                    .getSubimage(destOffset.x, destOffset.y, destWidth, destHeight);
        } else {
            // we've got a non-null destination image and there's no offset.
            // Nothing to do!
        }

        // figure out which tiles exactly, we'll be fetching
        final int minTileX = sourceRegion.x / _tileSize.width;
        final int minTileY = sourceRegion.y / _tileSize.height;
        if (LOGGER.isLoggable(Level.FINER))
            LOGGER
                    .finer("figured minTiles: " + minTileX + "," + minTileY + ".  Image is "
                            + curLevel.getNumTilesWide() + "x" + curLevel.getNumTilesHigh()
                            + " tiles wxh.");
        int maxTileX = (sourceRegion.x + sourceRegion.width) / _tileSize.width;
        int maxTileY = (sourceRegion.y + sourceRegion.height) / _tileSize.height;
        if (maxTileX > curLevel.getNumTilesWide())
            maxTileX = curLevel.getNumTilesWide() - 1;
        if (maxTileY > curLevel.getNumTilesHigh())
            maxTileY = curLevel.getNumTilesHigh() - 1;

        // figure out what our offset into the tile grid is
        final int tilegridOffsetX = sourceRegion.x % _tileSize.width;
        final int tilegridOffsetY = sourceRegion.y % _tileSize.height;

        if (LOGGER.isLoggable(Level.INFO))
            LOGGER.info("Reading " + param.getSourceRegion() + " offset by "
                    + sdeirp.getDestinationOffset() + " (tiles " + minTileX + "," + minTileY
                    + " to " + maxTileX + "," + maxTileY + " in level " + imageIndex + ")");

        // Now we do the actual reading from SDE.
        ArcSDEPooledConnection scon = sdeirp.getConnection();
        SeQuery query = null;

        try {
            // This rather strange set of query operations is apparently the way
            // one gets SDE Raster output. First, query the
            // database for the single row in the raster business table.
            // FIXME: Raster catalogs need to specify what their row number is.
            query = new SeQuery(scon, new String[] { _rasterColumn }, new SeSqlConstruct(
                    _rasterTable));
            query.prepareQuery();
            query.execute();
            // Next, fetch the single row back.
            final SeRow r = query.fetch();

            // Now build a SeRasterConstraint object which queries the db for
            // the right tiles/bands/pyramid level
            SeRasterConstraint rConstraint = new SeRasterConstraint();
            rConstraint.setEnvelope(minTileX, minTileY, maxTileX, maxTileY);
            rConstraint.setLevel(imageIndex);
            rConstraint.setBands(sdeirp.getSourceBands());

            // Finally, execute the raster query aganist the already-opened
            // SeQuery object which already has an SeRow fetched against it.
            query.queryRasterTile(rConstraint);

            // Now, magically, calls to r.getRasterTile() will fetch our list
            // of tiles.
            SeRasterTile curTile = r.getRasterTile();

            // the bit of our destination image that overlaps this tile
            WritableRaster destinationSubTile;

            // the offsets into the CURRENT TILE of where we should start
            // copying from the tile to the destination image. Will only be
            // non-zero
            // if we're at the "leading edge" or "top edge" of the grid of
            // tiles.
            int curTileOffsetX, curTileOffsetY;

            // When we copy from the current SeRasterTile into the destination
            // image, we'll create
            // a BufferedImage.getSubImage() of the destitation that perfectly
            // overlaps the current
            // SeRasterTile, and copy the data from the current SeRasterTile to
            // that sub-image. These
            // vars hold the proper offsets, width and height into the
            // DESTINATION IMAGE.
            int destImageOffsetX, destImageOffsetY;
            int destImageTileWidth, destImageTileHeight;

            // Copying from an ArcSDE Raster to the specified image format, we
            // can optionally have a
            // band mapper which specifies which data band goes to which image
            // band.
            HashMap bandMapper = sdeirp.getBandMapper();

            // And we need to create a bandcopier for this raster type.
            final ArcSDERasterBandCopier bandCopier = ArcSDERasterBandCopier.getInstance(r
                    .getRaster(0).getPixelType(), _tileSize.width, _tileSize.height);

            while (curTile != null) {
                // LOGGER.info("tile at " + curTile.getColumnIndex() + "," +
                // curTile.getRowIndex() + " has " + curTile.getNumPixels() + "
                // pixels");
            	//TODO: next if satament closed by SAMPAS GIS USER
//                if (curTile.getNumPixels() == 0 && false) {
//                    curTile = r.getRasterTile();
//                    continue;
//                }

                // Does our image start at the exact tile boundary? If we're in
                // the middle of a range of tiles,
                // it does, otherwise it's probably going to start slighty in
                // from the edge of the tile.
                if (curTile.getColumnIndex() == minTileX) {
                    curTileOffsetX = tilegridOffsetX;
                } else {
                    curTileOffsetX = 0;
                }

                if (curTile.getRowIndex() == minTileY) {
                    curTileOffsetY = tilegridOffsetY;
                } else {
                    curTileOffsetY = 0;
                }

                // Now we figure out how far into the destination image we go to
                // create a mini sub-tile
                // that overlaps this current tile. If we're at the first tile,
                // we start at zero.
                final int curTileX = curTile.getColumnIndex() - minTileX;
                if (curTileX == 0) {
                    destImageOffsetX = 0;
                } else {
                    destImageOffsetX = (_tileSize.width - tilegridOffsetX)
                            + ((curTileX - 1) * _tileSize.width);
                }

                final int curTileY = curTile.getRowIndex() - minTileY;
                if (curTileY == 0) {
                    destImageOffsetY = 0;
                } else {
                    destImageOffsetY = (_tileSize.height - tilegridOffsetY)
                            + ((curTileY - 1) * _tileSize.height);
                }

                if (curTile.getColumnIndex() == maxTileX
                        && (destImageOffsetX + _tileSize.width > destination.getWidth())) {
                    // if we're at the end of the row or column, we might try to
                    // grab too large of a sub-image. Make sure
                    // that we're only grabbing up to the actual size of the
                    // image.
                    destImageTileWidth = destination.getWidth() - destImageOffsetX;
                } else if (curTileX == 0) {
                    // if we're at the beginning of a row or column, we don't
                    // need to grab the entire
                    // sub-image. We need to grab just the bit that overlaps the
                    // current tile.
                    destImageTileWidth = (_tileSize.width - curTileOffsetX);
                } else {
                    destImageTileWidth = _tileSize.width;
                }

                if (curTile.getRowIndex() == maxTileY
                        && (destImageOffsetY + _tileSize.height > destination.getHeight())) {
                    destImageTileHeight = destination.getHeight() - destImageOffsetY;
                } else if (curTileY == 0) {
                    destImageTileHeight = (_tileSize.height - curTileOffsetY);
                } else {
                    destImageTileHeight = _tileSize.height;
                }

                // LOGGER.info("creating subtile at " + new
                // Rectangle(destImageOffsetX, destImageOffsetY,
                // destImageTileWidth, destImageTileHeight));
                if (destImageTileWidth == 0 || destImageTileHeight == 0) {
                    if (LOGGER.isLoggable(Level.FINER))
                        LOGGER.finer("Skipping tile " + curTileX + "," + curTileY
                                + " because it has imagetile height " + destImageTileHeight
                                + " and width " + destImageTileWidth);
                    curTile = r.getRasterTile();
                    continue;
                }
                BufferedImage subtile = destination.getSubimage(destImageOffsetX, destImageOffsetY,
                        destImageTileWidth, destImageTileHeight);
                destinationSubTile = subtile.getRaster();

                final Integer curBandId = new Integer((int) curTile.getBandId().longValue());
                final int targetBand = ((Integer) bandMapper.get(curBandId)).intValue();
                bandCopier.copyPixelData(curTile, destinationSubTile, curTileOffsetX,
                        curTileOffsetY, targetBand);

                if (DEBUG) {
                    int[] blackpixel = new int[] { 0x00, 0x00, 0x00, 0xff };
                    for (int x = 0; x < destinationSubTile.getWidth(); x++) {
                        destinationSubTile.setPixel(x, 0, blackpixel);
                        destinationSubTile.setPixel(x, destinationSubTile.getHeight() - 1,
                                blackpixel);
                    }
                    for (int y = 0; y < destinationSubTile.getHeight(); y++) {
                        destinationSubTile.setPixel(0, y, blackpixel);
                        destinationSubTile.setPixel(destinationSubTile.getWidth() - 1, y,
                                blackpixel);
                    }

                    final Graphics2D graphics = subtile.createGraphics();
                    graphics.setFont(new Font("Sans-serif", Font.BOLD, 10));
                    graphics.setColor(Color.yellow);
                    graphics.drawString(curTile.getRowIndex() + "," + curTile.getColumnIndex(), 10,
                            10);

                    graphics.drawString(destImageOffsetX + "," + destImageOffsetY + " -- "
                            + destImageTileWidth + "x" + destImageTileHeight, 10, 25);

                    graphics.dispose();
                }

                // fetch the next tile
                curTile = r.getRasterTile();
            }
            //always null at this point
            //curTile = null;
            destinationSubTile = null;

            // don't need to close connections, cause that's done in the
            // 'finally' block
        } catch (SeException se) {
            LOGGER.log(Level.SEVERE, se.getSeError().getErrDesc(), se);
            throw new DataSourceException(se);
        } finally {
            try {
                if (query != null)
                    query.close();
                /*
                 * if (scon != null && !scon.isClosed()) scon.close();
                 */
            } catch (SeException se) {
                LOGGER.log(Level.SEVERE, se.getSeError().getErrDesc(), se);
                throw new DataSourceException(
                        "Unable to clean up connections to database.  May have left one hanging.",
                        se);
            }
        }

        return destination;
    }

    /**
     * Not implemented
     */
    //@Override
    public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
        return null;
    }

    /**
     * Not implemented
     */
    //@Override
    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }
}
