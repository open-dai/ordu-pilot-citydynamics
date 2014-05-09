package com.sampas.socbs.core.tools;

public interface IFont {
	           
	 public short getCharset();	          
	           
	 public String getName();
	           
	 public int getSize();
	           
	 public short getWeight();
	           
	 public boolean isBold();
	           
	 public boolean isEqual(IFont pfontOther);
	           
	 public boolean isItalic();
	           
	 public boolean isStrikeThrough();
	           
	 public boolean isUnderline();	        
	           
	 public void setBold(boolean pbold);
	           
	 public void setCharset(short pcharset);
	           
	 public void setItalic(boolean pitalic);
	           
	 public void setName( String pname);
	           
	 public void setSize(int psize);
	           
	 public void setStrikeThrough(boolean pstrikethrough);
	           
	 public void setUnderline(boolean punderline);
	           
	 public void setWeight(short pweight);

}
