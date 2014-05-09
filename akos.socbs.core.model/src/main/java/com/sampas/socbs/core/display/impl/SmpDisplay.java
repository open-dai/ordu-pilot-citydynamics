package com.sampas.socbs.core.display.impl;

import java.awt.Color;

import com.sampas.socbs.core.display.IDevice;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.IDisplayEventsDisplayFinishedEvent;
import com.sampas.socbs.core.display.IDisplayEventsDisplayInvalidatedEvent;
import com.sampas.socbs.core.display.IDisplayEventsDisplayStartedEvent;
import com.sampas.socbs.core.display.IDisplayTransformation;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.symbology.ISymbol;

public class SmpDisplay implements IDisplay{

	private SmpDisplayTransformation displayTransformation = new SmpDisplayTransformation();
	protected IEnvelope envelope;
	private IDevice device;
	private ISymbol symbol;
	
	
	public SmpDisplay(IEnvelope envelope, IDisplayTransformation displayTransformation){
		
			this.envelope = envelope;
			this.displayTransformation = (SmpDisplayTransformation) displayTransformation;
			this.displayTransformation.setDeviceFrame(envelope);
			
	}

	public void displayFinished(IDisplayEventsDisplayFinishedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void displayInvalidated(IDisplayEventsDisplayInvalidatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void displayStarted(IDisplayEventsDisplayStartedEvent event) {

		this.symbol.setDevice(this.device,null);		
	}

	
	public void drawPoint(ICoordinate[] point) {			
		
			
		for(int i=0;i<point.length;i++){
			
			double x =  this.displayTransformation.fromMapCSToDisplayCS(point[i]).getX();
			double y =  this.envelope.getMaxY()-this.displayTransformation.fromMapCSToDisplayCS(point[i]).getY();
			
			this.symbol.draw(x,y);
		}
		
	}

	public void drawLine(ICoordinate[] line) {
		
		int x0,x1,y0,y1;
		
	
		for(int i=0; i<line.length-1;i++){
			
			
			x0	=  (int)
					(this.displayTransformation.fromMapCSToDisplayCS(line[i]).getX()+0.5); // 0.5 is round value
		
			x1	=  (int)
			(this.displayTransformation.fromMapCSToDisplayCS(line[i+1]).getX()+0.5); // 0.5 is round value
			
			y0	= (int)
			((this.envelope.getMaxY()-this.displayTransformation.fromMapCSToDisplayCS(line[i]).getY())+0.5); // 0.5 is round value
		
			y1	=  (int)
			((this.envelope.getMaxY()-this.displayTransformation.fromMapCSToDisplayCS(line[i+1]).getY())+0.5); // 0.5 is round value
			
			this.symbol.draw(x0,y0,x1,y1);
		}
				
	}
	
	public void drawPolygon(ICoordinate[] polygon) {
		
	    int xpoints[] =new int[polygon.length];// {25, 145, 25, 145, 25};
	    int ypoints[] =new int[polygon.length]; // {25, 25, 145, 145, 25};
	    int npoints = polygon.length;
	    
	    
	    for(int i=0;i<polygon.length;i++){

	    	xpoints[i] 	= (int) this.displayTransformation.fromMapCSToDisplayCS(polygon[i]).getX();
	    	ypoints[i] 	= (int) (this.envelope.getMaxY()-this.displayTransformation.fromMapCSToDisplayCS(polygon[i]).getY());
	    }	   

	    this.symbol.draw(xpoints, ypoints, npoints);		
	}

	
	public void drawPolyline(ICoordinate[] polyline) {
		// TODO Auto-generated method stub
		
	}

	
	public void drawRectangle(IEnvelope rectangle) {
		// TODO Auto-generated method stub
		
	}

	
	public void drawText(IGeometry baseLine, String text) {
		// TODO Auto-generated method stub
		
	}

	
	public void finishDrawing() {
		// TODO Auto-generated method stub
		
	}

	
	public IEnvelope getClipEnvelope() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IGeometry getClipGeometry() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IDisplayTransformation getDisplayTransformation() {
			
		return (this.displayTransformation);
		
	}

	
	public void setSymbol(ISymbol symbol) {
		
		this.symbol =symbol;
		this.symbol.setDevice(this.device, this.displayTransformation);
		
	}

	
	public void startDrawing(IDevice device, int cacheID) {
		// TODO Auto-generated method stub
		
	}

	
	public void setDisplayTransformation( IDisplayTransformation displayTransformation) {
		
		this.displayTransformation = (SmpDisplayTransformation) displayTransformation;
		
	}
	
	public IDevice getDevice() {
		
		return this.device;
	}

	
	public void setDevice(IDevice device) {
		
		this.device = device;
	}

	public IEnvelope getEnvelope() {
		// TODO Auto-generated method stub
		return this.envelope;
	}

	public void clearDevice() {
		
		this.device.getDevice().setColor(Color.WHITE);
		this.device.getDevice().fillRect(0, 0, (int) this.envelope.getWidth(), (int)this.envelope.getHeight());
		this.device.getDevice().setColor(Color.BLACK);
	
	}

}
