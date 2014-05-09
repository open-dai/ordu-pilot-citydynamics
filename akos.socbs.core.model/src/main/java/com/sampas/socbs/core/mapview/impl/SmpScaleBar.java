package com.sampas.socbs.core.mapview.impl;

import java.util.List;

import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.impl.MapItemTypesEnum;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.maplayer.IMap;
import com.sampas.socbs.core.mapview.IScaleBar;
import com.sampas.socbs.core.symbology.ITextSymbol;
import com.sampas.socbs.core.tools.IBitmap;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.IProcessContext;
import com.sampas.socbs.core.tools.impl.UnitTypesEnum;

public class SmpScaleBar implements IScaleBar {

	private int scaleBarWidth;
	private int scaleBarHeight;
	private List<IBitmap> icons;
	private IMap map;
	private String name;
	private double division;
	private int divisionCount;
	private int divisionCountBeforeZero;
	private IColor scaleBarColor;
	private ITextSymbol scaleBarLabelSymbol;
	private ScaleBarLabelFrequencyEnum scaleBarLabelFrequency;
	private MapItemTypesEnum type;
	

	public int getDivisionCount() {
		// TODO Auto-generated method stub
		return divisionCount;
	}

	public int getDivisionCountBeforeZero() {
		// TODO Auto-generated method stub
		return this.divisionCountBeforeZero;
	}

	public IColor getScaleBarColor() {
		// TODO Auto-generated method stub
		return this.scaleBarColor;
	}

	public int getScaleBarHeight() {
		// TODO Auto-generated method stub
		return this.scaleBarHeight;
	}

	public ScaleBarLabelFrequencyEnum getScaleBarLabelFrequency() {
		// TODO Auto-generated method stub
		return this.scaleBarLabelFrequency;
	}

	public ITextSymbol getScaleBarLabelSymbol() {
		// TODO Auto-generated method stub
		return this.scaleBarLabelSymbol;
	}

	public int getScaleBarSubdivisionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getScaleBarUnitLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	public ScaleBarUnitLabelPositionsEnum getScaleBarUnitLabelPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	public ITextSymbol getScaleBarUnitLabelSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	public UnitTypesEnum getScaleBarUnits() {
		// TODO Auto-generated method stub
		return null;
	}

	public ScaleBarVerticalPositionsEnum getScaleBarVerticalPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getScaleBarWidth() {
		// TODO Auto-generated method stub
		return this.scaleBarWidth;
	}

	public void setDivision(double division) {
		this.division = division;

	}

	public void setDivisionCount(int count) {
		this.divisionCount = count;

	}

	public void setDivisionCountBeforeZero(int count) {
		this.divisionCountBeforeZero = count;

	}

	public void setScaleBarColor(IColor color) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarHeight(int height) {
		
		this.scaleBarHeight = height;
	}

	public void setScaleBarLabelFrequency(ScaleBarLabelFrequencyEnum frequency) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarLabelGap(double gap) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarLabelSymbol(ITextSymbol symbol) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarSubdivisionCount(int count) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarUnitLabel(String label) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarUnitLabelGap(double gap) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarUnitLabelPosition(
			ScaleBarUnitLabelPositionsEnum position) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarUnitLabelSymbol(ITextSymbol symbol) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarUnits(UnitTypesEnum units) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarVerticalPosition(
			ScaleBarVerticalPositionsEnum position) {
		// TODO Auto-generated method stub

	}

	public void setScaleBarWidth(int scaleBarWidth) {
		
		this.scaleBarWidth = scaleBarWidth;
	}

	public void useMapSettings() {
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

	public void setIcons(List<IBitmap> icons) {
		
		this.icons = icons;
	}

	public void setMap(IMap map) {

		this.map = map;

	}

	public void setName(String name) {
		
		this.name= name;
	}

	public boolean isDivision() {
		// TODO Auto-generated method stub
		return (this.division==0)?false:true;
	}

	public boolean isScaleBarLabelGap() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isScaleBarUnitLabelGap() {
		// TODO Auto-generated method stub
		return false;
	}

	public MapItemTypesEnum getMapItemType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	public void setMapItemType(MapItemTypesEnum type) {
		this.type = type;
		
	}

}
