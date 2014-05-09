package com.sampas.socbs.core.maplayer.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.geotools.data.FeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.GeoTools;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureType;
import org.geotools.filter.FidFilter;
import org.geotools.filter.FilterFactory;
import org.geotools.filter.FilterFactoryFinder;
import org.opengis.filter.FilterFactory2;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureCursor;
import com.sampas.socbs.core.dataset.feature.IFeatureID;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.dataset.feature.IFilterBuilder;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCollection;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCursor;
import com.sampas.socbs.core.dataset.feature.impl.SmpFilterBuilder;
import com.sampas.socbs.core.dataset.filter.impl.SmpFilter;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.maplayer.ILayerAttribute;
import com.sampas.socbs.core.renderer.IRenderer;
import com.sampas.socbs.core.renderer.impl.RenderPhaseEnum;
import com.sampas.socbs.core.tools.IProcessContext;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

@SuppressWarnings({ "deprecation", "unused" })
public class SmpFeatureLayer extends SmpLayer implements IFeatureLayer {

	private boolean symbolsScaled 	= false;
	private boolean selectable 		= false;
	private IFeatureCollection smpFeatureCollection = new SmpFeatureCollection();
	private List<IRenderer> smpRenderer = new ArrayList<IRenderer>();
	private IFeatureDataStore dataProvider = null;

	public boolean isSymbolsScaled() {
		return (this.symbolsScaled);
	}

	public void addRenderer(IRenderer renderer){

		this.smpRenderer.add(renderer);

	}

	public void addRenderer(int index,IRenderer renderer){

		this.smpRenderer.add(index,renderer);

	}

	public IRenderer getRenderer(int index) {

		return this.smpRenderer.get(index);
	}

	public int getRendererCount() {
		
		return this.smpRenderer.size();
	}

	public boolean isSelectable() {

		return (this.selectable);
	}


	public IFeatureCursor search(IFilter filter) {

		return null;
	}


	public void setSelectable(boolean selectable) {

		this.selectable = selectable;
	}


	public void setSymbolsScaled(boolean symbolsScaled) {

		this.symbolsScaled	=	symbolsScaled;

	}
	public void setSmpFeatureCollection(IFeatureCollection smpFeatureCollection) {
		//setExtent(smpFeatureCollection.);
		this.smpFeatureCollection = smpFeatureCollection;
	}

	public void setSmpLayerAttributes(){


	}

	public IFeatureCollection getSmpFeatureCollection() {

		return this.smpFeatureCollection;
	}

	public int getFeatureCount() {

		return (this.smpFeatureCollection.getFeaturesCount());
	}


	public String getFeatureTypeAt(int index) {


		return (((SmpFeature) this.smpFeatureCollection.getFeatureAt(index)).getGeoToolFeature().getFeatureType().toString());
	}


	public void addLayerAttribute(ILayerAttribute layerAttribute) {

		getSmpLayerAttributes().add(layerAttribute);

	}


	public void clearAllAttribute() {

		for(int i=getAttributeCount()-1;i>=0;i--){
			removeLayerAttribute(i);
		}

	}


	public ILayerAttribute getAttributeAt(int index) {

		return getSmpLayerAttributes().get(index);
	}

	public ILayerAttribute getAttributeAt(String columnName) {

		for (int i = 0; i < getSmpLayerAttributes().size(); i++) {

			if(getSmpLayerAttributes().get(i).getAttributeColumnName().equals(columnName)){

				return getSmpLayerAttributes().get(i);
			}

		}

		return null;
	}

	public int getAttributeCount() {

		return getSmpLayerAttributes().size();
	}


	public void removeLayerAttribute(int index) {

		getSmpLayerAttributes().remove(index);
	}

