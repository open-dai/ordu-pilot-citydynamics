package com.sampas.socbs.core.mapview.impl;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.sampas.socbs.core.mapview.ILegend;
import com.sampas.socbs.core.mapview.ILegendGroup;

public class SmpLegendGroup implements ILegendGroup {

	private List<ILegend> legendGroup = new ArrayList<ILegend>();
	private List<BufferedImage> legendImages;
	
	public void addLegend(ILegend legend){
		
		this.legendGroup.add(legend);
	}
	
	public void addLegend(int index, ILegend legend){
		
		this.legendGroup.add(index,legend);
	}
	
	public ILegend getLegend(int index){
		return this.legendGroup.get(index);
	}
	
	public void removeLegend(int index){
		
		this.legendGroup.remove(index);
	}
	
	public void removeLegend(ILegend legend){
		
		this.legendGroup.remove(legend);
	}
	
	public void clearLegendGroup(){
		
		this.legendGroup.clear();
	}
	
	public int getLegendCount(){
		
		return this.legendGroup.size();
	}

	public void setLegendImages(List<BufferedImage> legendImages) {
		this.legendImages = legendImages;
	}

	public List<BufferedImage> getLegendImages() {
		return legendImages;
	}
}
