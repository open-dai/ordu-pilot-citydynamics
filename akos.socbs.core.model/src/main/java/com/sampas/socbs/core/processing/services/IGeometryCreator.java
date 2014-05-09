package com.sampas.socbs.core.processing.services;

import com.sampas.socbs.core.geometry.IGeometry;


public interface IGeometryCreator {
	public abstract IGeometry createLineGeometryFromWKT(String lineWKT);
	public abstract IGeometry createPointGeometryFromWKT(String pointWKT);
	public abstract IGeometry createPolygonGeometryFromWKT(String polygonWKT);
}