	public void render(RenderPhaseEnum renderPhase, IDisplay display, IProcessContext processContext) {

		if(getDataProvider() != null){

			if(!display.getDisplayTransformation().getCoordinateSystem().getEPSGCode().equals(getCoordinateSystem().getEPSGCode())){
				
				IEnvelope envelope = null;

				envelope = transformEnvelope(display.getDisplayTransformation().getFittedBounds(), (SmpCoordinateSystem)display.getDisplayTransformation().getCoordinateSystem(), (SmpCoordinateSystem)getCoordinateSystem());

				this.smpFeatureCollection = transformFeatureCollection(getFeaturesByBRect((SmpBoundingRectangle)envelope), getCoordinateSystem(), display.getDisplayTransformation().getCoordinateSystem());
			} else {
				
				this.smpFeatureCollection = getFeaturesByBRect((SmpBoundingRectangle) display.getDisplayTransformation().getFittedBounds());
			}
		}	
		/* Interaction Layer has no data provider but it may include features*/
//		if(this.smpFeatureCollection != null){
//
//			IFeatureCursor featureCursor = new SmpFeatureCursor(this.smpFeatureCollection,getSmpLayerAttributes());				 
//
//			/* Now only use one renderer*/
//			if(this.smpRenderer != null){
//				if(this.smpRenderer.size()>0){
//					this.smpRenderer.get(0).render(featureCursor, renderPhase, display, processContext);
//				}
//				else{
//					System.err.println("SMPFEATURELAYER______: Render List has no render");
//				}
//
//			}
//			else{
//				System.err.println("SMPFEATURELAYER______: Render is null");
//			}
//		}
	}

	public IFeatureCollection getFeatureCollection() {

		if(this.dataProvider != null) {

			FeatureSource source;

			try {

				source = dataProvider.getFeatureSource(this.getName());

				FeatureCollection features = source.getFeatures();

				this.smpFeatureCollection = new SmpFeatureCollection(features);

				return (this.smpFeatureCollection);				
			} catch (IOException e) {

				e.printStackTrace();
				return null;
			}
		} else {

			System.out.println("DataStore is Empty");
			return null;
		}

	}

	private IFeatureCollection getFeaturesByFilter(IFilter smpFilter){

		if(this.dataProvider != null) {

			FeatureSource source;

			try {

				source = this.getDataProvider().getFeatureSource(this.getName());

				FeatureCollection features = source.getFeatures(((SmpFilter)smpFilter).getGeoToolsFilter());

				this.smpFeatureCollection = new SmpFeatureCollection(features);

				return (this.smpFeatureCollection);				
			} catch (IOException e) {

				e.printStackTrace();
				return null;
			}
		} else {

			System.out.println("DataStore is Empty");
			return null;
		}

	}

	public void setDataProvider(IFeatureDataStore dataStore) {

		if(this.dataProvider == null) {

			this.dataProvider = dataStore;
		} else {
			//This means dataprovider not need to set
			//System.out.println("DataStore couldn't Set");
		}

	}

	private IFeatureDataStore getDataProvider() {

		return (this.dataProvider);
	}

