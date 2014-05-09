package com.sampas.socbs.core.dataset.filter.expression;

import org.opengis.filter.expression.Function;

import com.sampas.socbs.core.dataset.filter.impl.SmpExpression;

public class SmpFunction extends SmpExpression {

	private Function geoToolsFunction = null;

	public SmpFunction() {

	}

	public SmpFunction(Function geoToolsFunction) {

		this.geoToolsFunction = geoToolsFunction;
	}

	public Function getGeotoolsFunction() {
		
		return (this.geoToolsFunction);
	}
	
	public String getName() {
		
		return (this.geoToolsFunction.getName());
	}

    public SmpOgisExpression[] getParameters() {
    	
    	SmpOgisExpression[] smpOgisExpression = new SmpOgisExpression[this.getGeotoolsFunction().getParameters().size()];
    	
    	for (int i = 0; i < this.getGeotoolsFunction().getParameters().size(); i++) {
    		//Maybe There Will be a Problem in Casting Must Be Test
    		smpOgisExpression[i] = (SmpOgisExpression)this.getGeotoolsFunction().getParameters().get(i);
		}
    	
    	return (smpOgisExpression);
    }
	
}
