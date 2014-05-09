package com.sampas.socbs.core.maplayer.impl;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.IWMSDataStore;
import com.sampas.socbs.core.data.wms.impl.GetFeatureInfoResult;
import com.sampas.socbs.core.data.wms.impl.GetMapResult;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsImageReturnType;
import com.sampas.socbs.core.data.wms.impl.WMSDataStore.WmsResponseType;
import com.sampas.socbs.core.dataset.feature.impl.SmpMaxFeatureCount;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.geometry.impl.SmpCoordinate;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.geometry.impl.SmpEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpScrDimension;
import com.sampas.socbs.core.geometry.impl.SmpScrPoint;
import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.maplayer.IWMSLayer;
import com.sampas.socbs.core.style.IWMSNamedStyle;
import com.sampas.socbs.core.style.impl.WMSNamedStyle;
import com.sampas.socbs.core.symbology.IStyle;


public class SmpWMSLayer extends SmpLayer implements IWMSLayer {

	private IWMSDataStore dataProvider = null;	
	
	private int maxHeight = 0;
	
	private int maxWidth = 0;
	
	private boolean suitableForRequest = false;
	
	private List<WMSNamedStyle> wmsNamedStyles = new ArrayList<WMSNamedStyle>();
	
	private double minimumScale;
	
	private double maximumScale;
	
	
	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public boolean isSuitableForRequest() {
		return suitableForRequest;
	}

	public void setSuitableForRequest(boolean suitableForRequest) {
		this.suitableForRequest = suitableForRequest;
	}

	public  List<IWMSLayer> getLayerDefinitions() 
	{
		return(dataProvider.getLayerDefinitions());
	}
	
	public List<WMSNamedStyle> getWmsNamedStyles() {
		return wmsNamedStyles;
	}

	public void setWmsNamedStyles(List<WMSNamedStyle> wmsNamedStyles) {
		this.wmsNamedStyles = wmsNamedStyles;
	}

	public  GetMapResult getMap(ILayer[] layers, ICoordinateSystem coordinateSystem,
			IEnvelope bbox, IDimension scrDimension,
			WmsImageReturnType wmsImageReturnType, String style) {
		
		return (dataProvider.getMap((SmpLayer[])layers, (SmpCoordinateSystem)coordinateSystem, (SmpBoundingRectangle)bbox, (SmpScrDimension)scrDimension, wmsImageReturnType, style));
	}
	
	public  GetMapResult getMap(String[] layers, ICoordinateSystem coordinateSystem,
			IEnvelope bbox, IDimension scrDimension,
			WmsImageReturnType wmsImageReturnType, String style) {
		
		return(dataProvider.getMap(layers, (SmpCoordinateSystem)coordinateSystem, (SmpBoundingRectangle)bbox, (SmpScrDimension)scrDimension, wmsImageReturnType, style));
	}	
	
	public GetMapResult getMap(List<IWMSLayer> layers, List<IWMSNamedStyle> styles, ICoordinateSystem coordinateSystem,
			IEnvelope bbox, IDimension dimension,
			WmsImageReturnType wmsImageReturnType) {
		
		return(dataProvider.getMap(layers, styles, coordinateSystem, bbox, dimension, wmsImageReturnType));
	}
	
	WmsResponseType wmsResponseType = null;
	WmsImageReturnType wmsImageReturnType = null;
	SmpMaxFeatureCount maxFeatureCount = new SmpMaxFeatureCount();

	private boolean displayLegend;
	
	@SuppressWarnings("static-access")
	public  GetFeatureInfoResult getFeatureInfo(SmpDimension smpDimension,
			SmpScrPoint smpScrPoint) {
		
		maxFeatureCount.setMaxFeatureCount(100000);		
			
		return (dataProvider.getFeatureInfo(this, (SmpBoundingRectangle)this.getExtent(), (SmpCoordinateSystem)this.getCoordinateSystem(), smpDimension, smpScrPoint, wmsResponseType.plain, wmsImageReturnType.jpeg, maxFeatureCount));
		
		
		//return (dataProvider.getFeatureInfo(smpLayer, bbox, smpCoordinateSystem, smpDimension, smpScrPoint, wmsResponseType, wmsImageReturnType, maxFeatureCount));
	}
	
