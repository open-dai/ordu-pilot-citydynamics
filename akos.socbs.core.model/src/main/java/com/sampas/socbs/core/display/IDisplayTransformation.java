package com.sampas.socbs.core.display;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.ITransformation;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.tools.impl.UnitTypesEnum;

/**
 * When To Use
 * 
 * Use IDisplayTransformation for converting coordinates between real-world and
 * device space and back.  To prepare a transform for use, follow these steps:
 * 
 *   1. Set the full map extent with the Bounds property
 *   2. Set the visible map extent (zoom rectangle) with the VisibleBounds
 * property
 *   3. Set the output area of the device using the DeviceFrame property
 *   4. Set the resolution of the output device using the Resolution property
 * 
 * The transform is based on the ratio between the VisibleBounds and the
 * DeviceFrame.  Normally the DeviceFrame is simply the full extent of the device
 * with the origin equal to 0,0.
 * 
 * The transform object calculates the FittedBounds automatically and this is the
 * visible map extent adjusted to fit the device.
 * Remarks
 * 
 * Obtain an IDisplayTransformation reference to the DisplayTransformation object
 * from IDisplay->DisplayTransformation . Because IScreenDisplay inherits from
 * IDisplay, you can also use IScreenDisplay->DisplayTransformation.
 * 
 * Each DisplayTransformation manages a coordinate system object such as a
 * GeographicCoordinateSystem or ProjectedCoordinateSystem . Obtain a reference to
 * the display's coordinate system object using IDisplayTransformation-
 * >CoordinateSystem.
 * 
 * The DisplayTransformation of a PageLayout object does not have a spatial
 * reference and translates between device units and page units.
 * @version 1.0
 * @created 07-Kas-2008 13:35:41
 */
public interface IDisplayTransformation extends IDisplayTransformationBase, ITransformEvents, ITransformation {

	/**
	 * 
	 * @param event
	 */
	public void boundsUpdated(ITransformEventsBoundsUpdatedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void deviceFrameUpdated(ITransformEventsDeviceFrameUpdatedEvent event);

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
	 * @param event
	 */
	public void resolutionUpdated(ITransformEventsResolutionUpdatedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void rotationUpdated(ITransformEventsRotationUpdatedEvent event);

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

	/**
	 * 
	 * @param event
	 */
	public void unitsUpdated(ITransformEventsUnitsUpdatedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void visibleBoundsUpdated(ITransformEventsVisibleBoundsUpdatedEvent event);

}