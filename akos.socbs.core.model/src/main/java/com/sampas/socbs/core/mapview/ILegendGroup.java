package com.sampas.socbs.core.mapview;

public interface ILegendGroup {

	public void addLegend(ILegend legend);
	
	public void addLegend(int index, ILegend legend);
	
	public ILegend getLegend(int index);
	
	public void removeLegend(int index);
	
	public void removeLegend(ILegend legend);
	
	public void clearLegendGroup();
	
	public int getLegendCount();
	
}
