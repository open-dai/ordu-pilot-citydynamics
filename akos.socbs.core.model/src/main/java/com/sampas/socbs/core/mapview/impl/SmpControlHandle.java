package com.sampas.socbs.core.mapview.impl;

import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.mapview.IControlHandle;

public class SmpControlHandle implements IControlHandle {

	private IDisplay display;

	public SmpControlHandle(){
		
	}
	
	public SmpControlHandle(IDisplay display){
		
		this.display = display;
	}
	public IDisplay getDisplay() {

		return this.display;
	}

	public void setDisplay(IDisplay display) {
		
		this.display =  display;
	}

}
