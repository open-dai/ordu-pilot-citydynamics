package com.sampas.socbs.core.mapview;

import java.util.EventListener;

public interface IRefreshedEventListener extends EventListener {

	public void onViewRefreshed(IActiveViewEventsViewRefreshedEvent refreshedEvent);
	
}
