
package GeoInfoMngmnt.CityDynamics.V1;


/**
 * Map projection type definition
 */
public class MapProjectionType   {
	
    private GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate;

    private int width;
    private int height;    

    private int dimension;

    public MapProjectionType() {
    }

    /**
     * Gets the coordinate value for this MapProjectionType.
     * 
     * @return coordinate
     */
    public GeoInfoMngmnt.CityDynamics.V1.CoordinateType getCoordinate() {
        return coordinate;
    }


    /**
     * Sets the coordinate value for this MapProjectionType.
     * 
     * @param coordinate
     */
    public void setCoordinate(GeoInfoMngmnt.CityDynamics.V1.CoordinateType coordinate) {
        this.coordinate = coordinate;
    }

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	

	
  

}
