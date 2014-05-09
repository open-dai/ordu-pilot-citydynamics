package com.sampas.socbs.core.mapview;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:40
 */
public interface IActiveViewEvents {

	/**
	 * Fired after the specified phase is drawn.
	 * 
	 * @param event
	 */
	public void afterDraw(IActiveViewEventsAfterDrawEvent event);

	/**
	 * Fired after an individual view item is drawn.
	 * 
	 * @param event
	 */
	public void afterItemDraw(IActiveViewEventsAfterItemDrawEvent event);

	/**
	 * Fired when the contents of the view changes.
	 * 
	 * @param event
	 */
	public void contentsChanged(IActiveViewEventsContentsChangedEvent event);

	/**
	 * Fired when the contents of the view is cleared.
	 * 
	 * @param event
	 */
	public void contentsCleared(IActiveViewEventsContentsClearedEvent event);

	/**
	 * Fired when the coordinate system is changed.
	 * 
	 * @param event
	 */
	public void coordinateSystemChanged(
			IActiveViewEventsCoordinateSystemChangedEvent event);

	/**
	 * Fired when a new map is made active.
	 * 
	 * @param event
	 */
	public void focusMapChanged(IActiveViewEventsFocusMapChangedEvent event);

	/**
	 * Fired when an item is added to the view.
	 * 
	 * @param event
	 */
	public void itemAdded(IActiveViewEventsItemAddedEvent event);

	/**
	 * Fired when an item is deleted from the view.
	 * 
	 * @param event
	 */
	public void itemDeleted(IActiveViewEventsItemDeletedEvent event);

	/**
	 * Fired when a view item is reordered.
	 * 
	 * @param event
	 */
	public void itemReordered(IActiveViewEventsItemReorderedEvent event);

	/**
	 * Call this function to fire the selection changed event.
	 * 
	 * @param event
	 */
	public void selectionChanged(IActiveViewEventsSelectionChangedEvent event);

	/**
	 * Fired when view is refreshed before draw happens.
	 * 
	 * @param event
	 */
	public void viewRefreshed(IActiveViewEventsViewRefreshedEvent event);

}