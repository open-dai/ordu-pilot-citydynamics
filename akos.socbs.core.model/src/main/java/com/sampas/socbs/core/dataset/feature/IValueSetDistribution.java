package com.sampas.socbs.core.dataset.feature;

import com.sampas.socbs.core.dataset.feature.impl.HistogramDataCollection;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:46
 */
public interface IValueSetDistribution {

	/**
	 * 
	 * @param intervalCount
	 */
	public void distribute(int intervalCount);

	public String getMethodName();

	/**
	 * 
	 * @param histDataCollection
	 */
	public void setHistogramData(HistogramDataCollection histDataCollection);

}