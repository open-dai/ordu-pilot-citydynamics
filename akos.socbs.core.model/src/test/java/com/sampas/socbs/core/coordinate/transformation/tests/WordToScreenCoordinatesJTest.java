package com.sampas.socbs.core.coordinate.transformation.tests;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import junit.framework.TestCase;


public class WordToScreenCoordinatesJTest extends TestCase{

	public void testWorldToScreenTransform() throws Exception {
		
		
		
		AffineTransform translate = AffineTransform.getTranslateInstance(90, 180);
		System.out.println("Translate:" + translate.toString());
		Point2D p = new Point2D.Double(28.343434, 41.564534);
		System.out.println(translate.transform(p, null));
		
		
		
		AffineTransform scale= AffineTransform.getScaleInstance(400/8000.0, 300 / 9000.0);
		System.out.println("Scale:" + scale.toString());
		p = new Point2D.Double(28.343434, 41.564534);
		System.out.println(scale.transform(p, null));

		
		
		AffineTransform mirror_y = new AffineTransform(1, 0, 0, -1, 0, 300);
		System.out.println("Mirror:" + mirror_y.toString());
		p = new Point2D.Double(28.343434, 41.564534);
		System.out.println(mirror_y.transform(p, null));
		
		
		
		AffineTransform world2pixel = new AffineTransform(mirror_y);
		world2pixel.concatenate(scale);
		world2pixel.concatenate(translate);
		System.out.println("World2Pixel:" + world2pixel.toString());

		p = new Point2D.Double(2000,3000);
		System.out.println("LLC: " + world2pixel.transform(p,null));
		p = new Point2D.Double(2000,12000);
		System.out.println("ULC: " + world2pixel.transform(p,null));
		p = new Point2D.Double(10000,12000);
		System.out.println("URC: " + world2pixel.transform(p,null));
		p = new Point2D.Double(10000,3000);
		System.out.println("LRC: " + world2pixel.transform(p,null));
		
		
		
		AffineTransform pixel2World=null;
		
		try {
			
		    pixel2World = world2pixel.createInverse();
		    
		} catch (NoninvertibleTransformException e) {
			
		    e.printStackTrace();
		}
		System.out.println("Pixel2World:" + pixel2World.toString());

		p = new Point2D.Double(200,150);
		
		System.out.println("World : " + pixel2World.transform(p,null));
	}
}