package com.sampas.socbs.core.processing.services.impl;

import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.geometry.impl.SmpGeometry;
import com.sampas.socbs.core.processing.services.IGeometryCreator;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;


public class SmpGeometryCreator implements IGeometryCreator {
	
	public IGeometry createLineGeometryFromWKT(String lineWKT) {
		if (lineWKT == null) {
			return null;
		} else {
			WKTReader wktReader = new WKTReader();
			try {
				LineString geometry = (LineString) wktReader.read(lineWKT);
				return (new SmpGeometry(geometry));
			} catch (Exception ex) {
				System.err.println("Error on creating line geometry from WKT !!!");
				return null;
			}
		}
	}

	public IGeometry createPointGeometryFromWKT(String pointWKT) {
		if (pointWKT == null) {
			return null;
		} else {
			WKTReader wktReader = new WKTReader();
			try {
				Point geometry = (Point) wktReader.read(pointWKT);
				return (new SmpGeometry(geometry));
			} catch (Exception ex) {
				System.err.println("Error on creating point geometry from WKT !!!");
				return null;
			}
		}
	}

	@Override
	public IGeometry createPolygonGeometryFromWKT(String polygonWKT) {
		if (polygonWKT == null) {
			return null;
		} else {
			WKTReader wktReader = new WKTReader();
			try {
				Polygon geometry = (Polygon) wktReader.read(polygonWKT);
				return (new SmpGeometry(geometry));
			} catch (Exception ex) {
				System.err.println("Error on creating Polygon geometry from WKT !!!");
				return null;
			}
		}
	}
}