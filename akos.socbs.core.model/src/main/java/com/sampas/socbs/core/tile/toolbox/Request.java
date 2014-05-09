package com.sampas.socbs.core.tile.toolbox;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Request {

    private static Log log = LogFactory
            .getLog(com.sampas.socbs.core.tile.toolbox.Request.class);

    private URL server = null;

    private WMSParameters params = null;

    public Request(String server, WMSParameters params) {
        this.setServer(server);
        // this.setParametersFromConfiguration();
        if (this.params != null) {
            this.params.merge(params);
        } else {
            this.params = params;
        }
    }

    /**
     * @return the address URL
     */
    public URL getServer() {
        return server;
    }

    /**
     * @param address
     *            the address URL to set
     */
    public void setServer(URL server) {
        this.server = server;
    }

    public void setServer(String address) {
        try {
            server = new URL(address);
        } catch (MalformedURLException mue) {
            log.error("Invalid server URL String: ", mue);
            // Do nothing, leave this.server set to previous value
        }
    }

    public Parameters getParameters() {
        return params;
    }

    public void setParameters(WMSParameters params) {
        this.params = params;
    }

    public URL getURL() {
        URL address = null;
        try {
            String serverAdr = server.toExternalForm();
            if(serverAdr.charAt(serverAdr.length() - 1) != '?') { 
                address = new URL( serverAdr +'?'+ params.getURLString());
            } else {
                address = new URL( serverAdr + params.getURLString());
            }
            
            log.debug("url: " + address);
        } catch (MalformedURLException mue) {
            log.error("Invalid URL from server and parameters: ", mue);
        }
        return address;
    }

    @Override
    public String toString() {
        return getURL().toString();
    }

}
