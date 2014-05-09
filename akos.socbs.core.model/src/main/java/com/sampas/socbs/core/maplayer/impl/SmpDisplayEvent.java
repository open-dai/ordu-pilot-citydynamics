package com.sampas.socbs.core.maplayer.impl;

import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.display.IDisplayEventsDisplayStartedEvent;


public class SmpDisplayEvent implements IDisplayEventsDisplayStartedEvent {
	
	private IDisplay display;
	
	public SmpDisplayEvent(IDisplay display){
		
		this.display=display;
		
	}

	public IDisplay getDisplay() {

		return this.display;
	}
	

}
