/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002-2006, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */
package com.sampas.socbs.core.data.arcsde.impl;

/**
 * Exception that can be thrown if an error occurs while creating a
 * <code>Geometryy</code> from a <code>SeShape</code> or viceversa
 * 
 * @author Gabriel Rold?n
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/unsupported/arcsde/datastore/src/main/java/org/geotools/arcsde/data/ArcSDEGeometryBuildingException.java $
 * @version $Id: ArcSDEGeometryBuildingException.java 24660 2007-03-02 16:10:44Z saul.farber $
 */
@SuppressWarnings("serial")
public class ArcSDEGeometryBuildingException extends Exception {
	/**
	 * Creates a new ArcSDEGeometryBuildingException object.
	 * 
	 * @param msg
	 *            DOCUMENT ME!
	 */
	public ArcSDEGeometryBuildingException(String msg) {
		this(msg, null);
	}

	/**
	 * Creates a new ArcSDEGeometryBuildingException object.
	 * 
	 * @param msg
	 *            DOCUMENT ME!
	 * @param cause
	 *            DOCUMENT ME!
	 */
	public ArcSDEGeometryBuildingException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
