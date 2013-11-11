/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

import org.omg.CORBA.Object;


public interface GetMapByCoordinateResponseType extends Object {

	ImageType getMapImage();

	void setMapImage(ImageType value);

}
