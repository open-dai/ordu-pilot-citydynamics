package com.sampas.socbs.core.mapview;

import com.sampas.socbs.core.mapview.impl.ScaleBarLabelFrequencyEnum;
import com.sampas.socbs.core.mapview.impl.ScaleBarVerticalPositionsEnum;
import com.sampas.socbs.core.symbology.ILineSymbol;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:45
 */
public interface IScaleMarks {

	public ScaleBarLabelFrequencyEnum getDivisionMarkFrequency();

	public ScaleBarVerticalPositionsEnum getDivisionMarkPosition();

	public ILineSymbol getDivisionMarkSymbol();

	public ILineSymbol getSubdivisionMarkSymbol();

	public double IsDivisionMarkHeight();

	public double IsSubdivisionMarkHeight();

	/**
	 * 
	 * @param frequency
	 */
	public void setDivisionMarkFrequency(ScaleBarLabelFrequencyEnum frequency);

	/**
	 * 
	 * @param height
	 */
	public void setDivisionMarkHeight(double height);

	/**
	 * 
	 * @param position
	 */
	public void setDivisionMarkPosition(ScaleBarVerticalPositionsEnum position);

	/**
	 * 
	 * @param symbol
	 */
	public void setDivisionMarkSymbol(ILineSymbol symbol);

	/**
	 * 
	 * @param height
	 */
	public void setSubdivisionMarkHeight(double height);

	/**
	 * 
	 * @param symbol
	 */
	public void setSubdivisionMarkSymbol(ILineSymbol symbol);

}