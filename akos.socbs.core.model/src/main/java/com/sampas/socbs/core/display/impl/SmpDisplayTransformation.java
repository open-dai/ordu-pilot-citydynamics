package com.sampas.socbs.core.display.impl;

import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.display.IDisplayTransformation;
import com.sampas.socbs.core.display.ITransformEventsBoundsUpdatedEvent;
import com.sampas.socbs.core.display.ITransformEventsDeviceFrameUpdatedEvent;
import com.sampas.socbs.core.display.ITransformEventsResolutionUpdatedEvent;
import com.sampas.socbs.core.display.ITransformEventsRotationUpdatedEvent;
import com.sampas.socbs.core.display.ITransformEventsUnitsUpdatedEvent;
import com.sampas.socbs.core.display.ITransformEventsVisibleBoundsUpdatedEvent;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.geometry.impl.SmpCoordinate;
import com.sampas.socbs.core.processing.services.IMeasureTools;
import com.sampas.socbs.core.processing.services.impl.SmpMeasureTools;
import com.sampas.socbs.core.tools.impl.UnitTypesEnum;

/*
 * When To Use
 * 
 * Use IDisplayTransformation for converting coordinates between real-world and device space and back.  To prepare a transform for use, follow these steps:
 * 
 *    1. Set the full map extent with the Bounds property
 *    2. Set the visible map extent (zoom rectangle) with the VisibleBounds property
 *    3. Set the output area of the device using the DeviceFrame property
 *    4. Set the resolution of the output device using the Resolution property
 * 
 * The transform is based on the ratio between the VisibleBounds and the DeviceFrame.  Normally the DeviceFrame is simply the full extent of the device with the origin equal to 0,0. 
 * 
 * The transform object calculates the FittedBounds automatically and this is the visible map extent adjusted to fit the device.
 * Remarks
 * 
 * Obtain an IDisplayTransformation reference to the DisplayTransformation object from IDisplay::DisplayTransformation . Because IScreenDisplay inherits from IDisplay, you can also use IScreenDisplay::DisplayTransformation.
 * 
 * Each DisplayTransformation manages a coordinate system object such as a GeographicCoordinateSystem or ProjectedCoordinateSystem . Obtain a reference to the display's coordinate system object using IDisplayTransformation::SpatialReference.
 * 
 * The DisplayTransformation of a PageLayout object does not have a spatial reference and translates between device units and page units.
 */
@SuppressWarnings("unused")
public class SmpDisplayTransformation implements IDisplayTransformation {

	/*
	 * The coordinate system of the map.
	 */
	private ICoordinateSystem coordinateSystem;
	/*
	 * Rotation angle in degrees.
	 */
	private double rotation = 0;
	/*
	 * Visible extent in device coordinates.
	 */
	private IEnvelope deviceFrame;
	/*
	 *  Units used by world coordinates.
	 */
	private UnitTypesEnum units = UnitTypesEnum.METERS;
	/*
	 * Visible extent in world coordinates.
	 */
	private IEnvelope visibleBounds;
	/*
	 *  Full extent in world coordinates (e.g. map's full extent)
	 */
	private IEnvelope bounds;
	/*
	 * Device frame in world coordinates. Should be calculated
	 */
	private IEnvelope fittedBound;
	/*
	 * Intersection of Bounds and VisibleBounds. Should be calculated
	 */
	private IEnvelope constrainedBounds;
	/*
	 * Reference scale for computing scaled symbol sizes.
	 */
	private double referenceScale = 1;
	/*
	 *  Resolution of the device in dots (pixels) per inch. (dpi of device)
	 */
	private double resolution = 0.72;
	/*
	 * Indicates if resolution is tied to visible bounds. If true, zooming in magnifies contents (i.e., zoom in on page).
	 */
	private boolean useZoomResolution = false;
	
	/*
	 * Offset values
	 */
	private double deltaX;
	
	private double deltaY;
	
	private IMeasureTools measureTools = new SmpMeasureTools();
		
	public SmpDisplayTransformation(){
	
	}
	
