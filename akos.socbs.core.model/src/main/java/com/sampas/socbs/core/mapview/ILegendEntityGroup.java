package com.sampas.socbs.core.mapview;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:43
 */
public interface ILegendEntityGroup {

	public void setLegendEntity(int index, ILegendEntity m_ILegendEntity);
	
	/* Hash Map */
	public ILegendEntity getLegendEntity(String index);

	/**
	 * 
	 * @param legendEntity
	 */
	public void addEntity(ILegendEntity legendEntity);

	public int getLegendEntityGroupSize();
	/**
	 * 
	 * @param index
	 */

	public String getHeading();

	/**
	 * 
	 * @param index
	 * @param legendEntity
	 */
	public void InsertEntity(int index, ILegendEntity legendEntity);

	public boolean IsVisible();

	/**
	 * 
	 * @param index
	 */
	public void removeEntity(int index);

	/**
	 * 
	 * @param heading
	 */
	public void setHeading(String heading);

	/**
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible);

}