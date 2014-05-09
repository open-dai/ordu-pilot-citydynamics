package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.FactoryConfigurationError;
import org.geotools.factory.GeoTools;
import org.geotools.factory.Hints;
import org.geotools.filter.FilterFactory;

@SuppressWarnings("deprecation")
public abstract class SmpFilterFactoryFinder {

		public static SmpFilterFactory createFilterFactory() throws FactoryConfigurationError {
	    	
	        Hints hints = GeoTools.getDefaultHints();
	        
	        SmpFilterFactory smpFilterFactory = new SmpFilterFactory((FilterFactory) CommonFactoryFinder.getFilterFactory( hints ));
	        
	        return (smpFilterFactory);
	    }
	    
}
