package com.sampas.socbs.core.tools;

import java.awt.Shape;

public interface IShape {

	public Shape square(); 
	
	public Shape star();
	
	public Shape cross();

	public Shape plus();
	
	public Shape circle();
	
	public Shape diagonal();

	public int setX();
	
	public int setY();
	
	public int setOffset();
	
}
