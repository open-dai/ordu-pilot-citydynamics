package com.sampas.socbs.core.kml.model;


/**
 * @author  ctosunoglu
 */
public class AtomLink {

	/**
	 * @uml.property  name="href"
	 */
	private String href;

	public AtomLink() {}
	
	public AtomLink(String href) {
		this.href = href;
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
	
	public void write(Kml kml) throws KmlException {
		if (href == null) {
			throw new KmlException("href not set for atom:Link");
		}
		kml.println("<atom:link href=\"" + href + "\" />");
		kml.setAtomElementsIncluded(true);
	}
	
}
