package com.sampas.socbs.core.symbology.impl;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

import com.sampas.socbs.core.coordinatesystem.ITransformation;
import com.sampas.socbs.core.display.IDevice;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.symbology.ILineSymbol;
import com.sampas.socbs.core.symbology.IPointSymbol;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.impl.SmpRGBColor;

public class SmpLineSymbol implements ILineSymbol {
	
	private IColor color;
	private ITransformation transformation;
	private IDevice device;
	private float width; 			// line width
	private float[] dashline =null;		// if line is dash, set dash phase
	private boolean useDash =false; 
	private Stroke s =null;
	private IPointSymbol pointSymbol;

	public SmpLineSymbol(){
		
	}
	
	public IEnvelope getBoundary(IDevice device,
			ITransformation transformation, IGeometry geometry) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void removeDevice() {

		this.device = null;
		this.transformation = null;

	}

	
	public void setDevice(IDevice device, ITransformation transformation) {
		
		this.device = device;
		this.transformation = transformation;
		
		//Set Color To Device
		SmpRGBColor smpRGBColor = (SmpRGBColor)this.color;	
		this.device.getDevice().setColor(smpRGBColor.getColor());	
		
		//Set Stroke To Device -Line Width,stroke etc. -
		this.device.getDevice().setStroke(this.s);
		
	
	}
	
	public IDevice getDevice(){
		
		return this.device;
	}

	
	public IColor getColor() {

		return this.color;
	}

	
	public float getWidth() {

		return this.width;
	}

	
	public void setPointSymbol(IPointSymbol pointSymbol) {
		this.pointSymbol = pointSymbol;
	}

	public IPointSymbol getPointSymbol() {
		return pointSymbol;
	}

	public void setColor(IColor color) {
		
		this.color = color;

		
	}

	
	public void setWidth(float width) {
		
		this.width = width;
		
		this.s =  new BasicStroke(width,   // Width
                BasicStroke.CAP_SQUARE,   // End cap
                BasicStroke.JOIN_MITER);
		
	}

	public void setDashline(float[] dashline) {
		
		this.dashline = dashline;
		this.useDash = true;
		this.s = new BasicStroke(this.width,       // Width
                BasicStroke.CAP_SQUARE,      // End cap
                BasicStroke.JOIN_MITER,     // Join style
                10.0f,                     // Miter limit
                dashline,			 // Dash pattern
                0.0f);
	}

	public float[] getDashline() {
		return this.dashline;
	}

	public boolean isUseDash() {
		return this.useDash;
	}

	public void draw(double x, double y) {
		
		if(this.pointSymbol !=null){
			this.pointSymbol.setDevice(this.device, this.transformation);
			this.pointSymbol.draw(x, y);
		}
		else{
			Color oldColor = this.device.getDevice().getColor();			
			this.device.getDevice().setColor(Color.GREEN);
			Ellipse2D ellipse2D= new Ellipse2D.Double(x,y,5,5);
			this.device.getDevice().draw(ellipse2D);
			this.device.getDevice().setColor(oldColor);
		}
	}


	public void draw(int x0, int y0, int x1, int y1) {
		
		this.device.getDevice().drawLine(x0, y0, x1, y1);
		
	}

	public void draw(int[] xpoints, int[] ypoints, int npoints) {
		
		this.device.getDevice().drawPolygon(xpoints,ypoints,npoints);
		
	}
	
}
