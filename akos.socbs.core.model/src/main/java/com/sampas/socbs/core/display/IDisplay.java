package com.sampas.socbs.core.display;


/**
 * Use the IDisplay interface to draw points, lines, polygons, rectangles, and
 * text on a device.
 * Remarks
 * 
 * The Display objects, those that implement IDisplay, are a set of objects which
 * allow application developers to easily draw graphics on a variety of output
 * devices.  These objects allow you to render shapes stored in real-world
 * coordinates to a screen, printer, or export file.
 * 
 * The IDisplay interface abstracts a drawing surface.  A drawing surface is
 * simply any hardware device, export file, or memory bitmap that can be
 * represented by a Windows Device Context.   Each display manages its own
 * DisplayTransformation object which handles the conversion of coordinates from
 * real-world space to device space and back.
 * 
 * There are currently two Display objects: ScreenDisplay and SimpleDisplay.  The
 * ScreenDisplay object abstracts a normal application window and implements
 * scrolling and backing store.  The SimpleDisplay abstracts all other devices
 * that can be rendered to using a Windows Device Context such as printers and
 * metafiles.
 * @version 1.0
 * @created 07-Kas-2008 13:35:41
 */
public interface IDisplay extends IDisplayEvents, IDisplayBase {
	
	public IDevice getDevice();
	
	public void setDevice(IDevice device);

	public void clearDevice();
	
}