package com.sampas.socbs.core.dataset.filter.impl;

import java.util.List;

import org.geotools.filter.Expression;
import org.geotools.filter.FunctionExpression;

@SuppressWarnings("deprecation")
public class SmpFunctionExpression extends SmpExpression{

	
	private FunctionExpression geoToolsFunctionExpression = null;

	public SmpFunctionExpression() {

	}

	public SmpFunctionExpression(FunctionExpression geoToolsFunctionExpression) {

		this.geoToolsFunctionExpression = geoToolsFunctionExpression;
	}

	public FunctionExpression getGeoToolsFunctionExpression() {

		return (this.geoToolsFunctionExpression);
	}

	public int getArgCount() {

		return (this.geoToolsFunctionExpression.getArgCount());
	}

	
	public short getType() {

		return (this.geoToolsFunctionExpression.getType());
	}

	public SmpExpression[] getArgs() {

		SmpExpression[] smpExpression = new SmpExpression[this.geoToolsFunctionExpression.getArgs().length];

		for (int i = 0; i < this.geoToolsFunctionExpression.getArgs().length; i++) {
			smpExpression[i] = new SmpExpression(this.geoToolsFunctionExpression.getArgs()[i]);
		}
		
		return (smpExpression);
	}

	public void setArgs(SmpExpression[] args) {

		Expression[] expression = new Expression[args.length];

		for (int i = 0; i < args.length; i++) {
			expression[i] = args[i].getGeoToolsExpression();
		}

		this.geoToolsFunctionExpression.setArgs(expression);
	}

	public String getName() {

		return (this.geoToolsFunctionExpression.getName());
	}

	@SuppressWarnings("unchecked")
	public void setParameters(List parameters) {

		this.geoToolsFunctionExpression.setParameters(parameters);
	}
}
