package com.sampas.socbs.core.symbology;

import com.sampas.socbs.core.tools.IBitmap;
import com.sampas.socbs.core.tools.IColor;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:44
 */
public interface IPointSymbol extends ISymbol {
		
	/*
	 *      Marker symbol angle.
	 */
	public double getAngle();
	/*
	 *      Marker symbol color.
	 */
	public IColor getColor();
	/*
	 *  	Marker symbol size.
	 */
	public float getSize();
	/*
	 * Symbol X-axis offset from point location.
	 */
    public double getXOffset();
    /*
     * Symbol Y-axis offset from point location.
     */
    public double getYOffset();
    /*
     * Point(Marker) symbol angle.
     */
    public void setAngle(double angle);
    /*
     * Point(Marker) symbol color.
     */
    public void setColor(IColor color);
    /*
     * Point(Marker) symbol size.
     */
    public void setSize(float size);
    /*
     *  Symbol X-axis offset from point location.
     */    
    public void setXOffset(double xOffset);
    /*
     *  Symbol Y-axis offset from point location.
     */  
    public void setYOffset(double yOffset);
    
    public void setIcon(IBitmap icon);
    
}