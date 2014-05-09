package com.sampas.socbs.core.mapview;

import com.sampas.socbs.core.symbology.ITextSymbol;
import com.sampas.socbs.core.tools.impl.UnitTypesEnum;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:45
 */
public interface IScaleText extends IMapItem {

	public UnitTypesEnum getMapUnits();

	public String getMapUnitsLabel();

	public UnitTypesEnum getPageUnits();

	public String getPageUnitsLabel();

	public String getScaleText();

	public ITextSymbol getScaleTextSymbol();

	/**
	 * 
	 * @param mapUnits
	 */
	public void setMapUnits(UnitTypesEnum mapUnits);

	/**
	 * 
	 * @param mapUnitsLabel
	 */
	public void setMapUnitsLabel(String mapUnitsLabel);

	/**
	 * 
	 * @param pageUnits
	 */
	public void setPageUnits(UnitTypesEnum pageUnits);

	/**
	 * 
	 * @param pageUnitsLabel
	 */
	public void setPageUnitsLabel(String pageUnitsLabel);

	/**
	 * 
	 * @param scaleText
	 */
	public void setScaleText(String scaleText);

	/**
	 * 
	 * @param scaleTextSymbol
	 */
	public void setScaleTextSymbol(ITextSymbol scaleTextSymbol);

}