package com.sampas.socbs.core.tools.impl;

import java.awt.Color;

import com.sampas.socbs.core.tools.IColor;

public class SmpRGBColor implements IColor {
	
	private Color color;
	
	public SmpRGBColor(){
		

	}
	
	public SmpRGBColor(int r, int g, int b, int a){
		
		this.color=new Color(r,g,b,a);
		
	}

	
	public SmpRGBColor(Color color) {
		
		this.color = color; 
	}

	public int getA() {

		return this.color.getAlpha();
	}

	
	public int getB() {
		
		return this.color.getBlue();
	}

	
	public int getG() {
	
		return this.color.getGreen();
	}

	
	public int getR() {
		
		return this.color.getRed();
	}

	
	public void setARGB(int a, int r, int g, int b) {
		
		this.color=new Color(r,g,b,a);
	}
	
	public Color getColor(){
		
		return this.color;
	}

}
