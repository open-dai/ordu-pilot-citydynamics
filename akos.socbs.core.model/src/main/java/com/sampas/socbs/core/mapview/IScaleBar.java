package com.sampas.socbs.core.mapview;

import com.sampas.socbs.core.mapview.impl.ScaleBarLabelFrequencyEnum;
import com.sampas.socbs.core.mapview.impl.ScaleBarUnitLabelPositionsEnum;
import com.sampas.socbs.core.mapview.impl.ScaleBarVerticalPositionsEnum;
import com.sampas.socbs.core.symbology.ITextSymbol;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.impl.UnitTypesEnum;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:45
 */
public interface IScaleBar extends IMapItem {

	public int getDivisionCount();

	public int getDivisionCountBeforeZero();

	public IColor getScaleBarColor();

	public int getScaleBarHeight();
	
	public int getScaleBarWidth();

	public void setScaleBarWidth(int scaleBarWidth);

	public ScaleBarLabelFrequencyEnum getScaleBarLabelFrequency();

	public ITextSymbol getScaleBarLabelSymbol();

	public int getScaleBarSubdivisionCount();

	public String getScaleBarUnitLabel();

	public ScaleBarUnitLabelPositionsEnum getScaleBarUnitLabelPosition();

	public ITextSymbol getScaleBarUnitLabelSymbol();

	public UnitTypesEnum getScaleBarUnits();

	public ScaleBarVerticalPositionsEnum getScaleBarVerticalPosition();

	public boolean isDivision();

	public boolean isScaleBarLabelGap();

	public boolean isScaleBarUnitLabelGap();

	/**
	 * 
	 * @param division
	 */
	public void setDivision(double division);

	/**
	 * 
	 * @param count
	 */
	public void setDivisionCount(int count);

	/**
	 * 
	 * @param count
	 */
	public void setDivisionCountBeforeZero(int count);

	/**
	 * 
	 * @param color
	 */
	public void setScaleBarColor(IColor color);

	/**
	 * 
	 * @param height
	 */
	public void setScaleBarHeight(int height);

	/**
	 * 
	 * @param frequency
	 */
	public void setScaleBarLabelFrequency(ScaleBarLabelFrequencyEnum frequency);

	/**
	 * 
	 * @param gap
	 */
	public void setScaleBarLabelGap(double gap);

	/**
	 * 
	 * @param symbol
	 */
	public void setScaleBarLabelSymbol(ITextSymbol symbol);

	/**
	 * 
	 * @param count
	 */
	public void setScaleBarSubdivisionCount(int count);

	/**
	 * 
	 * @param label
	 */
	public void setScaleBarUnitLabel(String label);

	/**
	 * 
	 * @param gap
	 */
	public void setScaleBarUnitLabelGap(double gap);

	/**
	 * 
	 * @param position
	 */
	public void setScaleBarUnitLabelPosition(
			ScaleBarUnitLabelPositionsEnum position);

	/**
	 * 
	 * @param symbol
	 */
	public void setScaleBarUnitLabelSymbol(ITextSymbol symbol);

	/**
	 * 
	 * @param units
	 */
	public void setScaleBarUnits(UnitTypesEnum units);

	/**
	 * 
	 * @param position
	 */
	public void setScaleBarVerticalPosition(
			ScaleBarVerticalPositionsEnum position);

	public void useMapSettings();

}