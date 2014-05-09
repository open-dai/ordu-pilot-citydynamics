package com.sampas.socbs.core.mapview.impl;

import java.util.ArrayList;
import java.util.List;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.impl.MapItemTypesEnum;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.maplayer.IMap;
import com.sampas.socbs.core.mapview.ILegend;
import com.sampas.socbs.core.mapview.ILegendItem;
import com.sampas.socbs.core.tools.IBitmap;
import com.sampas.socbs.core.tools.IProcessContext;


public class SmpLegend implements ILegend{

	private List<ILegendItem> legendItemList= new ArrayList<ILegendItem>(); 
	private String legendTitle ="";
	private String name ="";
	private boolean fixedAspectRatio = false;
	private boolean fixedSize = false;
	private List<IBitmap> icons = null;
	private IMap map = null;
	private MapItemTypesEnum type;
	
	public void addLegendItem(ILegendItem legendItem) {
	
		this.legendItemList.add(legendItem);
	
	}

	
	public ILegendItem getLegendItem(int index) {

		return this.legendItemList.get(index);
	}

	
	public int getLegendItemCount() {
		
		return this.legendItemList.size();
	}

	
	public String getLegendTitle() {
	
		return this.legendTitle;
	}

	
	public void insertLegendItem(int index, ILegendItem legendItem) {
		
		this.legendItemList.add(index,legendItem);
	}

	
	public void removeLegendItem(int index) {
		
		this.legendItemList.remove(index);
	}

	
	public void setLegendTitle(String title) {
		
		this.legendTitle = title;
	}

	
	public boolean IsFixedAspectRatio() {
	
		return this.fixedAspectRatio;
	}

	
	public boolean IsFixedSize() {
		
		return this.fixedSize;
	}

	
	public void afterRender(IDisplay display) {
		// TODO Auto-generated method stub
		
	}

	
	public void beforeRender(IDisplay display) {
		// TODO Auto-generated method stub
		
	}

	
	public void contentsChanged() {
		// TODO Auto-generated method stub
		
	}

	
	public void fitToBounds(IDisplay display, IEnvelope bounds, boolean changed) {
		
		if(changed){
			

		}
		
	}

	
	public IEnvelope getBounds(IDisplay display, IEnvelope oldBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<IBitmap> getIcons() {
	
		return this.icons;
	}

	
	public IMap getMap() {
		
		return this.map;
	}

	
	public String getName() {
	
		return this.name;
	}

	
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	
	public void render(IDisplay display, IProcessContext processContext,
			IEnvelope boudingBox) {
		// TODO Auto-generated method stub
		
	}

	
	public void setFixedAspectRatio(boolean fixed) {
		
		this.fixedAspectRatio = fixed;
		
	}

	
	public void setIcons(List<IBitmap> icons) {
		
		this.icons = icons;
		
	}

	
	public void setMap(IMap map) {
		
		this.map = map;
		
	}

	
	public void setName(String name) {
		
		this.name = name;
		
	}


	public MapItemTypesEnum getMapItemType() {

		return this.type;
	}


	public void setMapItemType(MapItemTypesEnum type) {
		this.type = type;
		
	}


	public void clearLegendItemList() {
		
		this.legendItemList.clear();
		
	}

}
