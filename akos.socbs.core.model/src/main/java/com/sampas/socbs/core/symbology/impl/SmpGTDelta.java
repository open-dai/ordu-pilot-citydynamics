package com.sampas.socbs.core.symbology.impl;

import java.util.Collections;
import java.util.List;

import org.geotools.event.GTDelta;
import org.geotools.event.GTDeltaVisitor;
import org.geotools.event.GTDelta.Kind;

public class SmpGTDelta{

	private GTDelta geoToolsGTDelta = null;
	
    @SuppressWarnings("unchecked")
	public static final List NO_CHILDREN = Collections.EMPTY_LIST;

    public static final int NO_INDEX = -1;

	public SmpGTDelta() {

	}

	public SmpGTDelta(GTDelta geoToolsGTDelta) {

		this.geoToolsGTDelta = geoToolsGTDelta;
	}

	public GTDelta getGeotoolsGTDelta() {
		
		return (this.geoToolsGTDelta);
	}
	


    public SmpKind getKind() {    	
    	
    	SmpKind smpKind = new SmpKind(this.geoToolsGTDelta.getKind());
    	
    	return (smpKind);
    }

    public Object getValue() {
    	
    	return (this.geoToolsGTDelta.getValue());
    }

    public Object getOldValue() {
    	
    	return (this.geoToolsGTDelta.getOldValue());
    }

    public int getPosition() {
    	
    	return (this.geoToolsGTDelta.getPosition());
    }

    public String getName() {
    	
    	return (this.geoToolsGTDelta.getName());
    }

    @SuppressWarnings("unchecked")
	public List getChildren() {
    	
    	return (this.geoToolsGTDelta.getChildren());
    }

    public void accept(SmpGTDeltaVisitor smpDelteVisitor) {
    	
    	GTDeltaVisitor gTDeltaVisitor = smpDelteVisitor.getGeotoolsGTDeltaVisitor();
    	
    	this.geoToolsGTDelta.accept(gTDeltaVisitor);
    }

    public static class SmpKind extends Kind {

    	private Kind geoToolsKind = null;

    	public SmpKind() {

    	}

    	public SmpKind(Kind geoToolsKind) {

    		this.geoToolsKind = geoToolsKind;
    	}

    	public Kind getGeotoolsKind() {
    		
    		return (this.geoToolsKind);
    	}
    	
        public static final Kind NO_CHANGE = new Kind();

        public static final Kind ADDED = new Kind();

        public static final Kind REMOVED = new Kind();

        public static final Kind CHANGED = new Kind();
    }
	
}
