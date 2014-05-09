package com.sampas.socbs.core.dataset.feature.impl;

import org.geotools.feature.Feature;
import org.geotools.feature.IllegalAttributeException;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpGeometry;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.sampas.socbs.core.geometry.impl.SmpReferencedEnvelope;
import com.vividsolutions.jts.geom.Geometry;


public class SmpFeature implements IFeature {

	private Feature geoToolsFeature = null;

	public SmpFeature() {

	}

	public SmpFeature(Feature geoToolsFeature) {

		this.geoToolsFeature = geoToolsFeature;
	}

	public Feature getGeoToolFeature() {

		return (this.geoToolsFeature);
	}
	
	public SmpFeature(Object featureObject) throws Exception {
		
		try {
			
			this.geoToolsFeature = (Feature)featureObject;
		} catch (Exception ex) {

			throw new Exception("Error on converting object to feature !!!");
		}
	}

	public SmpFeatureType getFeatureType() {

		SmpFeatureType smpFeatureType = new SmpFeatureType(this.geoToolsFeature.getFeatureType());

		return (smpFeatureType);
	}

	public String getID() {
		return (this.geoToolsFeature.getID());
	}

	public Object[] getAttributes(Object[] attributes) {
		return (this.geoToolsFeature.getAttributes(attributes));
	}

	public Object getAttribute(String xPath) {

		return (this.geoToolsFeature.getAttribute(xPath));
	}

	public Object getAttribute(int index) {

		return (this.geoToolsFeature.getAttribute(index));
	}

	public void setAttribute(int position, Object val) throws IllegalAttributeException, ArrayIndexOutOfBoundsException {
		this.geoToolsFeature.setAttribute(position, val);
	}

	public int getNumberOfAttributes() {
		return (this.geoToolsFeature.getNumberOfAttributes());
	}

	public void setAttribute(String xPath, Object attribute) throws IllegalAttributeException {
		this.geoToolsFeature.setAttribute(xPath, attribute);
	}

	// public SmpGeometry getDefaultGeometry() {
	//		
	// GeometryFactory gf =
	// this.geoToolsFeature.getDefaultGeometry().getFactory();
	// int srID = gf.getSRID();
	// PrecisionModel pm = gf.getPrecisionModel();
	// CoordinateSequenceFactory csf = gf.getCoordinateSequenceFactory();
	// SmpGeometry smpGeometry = new SmpGeometry(new SmpGeometryFactory(pm,
	// srID, csf));
	//		
	// return (smpGeometry);
	// }

	public SmpGeometry getDefaultGeometry() {

		SmpGeometry smpGeometry = new SmpGeometry(this.geoToolsFeature.getDefaultGeometry());

		return (smpGeometry);
	}

	public void setDefaultGeometry(SmpGeometry smpGeometry)	throws IllegalAttributeException {

		Geometry geometry = smpGeometry.getGeoToolGeometry();

		this.geoToolsFeature.setDefaultGeometry(geometry);
	}

	public SmpEnvelope getBounds() {

		SmpReferencedEnvelope smpReferencedEnvelope = new SmpReferencedEnvelope(this.geoToolsFeature.getBounds());

		return (SmpEnvelope)(smpReferencedEnvelope.getEnvelope());
	}
	
	public boolean isGeometryEmpty() {
		
		if(this.geoToolsFeature.getDefaultGeometry() == null) {
			
			return (true);
		} else {
			
			return (false);
		}
	}
	
	public int hashCode() {
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((geoToolsFeature == null) ? 0 : geoToolsFeature.hashCode());
		
		return result;
	}
	
	public boolean equals(Object obj) {
		
		if (this == obj) {
			
			return true;
		}
		if (obj == null) {
			
			return false;
		}
		if (getClass() != obj.getClass()) {
			
			return false;
		}
		SmpFeature other = (SmpFeature) obj;
		
		if (geoToolsFeature == null) {
			
			if (other.geoToolsFeature != null) {
				return false;
			}
		} else if (!geoToolsFeature.equals(other.geoToolsFeature)) {
			
			return false;
		}
		return true;
	}
	
	public IPoint getCenteroid() {
		
		IPoint center = new SmpPoint(this.geoToolsFeature.getDefaultGeometry().getCentroid());
		
		return center;		
	}
	
	public double getArea() {
		
		return (this.geoToolsFeature.getDefaultGeometry().getArea());
	}

	public IFeature cloneFeature() {

		try {

			return new SmpFeature(this.geoToolsFeature.getFeatureType().duplicate(this.geoToolsFeature));
		} catch (Exception ex) {
			
			System.out.println("Error on cloning feature ! ERROR: " + ex.getMessage());
		}
		return null;
	}
	
}
