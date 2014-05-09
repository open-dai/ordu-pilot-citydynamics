package com.sampas.socbs.core.dataset.feature;

import org.geotools.feature.IllegalAttributeException;

import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.IPoint;

public interface IFeature {
	
	public IFeatureType getFeatureType();
	
	public String getID();
	
	public Object[] getAttributes(Object[] attributes);
	
	public Object getAttribute(String xPath);
	
	public Object getAttribute(int index);
	
	public void setAttribute(int position, Object val) throws IllegalAttributeException, ArrayIndexOutOfBoundsException;

	public int getNumberOfAttributes();
	
	public void setAttribute(String xPath, Object attribute) throws IllegalAttributeException;

	public IGeometry getDefaultGeometry();
	
	public int hashCode();
	
	public boolean equals(Object obj);
	
	public boolean isGeometryEmpty();
	
	public IPoint getCenteroid();
	
	public double getArea();
	
	public IFeature cloneFeature();
	
}
