package com.sampas.socbs.core.symbology.impl;

import org.geotools.event.GTComponent;
import org.geotools.event.GTDelta;
import org.geotools.event.GTNote;

public class SmpGTComponent {

	private GTComponent geoToolsGTComponent = null;

	public SmpGTComponent() {

	}

	public SmpGTComponent(GTComponent geoToolsGTComponent) {

		this.geoToolsGTComponent = geoToolsGTComponent;
	}

	public GTComponent getGeotoolsGTComponent() {
		
		return (this.geoToolsGTComponent);
	}
	
    @SuppressWarnings("deprecation")
	public SmpGTComponent getParent() {
    	
    	SmpGTComponent smpGTComponent = new SmpGTComponent(this.geoToolsGTComponent.getParent());
    	
    	return (smpGTComponent);
    }

    public SmpGTNote getNote() {
    	
    	SmpGTNote smpGTNote = new SmpGTNote(this.geoToolsGTComponent.getNote());    	
    	
    	return (smpGTNote);
    }

    public void setNote(SmpGTNote container) {
    	
    	GTNote gTNode = container.getGeotoolsGTNote();
    	
    	this.geoToolsGTComponent.setNote(gTNode);
    }

    public void removed(SmpGTDelta smpDelta) {
    	
    	GTDelta gTDelta = smpDelta.getGeotoolsGTDelta();
    	
    	this.geoToolsGTComponent.removed(gTDelta);
    }

    public void changed(SmpGTDelta smpDelta) {
    	
    	GTDelta gTDelta = smpDelta.getGeotoolsGTDelta();
    	
    	this.geoToolsGTComponent.changed(gTDelta);
    }
	
}
