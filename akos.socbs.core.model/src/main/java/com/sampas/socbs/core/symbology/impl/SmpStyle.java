package com.sampas.socbs.core.symbology.impl;

import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.styling.StyleFactoryImpl;

import com.sampas.socbs.core.coordinatesystem.ITransformation;
import com.sampas.socbs.core.display.IDevice;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.symbology.IStyle;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IColor;

public class SmpStyle implements ISymbol, IStyle {

	private Style geoToolsStyle = null;
	private IDevice device = null;
	
	public SmpStyle() {
			
	}
	
	public SmpStyle(Style geoToolsStyle) {
		
		this.geoToolsStyle = geoToolsStyle;
				
	}
	
	public SmpStyle(String styleName) {
		
		StyleFactory styleFactory = new StyleFactoryImpl();
		styleFactory.createStyle();
		this.geoToolsStyle= styleFactory.createStyle();
		geoToolsStyle.setName(styleName);				
	}
	
	public String getName() {
		
		return (this.geoToolsStyle.getName());
	}
	
	public void setName(String name) {
		
		this.geoToolsStyle.setName(name);
	}

	public String getTitle() {
		
		return (this.geoToolsStyle.getTitle());
	}

	public void setTitle(String title) {
		
		this.geoToolsStyle.setTitle(title);
	}

	public String getAbstract() {
		
		return (this.geoToolsStyle.getAbstract());
	}

	public void setAbstract(String abstractStr) {
		
		this.geoToolsStyle.setAbstract(abstractStr);
	}

	public boolean isDefault() {
		
		return (this.geoToolsStyle.isDefault());
	}

	public void setDefault(boolean isDefault) {
	 
		this.geoToolsStyle.setDefault(isDefault);
	}

	
	public IEnvelope getBoundary(IDevice device, ITransformation transformation,
			IGeometry geometry) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void removeDevice() {
		// TODO Auto-generated method stub
		
	}

	
	public void setDevice(IDevice device, ITransformation transformation) {
		// TODO Auto-generated method stub
		
	}
	
	public IDevice getDevice(){
		
		return this.device ;
	}
	
	public void draw(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	public void draw(int x0, int y0, int x1, int y1) {
		// TODO Auto-generated method stub
		
	}

	public void draw(int[] xpoints, int[] ypoints, int npoints) {
		// TODO Auto-generated method stub
		
	}

	public IColor getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public float[] getDashline() {
		// TODO Auto-generated method stub
		return null;
	}

	public float getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isUseDash() {
		// TODO Auto-generated method stub
		return false;
	}


//	public FeatureTypeStyle[] getFeatureTypeStyles() {
//		
//		return (this.geoToolsStyle.getFeatureTypeStyles());
//	}
//
//	public void setFeatureTypeStyles(FeatureTypeStyle[] types) {
//		
//		this.geoToolsStyle.setFeatureTypeStyles(types);
//	}
//
//	public void addFeatureTypeStyle(FeatureTypeStyle type) {
//		
//		this.geoToolsStyle.addFeatureTypeStyle(type);
//	}
//
//	public void accept(StyleVisitor visitor) {
//		
//		this.geoToolsStyle.accept(visitor);
//	}
	
	
}
