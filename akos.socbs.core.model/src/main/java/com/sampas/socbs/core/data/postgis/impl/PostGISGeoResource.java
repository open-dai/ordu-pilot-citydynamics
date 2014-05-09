package com.sampas.socbs.core.data.postgis.impl;

import org.geotools.catalog.DataStoreService;
import org.geotools.catalog.FeatureSourceGeoResource;

public class PostGISGeoResource extends FeatureSourceGeoResource {

	public PostGISGeoResource( DataStoreService parent, String name ) {
		super(parent, name);
	}

}