	private IFeatureCollection getFeaturesByBRect(IEnvelope smpBbox) {

		IFeatureCollection getFeatureResult = null;

		//smpBbox = getExtent();

		try {

			GeometryFactory geometryFactory = new GeometryFactory();

			Coordinate[] coordinates = new Coordinate[] {
					new Coordinate(smpBbox.getMinX(), smpBbox.getMinY()), 
					new Coordinate(smpBbox.getMinX(), smpBbox.getMaxY()), 
					new Coordinate(smpBbox.getMaxX(), smpBbox.getMaxY()), 
					new Coordinate(smpBbox.getMaxX(), smpBbox.getMinY()), 
					new Coordinate(smpBbox.getMinX(), smpBbox.getMinY())};

			LinearRing linearRing = geometryFactory.createLinearRing(coordinates);
			Polygon polygonBbox = geometryFactory.createPolygon(linearRing, null); 

			//FeatureSource featureSource = wfsDataStore.getFeatureSource(typeName.getName());
			FeatureSource featureSource = dataProvider.getFeatureSource(this.getName());

			FeatureType schema = featureSource.getSchema();
			String geometryPropertyName = schema.getDefaultGeometry().getLocalName();
			FilterFactory2 filterFactory2 = CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints());
			org.opengis.filter.Filter bboxFilter = filterFactory2.intersects(filterFactory2.property(geometryPropertyName), filterFactory2.literal(polygonBbox));

			getFeatureResult = new SmpFeatureCollection(featureSource.getFeatures( bboxFilter));			

			return (getFeatureResult);

		} catch (Exception e) {

			return (getFeatureResult);
		}
	}

	public IFeatureCollection getFeaturesByBRectAndFilter(IEnvelope smpBbox, IFilter smpFilter) {

		if(smpBbox != null && smpFilter != null) {

			IFeatureCollection getFeatureResult = null;

			try {

				GeometryFactory geometryFactory = new GeometryFactory(); 
				Coordinate[] coordinates = new Coordinate[] {
						new Coordinate(smpBbox.getMinX(), smpBbox.getMinY()), 
						new Coordinate(smpBbox.getMinX(), smpBbox.getMaxY()), 
						new Coordinate(smpBbox.getMaxX(), smpBbox.getMaxY()), 
						new Coordinate(smpBbox.getMaxX(), smpBbox.getMinY()), 
						new Coordinate(smpBbox.getMinX(), smpBbox.getMinY())};

				LinearRing linearRing = geometryFactory.createLinearRing(coordinates);
				Polygon polygonBbox = geometryFactory.createPolygon(linearRing, null); 

				FeatureSource featureSource = dataProvider.getFeatureSource(this.getName());

				FeatureType schema = featureSource.getSchema();
				String geometryPropertyName = schema.getDefaultGeometry().getLocalName();

				FilterFactory2 filterFactory2 = CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints());
				IFilter bboxFilter = new SmpFilter(filterFactory2.intersects(filterFactory2.property(geometryPropertyName), filterFactory2.literal(polygonBbox)));

				IFilterBuilder smpFilterBuilder = new SmpFilterBuilder();

				IFilter smpTotalFilter = new SmpFilter();

				try {

					smpTotalFilter = smpFilterBuilder.addAndFilter(bboxFilter, smpFilter);

				} catch (Exception ex)
				{
					ex.printStackTrace();
				}

				getFeatureResult = new SmpFeatureCollection(featureSource.getFeatures( ((SmpFilter)smpTotalFilter).getGeoToolsFilter()));			

				return (getFeatureResult);

			} catch (Exception e) {

				return (getFeatureResult);
			}


		} else if (smpBbox == null && smpFilter != null) {

			return (getFeaturesByFilter(smpFilter));

		} else if(smpBbox != null && smpFilter == null) {

			IEnvelope envelope = new SmpBoundingRectangle(smpBbox.getMinX(), smpBbox.getMinY(), smpBbox.getMaxX(), smpBbox.getMaxY());
			return (getFeaturesByBRect(envelope));

		} else {

			return null;
		}

	}

	public List<List<String>> getPointInfo(ICoordinate coordinate,
			ICoordinateSystem  coordinateSystem,IEnvelope visibleExtent, double offsetX,double offsetY ) {

		double minX = coordinate.getX()-offsetX;
		double minY = coordinate.getY()-offsetY;
		double maxX = coordinate.getX()+offsetX;
		double maxY = coordinate.getY()+offsetY;

		try{
			IEnvelope pointEnvelope = new SmpBoundingRectangle(minX,minY,maxX,maxY);

			if(!getCoordinateSystem().getEPSGCode().equals(coordinateSystem.getEPSGCode())){

				pointEnvelope = transformEnvelope(pointEnvelope,
						(SmpCoordinateSystem)coordinateSystem,
						(SmpCoordinateSystem)getCoordinateSystem());
			}

			//this.smpFeatureCollection = getFeaturesByBRect((SmpBoundingRectangle) pointEnvelope);
			this.smpFeatureCollection = getFeaturesByBRectAndFilter(pointEnvelope, null);

		}catch (Exception e) {
			// TODO: handle exception
		}
		List<List<String>> attributeList = new ArrayList<List<String>>();

		if(getSmpLayerAttributes() != null && getSmpLayerAttributes().size()>0 && getLayerAttributesFromMetadata().size()>0) {

			List<ILayerAttribute> layerAttributeList = getSmpLayerAttributes();

			List <String> layerAttributeStringList = new ArrayList<String>();
			List <String> layerKeyStringList = new ArrayList<String>();

			//			for (ILayerAttribute attribute : layerAttributeList){
			//				for (int i = 0; i < this.getLayerAttributesFromMetadata().size(); i++) {
			//					if(attribute.getAttributeName().equals(this.getLayerAttributesFromMetadata().get(i).getAttributeColumnName())){
			//						layerAttributeStringList.add(this.getLayerAttributesFromMetadata().get(i).getAttributeName());
			//						break;
			//					}
			//				}
			//			}	
			try{
				/*Some Attributes null in DB , so this way is not proper*/
//				for (int i = 0; i < this.getLayerAttributesFromMetadata().size(); i++) {
//					if(this.smpFeatureCollection!=null && this.smpFeatureCollection.getFeaturesCount()>0){
//						if(this.smpFeatureCollection.getFeatureAt(0).getAttribute(this.getLayerAttributesFromMetadata().get(i).getAttributeColumnName())!=null){
//							layerAttributeStringList.add(this.getLayerAttributesFromMetadata().get(i).getAttributeName());
//							break;
//						}
//					}
//				}
				for (int i = 0; i < getSmpLayerAttributes().size(); i++) {
					for (int j = 0; j < getLayerAttributesFromMetadata().size(); j++) {
						if(getLayerAttributesFromMetadata().get(j).getAttributeColumnName().equalsIgnoreCase(getSmpLayerAttributes().get(i).getAttributeColumnName())){
							layerAttributeStringList.add(getSmpLayerAttributes().get(i).getAttributeColumnName());
							layerKeyStringList.add(getLayerAttributesFromMetadata().get(j).getAttributeColumnName());
							break;
						}
					}
				}

				/* Includes attributes and String values of them */
				attributeList.add(layerKeyStringList);

				for (int i = 0; i <this.smpFeatureCollection.getFeaturesCount(); i++) {

					List<String> list = new ArrayList<String>(layerAttributeStringList.size());
					//String []tmp_arr = new String[layerAttributeStringList.size()];
					for (int j = 0; j < layerAttributeStringList.size(); j++) {

						String item;
						item =  this.smpFeatureCollection.getFeatureAt(i).getAttribute(layerAttributeStringList.get(j)) 
						== null ? "-"
								: this.smpFeatureCollection.getFeatureAt(i).getAttribute(layerAttributeStringList.get(j)).toString();
						
						list.add(item);
//						int row = findIndexOfValue(getLayerAttributesFromMetadata().get(j),layerAttributeStringList);
//						if (row!=-1) {
//							tmp_arr[row]=item;
//						}	
						
						
					}

//					for (int j = 0; j < tmp_arr.length; j++) {
//						list.add(tmp_arr[j]);
//					}
					attributeList.add(list);
				}
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		return attributeList;
	}

	private int findIndexOfValue(ILayerAttribute layerAttribute, List<String> layerAttributeStringList) {
		int counter=0;
		for (String string : layerAttributeStringList) {
			if(string.equalsIgnoreCase(layerAttribute.getAttributeName())){
				return counter;
			}
			counter++;
		}
		return -1;
	}

	public IFeatureCollection getFeaturesbyFIDs(List<IFeatureID> featureIDs) {

		IFeatureCollection featureCollection = null;
		
		if (featureIDs != null && featureIDs.size() != 0) {

			try {			
				
				//System.out.println("getFeaturesbyFIDs : "+this.getName());
				
				FeatureSource featureSource = dataProvider.getFeatureSource(this.getName());

				//				FilterFactory filterFactory = FilterFactoryFinder.createFilterFactory();
				//				
				//				Filter fidFilter = filterFactory.createFidFilter(featureIDs.get(0).getFeatureId());
				//				
				//			    for (int i = 1; i < featureIDs.size(); i++) {
				//			    	
				//			    	fidFilter = fidFilter.and(filterFactory.createFidFilter(featureIDs.get(i).getFeatureId()));
				//			    }

				FilterFactory filterFactory = FilterFactoryFinder.createFilterFactory();

				FidFilter fidFilter = filterFactory.createFidFilter();

				for (int i = 0; i < featureIDs.size(); i++) {

					fidFilter.addFid(featureIDs.get(i).getFeatureId());  
				}

				FeatureCollection features = featureSource.getFeatures(fidFilter);

				//System.out.println(features.getAttribute(0));

				featureCollection = new SmpFeatureCollection(features);

				return (featureCollection);

			}
			catch (Exception ex) {

				return (featureCollection);
			}
		} else {

			return (featureCollection);
		}
	}
	
	public IFeature getFeaturesbyFID(IFeatureID featureID) {
		
		List<IFeatureID> featureIDs = new ArrayList<IFeatureID>();
		System.out.println("getFeaturesbyFID 1  - "+featureIDs.size());
		featureIDs.add(featureID);
		
		IFeatureCollection tempFeatures = this.getFeaturesbyFIDs(featureIDs);
		
		if (tempFeatures != null && tempFeatures.getFeaturesCount() == 1) {
			
			return tempFeatures.getFeatureAt(0);
		} else {
			
			System.out.println("Error no feature found or more than one feature found !");
			return null;
		}
	}

}
