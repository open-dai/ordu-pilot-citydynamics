package com.sampas.socbs.core.mapview.impl;

import java.util.List;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.impl.MapItemTypesEnum;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.maplayer.ILayer;
import com.sampas.socbs.core.maplayer.IMap;
import com.sampas.socbs.core.maplayer.impl.SmpMap;
import com.sampas.socbs.core.mapview.IMapOverview;
import com.sampas.socbs.core.symbology.IFillSymbol;
import com.sampas.socbs.core.symbology.ITextSymbol;
import com.sampas.socbs.core.tools.IBitmap;
import com.sampas.socbs.core.tools.IProcessContext;


@SuppressWarnings("unused")
public class SmpMapOverview implements IMapOverview {

	private IMap map;
	private List<ILayer> layerList;
	private String name;
	private MapItemTypesEnum type;
	private List<IBitmap> icons;
	private boolean fixed;
	private IMap overviewMap;
	private int width;
	private int height;
	private IDisplay display;

	public SmpMapOverview(IMap map) {

		this.map = map;

	}
	public IFillSymbol getAOIFillSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	public void getOverlayGridCell(int gridLayerField) {
		// TODO Auto-generated method stub

	}

	public ITextSymbol getOverlayGridLabelSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	public ILayer getOverlayGridLayer() {

		return null;
	}

	public void setAOIFillSymbol(IFillSymbol aoiFillSymbol) {
		// TODO Auto-generated method stub

	}

	public void setOverlayGridLabelSymbol(ITextSymbol overlayGridLabelSymbol) {
		// TODO Auto-generated method stub

	}

	public void setOverlayGridLayer(ILayer overlayGridLayer) {
		// TODO Auto-generated method stub

	}

	public void updateDisplay(int width, int height) {
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

		return null;
	}

	public List<IBitmap> getIcons() {

		return this.icons;
	}

	public IMap getMap() {

		return this.map;
	}

	public MapItemTypesEnum getMapItemType() {

		return this.type;
	}

	public String getName() {

		return this.name;
	}

	public void refresh() {


	}

	public void render(IDisplay display, IProcessContext processContext,
			IEnvelope boudingBox) {


		if(overviewMap == null){
			overviewMap = new SmpMap();
			overviewMap.setCoordinateSystem(getMap().getCoordinateSystem());
			overviewMap.setDisplay(display);
			for (int i = 0; i < getLayerList().size(); i++) {
				getLayerList().get(i).setVisible(true);
				overviewMap.addLayer(getLayerList().get(i));
			}
		}

		overviewMap.render(null, null);
	}

	public void setFixedAspectRatio(boolean fixed) {

		this.fixed = fixed;

	}

	public void setIcons(List<IBitmap> icons) {

		this.icons = icons;

	}

	public void setMap(IMap map) {
		this.map = map;

	}

	public void setMapItemType(MapItemTypesEnum type) {
		this.type = type;

	}

	public void setName(String name) {
		this.name=name;
	}

	public void setLayerList(List<ILayer> layerList) {
		this.layerList = layerList;
	}

	public List<ILayer> getLayerList() {
		return layerList;
	}
	public IMap getOverviewMap() {
		return overviewMap;
	}
	public void setOverviewMap(IMap overviewMap) {
		this.overviewMap = overviewMap;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getWidth() {
		return width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}
	public void setDisplay(IDisplay display) {	
		this.display = display; 	
	}
	public IDisplay getDisplay() {	
		return this.display; 	
	}

}
