package com.sampas.socbs.core.display.impl;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sampas.socbs.core.display.IDevice;

public class SmpDevice implements IDevice {
	
	private Graphics2D device;

	public SmpDevice(){
			
	}
		
	public SmpDevice(Graphics2D device){
		
		   this.device = device;
		   this.device.setColor(Color.BLACK);
//		   this.device.setColor(Color.white);
//		   this.device.fillRect((int)deviceFrame.getMinX(),
//		    					(int)deviceFrame.getMinY(),
//		    					(int)deviceFrame.getWidth(),
//		    					(int)deviceFrame.getHeight());	 		   
//		    // Options on the rendering, i.e. the actual pixel
//		    // settings of the drawing, can be chosen via
//		    // RenderingHints.
//		   this.device.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//		    	    	        RenderingHints.VALUE_ANTIALIAS_ON); 
	}

	public Graphics2D getDevice() {

		return this.device;
	}

	public void setDevice(Graphics2D device) {

		 this.device = device;
		
	}

}
