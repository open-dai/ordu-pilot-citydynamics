package com.sampas.socbs.core.symbology.impl;

import com.sampas.socbs.core.coordinatesystem.ITransformation;
import com.sampas.socbs.core.display.IDevice;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.symbology.ITextBackground;
import com.sampas.socbs.core.symbology.ITextSymbol;
import com.sampas.socbs.core.symbology.TextHorizontalAlignmentEnum;
import com.sampas.socbs.core.symbology.TextVerticalAlignmentEnum;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.IFont;


@SuppressWarnings("unused")
public class SmpTextSymbol implements ITextSymbol {

	private double angle = 0;
	private double size  = 0;
	private String text = "";
	private IColor color = null;
	private IFont font = null;
	private TextVerticalAlignmentEnum textVerticalAlignment = null;
	private TextHorizontalAlignmentEnum textHorizontalAlignment = null;
	private IDevice device = null;
	private ITransformation transformation = null;
	private ITextBackground textBackground = null;
	private boolean rightToLeft= false;
	
	public SmpTextSymbol(){
		
	}
	
	
	public double getAngle() {

		return this.angle;
	}

	
	public IColor getColor() {

		return this.color;
	}

	
	public IFont getFont() {

		return this.font;
	}

	
	public TextHorizontalAlignmentEnum getHorizontalAlignment() {

		return this.textHorizontalAlignment;
	}

	
	public double getSize() {
	
		return this.size;
	}

	
	public String getText() {

		return this.text;
	}

	
	public ITextBackground getTextBackground() {

		return this.textBackground;
	}

	
	public IDimension getTextSize(int IDevice, ITransformation transformation,
			String text) {
		// TODO Auto-generated method stub
		return null;

	}

	
	public TextVerticalAlignmentEnum getVerticalAlignment() {
		
		return this.textVerticalAlignment;
	}

	
	public boolean isRightToLeft() {

		return this.rightToLeft;
	}

	
	public void setAngle(double angle) {
		
		this.angle = angle;

	}

	
	public void setBackground(ITextBackground background) {
		
		this.textBackground = background;

	}

	
	public void setColor(IColor color) {
		
		this.color= color;

	}

	
	public void setFont(IFont fontDisp) {
		
		this.font = fontDisp;

	}

	
	public void setHorizontalAlignment(TextHorizontalAlignmentEnum horizAlignment) {
		
		this.textHorizontalAlignment = horizAlignment;

	}

	
	public void setRightToLeft(boolean rightToLeft) {
		
		this.rightToLeft = rightToLeft;

	}

	
	public void setSize(double size) {
		
		this.size = size;

	}

	
	public void setText(String text) {
	
		this.text = text;

	}

	
	public void setVerticalAlignment(TextVerticalAlignmentEnum vertAlignment) {
	
		this.textVerticalAlignment = vertAlignment;

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
		// TODO Auto-generated method stub
		
	}


	public void draw(int x0, int y0, int x1, int y1) {
		// TODO Auto-generated method stub
		
	}


	public void draw(int[] xpoints, int[] ypoints, int npoints) {
		// TODO Auto-generated method stub
		
	}

}
