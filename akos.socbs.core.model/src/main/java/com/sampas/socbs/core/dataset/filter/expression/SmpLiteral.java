package com.sampas.socbs.core.dataset.filter.expression;

import org.opengis.filter.expression.Literal;

import com.sampas.socbs.core.dataset.filter.impl.SmpExpression;

public class SmpLiteral extends SmpExpression {

	private Literal geoToolsLiteral = null;

	public SmpLiteral() {

	}

	public SmpLiteral(Literal geoToolsLiteral) {

		this.geoToolsLiteral = geoToolsLiteral;
	}

	public Literal getGeotoolsLiteral() {
		
		return (this.geoToolsLiteral);
	}
	
	public Object getValue() {
		
		return (this.geoToolsLiteral.getValue());		
		
	}
	
}
