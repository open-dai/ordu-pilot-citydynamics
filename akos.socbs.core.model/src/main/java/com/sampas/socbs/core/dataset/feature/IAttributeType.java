package com.sampas.socbs.core.dataset.feature;

import org.geotools.feature.AttributeType;
import org.geotools.feature.IllegalAttributeException;
import org.opengis.filter.Filter;

public interface IAttributeType extends AttributeType {
	
	String getName();
	String getLocalName();
	Filter getRestriction();
	boolean isNillable();
	int getMinOccurs();
	int getMaxOccurs();
	Object parse(Object value) throws IllegalArgumentException;
	void validate(Object obj) throws IllegalArgumentException;
	Object duplicate(Object src) throws IllegalAttributeException;
	Object createDefaultValue();

}
