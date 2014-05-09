package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.feature.AttributeType;
import org.geotools.feature.FeatureType;
import org.geotools.filter.Expression;
import org.geotools.filter.Filter;
import org.geotools.filter.FilterFactory;
import org.geotools.filter.IllegalFilterException;

import com.sampas.socbs.core.dataset.feature.impl.SmpAttributeType;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureType;
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
import com.sampas.socbs.core.geometry.impl.SmpEnvelope;

@SuppressWarnings("deprecation")
public class SmpFilterFactory {

	private FilterFactory geoToolsFilterFactory = null;

	public SmpFilterFactory() {

	}

	public SmpFilterFactory(FilterFactory geoToolsFilterFactory) {

		this.geoToolsFilterFactory = geoToolsFilterFactory;
	}

	public FilterFactory getGeoToolsFilterFactory() {

		return (this.geoToolsFilterFactory);
	}

	public SmpFidFilter createFidFilter(String fid) {

		SmpFidFilter smpFidFilter = new SmpFidFilter(this.geoToolsFilterFactory.createFidFilter(fid));
		
		return (smpFidFilter);
	}	
	
	public SmpLogicFilter createLogicFilter(SmpGFilter smpFilter1, SmpGFilter smpFilter2, short filterType) throws IllegalFilterException {
		
		Filter filter1 = smpFilter1.getGeoToolsFilter();
		
		Filter filter2 = smpFilter2.getGeoToolsFilter();
		
		SmpLogicFilter smplogicFilter = new SmpLogicFilter(this.geoToolsFilterFactory.createLogicFilter(filter1, filter2, filterType)); 
		
		return (smplogicFilter);
	}

	public SmpLogicFilter createLogicFilter(short filterType) throws IllegalFilterException{
		
		SmpLogicFilter smpLogicFilter = new SmpLogicFilter(this.geoToolsFilterFactory.createLogicFilter(filterType));
		
		return (smpLogicFilter);
	}

	public SmpLogicFilter createLogicFilter(SmpGFilter smpGFilter, short filterType) throws IllegalFilterException{
		
		Filter filter = smpGFilter.getGeoToolsFilter();
		
		SmpLogicFilter smpLogicFilter = new SmpLogicFilter(this.geoToolsFilterFactory.createLogicFilter(filter, filterType));
		
		return (smpLogicFilter);
	}	
	
	public SmpBBoxExpression createBBoxExpression(SmpEnvelope smpEnvelope) throws IllegalFilterException{
		
		SmpBBoxExpression smpBBoxExpression = new SmpBBoxExpression(this.geoToolsFilterFactory.createBBoxExpression(smpEnvelope.getGeoToolsEnvelope()));
		
		return (smpBBoxExpression);
	}
	
	public SmpLiteralExpression createLiteralExpression(int i) {
		
		SmpLiteralExpression smpLiteralExpression = new SmpLiteralExpression(this.geoToolsFilterFactory.createLiteralExpression(i));
		
		return (smpLiteralExpression);
	}

	public SmpMathExpression createMathExpression() throws IllegalFilterException {
		
		SmpMathExpression smpMathExpression = new SmpMathExpression(this.geoToolsFilterFactory.createMathExpression());
		
		return (smpMathExpression);
	}

	public SmpFidFilter createFidFilter() {
		
		SmpFidFilter smpFidFilter = new SmpFidFilter(this.geoToolsFilterFactory.createFidFilter());
		
		return (smpFidFilter);
	}

	public SmpAttributeExpression createAttributeExpression(String xpath) {
		
		SmpAttributeExpression smpAttributeExpression = new SmpAttributeExpression(this.geoToolsFilterFactory.createAttributeExpression(xpath));
		
		return (smpAttributeExpression);
	}

	public SmpAttributeExpression createAttributeExpression(SmpFeatureType schema,
			String xpath) throws IllegalFilterException {
		
		FeatureType featureType = schema.getGeoToolsFeatureType();	
		
		SmpAttributeExpression smpAttributeExpression = new SmpAttributeExpression(this.geoToolsFilterFactory.createAttributeExpression(featureType, xpath));
		
		return (smpAttributeExpression);
	}

	public SmpAttributeExpression createAttributeExpression(SmpAttributeType smpAttributeType)
			throws IllegalFilterException {
		
		AttributeType attributeType = smpAttributeType.getGeoToolsAttributeType();
		
		SmpAttributeExpression smpAttributeExpression = new SmpAttributeExpression(this.geoToolsFilterFactory.createAttributeExpression(attributeType));		
		
		return (smpAttributeExpression);
	}

	public SmpLiteralExpression createLiteralExpression(Object o)
			throws IllegalFilterException {
		
		SmpLiteralExpression smpLiteralExpression = new SmpLiteralExpression(this.geoToolsFilterFactory.createLiteralExpression(o));
		
		return (smpLiteralExpression);
	}

