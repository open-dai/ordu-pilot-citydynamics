package com.sampas.socbs.core.renderer.impl;

import com.sampas.socbs.core.symbology.ISymbol;

public class FeatureRendererUniqueValue {
	private String heading;
	private String label;
	private ISymbol symbol;
	
	public FeatureRendererUniqueValue(String heading, String label, ISymbol symbol) {
		this.heading = heading;
		this.label = label;
		this.symbol = symbol;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public ISymbol getSymbol() {
		return symbol;
	}

	public void setSymbol(ISymbol symbol) {
		this.symbol = symbol;
	}
	
	
}
