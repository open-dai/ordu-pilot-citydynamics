package com.sampas.socbs.core.kml.model;


/**
 * @author  ctosunoglu
 */
public class NetworkLinkControl extends KmlObject {

	/**
	 * @uml.property  name="minRefreshPeriod"
	 */
	private Double minRefreshPeriod;
	/**
	 * @uml.property  name="maxSessionLength"
	 */
	private Double maxSessionLength;
	/**
	 * @uml.property  name="cookie"
	 */
	private String cookie;
	/**
	 * @uml.property  name="message"
	 */
	private String message;
	/**
	 * @uml.property  name="linkName"
	 */
	private String linkName;
	/**
	 * @uml.property  name="linkDescription"
	 */
	private String linkDescription;
	/**
	 * @uml.property  name="linkSnippet"
	 */
	private String linkSnippet;
	/**
	 * @uml.property  name="linkSnippetMaxLines"
	 */
	private Integer linkSnippetMaxLines;
	/**
	 * @uml.property  name="expires"
	 */
	private String expires;
	/**
	 * @uml.property  name="update"
	 * @uml.associationEnd  
	 */
	private Update update;
	/**
	 * @uml.property  name="abstractView"
	 * @uml.associationEnd  
	 */
	private AbstractView abstractView;
	
	public NetworkLinkControl() {}
	
	public NetworkLinkControl(Double minRefreshPeriod, Double maxSessionLength, String cookie, String message, String linkName, String linkDescription, String linkSnippet, Integer linkSnippetMaxLines, String expires, Update update, AbstractView abstractView) {
		this.minRefreshPeriod = minRefreshPeriod;
		this.maxSessionLength = maxSessionLength;
		this.cookie = cookie;
		this.message = message;
		this.linkName = linkName;
		this.linkDescription = linkDescription;
		this.linkSnippet = linkSnippet;
		this.linkSnippetMaxLines = linkSnippetMaxLines;
		this.expires = expires;
		this.update = update;
		this.abstractView = abstractView;
	}
	
	/**
	 * @return
	 * @uml.property  name="minRefreshPeriod"
	 */
	public Double getMinRefreshPeriod() {
		return minRefreshPeriod;
	}

	/**
	 * @param minRefreshPeriod
	 * @uml.property  name="minRefreshPeriod"
	 */
	public void setMinRefreshPeriod(Double minRefreshPeriod) {
		this.minRefreshPeriod = minRefreshPeriod;
	}

	/**
	 * @return
	 * @uml.property  name="maxSessionLength"
	 */
	public Double getMaxSessionLength() {
		return maxSessionLength;
	}

	/**
	 * @param maxSessionLength
	 * @uml.property  name="maxSessionLength"
	 */
	public void setMaxSessionLength(Double maxSessionLength) {
		this.maxSessionLength = maxSessionLength;
	}

	/**
	 * @return
	 * @uml.property  name="cookie"
	 */
	public String getCookie() {
		return cookie;
	}

	/**
	 * @param cookie
	 * @uml.property  name="cookie"
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	/**
	 * @return
	 * @uml.property  name="message"
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 * @uml.property  name="message"
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return
	 * @uml.property  name="linkName"
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * @param linkName
	 * @uml.property  name="linkName"
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * @return
	 * @uml.property  name="linkDescription"
	 */
	public String getLinkDescription() {
		return linkDescription;
	}

	/**
	 * @param linkDescription
	 * @uml.property  name="linkDescription"
	 */
	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}

	/**
	 * @return
	 * @uml.property  name="linkSnippet"
	 */
	public String getLinkSnippet() {
		return linkSnippet;
	}

	/**
	 * @param linkSnippet
	 * @uml.property  name="linkSnippet"
	 */
	public void setLinkSnippet(String linkSnippet) {
		this.linkSnippet = linkSnippet;
	}

	/**
	 * @return
	 * @uml.property  name="linkSnippetMaxLines"
	 */
	public Integer getLinkSnippetMaxLines() {
		return linkSnippetMaxLines;
	}

	/**
	 * @param linkSnippetMaxLines
	 * @uml.property  name="linkSnippetMaxLines"
	 */
	public void setLinkSnippetMaxLines(Integer linkSnippetMaxLines) {
		this.linkSnippetMaxLines = linkSnippetMaxLines;
	}

	/**
	 * @return
	 * @uml.property  name="expires"
	 */
	public String getExpires() {
		return expires;
	}

	/**
	 * @param expires
	 * @uml.property  name="expires"
	 */
	public void setExpires(String expires) {
		this.expires = expires;
	}

	/**
	 * @return
	 * @uml.property  name="update"
	 */
	public Update getUpdate() {
		return update;
	}

	/**
	 * @param update
	 * @uml.property  name="update"
	 */
	public void setUpdate(Update update) {
		this.update = update;
	}

	/**
	 * @return
	 * @uml.property  name="abstractView"
	 */
	public AbstractView getAbstractView() {
		return abstractView;
	}

	/**
	 * @param abstractView
	 * @uml.property  name="abstractView"
	 */
	public void setAbstractView(AbstractView abstractView) {
		this.abstractView = abstractView;
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<NetworkLinkControl" + getIdAndTargetIdFormatted(kml) + ">", 1);
		if (minRefreshPeriod != null) {
			kml.println("<minRefreshPeriod>" + minRefreshPeriod + "</minRefreshPeriod>");
		}
		if (maxSessionLength != null) {
			kml.println("<maxSessionLength>" + maxSessionLength + "</maxSessionLength>");
		}
		if (cookie != null) {
			kml.println("<cookie>" + cookie + "</cookie>");
		}
		if (message != null) {
			kml.println("<message>" + message + "</message>");
		}
		if (linkName != null) {
			kml.println("<linkName>" + linkName + "</linkName>");
		}
		if (linkDescription != null) {
			kml.println("<linkDescription>" + linkDescription + "</linkDescription>");
		}
		if (linkSnippet != null) {
			kml.println("<linkSnippet maxLines=\"" + (linkSnippetMaxLines != null ? linkSnippetMaxLines : "2") + "\">" + linkSnippet + "</linkSnippet>");
		}
		if (expires != null) {
			kml.println("<expires>" + expires + "</expires>");
		}
		if (update != null) {
			update.write(kml);
		}
		if (abstractView != null) {
			abstractView.write(kml);
		}
		kml.println(-1, "</NetworkLinkControl>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<NetworkLinkControl" + getIdAndTargetIdFormatted(kml) + "></>");
	}
}