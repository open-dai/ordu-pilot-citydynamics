package com.sampas.gis.ortak.gisdatabase.creator.servis.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.sampas.gis.ortak.gisdatabase.creator.servis.IGisDatabaseCreator;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.dataset.feature.IAttributeType;
import com.sampas.socbs.core.dataset.feature.IFeatureTypeName;
import com.sampas.socbs.core.dataset.feature.impl.SmpAttributeType;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureTypeName;
import com.sampas.socbs.core.gisdatabase.tools.servis.IGisDatabaseServis;
import com.sampas.socbs.core.gisdatabase.tools.servis.impl.GisDatabaseServis;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class GisDatabaseCreator implements IGisDatabaseCreator {

	public boolean creatGISDatabaseComponents(IFeatureDataStore dataStore) {
		
		InputStream objectNames = this.getClass().getResourceAsStream("giscreateclasses.properties");
		
		if (objectNames != null) {
			
			try {
				
				Properties properties = new Properties();
				Set<Object> keyCollection = null;
				List<List<String>> attributes = new ArrayList<List<String>>();
				properties.load(objectNames);
				
				keyCollection = properties.keySet();
						
				for (Object attributekey : keyCollection) {
					
					if (attributekey!=null){
						String attrValues=properties.get(attributekey).toString();	
						String[] attrValuesArray=attrValues.split(",");
						
						List<String> attrValuesList=new ArrayList<String>();
						
						for (int i = 0; i < attrValuesArray.length; i++) {
							attrValuesList.add(attrValuesArray[i]);
						}
						attributes.add(attrValuesList);
					}
				}
				
				IGisDatabaseServis gisDatabaseServis = new GisDatabaseServis(dataStore);
				
				List<IFeatureTypeName> featureTypes = new ArrayList<IFeatureTypeName>();
				
				List<List<IAttributeType>> allFeatureAttLists = new ArrayList<List<IAttributeType>>();
				
				for (List<String> attrValuesList : attributes) {
					
					String geomTableName = attrValuesList.get(0).toString().trim();
					IFeatureTypeName featureTypeName = new SmpFeatureTypeName(geomTableName); 
					featureTypes.add(featureTypeName);
					
					String geomColumnName = attrValuesList.get(1).toString().trim();
					String geomClassName = attrValuesList.get(2).toString().trim();
					
					Class<?> geomClass = null;
					
					if (geomClassName.equals("Point")) {
						
						geomClass = Point.class;
					}
					else if (geomClassName.equals("Polygon")) {
						
						geomClass=Polygon.class;
					}
					else if (geomClassName.equals("LineString")) {
						
						geomClass=LineString.class;
					}
					else{
						
						System.out.println("Error on finding geometry type. Check class properties" );
					}
					
					IAttributeType geomAttr = new SmpAttributeType(geomColumnName, geomClass);
				
					List<IAttributeType> attItems = new ArrayList<IAttributeType>();
					attItems.add(geomAttr);
					allFeatureAttLists.add(attItems);
				}
				
				boolean createType = false;
				if (featureTypes.size() == allFeatureAttLists.size()) {
					
					for (int i = 0; i < featureTypes.size(); i++) {
						
						createType = gisDatabaseServis.createFeatureType(featureTypes.get(i), allFeatureAttLists.get(i));
						
						if (!createType) {
							
							System.out.println("Error on creating type : " + featureTypes.get(i));
						}
					}
					
				} else {
					
					System.out.println("Feature type list size and feature attribute list size must be same !");
				}
				
			} catch (Exception ex) {
				
				System.out.println("Error on creating GIS database components ! ERROR : " + ex);
			}
		
		} else {
	
			System.out.println("Properties file can't be empty !");
		}
		
		return false;
	}
}
