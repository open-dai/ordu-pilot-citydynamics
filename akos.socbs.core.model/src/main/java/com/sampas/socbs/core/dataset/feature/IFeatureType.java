package com.sampas.socbs.core.dataset.feature;

import java.net.URI;

public interface IFeatureType {
	
	URI getNamespace();
	String getTypeName();
	String getFeatureType();
	boolean isDescendedFrom(URI nsURI, String typeName);
	boolean isAbstract();
	int getAttributeCount();

}
