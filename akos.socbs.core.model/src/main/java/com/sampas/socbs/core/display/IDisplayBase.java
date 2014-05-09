package com.sampas.socbs.core.display;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.symbology.ISymbol;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:41
 */
public interface IDisplayBase {

	/**
	 * 
	 * @param point
	 */
	public void drawPoint(ICoordinate[] point);

	/**
	 * 
	 * @param polygon
	 */
	public void drawPolygon(ICoordinate[] polygon);

	public void drawLine(ICoordinate[] line);
	/**
	 * 
	 * @param polyline
	 */
	public void drawPolyline(ICoordinate[] polyline);

	/**
	 * 
	 * @param rectangle
	 */
	public void drawRectangle(IEnvelope rectangle);

	/**
	 * 
	 * @param baseLine
	 * @param text
	 */
	public void drawText(IGeometry baseLine, String text);

	public void finishDrawing();

	public IEnvelope getEnvelope();
	
	public IEnvelope getClipEnvelope();
	
	public IGeometry getClipGeometry();

	public IDisplayTransformation getDisplayTransformation();

	/**
	 * 
	 * @param displayTransformation
	 */
	public void setDisplayTransformation(IDisplayTransformation displayTransformation);

	/**
	 * 
	 * @param symbol
	 */
	public void setSymbol(ISymbol symbol);

	/**
	 * 
	 * @param device
	 * @param cacheID
	 */
	public void startDrawing(IDevice device, int cacheID);

}