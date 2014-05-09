package com.sampas.socbs.core.tile.toolbox;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@SuppressWarnings("unchecked")
public abstract class Parameters {

    private static Log log = LogFactory.getLog(com.sampas.socbs.core.tile.toolbox.Parameters.class);

    // Charset to use for URL strings
    protected static final String CHARSET = "UTF-8";

    protected Map params;

    public Parameters() {
        params = new HashMap();
    }

    public Parameters(HttpServletRequest httprequest) {
        params = new HashMap();
        setFromHttpServletRequest(httprequest);
    }

    // TODO What does this really achieve? I know there was something...
    public void setFromHttpServletRequest(HttpServletRequest httprequest) {
        if (log.isTraceEnabled()) {
            log.trace("Setting from HttpServletRequest.");
        }
        
        Iterator<Entry<String,String>> itr = httprequest.getParameterMap().entrySet().iterator();

        while(itr.hasNext()) {
            Entry<String,String> ent = itr.next();
            params.put(ent.getKey().toLowerCase(),ent.getValue());   
        }
    }

    /**
     * Allows arbitary key / values to be set
     */
    public void set(String key, Object value) {
        params.put(key.toLowerCase(), value);
    }

    public Object get(String key) {
        return params.get(key.toLowerCase());
    }

    public void remove(String key) {
        params.remove(key.toLowerCase());
    }

    /**
     * Converts the map object to a proper URL string (such as by turning string
     * arrays in comma separated values) Assumes that the object in the map
     * implements a correct toString()
     * 
     * @param obj
     * @return
     */
    protected String convertToString(Object obj) {
        if (obj != null) {
            if (obj instanceof String) {
                return (String) obj;
            } else if (obj instanceof Boolean) {
                // Tweak for Ionic
                return ((Boolean) obj).toString().toUpperCase();
            } else if (obj instanceof String[]) {
                // Make a comma separated list out of the array
                String[] array = (String[]) obj;
                StringBuffer str = new StringBuffer(100);
                boolean notfirst = false;
                for (int i = 0; i < array.length; ++i) {
                    if (notfirst) {
                        str.append(',');
                    } else {
                        notfirst = true;
                    }
                    str.append(array[i]);
                }
                return str.toString();
            } else {
                // Assume this class implements toString
                return obj.toString();
            }
        }
        // If object is null, return null
        return null;
    }

    /**
     * Merges another Paramters object with this one if the old value is unset,
     * it is set to the new value if the old value is set and the new value is
     * not null, the new value is used
     */
    public void merge(Parameters params) {
        this.params.putAll(params.params);
    }

    /**
     * Service parameter classes will define how to get the layer name from the
     * map
     * 
     * @return
     */
    public abstract String getLayer();

    /**
     * Service parameter classes will define how to get the image format from
     * the map
     * 
     * @return
     */
    //public abstract ImageFormat getImageFormat();
    /**
     * Service parameter classes will define how to get the BBOX from the map
     * 
     * @return
     */
    public abstract BBOX getBBOX();

    /**
     * Compares if the given parameter set is on the same "Layer"
     * 
     * @param params
     * @return
     */
     //public abstract boolean sameLayerAs(Parameters params);
    
}
