package com.sampas.socbs.core.dataset.filter.impl;

import org.opengis.filter.ExcludeFilter;
import org.opengis.filter.FilterVisitor;
import org.opengis.filter.IncludeFilter;

public interface SmpFilterFactory2 extends FilterVisitor {

	void visit(IncludeFilter filter);
	
	void visit(ExcludeFilter filter);
	
}