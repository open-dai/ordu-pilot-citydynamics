/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2003-2006, GeoTools Project Managment Committee (PMC)
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

import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.coverage.grid.io.GridFormatFactorySpi;
import org.opengis.coverage.grid.Format;
import com.esri.sde.sdk.client.SeConnection;
import com.esri.sde.sdk.pe.PeCoordinateSystem;

/**
 * Implementation of the GridCoverageFormat service provider interface for
 * ArcSDE Databases. Based on the Arc Grid implementation.
 * 
 * 
 * 
 * @author Saul Farber (saul.farber)
 * @author aaime
 * @author Simone Giannecchini (simboss)
 * @source $URL:
 *         http://svn.geotools.org/geotools/trunk/gt/modules/plugin/arcsde/datastore/src/main/java/org/geotools/arcsde/ArcSDERasterFormatFactory.java $
 */
@SuppressWarnings({ "unchecked", "deprecation" })
public class ArcSDERasterFormatFactory implements GridFormatFactorySpi {

    /** package's logger */
    protected static final Logger LOGGER = org.geotools.util.logging.Logging
            .getLogger(ArcSDERasterFormatFactory.class.getPackage().getName());

    /** friendly factory description */
    //private static final String FACTORY_DESCRIPTION = "ESRI(tm) ArcSDE 9.x Raster Support via GridCoverageExchange Interface";

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public boolean isAvailable() {
        LOGGER.fine("Checking availability of ArcSDE Jars.");
        try {
            LOGGER.fine(SeConnection.class.getName() + " is in place.");
            LOGGER.fine(PeCoordinateSystem.class.getName() + " is in place.");
        } catch (Throwable t) {
            LOGGER.log(Level.WARNING, "ArcSDE Java API seems to not be on your classpath. Please"
                    + " verify that all needed jars are. ArcSDE data stores"
                    + " will not be available.", t);
            return false;
        }

        return true;
    }

    public Format createFormat() {
        return new ArcSDERasterFormat();
    }

    /**
     * Returns the implementation hints. The default implementation returns en
     * empty map.
     * 
     * @return DOCUMENT ME!
     */
    public Map getImplementationHints() {
        return Collections.EMPTY_MAP;
    }
}
