package com.sampas.socbs.core.display;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:41
 */
public interface IDisplayEvents {

	/**
	 * 
	 * @param event
	 */
	public void displayFinished(IDisplayEventsDisplayFinishedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void displayInvalidated(IDisplayEventsDisplayInvalidatedEvent event);

	/**
	 * 
	 * @param event
	 */
	public void displayStarted(IDisplayEventsDisplayStartedEvent event);

}