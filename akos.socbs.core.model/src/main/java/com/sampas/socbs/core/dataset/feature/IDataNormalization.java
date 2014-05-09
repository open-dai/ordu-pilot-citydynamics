package com.sampas.socbs.core.dataset.feature;

import com.sampas.socbs.core.dataset.feature.impl.DataNormalizationTypesEnum;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:41
 */
public interface IDataNormalization {

	public String getNormalizationField();

	public String getNormalizationFieldAlias();

	public double getNormalizationTotal();

	public DataNormalizationTypesEnum getNormalizationType();

	/**
	 * 
	 * @param fieldName
	 */
	public void setNormalizationField(String fieldName);

	/**
	 * 
	 * @param alias
	 */
	public void setNormalizationFieldAlias(String alias);

	/**
	 * 
	 * @param total
	 */
	public void setNormalizationTotal(double total);

	/**
	 * 
	 * @param type
	 */
	public void setNormalizationType(DataNormalizationTypesEnum type);

}