	public SmpDisplayTransformation(ICoordinateSystem coordinateSystem){
		
		this.coordinateSystem =coordinateSystem;
	}
	
	
	public boolean IsZoomResolution() {

		return this.useZoomResolution;
	}

	
	public IEnvelope getBounds() {

		return this.bounds ;
	}

	
	public IEnvelope getConstrainedBounds() {
		
		return this.constrainedBounds;
	}

	
	public ICoordinateSystem getCoordinateSystem() {

		return this.coordinateSystem;
	}

	
	public IEnvelope getDeviceFrame() {

		return this.deviceFrame;
	}

	
	public IEnvelope getFittedBounds() {
		
		return this.fittedBound;
	}

	
	public double getReferenceScale() {

		return this.referenceScale;
	}

	
	public double getResolution() {

		return this.resolution;
	}

	
	public double getRotation() {

		return this.rotation;
	}

	
	public double getScaleRatio() {
		// TODO Auto-generated method stub
		// TODO Scale between FittedBound and DeviceFrame.
		// TODO calculate	
		
//		double inchPixelX = this.deviceFrame.getWidth() / this.resolution;
//		
//		double inchPixelY = this.deviceFrame.getHeight() / this.resolution;
//		
//		inchPixelX *= 2.54 /100;
//		
//		inchPixelY *= 2.54 /100;
//		
		if(this.fittedBound == null || this.deviceFrame == null){
			
			//It means no layer's added || no device frame is set
			return  0;
		}
		ICoordinate coordinateMin 	= new SmpCoordinate(this.fittedBound.getMinX(),this.fittedBound.getMinY());
		ICoordinate coordinateHeightMax 	= new SmpCoordinate(this.fittedBound.getMinX(),this.fittedBound.getMaxY());
		double realHeight = this.measureTools.getDistance(coordinateMin , coordinateHeightMax, coordinateSystem);
//		
		ICoordinate coordinateWidthMax 	= new SmpCoordinate(this.fittedBound.getMaxX(),this.fittedBound.getMinY());
		double realWidth = this.measureTools.getDistance(coordinateMin, coordinateWidthMax, coordinateSystem);
		
		
		double coordinateWidth= this.fittedBound.getWidth();
		double coordinateHeight= this.fittedBound.getHeight();
		
//		System.out.println("CooWidth:  "+coordinateWidth+" CooHeight:  "+coordinateHeight);
//		System.out.println("DevWidth:  "+this.deviceFrame.getWidth()+" DevHeight:  "+this.deviceFrame.getHeight());
//		
		
//		double scaleX	=	realWidth/inchPixelX;
//		
//		double scaleY	= 	realHeight/inchPixelX;
			
 		double scaleX	=	realWidth/this.deviceFrame.getWidth();
		
		double scaleY	= 	realHeight/this.deviceFrame.getHeight();
		
//		System.out.println("ScaleX :"+scaleX+" ScaleY :"+scaleY );
		return Math.min(scaleX, scaleY);
	}

	
	public UnitTypesEnum getUnits() {
		// TODO Auto-generated method stub
		return this.units;
	}

	
	public IEnvelope getVisibleBounds() {

		return this.visibleBounds;
	}

	
	public void setBounds(IEnvelope bounds) {
		// TODO update bounds		
		
		this.bounds = bounds;
		
		setVisibleBounds(bounds);

		// TODO update fittedBounds
		//updateFittedBounds();
		
		// TODO update constrainedBounds
		//updateConstrainedBounds();
		
		// TODO fire boundsUpdated event
	}

	private void updateConstrainedBounds() {

		double minX = Math.max(this.visibleBounds.getMinX(), this.bounds.getMinX());
		double minY = Math.max(this.visibleBounds.getMinY(), this.bounds.getMinY());
		double maxX = Math.min(this.visibleBounds.getMaxX(), this.bounds.getMaxX());
		double maxY = Math.min(this.visibleBounds.getMaxY(), this.bounds.getMaxY());
		
		IEnvelope envelope = new SmpBoundingRectangle(minX,minY,maxX,maxY);
		this.constrainedBounds = envelope;
		
	}

