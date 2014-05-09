package com.sampas.socbs.core.data.impl;

import org.geotools.data.ows.Capabilities;
import org.geotools.data.ows.Service;

public class SmpCapabilities extends Capabilities {

	private Capabilities geoToolsCapabilities = null;

	public SmpCapabilities() {

	}

	public SmpCapabilities(Capabilities geoToolsCapabilities) {

		this.geoToolsCapabilities = geoToolsCapabilities;
	}

	public Capabilities getGeotoolsCapabilities() {
		
		return (this.geoToolsCapabilities);
	}
	
    private Service service;
	private String version;
	private String updateSequence;

    
	public Service getService() {
        return service;
    }

    
	public void setService(Service service) {
        this.service = service;
    }

	
	public String getVersion() {
	    return version;
	}

	
	public void setVersion(String version) {
	    this.version = version;
	}

	
	public String getUpdateSequence() {
		return updateSequence;
	}

	
	public void setUpdateSequence(String updateSequence) {
		this.updateSequence = updateSequence;
	}
	
}
