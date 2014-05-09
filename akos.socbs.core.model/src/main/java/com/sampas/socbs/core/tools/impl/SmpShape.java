package com.sampas.socbs.core.tools.impl;

import java.awt.Shape;
import java.awt.geom.GeneralPath;

import com.sampas.socbs.core.tools.IShape;

public class SmpShape implements IShape {
	
	private float x;
	private float y;
	private float offSet;
	private GeneralPath shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);

	public Shape circle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Shape cross() {
		// TODO Auto-generated method stub
		return null;
	}

	public Shape diagonal() {
		
		//Diagonal Symbol	
	    this.shape.moveTo( (this.x-this.offSet),(this.y));
	    this.shape.lineTo((this.x), (this.y-this.offSet));
	    this.shape.lineTo((this.x+this.offSet), (this.y));
	    this.shape.lineTo((this.x),(this.y+this.offSet));
	    this.shape.lineTo( (this.x-this.offSet),(this.y));
	
		return this.shape;
	}

	public Shape plus() {
		// TODO Auto-generated method stub
		return null;
	}

	public int setOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Shape square() {
		
		//Square Pattern
	    this.shape.moveTo((this.x-this.offSet),(this.y-this.offSet));
	    this.shape.lineTo((this.x+this.offSet),(this.y-this.offSet));
	    this.shape.lineTo((this.x+this.offSet),(this.y+this.offSet));
	    this.shape.lineTo((this.x-this.offSet),(this.y+this.offSet));
	    this.shape.lineTo((this.x-this.offSet),(this.y-this.offSet));
	    
		return this.shape;
	}

	public Shape star() {
		// TODO Auto-generated method stub
		return null;
	}

}
