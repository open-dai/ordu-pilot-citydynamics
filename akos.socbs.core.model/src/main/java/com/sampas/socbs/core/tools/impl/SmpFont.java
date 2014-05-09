package com.sampas.socbs.core.tools.impl;

import com.sampas.socbs.core.tools.IFont;

public class SmpFont implements IFont {
	
	private short charset = 0;
	private int size= 0;
	private short weight = 0; 
	private String name = "";
	private boolean bold= false;
	private boolean equal = false;
	private boolean italic= false;
	private boolean strikeThrough = false;
	private boolean underline= false;
	
	
	
	public SmpFont(){
		
	}

	
	public short getCharset() {
		
		return this.charset;
	}

	
	public String getName() {
		
		return this.name;
	}

	
	public int getSize() {
		
		return this.size;
	}

	
	public short getWeight() {
		
		return this.weight;
	}

	
	public boolean isBold() {
	
		return this.bold;
	}

	
	public boolean isEqual(IFont pfontOther) {
	
		return this.equal;
	}

	
	public boolean isItalic() {

		return this.italic;
	}

	
	public boolean isStrikeThrough() {
		
		return this.strikeThrough;
	}

	
	public boolean isUnderline() {

		return this.underline;
	}

	
	public void setBold(boolean pbold) {
		
		this.bold = pbold;

	}

	
	public void setCharset(short pcharset) {
		
		this.charset = pcharset;

	}

	
	public void setItalic(boolean pitalic) {
	
		this.italic = pitalic;

	}

	
	public void setName(String pname) {
		
		this.name = pname;

	}


	
	public void setSize(int psize) {
		
		this.size = psize;

	}

	
	public void setStrikeThrough(boolean pstrikethrough) {
		
		this.strikeThrough = pstrikethrough;

	}

	
	public void setUnderline(boolean punderline) {
		
		this.underline = punderline;

	}

	
	public void setWeight(short pweight) {
		
		this.weight = pweight;

	}

}