	private void updateFittedBounds() {
		
		IEnvelope tempFittedBound = null;
		double scaleX = 0;
		double scaleY = 0;
		double scale = 0;
		
		
		
		/**
		scaleX	=	this.deviceFrame.getWidth()  / this.visibleBounds.getWidth();

		scaleY	= 	this.deviceFrame.getHeight() / this.visibleBounds.getHeight();
		
//		System.out.println("ScaleX:  "+scaleX+" scaleY:  "+scaleY);
//		System.out.println("DevWidth:  "+this.deviceFrame.getWidth()+" DevHeight:  "+this.deviceFrame.getHeight());
//		
//		
		double scaleRatioUsed =  Math.min(scaleX, scaleY);
		
		this.deltaX 	= (this.deviceFrame.getWidth() / scaleRatioUsed) - this.visibleBounds.getWidth();
		
		this.deltaY 	= (this.deviceFrame.getHeight() / scaleRatioUsed) - this.visibleBounds.getHeight();
		
		System.out.println("OffsetX : "+deltaX+ " OffsetY : "+deltaY );
		
		tempFittedBound = new SmpBoundingRectangle(
				this.visibleBounds.getMinX() - (deltaX / 2) ,
				this.visibleBounds.getMinY() - (deltaY / 2) ,
				this.visibleBounds.getMaxX() + (deltaX / 2) ,
				this.visibleBounds.getMaxY() + (deltaY / 2) 
				);
		
		**/
		
	//	if(this.visibleBounds.getWidth() > this.deviceFrame.getWidth()){
			
			scaleX =  this.visibleBounds.getWidth()/this.deviceFrame.getWidth();
			scaleY =  this.visibleBounds.getHeight()/this.deviceFrame.getHeight();
			scale = Math.max(scaleX, scaleY);
			
			this.deltaX = Math.abs(this.visibleBounds.getWidth() - (this.deviceFrame.getWidth() * scale));
			
			this.deltaY = Math.abs(this.visibleBounds.getHeight() - (this.deviceFrame.getHeight() * scale));
			

//			tempFittedBound = new SmpBoundingRectangle(
//					this.visibleBounds.getMinX() + (deltaX / 2) ,
//					this.visibleBounds.getMinY() + (deltaY / 2) ,
//					this.visibleBounds.getMaxX() - (deltaX / 2) ,
//					this.visibleBounds.getMaxY() - (deltaY / 2) 
//					);
			
			double centerPointX =  (this.visibleBounds.getMinX() +this.visibleBounds.getMaxX())/2 ;
			double centerPointY =  (this.visibleBounds.getMinY() +this.visibleBounds.getMaxY())/2 ;
			
			
			
			tempFittedBound = new SmpBoundingRectangle(
					centerPointX - (scale* (this.deviceFrame.getWidth()/2)),
					centerPointY - (scale* (this.deviceFrame.getHeight()/2)),
					centerPointX + (scale* (this.deviceFrame.getWidth()/2)),
					centerPointY + (scale* (this.deviceFrame.getHeight()/2))
					);
			
//		}
//		else{
//			
//		}
		
		this.fittedBound = tempFittedBound;
		
	}

	
	public void setCoordinateSystem(ICoordinateSystem coordinateSystem) {

		this.coordinateSystem = coordinateSystem;
		//TODO convert the All Bounds points according to new coordinateSystem 
		
		if(this.fittedBound != null){
			// TODO update fittedBounds
			updateFittedBounds();			
		}
		if(this.constrainedBounds != null){
			// TODO update constrainedBounds
			updateConstrainedBounds();
			// TODO fire events
		}
	}

	
	public void setDeviceFrame(IEnvelope deviceFrame) {

		this.deviceFrame = deviceFrame;
		// TODO update fittedBounds
	
		//updateFittedBounds();
		// TODO update constrainedBounds
		//updateConstrainedBounds();
		// TODO fire deviceFrameUpdated event
	}

	
	public void setReferenceScale(double referenceScale) {

		this.referenceScale = referenceScale;
	}

	
	public void setResolution(double resolution) {

		this.resolution = resolution;
	}

	
	public void setRotation(double rotation) {

		this.rotation = rotation;
	}

	
	public void setUnits(UnitTypesEnum unit) {

		this.units = unit;
	}

	
	public void setVisibleBounds(IEnvelope visibleBounds) {	
		

		this.visibleBounds = visibleBounds;
		
		if(visibleBounds !=null){
			updateFittedBounds();
		}
		else{
			this.fittedBound = null;
		}
		// TODO fire visibleBoundsUpdated event
	}

	
	public void setZoomResolution(boolean zoomResolution) {
		this.useZoomResolution = zoomResolution;
		// TODO calculate and change the view accordingly
	}
	
	
	
	public ICoordinate fromDisplayCSToMapCS(ICoordinate displayPoint) {
		
		if(this.fittedBound == null || this.deviceFrame == null){
			
			return null;
		}
		
		double x = 
				((displayPoint.getX()*this.fittedBound.getWidth()) 
						/this.deviceFrame.getWidth()) 
						+ this.fittedBound.getMinX();
		double y =
				((displayPoint.getY()*this.fittedBound.getHeight()) 
						/this.deviceFrame.getHeight()) 
						+ this.fittedBound.getMinY();
		
		return new SmpCoordinate(x,y);
	}
	
	public ICoordinate fromMapCSToDisplayCS(ICoordinate mapPoint) {
		// TODO Auto-generated method stub
		double x = Math.rint((mapPoint.getX() - this.fittedBound.getMinX()) * (this.deviceFrame.getWidth() / this.fittedBound.getWidth())) ;
		double y = Math.rint((mapPoint.getY() - this.fittedBound.getMinY()) * (this.deviceFrame.getHeight() / this.fittedBound.getHeight())) ;
		
		
//		double x = Math.rint((mapPoint.getX() - this.visibleBounds.getMinX()) * (this.deviceFrame.getWidth() / this.visibleBounds.getWidth())) ;
//		double y = Math.rint((mapPoint.getY() - this.visibleBounds.getMinY()) * (this.deviceFrame.getHeight() / this.visibleBounds.getHeight())) ;
		
		return new SmpCoordinate(x,y);
	}
	
	
	public void unitsUpdated(ITransformEventsUnitsUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void visibleBoundsUpdated(
			ITransformEventsVisibleBoundsUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void boundsUpdated(ITransformEventsBoundsUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void deviceFrameUpdated(ITransformEventsDeviceFrameUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void resolutionUpdated(ITransformEventsResolutionUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	public void rotationUpdated(ITransformEventsRotationUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}
}
