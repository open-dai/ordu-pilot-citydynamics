package com.sampas.socbs.core.dataset.filter.spatial;

import org.opengis.filter.spatial.Disjoint;

public class SmpOgisDisjoint extends SmpOgisBinarySpatialOperator {

	private Disjoint openGisDisjoint = null;

	public SmpOgisDisjoint() {

	}

	public SmpOgisDisjoint(Disjoint openGisDisjoint) {

		this.openGisDisjoint = openGisDisjoint;
	}

	public Disjoint getOpenGisDisjoint() {
		
		return (this.openGisDisjoint);
	}
}
