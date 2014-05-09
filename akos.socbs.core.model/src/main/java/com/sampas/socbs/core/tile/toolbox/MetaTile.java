package com.sampas.socbs.core.tile.toolbox;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class MetaTile implements TileResponseReceiver {
    
	private static Log log = LogFactory.getLog(com.sampas.socbs.core.tile.toolbox.MetaTile.class);

    protected int[] metaTileGridBounds = null; // minx,miny,maxx,maxy,zoomlevel

    protected int[][] tilesGridPositions = null; // the grid positions of the

    // individual tiles

    protected int metaX; // X metatiling factor, after adjusting to bounds

    protected int metaY; // Y metatiling factor, after adjusting to bounds

    protected SRS srs;

    protected long status = -1;

    protected boolean error = false;

    protected String errorMessage;

    protected long expiresHeader = -1;

    protected MimeType mimeType;

    protected MetaTile(SRS srs, MimeType mimeType, int[] gridBounds,
            int[] tileGridPosition, int metaX, int metaY) {
        this.srs = srs;
        this.mimeType = mimeType;
        this.metaX = metaX;
        this.metaY = metaY;

        metaTileGridBounds = calculateMetaTileGridBounds(gridBounds,
                tileGridPosition);
        tilesGridPositions = calculateTilesGridPositions();
    }

    public int getStatus() {
        return (int) status;
    }

    public void setStatus(int status) {
        this.status = (long) status;
    }

    public boolean getError() {
        return this.error;
    }

    public void setError() {
        this.error = true;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getExpiresHeader() {
        return this.expiresHeader;
    }

    public void setExpiresHeader(long seconds) {
        this.expiresHeader = seconds;
    }

    /**
     * Figures out the bounds of the metatile, in terms of the gridposition of
     * all contained tiles. To get the BBOX you need to add one tilewidth to the
     * top and right.
     * 
     * It also updates metaX and metaY to the actual metatiling factors
     * 
     * @param gridBounds
     * @param tileGridPosition
     * @return
     */
    private int[] calculateMetaTileGridBounds(int[] gridBounds,
            int[] tileGridPosition) {

        int[] metaTileGridBounds = new int[5];
        metaTileGridBounds[0] = tileGridPosition[0]
                - (tileGridPosition[0] % metaX);
        metaTileGridBounds[1] = tileGridPosition[1]
                - (tileGridPosition[1] % metaY);
        metaTileGridBounds[2] = Math.min(metaTileGridBounds[0] + metaX - 1,
                gridBounds[2]);
        metaTileGridBounds[3] = Math.min(metaTileGridBounds[1] + metaY - 1,
                gridBounds[3]);
        metaTileGridBounds[4] = tileGridPosition[2];

        // Save the actual metatiling factor, important at the boundaries
        metaX = metaTileGridBounds[2] - metaTileGridBounds[0] + 1;
        metaY = metaTileGridBounds[3] - metaTileGridBounds[1] + 1;

        return metaTileGridBounds;
    }

    /**
     * Creates an array with all the grid positions, used for cache keys
     */
    private int[][] calculateTilesGridPositions() {
        if (metaX < 0 || metaY < 0) {
            return null;
        }
        int[][] tilesGridPositions = new int[metaX * metaY][3];

        try {
            for (int y = 0; y < metaY; y++) {
                for (int x = 0; x < metaX; x++) {
                    int tile = y * metaX + x;
                    tilesGridPositions[tile][0] = metaTileGridBounds[0] + x;
                    tilesGridPositions[tile][1] = metaTileGridBounds[1] + y;
                    tilesGridPositions[tile][2] = metaTileGridBounds[4];
                }
            }
        } catch (java.lang.NullPointerException npe) {
            log.error("Null pointer exception in calculateTilesGridositions()");
        }
        return tilesGridPositions;
    }

    /**
     * The bottom left grid position and zoomlevel for this metatile, used for
     * locking.
     * 
     * @return
     */
    public int[] getMetaGridPos() {
        int[] gridPos = { metaTileGridBounds[0], metaTileGridBounds[1],
                metaTileGridBounds[4] };
        return gridPos;
    }

    /**
     * The bounds for the metatile
     * 
     * @return
     */
    public int[] getMetaTileGridBounds() {
        return metaTileGridBounds;
    }

    public int[][] getTilesGridPositions() {
        return tilesGridPositions;
    }

    public SRS getSRS() {
        return this.srs;
    }
}
