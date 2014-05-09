package com.sampas.socbs.core.kml.model;

import java.util.List;

/**
 * @author  ctosunoglu
 */
public class PhotoOverlay extends Overlay {

	/**
	 * @uml.property  name="rotation"
	 */
	private Double rotation;
	/**
	 * @uml.property  name="leftFov"
	 */
	private Double leftFov;
	/**
	 * @uml.property  name="rightFov"
	 */
	private Double rightFov;
	/**
	 * @uml.property  name="bottomFov"
	 */
	private Double bottomFov;
	/**
	 * @uml.property  name="topFov"
	 */
	private Double topFov;
	/**
	 * @uml.property  name="near"
	 */
	private Double near;
	/**
	 * @uml.property  name="tileSize"
	 */
	private Integer tileSize;
	/**
	 * @uml.property  name="maxWidth"
	 */
	private Integer maxWidth;
	/**
	 * @uml.property  name="maxHeight"
	 */
	private Integer maxHeight;
	/**
	 * @uml.property  name="gridOrigin"
	 * @uml.associationEnd  
	 */
	private GridOriginEnum gridOrigin;
	/**
	 * @uml.property  name="point"
	 * @uml.associationEnd  
	 */
	private Point point;
	/**
	 * @uml.property  name="shape"
	 * @uml.associationEnd  
	 */
	private ShapeEnum shape;
	
	public PhotoOverlay() {}
	
	public PhotoOverlay(String name, Boolean visibility, Boolean open, AtomAuthor atomAuthor, AtomLink atomLink, String address, String xalAddressDetails, String phoneNumber, String snippet, Integer snippetMaxLines,String description, AbstractView abstractView, TimePrimitive timePrimitive, String styleUrl, List<StyleSelector> styleSelectors, Region region, ExtendedData extendedData, String color, Integer drawOrder, Icon icon, Double rotation, Double leftFov, Double rightFov, Double bottomFov, Double topFov, Double near, Integer tileSize, Integer maxWidth, Integer maxHeight, GridOriginEnum gridOrigin, Point point, ShapeEnum shape) {
		super(name, visibility, open, atomAuthor, atomLink, address, xalAddressDetails, phoneNumber, snippet, snippetMaxLines, description, abstractView, timePrimitive, styleUrl, styleSelectors, region, extendedData, color, drawOrder, icon);
		this.rotation = rotation;
		this.leftFov = leftFov;
		this.rightFov = rightFov;
		this.bottomFov = bottomFov;
		this.topFov = topFov;
		this.near = near;
		this.tileSize = tileSize;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.gridOrigin = gridOrigin;
		this.point = point;
		this.shape = shape;
	}
	
	/**
	 * @return
	 * @uml.property  name="rotation"
	 */
	public Double getRotation() {
		return rotation;
	}

	/**
	 * @param rotation
	 * @uml.property  name="rotation"
	 */
	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

	/**
	 * @return
	 * @uml.property  name="leftFov"
	 */
	public Double getLeftFov() {
		return leftFov;
	}

	/**
	 * @param leftFov
	 * @uml.property  name="leftFov"
	 */
	public void setLeftFov(Double leftFov) {
		this.leftFov = leftFov;
	}

	/**
	 * @return
	 * @uml.property  name="rightFov"
	 */
	public Double getRightFov() {
		return rightFov;
	}

	/**
	 * @param rightFov
	 * @uml.property  name="rightFov"
	 */
	public void setRightFov(Double rightFov) {
		this.rightFov = rightFov;
	}

	/**
	 * @return
	 * @uml.property  name="bottomFov"
	 */
	public Double getBottomFov() {
		return bottomFov;
	}

	/**
	 * @param bottomFov
	 * @uml.property  name="bottomFov"
	 */
	public void setBottomFov(Double bottomFov) {
		this.bottomFov = bottomFov;
	}

	/**
	 * @return
	 * @uml.property  name="topFov"
	 */
	public Double getTopFov() {
		return topFov;
	}

