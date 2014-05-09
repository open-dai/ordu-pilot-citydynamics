package com.sampas.socbs.core.network.impl;

import java.util.Iterator;

import com.sampas.socbs.core.network.IDirectedGraphable;
import com.sampas.socbs.core.network.IGraphable;


public class SmpDirectedDijkstraIterator extends SmpDijkstraIterator {

	public SmpDirectedDijkstraIterator(EdgeWeighter weighter) {
		super(weighter);
	}
	
	protected Iterator getRelated(IGraphable current) {
		return(((IDirectedGraphable)current).getOutRelated());
	}

	
}