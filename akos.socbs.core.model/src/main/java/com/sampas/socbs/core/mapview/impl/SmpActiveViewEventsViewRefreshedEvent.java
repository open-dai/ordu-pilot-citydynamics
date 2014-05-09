package com.sampas.socbs.core.mapview.impl;

import java.util.EventObject;

import com.sampas.socbs.core.mapview.IActiveViewEventsViewRefreshedEvent;

public class SmpActiveViewEventsViewRefreshedEvent extends EventObject implements
		IActiveViewEventsViewRefreshedEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8182604052729440114L;

	public SmpActiveViewEventsViewRefreshedEvent(Object source) {
		super(source);
	}

}
