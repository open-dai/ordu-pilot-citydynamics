package com.sampas.socbs.core.mapview;



/**
 * This interface manages the main application window all drawing operations.
 * 
 * Two objects implement this interface: PageLayout and Map. These two objects
 * correspond to the two different views, layout and data view. Only one view
 * can be active at a time and this is termed the active view. ActiveView holds
 * a reference to the current active view object (a Map or the PageLayout).
 * Alternatively, if the application is in data view, this propery returns a
 * reference to the focus map.
 * 
 * @version 1.0
 * @created 07-Kas-2008 13:35:40
 */
public interface IActiveView extends IActiveViewBase, IActiveViewEvents {

}