	public SmpCompareFilter createCompareFilter(short type)
			throws IllegalFilterException {
		
		SmpCompareFilter smpCompareFilter = new SmpCompareFilter(this.geoToolsFilterFactory.createCompareFilter(type));
		
		return (smpCompareFilter);
	}

	public SmpLiteralExpression createLiteralExpression() {
	
		SmpLiteralExpression smpLiteralExpression =  new SmpLiteralExpression(this.geoToolsFilterFactory.createLiteralExpression());
		
		return (smpLiteralExpression);
	}
	
	public SmpLiteralExpression createLiteralExpression(String string) {
		
		SmpLiteralExpression smpLiteralExpression =  new SmpLiteralExpression(this.geoToolsFilterFactory.createLiteralExpression(string));
		
		return (smpLiteralExpression);
	}

	public SmpLiteralExpression createLiteralExpression(double dbl) {
		
		SmpLiteralExpression smpLiteralExpression =  new SmpLiteralExpression(this.geoToolsFilterFactory.createLiteralExpression(dbl));
		
		return (smpLiteralExpression);
	}

	public SmpAttributeExpression createAttributeExpression(SmpFeatureType smpSchema) {
		
		FeatureType featureType = smpSchema.getGeoToolsFeatureType();
		
		SmpAttributeExpression smpAttributeExpression = new SmpAttributeExpression(this.geoToolsFilterFactory.createAttributeExpression(featureType));
		
		return (smpAttributeExpression);
	}

	public SmpMathExpression createMathExpression(short expressionType)
			throws IllegalFilterException {
		
		SmpMathExpression smpMathExpression = new SmpMathExpression(this.geoToolsFilterFactory.createMathExpression(expressionType));
		
		return (smpMathExpression);
	}

	public SmpNullFilter createNullFilter() {
		
		SmpNullFilter smpNullFilter = new SmpNullFilter(this.geoToolsFilterFactory.createNullFilter());
		
		return (smpNullFilter);
	}

	public SmpBetweenFilter createBetweenFilter() throws IllegalFilterException {
		
		SmpBetweenFilter smpBetweenFilter = new SmpBetweenFilter(this.geoToolsFilterFactory.createBetweenFilter());
		
		return (smpBetweenFilter);
	}

	public SmpGeometryFilter createGeometryFilter(short filterType)
			throws IllegalFilterException {
		
		SmpGeometryFilter smpGeometryFilter = new SmpGeometryFilter(this.geoToolsFilterFactory.createGeometryFilter(filterType));
		
		return (smpGeometryFilter);
	}

	public SmpGeometryDistanceFilter createGeometryDistanceFilter(short filterType)
			throws IllegalFilterException {
		
		SmpGeometryDistanceFilter smpGeometryDistanceFilter = new SmpGeometryDistanceFilter(this.geoToolsFilterFactory.createGeometryDistanceFilter(filterType));
		
		return (smpGeometryDistanceFilter);
	}

	public SmpLikeFilter createLikeFilter() {
		
		SmpLikeFilter smpLikeFilter = new SmpLikeFilter(this.geoToolsFilterFactory.createLikeFilter());
		
		return (smpLikeFilter);
	}

	public SmpFunctionExpression createFunctionExpression(String name) {
		
		SmpFunctionExpression smpFunctionExpression = new SmpFunctionExpression(this.geoToolsFilterFactory.createFunctionExpression(name));
		
		return (smpFunctionExpression);		
	}

	public SmpEnvironmentVariable createEnvironmentVariable(String name) {
		
		SmpEnvironmentVariable smpEnvironmentVariable =  new SmpEnvironmentVariable(this.geoToolsFilterFactory.createEnvironmentVariable(name));
		
		return (smpEnvironmentVariable);
	}

	public SmpGFilter or(SmpGFilter smpFilter1, SmpGFilter smpFilter2) {
		
		Filter filter1 = smpFilter1.getGeoToolsFilter();
		
		Filter filter2 = smpFilter2.getGeoToolsFilter();
		
		SmpGFilter smpGFilter = new SmpGFilter(this.geoToolsFilterFactory.or(filter1, filter2));
				
		return (smpGFilter);
	}
	
	public SmpGFilter and(SmpGFilter smpFilter1, SmpGFilter smpFilter2) {
		
		Filter filter1 = smpFilter1.getGeoToolsFilter();
		
		Filter filter2 = smpFilter2.getGeoToolsFilter();
		
		SmpGFilter smpGFilter = new SmpGFilter(this.geoToolsFilterFactory.and(filter1, filter2));
				
		return (smpGFilter);
	}

	public SmpGFilter not(SmpGFilter smpGFilter) {
		
		Filter filter = smpGFilter.getGeoToolsFilter();
		
		SmpGFilter smpFilterOut = new SmpGFilter(this.geoToolsFilterFactory.not(filter));
		
		return (smpFilterOut);
	}

