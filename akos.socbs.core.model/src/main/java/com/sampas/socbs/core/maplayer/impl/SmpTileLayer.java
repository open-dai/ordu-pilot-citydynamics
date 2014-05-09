package com.sampas.socbs.core.maplayer.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.data.ITileDataStore;
import com.sampas.socbs.core.data.provider.services.impl.TileDataStoreSrv;
import com.sampas.socbs.core.data.providers.ITileLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpTileLayerProviderSrv;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.maplayer.ILayerAttribute;
import com.sampas.socbs.core.maplayer.ITileLayer;
import com.sampas.socbs.core.renderer.IRenderer;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.symbology.IStyle;
import com.sampas.socbs.core.symbology.impl.SmpStyle;
import com.sampas.socbs.core.tile.impl.TileDataStore;
import com.sampas.socbs.core.tile.impl.TileLayer;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileImageReturnType;
import com.sampas.socbs.core.tile.impl.TileDataStore.TileServerVersion;
import com.sampas.socbs.core.tile.toolbox.BBOX;
import com.sampas.socbs.core.tile.toolbox.BadTileException;
import com.sampas.socbs.core.tile.toolbox.Cache;
import com.sampas.socbs.core.tile.toolbox.CacheFactory;
import com.sampas.socbs.core.tile.toolbox.CacheKey;
import com.sampas.socbs.core.tile.toolbox.GeoWebCacheException;
import com.sampas.socbs.core.tile.toolbox.MimeType;
import com.sampas.socbs.core.tile.toolbox.SRS;
import com.sampas.socbs.core.tile.toolbox.Tile;
import com.sampas.socbs.core.tools.IProcessContext;

public class SmpTileLayer extends TileLayer implements ITileLayer{

	private TileDataStore tileDataProvider;
	private List<ITileLayer> tileLayers = new ArrayList<ITileLayer>();
	private TileImageReturnType imageReturnType;
	private List<IStyle> styles = new ArrayList<IStyle>();
	private SRS srs;
	private List<ITileLayer> allTileLayers;
	private IEnvelope extent;
	private List<ITileLayer> tmpTileLayer = new ArrayList<ITileLayer>();
	private ICoordinateSystem coordinateSystem;
	private boolean visible;
	private int layerZoomLevel;
	private List<ILayerAttribute> layerAttributesFromMetadata;

	public SmpTileLayer(URL tileServerUrl, TileServerVersion tileServerVersion, TileImageReturnType imageReturnType,int metaTyling,boolean allowCache){

		TileDataStoreSrv  tileDataStoreSrv = new TileDataStoreSrv(tileServerUrl, tileServerVersion, imageReturnType, metaTyling,allowCache);
		tileDataProvider = tileDataStoreSrv.getTileDataStore();
		ITileLayerProvider tileLayerProvider = new SmpTileLayerProviderSrv();
		allTileLayers = tileLayerProvider.getLayers(tileDataProvider);
		setImageReturnType(imageReturnType);
		//ITileLayerProvider tileLayerProvider = new SmpTileLayerProviderSrv();

	}

	@Override
	public void acquireLayerLock() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Tile doNonMetatilingRequest(Tile tile) throws GeoWebCacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getBackendTimeout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BBOX getBboxForGridLoc(SRS srs, int[] gridLoc) throws GeoWebCacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cache getCache() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheKey getCacheKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCachePrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getCoveredGridLevels(SRS srs, BBOX bounds)
	throws GeoWebCacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MimeType getDefaultMimeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getGridLocForBounds(SRS srs, BBOX bounds)
	throws BadTileException, GeoWebCacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getMetaTilingFactors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MimeType> getMimeTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile getNoncachedTile(Tile tile, boolean requestTiled)
	throws GeoWebCacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStyles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile getTile(Tile tile) throws GeoWebCacheException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getZoomInGridLoc(SRS srs, int[] gridLoc)
	throws GeoWebCacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getZoomedOutGridLoc(SRS srs) throws GeoWebCacheException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Boolean initialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isCacheBypassAllowed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void isCacheBypassAllowed(boolean allowed) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean isInitialized() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putTile(Tile tile) throws GeoWebCacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public void releaseLayerLock() {
		// TODO Auto-generated method stub

	}

