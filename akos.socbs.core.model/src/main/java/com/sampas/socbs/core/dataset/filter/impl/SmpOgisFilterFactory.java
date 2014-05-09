package com.sampas.socbs.core.dataset.filter.impl;

import java.util.List;

import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.expression.Expression;

import com.sampas.socbs.core.dataset.filter.expression.SmpAdd;
import com.sampas.socbs.core.dataset.filter.expression.SmpDivide;
import com.sampas.socbs.core.dataset.filter.expression.SmpFunction;
import com.sampas.socbs.core.dataset.filter.expression.SmpLiteral;
import com.sampas.socbs.core.dataset.filter.expression.SmpMultiply;
import com.sampas.socbs.core.dataset.filter.expression.SmpOgisExpression;
import com.sampas.socbs.core.dataset.filter.expression.SmpSubtract;
import com.sampas.socbs.core.dataset.filter.spatial.SmpOgisBbox;

public class SmpOgisFilterFactory {

	private FilterFactory openGisFilterFactory = null;

	public SmpOgisFilterFactory() {

	}

	public SmpOgisFilterFactory(FilterFactory openGisFilterFactory) {

		this.openGisFilterFactory = openGisFilterFactory;
	}

	public FilterFactory getOpenGisFilterFactory() {

		return (this.openGisFilterFactory);
	}
		
	//  FILTERS
	
	public SmpAnd and(SmpFilter f, SmpFilter g) {
		
		Filter fo = f.getGeoToolsFilter();
		Filter go = g.getGeoToolsFilter();
		
		SmpAnd smpAnd = new SmpAnd(this.openGisFilterFactory.and(fo, go));
		
		return(smpAnd);
		
	}
	
	@SuppressWarnings("unchecked")
	public SmpAnd and(List f) {
		
		SmpAnd smpAnd = new SmpAnd(this.openGisFilterFactory.and(f));
		
		return(smpAnd);
	}
	
	public SmpOr or(SmpFilter f, SmpFilter g) {
		
		Filter fo = f.getGeoToolsFilter();
		Filter go = g.getGeoToolsFilter();
		
		SmpOr smpOr= new SmpOr(this.openGisFilterFactory.or(fo, go));
		
		return(smpOr);
	}
	
	@SuppressWarnings("unchecked")
	public SmpOr or(List f) {
		
		SmpOr smpOr= new SmpOr(this.openGisFilterFactory.or(f));
		
		return(smpOr);
	}
	
	public SmpNot not(SmpFilter f) {
		
		Filter filter = f.getGeoToolsFilter();
		
		SmpNot smpNot = new SmpNot(this.openGisFilterFactory.not(filter));
		
		return (smpNot);
	}

//	public SmpFeatureId featureId(String ids) {
//		
//		SmpFeatureId smpFeatureId = new SmpFeatureId(this.openGisFilterFactory.featureId(ids));
//		
//		return(smpFeatureId);
//		
//	}
	
	public SmpPropertyName property(String name) {
		
		SmpPropertyName smpPropertyName = new SmpPropertyName(this.openGisFilterFactory.property(name));
		
		return(smpPropertyName);
	}
	
	public SmpPropertyIsEqualTo equals(SmpOgisExpression smpOgisExpression1, SmpOgisExpression smpOgisExpression2) {
		
		Expression exp1 = smpOgisExpression1.getGeotoolsOgisExpression();
		Expression exp2 = smpOgisExpression2.getGeotoolsOgisExpression();
		
		SmpPropertyIsEqualTo smpPropertyIsEqualTo = new SmpPropertyIsEqualTo(this.openGisFilterFactory.equals(exp1, exp2));
		
		return (smpPropertyIsEqualTo);		
		
	}
	
	public SmpPropertyIsGreaterThan greater(SmpOgisExpression smpOgisExpression1, SmpOgisExpression smpOgisExpression2) {
		
		Expression exp1 = smpOgisExpression1.getGeotoolsOgisExpression();
		Expression exp2 = smpOgisExpression2.getGeotoolsOgisExpression();
		
		SmpPropertyIsGreaterThan smpPropertyIsGreaterThan = new SmpPropertyIsGreaterThan(this.openGisFilterFactory.greater(exp1, exp2));
		
		return (smpPropertyIsGreaterThan);
		
	}
	
	public SmpPropertyIsGreaterThanOrEqualTo greaterOrEqual(SmpOgisExpression smpOgisExpression1, SmpOgisExpression smpOgisExpression2) {
		
		Expression exp1 = smpOgisExpression1.getGeotoolsOgisExpression();
		Expression exp2 = smpOgisExpression2.getGeotoolsOgisExpression();
		
		SmpPropertyIsGreaterThanOrEqualTo smpPropertyIsGreaterThanOrEqualTo = new SmpPropertyIsGreaterThanOrEqualTo(this.openGisFilterFactory.greaterOrEqual(exp1, exp2));
		
		return (smpPropertyIsGreaterThanOrEqualTo);
		
	}
	
