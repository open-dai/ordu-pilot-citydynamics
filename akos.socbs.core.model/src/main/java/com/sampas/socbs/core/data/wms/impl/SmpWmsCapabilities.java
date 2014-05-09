package com.sampas.socbs.core.data.wms.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.geotools.data.ows.Capabilities;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.WMSRequest;

public class SmpWmsCapabilities extends Capabilities{

    private WMSRequest request;
    private Layer layer;
    
    @SuppressWarnings("unchecked")
	private List layers; //cache
	private String[] exceptions;

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }
    
    @SuppressWarnings({ "unchecked" })
	public List getLayerList() {
        if (layers == null) {
            layers = new ArrayList();
            layers.add(layer);
            addChildrenRecursive(layers, layer);
        }        
        return Collections.unmodifiableList(layers);
    }
    
    @SuppressWarnings("unchecked")
	private void addChildrenRecursive(List layers, Layer layer) {
        if (layer.getChildren() != null) {
            for (int i = 0; i < layer.getChildren().length; i++) {
                layers.add(layer.getChildren()[i]);
                addChildrenRecursive(layers, layer.getChildren()[i]);
            }
        }
    }

    /**
     * The request contains information about possible Requests that can be 
     * made against this server, including URLs and formats.
     *
     * @return Returns the request.
     */
    public WMSRequest getRequest() {
        return request;
    }

    /**
     * @param request The request to set.
     */
    public void setRequest(WMSRequest request) {
        this.request = request;
    }

	/**
	 * Exceptions declare what kind of formats this server can return exceptions
	 * in. They are used during subsequent requests.
	 */
	public String[] getExceptions() {
	    return exceptions;
	}

	public void setExceptions(String[] exceptions) {
	    this.exceptions = exceptions;
	}

}
