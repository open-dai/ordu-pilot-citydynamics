package com.sampas.socbs.core.dataset.feature;

import com.sampas.socbs.core.dataset.feature.impl.HistogramDataCollection;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:42
 */
public interface IEqualIntervalDistribution extends IValueSetDistribution {

	/**
	 * 
	 * @param intervalCount
	 */
	public void distribute(int intervalCount);

	/**
	 * 
	 * @param histDataCollection
	 */
	public void setHistogramData(HistogramDataCollection histDataCollection);

	/**
	 * 
	 * @param value
	 */
	public void setMaximum(double value);

	/**
	 * 
	 * @param value
	 */
	public void setMinimum(double value);

}