	public  SmpPropertyIsLessThan less(SmpOgisExpression smpOgisExpression1, SmpOgisExpression smpOgisExpression2) {
		
		Expression exp1 = smpOgisExpression1.getGeotoolsOgisExpression();
		Expression exp2 = smpOgisExpression2.getGeotoolsOgisExpression();
		
		SmpPropertyIsLessThan smpPropertyIsLessThan = new SmpPropertyIsLessThan(this.openGisFilterFactory.less(exp1, exp2));
		
		return (smpPropertyIsLessThan);
		
	}
	
	public SmpPropertyIsLessThanOrEqualTo lessOrEqual(SmpOgisExpression smpOgisExpression1, SmpOgisExpression smpOgisExpression2) {
		
		Expression exp1 = smpOgisExpression1.getGeotoolsOgisExpression();
		Expression exp2 = smpOgisExpression2.getGeotoolsOgisExpression();
		
		SmpPropertyIsLessThanOrEqualTo smpPropertyIsLessThanOrEqualTo = new SmpPropertyIsLessThanOrEqualTo(this.openGisFilterFactory.lessOrEqual(exp1, exp2));
		
		return (smpPropertyIsLessThanOrEqualTo);
		
	}
	
	 public SmpPropertyIsLike like(SmpOgisExpression smpOgisExpression, String pattern) {
		 
		 Expression expr = smpOgisExpression.getGeotoolsOgisExpression();
		 
		 SmpPropertyIsLike smpPropertyIsLike = new SmpPropertyIsLike(this.openGisFilterFactory.like(expr, pattern));
		 
		 return (smpPropertyIsLike);
		 
	 }
	 
	 public  SmpPropertyIsLike like(SmpOgisExpression smpOgisExpression, String pattern, String wildcard, String singleChar, String escape) {
		 
		 Expression expr = smpOgisExpression.getGeotoolsOgisExpression();
		 
		 SmpPropertyIsLike smpPropertyIsLike = new SmpPropertyIsLike(this.openGisFilterFactory.like(expr, pattern, wildcard, singleChar, escape));
		 
		 return (smpPropertyIsLike);
	 }
	 
	 public SmpPropertyIsNull isNull(SmpOgisExpression smpOgisExpression) {
		 
		 Expression expr = smpOgisExpression.getGeotoolsOgisExpression();
		 
		 SmpPropertyIsNull smpPropertyIsNull = new SmpPropertyIsNull(this.openGisFilterFactory.isNull(expr));
		 
		 return (smpPropertyIsNull);
		 
	 }
	 
	 
	//  SPATIAL FILTERS for initialize this must imlement "org.opengis.spatialschema.geometry.Geometry" source and use it
	 
	 public SmpOgisBbox bbox(String propertyName, double minx, double miny, double maxx, double maxy, String srs) {

		 SmpOgisBbox smpBbox = new SmpOgisBbox(this.openGisFilterFactory.bbox(propertyName, minx, miny, maxx, maxy, srs));
		 
		 return (smpBbox);
		 
	 }
	 
//	 public SmpBeyond beyond(String propertyName, SmpGeometry smpGeometry, double distance, String units) {
//		 
//		 Geometry geometry = smpGeometry.getGeotoolsGeometry();
//		 
//		 SmpBeyond smpBeyond = new SmpBeyond(this.openGisFilterFactory.beyond(propertyName, geometry, distance, units));
//		 
//		 return (smpBeyond);
//	 }
	 
	 
	 //  EXPRESSIONS
	 
	 public SmpAdd add(SmpExpression smpExpression1, SmpExpression smpExpression2) {
		 
		 Expression expr1 = smpExpression1.getGeoToolsExpression();
		 Expression expr2 = smpExpression2.getGeoToolsExpression();
		 
		 SmpAdd smpAdd = new SmpAdd(this.openGisFilterFactory.add(expr1, expr2));
		 
		 return (smpAdd);
	 }
	 
	 public SmpDivide divide(SmpExpression smpExpression1, SmpExpression smpExpression2) {
		 
		 Expression expr1 = smpExpression1.getGeoToolsExpression();
		 Expression expr2 = smpExpression2.getGeoToolsExpression();
		 
		 SmpDivide smpDivide = new SmpDivide(this.openGisFilterFactory.divide(expr1, expr2));
		 
		 return (smpDivide);
		 
	 }
	 