	public  GetFeatureInfoResult getFeatureInfo(String layer,
			SmpBoundingRectangle bbox, SmpCoordinateSystem smpCoordinateSystem, SmpDimension smpDimension,
			SmpScrPoint smpScrPoint, WmsResponseType wmsResponseType,
			WmsImageReturnType wmsImageReturnType,
			SmpMaxFeatureCount maxFeatureCount, String style) {
		
		return (dataProvider.getFeatureInfo(layer, bbox, smpCoordinateSystem, smpDimension, smpScrPoint, wmsResponseType, wmsImageReturnType, maxFeatureCount, style));
	}
	
	
	@SuppressWarnings("static-access")
	public  GetFeatureInfoResult getFeatureInfo(ICoordinate coordinateSystem, SmpDimension smpDimension) {
		
		SmpScrPoint smpScrPoint = null;
		
		maxFeatureCount.setMaxFeatureCount(100000);		
			
		return (dataProvider.getFeatureInfo(this, (SmpBoundingRectangle)this.getExtent(), (SmpCoordinateSystem)this.getCoordinateSystem(), smpDimension, smpScrPoint, wmsResponseType.plain, wmsImageReturnType.jpeg, maxFeatureCount));
		
		
		//return (dataProvider.getFeatureInfo(smpLayer, bbox, smpCoordinateSystem, smpDimension, smpScrPoint, wmsResponseType, wmsImageReturnType, maxFeatureCount));
	}
	
	
	
//	public List<List<String>> getPointInfo(IEnvelope boundingRectangle,ICoordinateSystem  coordinateSystem) {
//		
//		
//		
//		if(!getCoordinateSystem().getEPSGCode().equals(coordinateSystem.getEPSGCode())){
//			
//			boundingRectangle = transformEnvelope(boundingRectangle,
//					(SmpCoordinateSystem)coordinateSystem,
//					(SmpCoordinateSystem)getCoordinateSystem());
//		}
//		return null;
//	}
	@SuppressWarnings("static-access")
	public List<List<String>> getPointInfo(ICoordinate  coordinate,
			ICoordinateSystem  coordinateSystem,IEnvelope visibleExtent, double offsetX, double offsetY) {
		// TODO Auto-generated method stub		
		
		
		SmpDimension screenDimension = new SmpDimension();
		screenDimension.setHeight(600);
		screenDimension.setWidth(800);
		
		if(!coordinateSystem.getEPSGCode().equals(getCoordinateSystem().getEPSGCode())){
			
			IEnvelope envelope = new SmpEnvelope(coordinate.getX(),coordinate.getY(),coordinate.getX(),coordinate.getY());
			envelope = transformEnvelope(envelope, (SmpCoordinateSystem)coordinateSystem,(SmpCoordinateSystem) getCoordinateSystem());
			coordinate.setCoordinate(new SmpCoordinate(envelope.getMinX(),envelope.getMinY()));
			visibleExtent = transformEnvelope(visibleExtent, (SmpCoordinateSystem)coordinateSystem,(SmpCoordinateSystem) getCoordinateSystem());
		}
		
		int screenX =  (int)( (coordinate.getX()-visibleExtent.getMinX() ) *(screenDimension.getWidth()/visibleExtent.getWidth()) );
		int screenY = (int) (screenDimension.getHeight()- (coordinate.getY()-visibleExtent.getMinY() ) *(screenDimension.getHeight()/visibleExtent.getHeight()) );

		SmpScrPoint scrPoint = new SmpScrPoint();
		scrPoint.setScrX(screenX);
		scrPoint.setScrY(screenY);
		
		maxFeatureCount.setMaxFeatureCount(100000);		
		
		SmpBoundingRectangle boundingRectangle = new SmpBoundingRectangle(
				visibleExtent.getMinX(),visibleExtent.getMinY(),
				visibleExtent.getMaxX(),visibleExtent.getMaxY());
		
		String getFeatureResult = (dataProvider.getFeatureInfo
				(this, boundingRectangle, (SmpCoordinateSystem)this.getCoordinateSystem(), 
						screenDimension, scrPoint, wmsResponseType.plain, wmsImageReturnType.jpeg, 
						maxFeatureCount)).getFeatures();
		
//		List<String>attributeNames =  findAttributeNames(getFeatureResult).get(0);
//		
//		if(attributeNames == null){
//			return null;
//		}
//		
//		
//		System.out.println("---------------------------");
//		for (int i = 0; i < attributeNames.size(); i++) {
//			
//			
//			System.out.println(attributeNames.get(i));
//		}
//		System.out.println("---------------------------");
//		for (int i = 0; i < findAttributeNames(getFeatureResult).get(1).size(); i++) {
//			
//			
//			System.out.println(findAttributeNames(getFeatureResult).get(1).get(i));
//		}
		
		
		return findAttributeNames(getFeatureResult);
	}
	
