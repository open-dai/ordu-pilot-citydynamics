package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.And;
import org.opengis.filter.FilterVisitor;
import org.opengis.filter.Not;
import org.opengis.filter.Or;
import org.opengis.filter.PropertyIsBetween;
import org.opengis.filter.PropertyIsEqualTo;
import org.opengis.filter.PropertyIsGreaterThan;
import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
import org.opengis.filter.PropertyIsLessThan;
import org.opengis.filter.PropertyIsLessThanOrEqualTo;
import org.opengis.filter.PropertyIsLike;
import org.opengis.filter.PropertyIsNull;
import org.opengis.filter.spatial.BBOX;
import org.opengis.filter.spatial.Beyond;
import org.opengis.filter.spatial.Contains;
import org.opengis.filter.spatial.Crosses;
import org.opengis.filter.spatial.DWithin;
import org.opengis.filter.spatial.Disjoint;
import org.opengis.filter.spatial.Equals;
import org.opengis.filter.spatial.Intersects;
import org.opengis.filter.spatial.Overlaps;
import org.opengis.filter.spatial.Touches;
import org.opengis.filter.spatial.Within;

import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisBbox;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisBeyond;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisContains;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisCrosses;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisDWithin;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisDisjoint;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisEquals;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisIntersects;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisOverlaps;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisTouches;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisWithin;

public class SmpOgisFilterVisitor {
	
	private FilterVisitor geoToolsFilterVisitor = null;
	
    public SmpOgisFilterVisitor() {	
		
	}
    
	public SmpOgisFilterVisitor(FilterVisitor geoToolsFilterVisitor) {
		
		this.geoToolsFilterVisitor = geoToolsFilterVisitor;
	}
	
	public FilterVisitor getGeoToolsFilterVisitor() {
		
		return (this.geoToolsFilterVisitor);
	}
	
	public Object visit(SmpAnd smpFilter, Object extraData) {
		
		And filter = smpFilter.getOpenGisAnd();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
//  There must be a function with featureID but there is not in OpenGIS FilterVisitor
//	public Object visit(SmpFeatureId smpFilter, Object extraData) {
//		
//		FeatureId filter = smpFilter.getOpenGisFeatureId();
//		
//		return (this.geoToolsFilterVisitor.visit(filter, extraData));
//		
//	}
	
	public Object visit(SmpOr smpFilter, Object extraData) {
		
		Or filter = smpFilter.getGeotoolsOr();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpPropertyIsBetween smpFilter, Object extraData) {
		
		PropertyIsBetween filter = smpFilter.getGeotoolsPropertyIsBetween();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	
	
	public Object visit(SmpPropertyIsEqualTo smpFilter, Object extraData) {
		
		PropertyIsEqualTo filter = smpFilter.getOpenGisPropertyIsEqualTo();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpPropertyIsGreaterThan smpFilter, Object extraData) {
		
		PropertyIsGreaterThan filter = smpFilter.getOpenGisPropertyIsEqualTo();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpPropertyIsGreaterThanOrEqualTo smpFilter, Object extraData) {
		
		PropertyIsGreaterThanOrEqualTo filter = smpFilter.getOpenGisPropertyIsGreaterThanOrEqualTo();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpPropertyIsLessThan smpFilter, Object extraData) {
		
		PropertyIsLessThan filter = smpFilter.getOpenGisPropertyIsLessThan();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpPropertyIsLessThanOrEqualTo smpFilter, Object extraData) {
		
		PropertyIsLessThanOrEqualTo filter = smpFilter.getOpenGisPropertyIsLessThanOrEqualTo();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpPropertyIsLike smpFilter, Object extraData) {
		
		PropertyIsLike filter = smpFilter.getGeotoolsPropertyIsLike();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpPropertyIsNull smpFilter, Object extraData) {
		
		PropertyIsNull filter = smpFilter.getGeotoolsPropertyIsNull();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpNot smpFilter, Object extraData) {
		
		Not filter = smpFilter.getGeotoolsNot();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisBbox smpFilter, Object extraData) {
		
		BBOX filter = smpFilter.getOpenGisBbox();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisBeyond smpFilter, Object extraData) {
		
		Beyond filter = smpFilter.getOpenGisBeyond();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisContains smpFilter, Object extraData) {
		
		Contains filter = smpFilter.getOpenGisContains();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisCrosses smpFilter, Object extraData) {
		
		Crosses filter = smpFilter.getOpenGisCrosses();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisDisjoint smpFilter, Object extraData) {
		
		Disjoint filter = smpFilter.getOpenGisDisjoint();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisDWithin smpFilter, Object extraData) {
		
		DWithin filter = smpFilter.getOpenGisDWithin();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisEquals smpFilter, Object extraData) {
		
		Equals filter = smpFilter.getOpenGisEquals();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisIntersects smpFilter, Object extraData) {
		
		Intersects filter = smpFilter.getOpenGisIntersects();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisOverlaps smpFilter, Object extraData) {
		
		Overlaps filter = smpFilter.getOpenGisOverlaps();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisTouches smpFilter, Object extraData) {
		
		Touches filter = smpFilter.getOpenGisTouches();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}
	
	public Object visit(SmpOgisWithin smpFilter, Object extraData) {
		
		Within filter = smpFilter.getOpenGisWithin();
		
		return (this.geoToolsFilterVisitor.visit(filter, extraData));
		
	}	

}