	 public SmpMultiply  multiply(SmpExpression smpExpression1, SmpExpression smpExpression2) {
		 
		 Expression expr1 = smpExpression1.getGeoToolsExpression();
		 Expression expr2 = smpExpression2.getGeoToolsExpression();
		 
		 SmpMultiply smpMultiply = new SmpMultiply(this.openGisFilterFactory.multiply(expr1, expr2));
		 
		 return (smpMultiply);
		 
	 }
	 
	 public  SmpSubtract  subtract(SmpExpression smpExpression1, SmpExpression smpExpression2) {
		 
		 Expression expr1 = smpExpression1.getGeoToolsExpression();
		 Expression expr2 = smpExpression2.getGeoToolsExpression();
		 
		 SmpSubtract smpSubtract = new SmpSubtract(this.openGisFilterFactory.subtract(expr1, expr2));
		 
		 return (smpSubtract);
		 
	 }
	 
	 public  SmpFunction  function(String name, SmpExpression[] smpExpressions) {
		 
		 Expression[] exprs = new Expression[smpExpressions.length];
		 
		 for (int i = 0; i < smpExpressions.length; i++) {
			 
			 exprs[i] = smpExpressions[i].getGeoToolsExpression();
		 }
		 
		 SmpFunction smpFunction = new SmpFunction(this.openGisFilterFactory.function(name, exprs));
		 
		 return (smpFunction);
		 
	 }
	 
	 public SmpFunction  function(String name, SmpExpression smpExpression) {
		 
		 Expression expr = smpExpression.getGeoToolsExpression();
		 
		 SmpFunction smpFunction = new SmpFunction(this.openGisFilterFactory.function(name, expr));
		 
		 return (smpFunction);
		 
	 }
	 
	 public SmpFunction function(String name, SmpExpression smpExpression1, SmpExpression smpExpression2) {
		 
		 Expression expr1 = smpExpression1.getGeoToolsExpression();
		 Expression expr2 = smpExpression2.getGeoToolsExpression();
		 
		 SmpFunction smpFunction = new SmpFunction(this.openGisFilterFactory.function(name, expr1, expr2));
		
		 return (smpFunction);
		 
	 }
	 
	 public SmpFunction function(String name, SmpExpression smpExpression1, SmpExpression smpExpression2, SmpExpression smpExpression3) {
		 
		 Expression expr1 = smpExpression1.getGeoToolsExpression();
		 Expression expr2 = smpExpression2.getGeoToolsExpression();
		 Expression expr3 = smpExpression3.getGeoToolsExpression();
		 
		 SmpFunction smpFunction = new SmpFunction(this.openGisFilterFactory.function(name, expr1, expr2, expr3));
		
		 return (smpFunction);
		 
	 }
	 
	 public SmpLiteral literal(Object obj) {
		 
		 SmpLiteral smpLiteral = new SmpLiteral(this.openGisFilterFactory.literal(obj));
		 
		 return (smpLiteral);
		 
	 }
	 
	 public SmpLiteral literal(byte b) {
		 
		 SmpLiteral smpLiteral = new SmpLiteral(this.openGisFilterFactory.literal(b));
		 
		 return (smpLiteral);
		 
	 }
	 
	 public SmpLiteral literal(short s) {
		 
		 SmpLiteral smpLiteral = new SmpLiteral(this.openGisFilterFactory.literal(s));
		 
		 return (smpLiteral);
		 
	 }
	 
	 public SmpLiteral literal(int i) {
		 
		 SmpLiteral smpLiteral = new SmpLiteral(this.openGisFilterFactory.literal(i));
		 
		 return (smpLiteral);
		 
	 }
	 
	 public SmpLiteral literal(long l) {
		 
		 SmpLiteral smpLiteral = new SmpLiteral(this.openGisFilterFactory.literal(l));
		 
		 return (smpLiteral);
		 
	 }
	 
	 public SmpLiteral literal(float f) {
		 
		 SmpLiteral smpLiteral = new SmpLiteral(this.openGisFilterFactory.literal(f));
		 
		 return (smpLiteral);
		 
	 }
	 
	 public SmpLiteral literal(double d) {
		 
		 SmpLiteral smpLiteral = new SmpLiteral(this.openGisFilterFactory.literal(d));
		 
		 return (smpLiteral);
		 
	 }
	 
	 public SmpLiteral literal(char c) {
		 
		 SmpLiteral smpLiteral = new SmpLiteral(this.openGisFilterFactory.literal(c));
		 
		 return (smpLiteral);
		 
	 }
	 
	 public SmpLiteral literal(boolean b) {
		 
		 SmpLiteral smpLiteral = new SmpLiteral(this.openGisFilterFactory.literal(b));
		 
		 return (smpLiteral);
		 
	 }
	 
	 
}
