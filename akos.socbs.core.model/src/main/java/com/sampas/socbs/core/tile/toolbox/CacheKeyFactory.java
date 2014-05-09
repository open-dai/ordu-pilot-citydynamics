package com.sampas.socbs.core.tile.toolbox;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


@SuppressWarnings({"unchecked", "unused"})
public class CacheKeyFactory implements ApplicationContextAware {
    
	private static Log log = LogFactory.getLog(com.sampas.socbs.core.tile.toolbox.CacheKeyFactory.class);

    private ApplicationContext context = null;
    
    private Map<String,CacheKey> cacheKeys = null;
    
    public CacheKeyFactory() {
    	
    }
    
    public CacheKey getCacheKey(String cacheKeyBeanId) {
        if(cacheKeys == null) {
        	loadCacheKeys();
        }
    	return (CacheKey) cacheKeys.get(cacheKeyBeanId);
    }
        
    private void loadCacheKeys() {
        Map<String,CacheKey> cacheBeans = context.getBeansOfType(CacheKey.class);
        Iterator<Entry<String,CacheKey>> beanIter = cacheBeans.entrySet().iterator();
        
        cacheKeys = new HashMap<String,CacheKey>();
        
        while(beanIter.hasNext()) {
            Entry<String,CacheKey> entry = beanIter.next();
            cacheKeys.put(entry.getKey(), entry.getValue());
            
            //log.debug("Added cache key bean for " + entry.getValue().getClass().toString());
        }
    }
    
    public void setCacheKeys(Map<String,CacheKey> cacheKeys) {
        this.cacheKeys = cacheKeys;
    }
    
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		context = arg0;
	}
}
