package com.sampas.socbs.opendai.esb;

import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextFactory;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextFactory;


public class StartESB {
	
    public static void main( String[] args ) throws Exception {
    	
    	String[] config = new String[]{"OpenDAIServis-config.xml"};
    	
		MuleContextFactory factory = new DefaultMuleContextFactory();
		
		MuleContext context = factory.createMuleContext(new SpringXmlConfigurationBuilder(config));
		
		context.start();
    }
    
}
