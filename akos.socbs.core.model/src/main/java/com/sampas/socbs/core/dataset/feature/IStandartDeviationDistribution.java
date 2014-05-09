package com.sampas.socbs.core.dataset.feature;

import com.sampas.socbs.core.dataset.feature.impl.HistogramDataCollection;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:45
 */
public interface IStandartDeviationDistribution extends IValueSetDistribution {

	/**
	 * 
	 * @param intervalCount
	 */
	public void distribute(int intervalCount);

	public double getDeviationInterval();

	/**
	 * 
	 * @param interval
	 */
	public void setDeviationInterval(double interval);

	/**
	 * 
	 * @param histDataCollection
	 */
	public void setHistogramData(HistogramDataCollection histDataCollection);

	/**
	 * 
	 * @param value
	 */
	public void setMean(double value);

	/**
	 * 
	 * @param value
	 */
	public void setStandartDeviation(double value);

}