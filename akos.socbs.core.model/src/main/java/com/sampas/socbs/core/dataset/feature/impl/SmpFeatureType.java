package com.sampas.socbs.core.dataset.feature.impl;

import java.net.URI;

import org.geotools.feature.AttributeType;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureType;
import org.geotools.feature.IllegalAttributeException;

import com.sampas.socbs.core.dataset.feature.IFeatureType;

public class SmpFeatureType implements IFeatureType {

	private FeatureType geoToolsFeatureType = null;
	
    public SmpFeatureType() {
				
	}	
	
	public SmpFeatureType(FeatureType geoToolsFeatureType) {
		
		this.geoToolsFeatureType = geoToolsFeatureType;		
	}
	
	public FeatureType getGeoToolsFeatureType() {
		
		return (this.geoToolsFeatureType);
	}
	
	public URI getNamespace() {
		
		return (this.geoToolsFeatureType.getNamespace());
	}
	
	public String getTypeName() {
		
		return (this.geoToolsFeatureType.getTypeName());
	}
	
	public String getFeatureType() {
		
		return (this.geoToolsFeatureType.getTypeName());
	}

	public boolean isDescendedFrom(URI nsURI, String typeName) {
		
		return (this.geoToolsFeatureType.isDescendedFrom(nsURI, typeName));
	}
	
	public boolean isDescendedFrom(SmpFeatureType type) {
		
		FeatureType featureType = type.getGeoToolsFeatureType();
        return (this.geoToolsFeatureType.isDescendedFrom(featureType));
	}
	
	 public boolean isAbstract() {
		 
		 return (this.geoToolsFeatureType.isAbstract());
	 }
	 
	 public SmpFeatureType[] getAncestors() {
	 
		 SmpFeatureType[] smpFeatureTypes = new SmpFeatureType[this.geoToolsFeatureType.getAncestors().length];
		 
		 for (int i = 0; i < this.geoToolsFeatureType.getAncestors().length; i++) {
			 
			 smpFeatureTypes[i] = new SmpFeatureType(this.geoToolsFeatureType.getAncestors()[i]);
		}
		 
		 return (smpFeatureTypes);
	 
	 }
	 
	 
	 //must wrap from GeometryAttributeType
//	 public GeometryAttributeType getDefaultGeometry() {
//		 
//	 }

	 public int getAttributeCount() {
		 
		 return (geoToolsFeatureType.getAttributeCount());
	 }
	 
	 public SmpAttributeType getAttributeType(String xPath) {
	 
		 SmpAttributeType smpAttributeType = new SmpAttributeType(this.geoToolsFeatureType.getAttributeType(xPath));
		 
		 return (smpAttributeType);
	 }
	 
	 public int find(SmpAttributeType type) {
	 
		 AttributeType attributeType = type.getGeoToolsAttributeType();
		 
		 return (this.geoToolsFeatureType.find(attributeType));
	 }
	 
	 public int find(String attName) {
		 
		 return (geoToolsFeatureType.find(attName));
	 }
	 
	 public SmpAttributeType getAttributeType(int position) {
		 
		 SmpAttributeType smpAttributeType = new SmpAttributeType(this.geoToolsFeatureType.getAttributeType(position));
		 
		 return (smpAttributeType);
	 }
	 
	 public SmpAttributeType[] getAttributeTypes() {
		 
		 SmpAttributeType[] smpAttributeTypes = new SmpAttributeType[this.geoToolsFeatureType.getAttributeTypes().length];
		 
		 for (int i = 0; i < this.geoToolsFeatureType.getAttributeTypes().length; i++) {
		
			 smpAttributeTypes[i] = new SmpAttributeType(this.geoToolsFeatureType.getAttributeTypes()[i]);
		 }
		 
		 return (smpAttributeTypes);
	 }
	 
	 @SuppressWarnings("deprecation")
	 public boolean hasAttributeType(String xPath) {
		 
		 return (geoToolsFeatureType.hasAttributeType(xPath));
	 }
	 
	 public SmpFeature duplicate(SmpFeature smpFeature) throws IllegalAttributeException {
	 
		 Feature feature = smpFeature.getGeoToolFeature();
		 
		 SmpFeature smpFeature2 = new SmpFeature(this.geoToolsFeatureType.duplicate(feature));
		 
		 return (smpFeature2);
	 }
	 
	 public SmpFeature create(Object[] attributes) throws IllegalAttributeException{
		 
		 SmpFeature smpFeature = new SmpFeature(this.geoToolsFeatureType.create(attributes));
		 
		 return (smpFeature);
	 }
	 
	 public SmpFeature create(Object[] attributes, String featureID) throws IllegalAttributeException {
		 
		 SmpFeature smpFeature = new SmpFeature(this.geoToolsFeatureType.create(attributes, featureID));
		 
		 return (smpFeature);
	 }
	 
	 
	public boolean equals(Object arg0) {
	 
		 return (geoToolsFeatureType.equals(arg0));
	 }
	 
	 
	public int hashCode() {
		 
		 return (geoToolsFeatureType.hashCode());
	 }

	 
}
