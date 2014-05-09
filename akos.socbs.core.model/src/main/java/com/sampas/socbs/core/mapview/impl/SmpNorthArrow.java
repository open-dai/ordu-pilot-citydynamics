package com.sampas.socbs.core.mapview.impl;

import java.util.List;

import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.impl.MapItemTypesEnum;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.maplayer.IMap;
import com.sampas.socbs.core.mapview.IMapNorthArrow;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IBitmap;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.IProcessContext;


public class SmpNorthArrow implements IMapNorthArrow {

	private List<IBitmap> icons;
	private double size;
	private IMap map;
	private String name;
	private MapItemTypesEnum type;
	
	
	public void setIcons(List<IBitmap> icons) {

		this.icons = icons;
	}

	public void setMap(IMap map) {
		
		this.map = map;
	}

	public void setName(String name) {

		this.name = name;
	}
	public double getSize() {
		
		return this.size;
	}

	public void setSize(double size) {
		
		this.size = size;
		
	}
	public List<IBitmap> getIcons() {
		// TODO Auto-generated method stub
		return this.icons;
	}

	public IMap getMap() {
		// TODO Auto-generated method stub
		return this.map;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void getAngle(double angle) {
		// TODO Auto-generated method stub

	}

	public IColor getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public IPoint getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public ISymbol getNorthSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setColor(IColor color) {
		// TODO Auto-generated method stub

	}

	public void setLocation(IPoint point) {
		// TODO Auto-generated method stub

	}

	public void setNorthSymbol(ISymbol northSymbol) {
		// TODO Auto-generated method stub

	}


	public boolean IsFixedAspectRatio() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean IsFixedSize() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub

	}

	public IEnvelope getBounds(IDisplay display, IEnvelope oldBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	public void refresh() {
		// TODO Auto-generated method stub

	}

	public void render(IDisplay display, IProcessContext processContext,
			IEnvelope boudingBox) {
		// TODO Auto-generated method stub

	}

	public void setFixedAspectRatio(boolean fixed) {
		// TODO Auto-generated method stub

	}

	public MapItemTypesEnum getMapItemType() {

		return this.type;
	}

	public void setMapItemType(MapItemTypesEnum type) {
		
		this.type= type;
	}

}
