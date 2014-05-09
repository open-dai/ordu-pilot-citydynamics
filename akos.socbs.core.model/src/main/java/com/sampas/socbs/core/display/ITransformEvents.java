package com.sampas.socbs.core.display;

/**
 * The ITransformEvents interface provides access to display transformation events.
 * 
 * The ITransformEvents events all have their counterparts in the properties of
 * the IDisplayTransformation.
 * For instance the ITransformEvents::BoundsUpdated event will be fired when the
 * IDisplayTransformation::Bounds is set. Refer to the documentation for
 * IDisplayTransformation for information on these properties and the related
 * ITransformEvents events.
 * These events are fired by the DisplayTransformation coclass and can be listened
 * to by implementing ITransformEvents.
 * @version 1.0
 * @created 07-Kas-2008 13:35:46
 */
public interface ITransformEvents {

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
	 * @param event
	 */
	public void unitsUpdated(ITransformEventsUnitsUpdatedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void visibleBoundsUpdated(ITransformEventsVisibleBoundsUpdatedEvent event);

}