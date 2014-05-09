package com.sampas.socbs.core.symbology;

import com.sampas.socbs.core.coordinatesystem.ITransformation;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.tools.IColor;
import com.sampas.socbs.core.tools.IFont;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:45
 */
public interface ITextSymbol extends ISymbol {


	public ITextBackground getTextBackground();

	/**
	 * 
	 * @param background
	 */
	public void setBackground(ITextBackground background);
	/*
	 *      Text baseline angle.
	 */
	public double getAngle();
	/*
	 *      Text color.
	 */
	public IColor getColor();
	/*
	 * 		Text font.
	 */
	public IFont getFont();
	/*
	 *      Horizontal alignment style.
	 */
    public TextHorizontalAlignmentEnum getHorizontalAlignment();
    /*
     *      Text size.
     */
    public double getSize();
    /*
     *      Text to draw.
     */
    public String 	getText();
    /*
     * 		Gets the x and y dimensions of 'text' in points (1/72 inch).
     */
    public IDimension getTextSize(int IDevice, ITransformation transformation, String text);
    /*
     * 		Vertical alignment style.
     */
    public TextVerticalAlignmentEnum getVerticalAlignment();
    /*
     * 		Indicates if the text is drawn from right to left.
     */    
    public boolean isRightToLeft();
    /*
     *      Text baseline angle.
     */ 
    public void setAngle(double angle);
    /*
     *      Text color.
     */
    public void setColor(IColor color);
	/*
	 *      Text font.
	 */
    public void setFont(IFont fontDisp);
    /*
     *      Horizontal alignment style.
     */
    public void setHorizontalAlignment(TextHorizontalAlignmentEnum horizAlignment);
    /*
     * 		Indicates if the text is drawn from right to left.
     */
    public void setRightToLeft(boolean rightToLeft);
    /*
     * 		Text size.
     */
     public void setSize(double size);
     /*
      * 	Text to draw.
      */
     public void setText(String text);
     /*
      *      Vertical alignment style.
      */
     public void setVerticalAlignment(TextVerticalAlignmentEnum vertAlignment);

	
	
	


}