	public SmpOgisBbox bbox(SmpExpression smpGeometry, double minx, double miny, double maxx,
			double maxy, String srs) {
		
		Expression expression = smpGeometry.getGeoToolsExpression();
		
		SmpOgisBbox smpBbox = new SmpOgisBbox(this.geoToolsFilterFactory.bbox(expression, minx, miny, maxx, maxy, srs));
		
		return (smpBbox);
	}

	public SmpOgisBeyond beyond(SmpExpression smpGeometry1, SmpExpression smpGeometry2, double distance,
			String units) {
		
		Expression expression1 = smpGeometry1.getGeoToolsExpression();
		
		Expression expression2 = smpGeometry2.getGeoToolsExpression();
		
		SmpOgisBeyond smpBeyond = new SmpOgisBeyond(this.geoToolsFilterFactory.beyond(expression1, expression2, distance, units));
		
		return (smpBeyond);
	}
	
	public SmpOgisDWithin dwithin(SmpExpression smpGeometry1, SmpExpression smpGeometry2, double distance, String units) {
		
		Expression expression1 = smpGeometry1.getGeoToolsExpression();
		
		Expression expression2 = smpGeometry2.getGeoToolsExpression();
		
		SmpOgisDWithin smpDWithin = new SmpOgisDWithin(this.geoToolsFilterFactory.dwithin(expression1, expression2, distance, units));
		
		return (smpDWithin);
	}

	public SmpOgisContains contains(SmpExpression smpGeometry1, SmpExpression smpGeometry2) {
		
		Expression expression1 = smpGeometry1.getGeoToolsExpression();
		
		Expression expression2 = smpGeometry2.getGeoToolsExpression();
		
		SmpOgisContains smpcontains = new SmpOgisContains(this.geoToolsFilterFactory.contains(expression1, expression2));
		
		return (smpcontains);
	}

	public SmpOgisCrosses crosses(SmpExpression smpGeometry1, SmpExpression smpGeometry2) {
		
		Expression expression1 = smpGeometry1.getGeoToolsExpression();
		
		Expression expression2 = smpGeometry2.getGeoToolsExpression();
		
		SmpOgisCrosses smpCrosses = new SmpOgisCrosses(this.geoToolsFilterFactory.crosses(expression1, expression2));
		
		return (smpCrosses);
	}

	public SmpOgisDisjoint disjoint(SmpExpression smpGeometry1, SmpExpression smpGeometry2) {
		
		Expression expression1 = smpGeometry1.getGeoToolsExpression();
		
		Expression expression2 = smpGeometry2.getGeoToolsExpression();
		
		SmpOgisDisjoint smpDisjoint = new SmpOgisDisjoint(this.geoToolsFilterFactory.disjoint(expression1, expression2));
		
		return (smpDisjoint);
	}
	
	public SmpOgisEquals equal(SmpExpression smpGeometry1, SmpExpression smpGeometry2) {
		
		Expression expression1 = smpGeometry1.getGeoToolsExpression();
		
		Expression expression2 = smpGeometry2.getGeoToolsExpression();
		
		SmpOgisEquals smpEquals = new SmpOgisEquals(this.geoToolsFilterFactory.equal(expression1, expression2));
		
		return (smpEquals);
	}

	public SmpOgisIntersects intersects(SmpExpression smpGeometry1, SmpExpression smpGeometry2) {
		
		Expression expression1 = smpGeometry1.getGeoToolsExpression();
		
		Expression expression2 = smpGeometry2.getGeoToolsExpression();
		
		SmpOgisIntersects smpIntersects = new SmpOgisIntersects(this.geoToolsFilterFactory.intersects(expression1, expression2));
		
		return (smpIntersects);
	}
	
	public SmpOgisOverlaps overlaps(SmpExpression smpGeometry1, SmpExpression smpGeometry2) {
		
		Expression expression1 = smpGeometry1.getGeoToolsExpression();
		
		Expression expression2 = smpGeometry2.getGeoToolsExpression();
		
		SmpOgisOverlaps smpOverlaps = new SmpOgisOverlaps(this.geoToolsFilterFactory.overlaps(expression1, expression2));
		
		return (smpOverlaps);
	}
	
	public SmpOgisTouches touches(SmpExpression smpGeometry1, SmpExpression smpGeometry2) {
		
		Expression expression1 = smpGeometry1.getGeoToolsExpression();
		
		Expression expression2 = smpGeometry2.getGeoToolsExpression();
		
		SmpOgisTouches smpTouches = new SmpOgisTouches(this.geoToolsFilterFactory.touches(expression1, expression2));
		
		return (smpTouches);
	}
	
	public SmpOgisWithin within(SmpExpression smpGeometry1, SmpExpression smpGeometry2) {
		
		Expression expression1 = smpGeometry1.getGeoToolsExpression();
		
		Expression expression2 = smpGeometry2.getGeoToolsExpression();
		
		SmpOgisWithin smpWithin = new SmpOgisWithin(this.geoToolsFilterFactory.within(expression1, expression2));
		
		return (smpWithin);
	}

}
