package com.sampas.socbs.core.dataset.filter.expression;

import org.geotools.filter.AreaFunction;
import com.vividsolutions.jts.geom.Geometry;


public class SmpAreaFunction extends AreaFunction {

    public SmpAreaFunction() {
    	
        super();
    }
    
    public Object evaluate(Object feature) {
    	
    	return super.evaluate(feature);
    }
    
    public int getArgCount() {
    	
        return 1;
    }
    
    public double getArea(Geometry geometry) {
    
    	return super.getArea(geometry);
    }
    	
    public double getPerimeter(Geometry geometry) {
    	
    	return super.getPerimeter(geometry);
    }
    
}
