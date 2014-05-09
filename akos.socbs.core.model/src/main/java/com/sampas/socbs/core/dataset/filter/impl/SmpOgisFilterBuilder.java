package com.sampas.socbs.core.dataset.filter.impl;

import org.geotools.factory.Hints;
import org.geotools.filter.FilterBuilder;
import org.opengis.filter.FilterFactory;

public class SmpOgisFilterBuilder extends FilterBuilder {

	public SmpOgisFilterBuilder() {
		super();
	}

	public SmpOgisFilterBuilder(FilterFactory filterFactory) {
		super(filterFactory);
	}

	public SmpOgisFilterBuilder(Hints hints) {
		super(hints);
	}

}