	/*
	 * 		@param:featureResult  
	 * 		example format: 
	 * 		Results for FeatureType 'p_grf_mahalle':
			--------------------------------------------
			fc = 0
			gis_id = 23
			version_id = 90
			mahalle_kodu = 145
			temp_id = 145
			mahalle_adi = PÄ°YALEPAÅžA MAH.
			geometry = [GEOMETRY (Polygon) with 60 points]
			--------------------------------------------
	 */
	private List<List<String>> findAttributeNames(String featureResult){

		List<String> attributeNames = new ArrayList<String>();
		List<String> attributeValues = new ArrayList<String>();
		
		String []splitArr = featureResult.split("\n");
		
		if(splitArr.length<2){
			return null;
		}
		
		for (int i = 2; i < splitArr.length-1; i++) {
			
			String[]attribute = splitArr[i].split("=");
			
			for (int j = 0; j < getLayerAttributesFromMetadata().size(); j++) {
				
				if(attribute[0].toLowerCase().trim().equals(getLayerAttributesFromMetadata().get(j).getAttributeColumnName().toLowerCase().trim())){
					
					attributeNames.add(getLayerAttributesFromMetadata().get(j).getAttributeName());
					attributeValues.add(attribute[1]);
				}	
			}
		}	
		List<List<String>> attributeList = new ArrayList<List<String>>();
		attributeList.add(attributeNames);
		attributeList.add(attributeValues);
		return attributeList;
	}
	
	public BufferedImage getLayerStyleLegend(IStyle style, IDimension iconDimension, WmsImageReturnType returnType) {
		
		return (this.dataProvider.getLayerStyleLegend(this, style, iconDimension, returnType));
	}
	
	
	public IWMSDataStore getWMSDataProvider() {
		
		return dataProvider;
	}

	public void setWMSDataProvider(IWMSDataStore wmsDataProvider) {
		
		this.dataProvider = wmsDataProvider;		
	}

	public List<String> addVersions(String version) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServeDirectory() {
		// TODO Auto-generated method stub
		return null;
	}

	public URL getServiceAdress() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServisPage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getVersionAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getVersionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void removeVersions(int index) {
		// TODO Auto-generated method stub
		
	}

	public void setMaxImgHeight(int height) {
		// TODO Auto-generated method stub
		
	}

	public void setMaxImgWidth(int width) {
		// TODO Auto-generated method stub
		
	}

	public void setServeDirectory(String serveDirectory) {
		// TODO Auto-generated method stub
		
	}

	public void setServiceAdress(String serviceAdress) {
		// TODO Auto-generated method stub
		
	}

	public void setServisPage(String servisPage) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isDisplayLegend() {
		return this.displayLegend;
	}

	public void setDisplayLegend(boolean legendNeeded) {
		this.displayLegend = legendNeeded;
	}
	
	//TODO: Must test
	@SuppressWarnings("static-access")
	public List<List<String>> getPointInfo(ICoordinate  coordinate, ICoordinateSystem  coordinateSystem) {
		
		SmpDimension screenDimension = new SmpDimension();
		screenDimension.setHeight(600);
		screenDimension.setWidth(800);
		ICoordinate tmpCoordinate = coordinate;
		
		if(!coordinateSystem.getEPSGCode().equals(getCoordinateSystem().getEPSGCode())){
			
			IEnvelope envelope = new SmpEnvelope(coordinate.getX(),coordinate.getY(),coordinate.getX(),coordinate.getY());
			envelope = transformEnvelope(envelope, (SmpCoordinateSystem)coordinateSystem,(SmpCoordinateSystem) getCoordinateSystem());
			tmpCoordinate =new SmpCoordinate(envelope.getMinX(),envelope.getMinY());
			//visibleExtent = transformEnvelope(visibleExtent, (SmpCoordinateSystem)coordinateSystem,(SmpCoordinateSystem) getCoordinateSystem());
			//this.setExtent(transformEnvelope(this.getExtent(), (SmpCoordinateSystem)coordinateSystem,(SmpCoordinateSystem) getCoordinateSystem()));
		}
		
		int screenX =  (int)( (tmpCoordinate.getX()-this.getExtent().getMinX() ) *(screenDimension.getWidth()/this.getExtent().getWidth()) );
		int screenY = (int) (screenDimension.getHeight()- (tmpCoordinate.getY()-this.getExtent().getMinY() ) *(screenDimension.getHeight()/this.getExtent().getHeight()) );

		SmpScrPoint scrPoint = new SmpScrPoint();
		scrPoint.setScrX(screenX);
		scrPoint.setScrY(screenY);
		
		maxFeatureCount.setMaxFeatureCount(100000);		
		
		SmpBoundingRectangle boundingRectangle = new SmpBoundingRectangle(
				this.getExtent().getMinX(),this.getExtent().getMinY(),
				this.getExtent().getMaxX(),this.getExtent().getMaxY());
		
		String getFeatureResult = (dataProvider.getFeatureInfo
				(this, boundingRectangle, (SmpCoordinateSystem)this.getCoordinateSystem(), 
						screenDimension, scrPoint, wmsResponseType.plain, wmsImageReturnType.jpeg, 
						maxFeatureCount)).getFeatures();
		
		return findAttributeNames(getFeatureResult);
	}
	
	public double getMinimumScale() {
		return minimumScale;
	}

	public void setMinimumScale(double minimumScale) {
		this.minimumScale = minimumScale;
	}

	public double getMaximumScale() {
		return maximumScale;
	}
	
	public void setMaximumScale(double maximumScale) {
		this.maximumScale = maximumScale;
	}
	
}