	@Override
	public void seedTile(Tile tile, boolean tryCache)
	throws GeoWebCacheException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBackendTimeout(int seconds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCacheFactory(CacheFactory cacheFactory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setExpirationHeader(HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean tryCacheFetch(Tile tile) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setTileDataProvider(ITileDataStore tileDataProvider) {
		// TODO Auto-generated method stub

	}

	public void addLayer(ILayer layer) throws Exception {

		for (int i = 0; i < allTileLayers.size(); i++) {
			if(allTileLayers.get(i).getName().equals(layer.getName())){
				allTileLayers.get(i).setVisible(layer.isVisible());
				this.tileLayers.add(allTileLayers.get(i));
				break;
			}				
		}		

	}

	public void addRenderer(IRenderer renderer) throws Exception {
		// TODO Auto-generated method stub

	}

	public void changeLayerVisibility() {
		// TODO Auto-generated method stub

	}

	public ICoordinateSystem getCoordinateSystem() {

		return this.coordinateSystem;
	}

	public IEnvelope getExtent() {

		return this.extent;
	}

	public String getGeometryColumnName() {
		// TODO Auto-generated method stub
		return null;
	}

	public ILayer getLayerAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLayerCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getLayerType() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getMaximumScale() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getMinimumScale() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<List<String>> getPointInfo(ICoordinate coordinate,
			ICoordinateSystem coordinateSystem, IEnvelope visibleExtent,
			double offsetX, double offsetY) {
		// TODO Auto-generated method stub
		return null;
	}

	public IRenderer getRenderer(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getRendererCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ILayerAttribute> getSmpLayerAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTipText(IPoint point, double tolerance) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserLayerName() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void removeLayer(int index) {
		// TODO Auto-generated method stub

	}

	public void removeRenderer(int index) {
		// TODO Auto-generated method stub

	}

	public void render(RenderPhaseEnum renderPhase, IDisplay display,
			IProcessContext processContext) {


		//		ITileLayer tileLayer = null;
		//		
		//		for (int i = 0; i < tileLayers.size(); i++) {
		//			
		//			if(tileLayers.get(i).getName().equals(selectedLayer)) {
		//				
		//				tileLayer = tileLayers.get(i);
		//				System.out.println("Selected Layer : " + selectedLayer);
		//			}
		//		}


		if(this.tileLayers.size() == 0){
			return;
		}
		else if(getCoordinateSystem() == null){
			return;
		}

		if(srs == null || srs.getNumber() != getCoordinateSystem().getEPSGCodeNo()){
			srs = new SRS(getCoordinateSystem().getEPSGCodeNo());
		}

		try{

			IEnvelope currentExtent = display.getDisplayTransformation().getFittedBounds();
			BBOX targetBBox  = new BBOX(currentExtent.getMinX(),currentExtent.getMinY(),currentExtent.getMaxX(),currentExtent.getMaxY());
			//			
//			// Example bbox for testing 
//			double targetBBoxMinX = 28.95751094;
//			double targetBBoxMinY = 41.04139901;
//			double targetBBoxMaxX = 28.96482083;
//			double targetBBoxMaxY = 41.04512771;
//			BBOX targetBBox = new BBOX(targetBBoxMinX, targetBBoxMinY, targetBBoxMaxX, targetBBoxMaxY);	

			layerZoomLevel = calculateZoom(currentExtent,(int)display.getEnvelope().getWidth(),(int)display.getEnvelope().getHeight());

			System.out.println("Tile Layer Zoom Level :"+layerZoomLevel);
			// allTileLayers.get(13)
			int [] tileBounds = tileDataProvider.getTileBoundsFromBBox(allTileLayers.get(0),srs, targetBBox, layerZoomLevel);

			setExtent(new SmpBoundingRectangle(tileBounds[0],tileBounds[1],tileBounds[2],tileBounds[3]));
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void resetExtent() {
		// TODO Auto-generated method stub

	}

	public void setCoordinateSystem(ICoordinateSystem coordinateSystem) {

		this.coordinateSystem = coordinateSystem;

	}

	public void setExtent(IEnvelope envelope) {
		this.extent = envelope;

	}

	public void setGeometryColumnName(String geometryColumnName) {
		// TODO Auto-generated method stub

	}

	public void setLayerType(String layerType) {
		// TODO Auto-generated method stub

	}

	public void setMaximumScale(double scale) {
		// TODO Auto-generated method stub

	}

	public void setMinimumScale(double scale) {
		// TODO Auto-generated method stub

	}

	public void setRenderer(List<IRenderer> smpRenderer) {
		// TODO Auto-generated method stub

	}

	public void setSmpLayerAttributes(List<ILayerAttribute> layerAttribute) {
		// TODO Auto-generated method stub

	}

	public void setUserLayerName(String userLayerName) {


	}

	public void setVisible(boolean visible) {

		this.visible = visible;
	}

	public byte[] getTileAtPosition(int tileNoX,int tileNoY, IDimension tileDimension){

		if(this.tileLayers.size() == 0){
			return null;
		}
		else if(getCoordinateSystem() == null){
			return null;
		}
		else if(getExtent() == null){
			return null;
		}

		if(srs == null || srs.getNumber() != getCoordinateSystem().getEPSGCodeNo()){
			srs = new SRS(getCoordinateSystem().getEPSGCodeNo());
		}

		tmpTileLayer.clear();
		styles.clear();

		for (int i = 0; i < this.tileLayers.size(); i++) {
			if(tileLayers.get(i).isVisible()){
				tmpTileLayer.add(tileLayers.get(i));
				styles.add(new SmpStyle(""));
			}
		}

		//		tmpTileLayer.add(this.allTileLayers.get(13));
		//		tmpTileLayer.add(this.allTileLayers.get(14));
		//		tmpTileLayer.add(this.allTileLayers.get(16));
		//		
		//		styles.add(new SmpStyle(""));
		//		styles.add(new SmpStyle(""));
		//		styles.add(new SmpStyle(""));

		if(tmpTileLayer.size() == 0){
			return null;
		}

		BufferedImage tmpImage = 
			tileDataProvider.getTileWithTileLocation(tmpTileLayer,srs,styles, tileNoX, tileNoY, layerZoomLevel, tileDimension, getImageReturnType());

		return bufferedImageToByteArr(tmpImage);
	}

	public void setTileLayers(List<ITileLayer> tileLayers) {
		this.tileLayers = tileLayers;
	}

	public List<ITileLayer> getTileLayers() {
		return tileLayers;
	}

	public TileImageReturnType getImageReturnType() {
		return imageReturnType;
	}

	public void setImageReturnType(TileImageReturnType imageReturnType) {
		this.imageReturnType = imageReturnType;
	}

	private byte[] bufferedImageToByteArr(BufferedImage image) {

		if(image == null){
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
		byte[] resultImageAsRawBytes = null;
		// W R I T E
		try {
			ImageIO.write(image, "png" /*
			 * "png" "jpeg" format desired, no "gif"
			 * yet.
			 */, baos);
			baos.flush();
			resultImageAsRawBytes = baos.toByteArray();
			// C L O S E
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultImageAsRawBytes;
	}

	private Long[] fromLatLngToPxl(Double lat, Double lng, int zoom) {

		Long[] result = new Long[2];

		Long[] pixelRange = new Long[19];

		Long pixels = 256l;
		Long origin;

		Long[] pixelsPerLonDegree = new Long[19];
		Long[] pixelsPerLonRadian = new Long[19];

		Long o = 0l;

		for (Integer z = 0; z <= 18; z++) {
			origin = pixels / 2;
			pixelsPerLonDegree[z] = pixels / 360;
			pixelsPerLonRadian[z] = ((Double) (pixels / (2 * Math.PI))).longValue();
			//pixelOrigo[z][0] = origin;
			//pixelOrigo[z][1] = origin;
			if (z==zoom) {
				o = origin;	
				break;
			}
			pixelRange[z] = pixels;
			pixels *= 2; 
		}

		result[0] = Math.round(o.doubleValue() + lng * pixelsPerLonDegree[zoom].doubleValue());
		Double siny = Math.sin(Math.toRadians(lat));
		siny = (siny < -0.9999) ? -0.9999 : (siny > 0.9999) ? 0.9999 : siny;
		result[1] = Math.round(o + 0.5 * Math.log((1 + siny) / (1 - siny)) * -pixelsPerLonRadian[zoom]);
		return result;
	}

	private Integer calculateZoom(IEnvelope extent, int mapWidth, int mapHeight) {

		Long[] pixelRange = new Long[19];
		Long pixels = 256l;

		for (Integer z = 0; z <= 18; z++) {
			pixelRange[z] = pixels;
			pixels *= 2; 
		}

		for (Integer z = 18; z > 0; z--) {
			Long[] btmLeftPxl = fromLatLngToPxl(extent.getMinX(), extent.getMinY(), z);
			Long[] topRghtPxl = fromLatLngToPxl(extent.getMaxX(), extent.getMaxY(), z);
			if (btmLeftPxl[0] > topRghtPxl[0])
				btmLeftPxl[0] -= pixelRange[z];
			if (Math.abs(topRghtPxl[0] - btmLeftPxl[0]) <= mapWidth && Math.abs(topRghtPxl[1] - btmLeftPxl[1]) <= mapHeight)
				return z;
		}
		return 0;
	}

	public ILayerAttribute getSmpLayerAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addLayerAttributesFromMetadata(ILayerAttribute layerAttribute) {

		this.layerAttributesFromMetadata.add(layerAttribute);
	}

	public void addLayerAttributesFromMetadata(int index,
			ILayerAttribute layerAttribute) {

		this.layerAttributesFromMetadata.add(index,layerAttribute);
	}

	public List<ILayerAttribute> getLayerAttributesFromMetadata() {
		
		return this.layerAttributesFromMetadata;
	}

	public void removeLayerAttributesFromMetadata(int index) {
		
		this.layerAttributesFromMetadata.remove(index);	
	}

	public int getLayerPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setLayerPriority(int layerPriority) {
		// TODO Auto-generated method stub
		
	}

	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BBOX getBboxForGridLoc(SRS srs, int[] gridLoc, int zoomLevel)
			throws GeoWebCacheException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<List<String>> getPointInfo(ICoordinate coordinate,
			ICoordinateSystem coordinateSystem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNamespace(String nameSpace) {
		// TODO Auto-generated method stub
		
	}

}
