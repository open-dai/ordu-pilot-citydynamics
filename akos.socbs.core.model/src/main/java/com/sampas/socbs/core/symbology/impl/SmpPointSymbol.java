package com.sampas.socbs.core.symbology.impl;

import java.awt.Color;
import com.sampas.socbs.core.coordinatesystem.ITransformation;
import com.sampas.socbs.core.display.IDevice;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.symbology.IPointSymbol;
import com.sampas.socbs.core.tools.IBitmap;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.IShape;
import com.sampas.socbs.core.tools.impl.SmpRGBColor;


@SuppressWarnings("unused")
public class SmpPointSymbol implements IPointSymbol {
	
	private IColor smpRGBColor;
	private double angle = 0;
	private float size;
	private double xOffset=6;
	private double yOffset=6;
	private IDevice device;
	private ITransformation transformation;
	private IBitmap icon;
	private IShape shape;
		
	public SmpPointSymbol(){
				
		this.smpRGBColor = new SmpRGBColor(0,0,0,0);
	}
	
	public void setIcon(IBitmap icon){
		
		this.icon = icon;
		this.xOffset	= this.icon.getBufferedImage().getWidth()/2.0;
		this.yOffset	= this.icon.getBufferedImage().getHeight()/2.0;
	}
	
	public double getAngle() {

		return this.angle;
	}

	
	public IColor getColor() {
		
		return this.smpRGBColor;
	}

	
	public float getSize() {
		
		return this.size;
	}

	
	public double getXOffset() {
		
		return this.xOffset;
	}

	
	public double getYOffset() {
		
		return this.yOffset;
	}

	
	public void setAngle(double angle) {
		
		this.angle = angle;
	}

	
	public void setColor(IColor color) {
		
		this.smpRGBColor = color;
	}

	
	public void setSize(float size) {
		
		this.size = size;

	}

	
	public void setXOffset(double offset) {

		this.xOffset = offset;

	}

	
	public void setYOffset(double offset) {

		this.yOffset = offset;

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
		
		System.out.println("Draw Point");
		
		//TODO: Symbol will be generated later (circle,cros.. etc.)
		
		if(this.icon !=null){
	
			this.device.getDevice().drawImage(this.icon.getBufferedImage(),null,
					(int)(x-(this.icon.getBufferedImage().getWidth()/2)),
					(int)(y-(this.icon.getBufferedImage().getHeight()/2)));
		
		}
	
		else{
		
		
		//Stroke oldStroke = this.device.getDevice().getStroke();
		
//		this.device.getDevice().setStroke(new BasicStroke(
//				this.size,   // Width
//                BasicStroke.CAP_SQUARE,   // End cap
//                BasicStroke.JOIN_MITER
//		));
		
		Color oldColor = this.device.getDevice().getColor();
		
		this.smpRGBColor =(this.smpRGBColor != null)?this.smpRGBColor: new SmpRGBColor(Color.RED);
		this.device.getDevice().setColor(((SmpRGBColor)this.smpRGBColor).getColor());
		
		//Star Symbol	
//	    shape.moveTo((float) (x-6),(float)(y));
//	    shape.lineTo((float)(x), (float)(y-6));
//	    shape.lineTo((float)(x+6),(float) (y));
//	    shape.lineTo((float)(x),(float)(y+6));
//	    shape.lineTo((float) (x-6),(float)(y));
//		this.device.getDevice().draw(shape);

		//Ellipse2D ellipse2D= new Ellipse2D.Double(x-(this.xOffset/2),y-(this.yOffset/2),this.xOffset,this.yOffset);
		//this.device.getDevice().draw(ellipse2D);
		
//		this.device.getDevice().draw3DRect((int)( x-(this.xOffset/2)+0.5), 
//				(int)(y-(this.yOffset/2)+0.5),
//				(int)this.xOffset,
//				(int)this.yOffset, true);
		this.device.getDevice().fillRect((int)( x-(this.xOffset/2)+0.5), 
											(int)(y-(this.yOffset/2)+0.5),
											(int)this.xOffset,
											(int)this.yOffset);
		
		
//		this.device.getDevice().drawRect((int)( x-(this.xOffset/2)+0.5), 
//									(int)(y-(this.yOffset/2)+0.5),
//									(int)this.xOffset,
//									(int)this.yOffset);
//		this.device.getDevice().drawRoundRect((int)( x-(this.xOffset/2)+0.5), 
//				(int)(y-(this.yOffset/2)+0.5),
//				(int)this.xOffset,
//				(int)this.yOffset,5,5);
		
	    this.device.getDevice().setColor(oldColor);
	   // this.device.getDevice().setStroke(oldStroke);
	    
		}
		
	}

	public void draw(int x0, int y0, int x1, int y1) {
		// TODO Auto-generated method stub
		
	}


	public void draw(int[] xpoints, int[] ypoints, int npoints) {
		// TODO Auto-generated method stub
		
	}

	public void setShape(IShape shape) {
		this.shape = shape;
	}

	public IShape getShape() {
		return shape;
	}
	
}
