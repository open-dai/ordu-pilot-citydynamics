package com.sampas.socbs.core.dataset.filter.expression;

import org.geotools.feature.Feature;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.ExpressionVisitor;

import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;

public class SmpOgisExpression {

	private Expression geoToolsOgisExpression = null;

	public SmpOgisExpression() {

	}

	public SmpOgisExpression(Expression geoToolsOgisExpression) {

		this.geoToolsOgisExpression = geoToolsOgisExpression;
	}

	public Expression getGeotoolsOgisExpression() {

		return (this.geoToolsOgisExpression);
	}

	public Object evaluate(SmpFeature smpFeature) {

		Feature feature = smpFeature.getGeoToolFeature();
		
		return (this.geoToolsOgisExpression.evaluate(feature));				
	}

	public  Object accept(SmpExpressionVisitor smpExpressionVisitor, Object extraData) {

		ExpressionVisitor expressionVisitor = smpExpressionVisitor.getGeotoolsExpressionVisitor();
		
		return (this.geoToolsOgisExpression.accept(expressionVisitor, extraData));
	}
}
