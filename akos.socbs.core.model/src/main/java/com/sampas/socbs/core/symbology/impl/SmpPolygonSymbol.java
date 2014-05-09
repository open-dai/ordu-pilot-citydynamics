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
import com.sampas.socbs.core.symbology.IPolygonSymbol;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.impl.SmpRGBColor;

public class SmpPolygonSymbol implements IPolygonSymbol {
	
	private IColor smpRGBColor = null;
	private ILineSymbol lineSymbol = new SmpLineSymbol();
	private IDevice device = null; 
	private ITransformation transformation =null;
	
	
	public IColor getColor() {
		
		return this.smpRGBColor;
	}


	
	public ILineSymbol getOutline() {
	
		return this.lineSymbol;
	}

	
	public void setColor(IColor color) {
		
		this.smpRGBColor = color;

	}

	
	public void setOutline(ILineSymbol lineSymbol) {
		
		this.lineSymbol = lineSymbol;

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
			
	//	SmpRGBColor color = (SmpRGBColor) this.lineSymbol.getColor();		
	//	this.device.getDevice().setColor(color.getColor());
	
	}

	public IDevice getDevice(){
		
		return this.device;
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



	public void draw(double x, double y) {
		
		if(this.lineSymbol !=null){
			if(this.lineSymbol.getPointSymbol() !=null){
				
				this.lineSymbol.getPointSymbol().setDevice(this.device, this.transformation);					
				this.lineSymbol.getPointSymbol().draw(x, y);
			}
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
		// TODO Auto-generated method stub
		if(this.lineSymbol !=null){
			
			this.lineSymbol.setDevice(this.device, this.transformation);
			this.lineSymbol.draw(x0, y0, x1, y1);
		}
		else{
			this.device.getDevice().drawLine(x0, y0, x1, y1);
		}
	}

	public void draw(int[] xPoints, int[] yPoints, int nPoints) {
		// Fill in polygon
		SmpRGBColor color = (SmpRGBColor) this.smpRGBColor;	
		this.device.getDevice().setColor(color.getColor());
		this.device.getDevice().fillPolygon(xPoints,yPoints,nPoints);
			
		//Draw polygon surface
		color = (SmpRGBColor) this.lineSymbol.getColor();
		this.device.getDevice().setColor(color.getColor());
		
		Stroke s =null;
		if(this.lineSymbol.isUseDash()){
			s = new BasicStroke(this.lineSymbol.getWidth(),       //Line Width
	                BasicStroke.CAP_SQUARE,      // End cap
	                BasicStroke.JOIN_MITER,     // Join style
	                10.0f,                     // Miter limit
	                this.lineSymbol.getDashline(),			 // Dash pattern
	                0.0f);
		}
		else{
			s =  new BasicStroke(this.lineSymbol.getWidth(),   // Line Width
	                BasicStroke.CAP_SQUARE,   // End cap
	                BasicStroke.JOIN_MITER);
		}
		
		this.device.getDevice().setStroke(s);
		this.device.getDevice().drawPolygon(xPoints, yPoints, nPoints);
	}

}
