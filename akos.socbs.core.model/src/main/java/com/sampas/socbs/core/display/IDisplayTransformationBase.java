package com.sampas.socbs.core.display;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.tools.impl.UnitTypesEnum;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:41
 */
public interface IDisplayTransformationBase {

	/**
	 * 
	 * @param displayPoint
	 */
	public ICoordinate fromDisplayCSToMapCS(ICoordinate displayPoint);

	/**
	 * 
	 * @param mapPoint
	 */
	public ICoordinate fromMapCSToDisplayCS(ICoordinate mapPoint);

	public IEnvelope getBounds();

	public IEnvelope getConstrainedBounds();

	public ICoordinateSystem getCoordinateSystem();

	public IEnvelope getDeviceFrame();

	public IEnvelope getFittedBounds();

	public double getReferenceScale();

	public double getResolution();

	public double getRotation();

	public double getScaleRatio();

	public UnitTypesEnum getUnits();

	public IEnvelope getVisibleBounds();

	public boolean IsZoomResolution();

	/**
	 * 
	 * @param bounds
	 */
	public void setBounds(IEnvelope bounds);

	/**
	 * 
	 * @param coordinateSystem
	 */
	public void setCoordinateSystem(ICoordinateSystem coordinateSystem);

	/**
	 * 
	 * @param deviceFrame
	 */
	public void setDeviceFrame(IEnvelope deviceFrame);

	/**
	 * 
	 * @param referenceScale
	 */
	public void setReferenceScale(double referenceScale);

	/**
	 * 
	 * @param resolution
	 */
	public void setResolution(double resolution);

	/**
	 * 
	 * @param rotation
	 */
	public void setRotation(double rotation);

	/**
	 * 
	 * @param unit
	 */
	public void setUnits(UnitTypesEnum unit);

	/**
	 * 
	 * @param visibleBounds
	 */
	public void setVisibleBounds(IEnvelope visibleBounds);

	/**
	 * 
	 * @param zoomResolution
	 */
	public void setZoomResolution(boolean zoomResolution);

}