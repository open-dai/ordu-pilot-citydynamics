package com.sampas.socbs.core.kml.model;

/**
 * @author  ctosunoglu
 */
public class Link extends KmlObject {

	/**
	 * @uml.property  name="href"
	 */
	private String href;
	/**
	 * @uml.property  name="refreshMode"
	 * @uml.associationEnd  
	 */
	private RefreshModeEnum refreshMode;
	/**
	 * @uml.property  name="refreshInterval"
	 */
	private Double refreshInterval;
	/**
	 * @uml.property  name="viewRefreshMode"
	 * @uml.associationEnd  
	 */
	private ViewRefreshModeEnum viewRefreshMode;
	/**
	 * @uml.property  name="viewRefreshTime"
	 */
	private Double viewRefreshTime;
	/**
	 * @uml.property  name="viewBoundScale"
	 */
	private Double viewBoundScale;
	/**
	 * @uml.property  name="viewFormat"
	 * @uml.associationEnd  
	 */
	private ViewFormat viewFormat;
	/**
	 * @uml.property  name="httpQuery"
	 */
	private String httpQuery;
	
	public Link() {}
	
	public Link(String href, RefreshModeEnum refreshMode, Double refreshInterval, ViewRefreshModeEnum viewRefreshMode, Double viewRefreshTime, Double viewBoundScale, ViewFormat viewFormat, String httpQuery) {
		this.href = href;
		this.refreshMode = refreshMode;
		this.refreshInterval = refreshInterval;
		this.viewRefreshMode = viewRefreshMode;
		this.viewRefreshTime = viewRefreshTime;
		this.viewFormat = viewFormat;
		this.httpQuery = httpQuery;
	}
	
	/**
	 * @return
	 * @uml.property  name="href"
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href
	 * @uml.property  name="href"
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return
	 * @uml.property  name="refreshMode"
	 */
	public RefreshModeEnum getRefreshMode() {
		return refreshMode;
	}

	/**
	 * @param refreshMode
	 * @uml.property  name="refreshMode"
	 */
	public void setRefreshMode(RefreshModeEnum refreshMode) {
		this.refreshMode = refreshMode;
	}

	/**
	 * @return
	 * @uml.property  name="refreshInterval"
	 */
	public Double getRefreshInterval() {
		return refreshInterval;
	}

	/**
	 * @param refreshInterval
	 * @uml.property  name="refreshInterval"
	 */
	public void setRefreshInterval(Double refreshInterval) {
		this.refreshInterval = refreshInterval;
	}

	/**
	 * @return
	 * @uml.property  name="viewRefreshMode"
	 */
	public ViewRefreshModeEnum getViewRefreshMode() {
		return viewRefreshMode;
	}

	/**
	 * @param viewRefreshMode
	 * @uml.property  name="viewRefreshMode"
	 */
	public void setViewRefreshMode(ViewRefreshModeEnum viewRefreshMode) {
		this.viewRefreshMode = viewRefreshMode;
	}

	/**
	 * @return
	 * @uml.property  name="viewRefreshTime"
	 */
	public Double getViewRefreshTime() {
		return viewRefreshTime;
	}

	/**
	 * @param viewRefreshTime
	 * @uml.property  name="viewRefreshTime"
	 */
	public void setViewRefreshTime(Double viewRefreshTime) {
		this.viewRefreshTime = viewRefreshTime;
	}

	/**
	 * @return
	 * @uml.property  name="viewBoundScale"
	 */
	public Double getViewBoundScale() {
		return viewBoundScale;
	}

	/**
	 * @param viewBoundScale
	 * @uml.property  name="viewBoundScale"
	 */
	public void setViewBoundScale(Double viewBoundScale) {
		this.viewBoundScale = viewBoundScale;
	}

	/**
	 * @return
	 * @uml.property  name="viewFormat"
	 */
	public ViewFormat getViewFormat() {
		return viewFormat;
	}

	/**
	 * @param viewFormat
	 * @uml.property  name="viewFormat"
	 */
	public void setViewFormat(ViewFormat viewFormat) {
		this.viewFormat = viewFormat;
	}

	/**
	 * @return
	 * @uml.property  name="httpQuery"
	 */
	public String getHttpQuery() {
		return httpQuery;
	}

	/**
	 * @param httpQuery
	 * @uml.property  name="httpQuery"
	 */
	public void setHttpQuery(String httpQuery) {
		this.httpQuery = httpQuery;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<Link" + getIdAndTargetIdFormatted(kml) + ">", 1);
		writeInner(kml);
		kml.println(-1, "</Link>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Link" + getIdAndTargetIdFormatted(kml) + "></>");
	}

	protected void writeInner(Kml kml) throws KmlException {
		if (href != null) {
			kml.println("<href>" + href + "</href>");
		}
		if (refreshMode != null) {
			kml.println("<refreshMode>" + refreshMode + "</refreshMode>");
		}
		if (refreshInterval != null) {
			kml.println("<refreshInterval>" + refreshInterval + "</refreshInterval>");
		}
		if (refreshInterval != null) {
			kml.println("<refreshInterval>" + refreshInterval + "</refreshInterval>");
		}
		if (viewRefreshMode != null) {
			kml.println("<viewRefreshMode>" + viewRefreshMode + "</viewRefreshMode>");
		}
		if (viewRefreshTime != null) {
			kml.println("<viewRefreshTime>" + viewRefreshTime + "</viewRefreshTime>");
		}
		if (viewBoundScale != null) {
			kml.println("<viewBoundScale>" + viewBoundScale + "</viewBoundScale>");
		}
		if (viewFormat != null) {
			viewFormat.write(kml);
		}
		if (httpQuery != null) {
			kml.println("<httpQuery>" + httpQuery + "</httpQuery>");
		}
	}
}