	/**
	 * @param topFov
	 * @uml.property  name="topFov"
	 */
	public void setTopFov(Double topFov) {
		this.topFov = topFov;
	}

	/**
	 * @return
	 * @uml.property  name="near"
	 */
	public Double getNear() {
		return near;
	}

	/**
	 * @param near
	 * @uml.property  name="near"
	 */
	public void setNear(Double near) {
		this.near = near;
	}

	/**
	 * @return
	 * @uml.property  name="tileSize"
	 */
	public Integer getTileSize() {
		return tileSize;
	}

	/**
	 * @param tileSize
	 * @uml.property  name="tileSize"
	 */
	public void setTileSize(Integer tileSize) {
		this.tileSize = tileSize;
	}

	/**
	 * @return
	 * @uml.property  name="maxWidth"
	 */
	public Integer getMaxWidth() {
		return maxWidth;
	}

	/**
	 * @param maxWidth
	 * @uml.property  name="maxWidth"
	 */
	public void setMaxWidth(Integer maxWidth) {
		this.maxWidth = maxWidth;
	}

	/**
	 * @return
	 * @uml.property  name="maxHeight"
	 */
	public Integer getMaxHeight() {
		return maxHeight;
	}

	/**
	 * @param maxHeight
	 * @uml.property  name="maxHeight"
	 */
	public void setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
	}

	/**
	 * @return
	 * @uml.property  name="gridOrigin"
	 */
	public GridOriginEnum getGridOrigin() {
		return gridOrigin;
	}

	/**
	 * @param gridOrigin
	 * @uml.property  name="gridOrigin"
	 */
	public void setGridOrigin(GridOriginEnum gridOrigin) {
		this.gridOrigin = gridOrigin;
	}

	/**
	 * @return
	 * @uml.property  name="point"
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @param point
	 * @uml.property  name="point"
	 */
	public void setPoint(Point point) {
		this.point = point;
	}

	/**
	 * @return
	 * @uml.property  name="shape"
	 */
	public ShapeEnum getShape() {
		return shape;
	}

	/**
	 * @param shape
	 * @uml.property  name="shape"
	 */
	public void setShape(ShapeEnum shape) {
		this.shape = shape;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<PhotoOverlay" + getIdAndTargetIdFormatted(kml) + ">", 1);
		super.writeInner(kml);
		if (rotation != null) {
			kml.println("<rotation>" + rotation + "</rotation>");
		}
		if (leftFov != null || rightFov != null || bottomFov != null || topFov != null || near != null) {
			kml.println("<ViewVolume>", 1);
			if (leftFov != null) {
				kml.println("<leftFov>" + leftFov + "</leftFov>");
			}
			if (rightFov != null) {
				kml.println("<rightFov>" + rightFov + "</rightFov>");
			}
			if (bottomFov != null) {
				kml.println("<bottomFov>" + bottomFov + "</bottomFov>");
			}
			if (topFov != null) {
				kml.println("<topFov>" + topFov + "</topFov>");
			}
			if (near != null) {
				kml.println("<near>" + near + "</near>");
			}
			kml.println(-1, "</ViewVolume>");
		}
		if (tileSize != null || maxWidth != null || maxHeight != null || gridOrigin != null) {
			kml.println("<ImagePyramid>", 1);
			if (tileSize != null) {
				kml.println("<tileSize>" + tileSize + "</tileSize>");
			}
			if (maxWidth != null) {
				kml.println("<maxWidth>" + maxWidth + "</maxWidth>");
			}
			if (maxHeight != null) {
				kml.println("<maxHeight>" + maxHeight + "</maxHeight>");
			}
			if (gridOrigin != null) {
				kml.println("<gridOrigin>" + gridOrigin + "</gridOrigin>");
			}
			kml.println(-1, "</ImagePyramid>");
			if (point != null) {
				point.write(kml);
			}
			if (shape != null) {
				kml.println("<shape>" + shape + "</shape>");
			}
		}
		kml.println(-1, "</PhotoOverlay>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<PhotoOverlay" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}