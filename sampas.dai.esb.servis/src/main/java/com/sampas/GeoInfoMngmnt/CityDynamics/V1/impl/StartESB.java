package com.sampas.GeoInfoMngmnt.CityDynamics.V1.impl;


import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextFactory;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextFactory;


public class StartESB {

    public static void main( String[] args ) throws Exception {

    	String[] config = new String[]{"service-config.xml"};

		MuleContextFactory factory = new DefaultMuleContextFactory();

		MuleContext context = factory.createMuleContext(new SpringXmlConfigurationBuilder(config));

		context.start();
    }

}
