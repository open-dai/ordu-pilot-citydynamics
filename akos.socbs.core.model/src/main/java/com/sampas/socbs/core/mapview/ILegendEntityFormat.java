package com.sampas.socbs.core.mapview;

import com.sampas.socbs.core.symbology.ITextSymbol;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface ILegendEntityFormat {

	public ITextSymbol getDescriptionSymbol();

	public ITextSymbol getLabelSymbol();

	/**
	 * 
	 * @param symbol
	 */
	public void setDescriptionSymbol(ITextSymbol symbol);

	/**
	 * 
	 * @param symbol
	 */
	public void setLabelSymbol(ITextSymbol symbol);

}