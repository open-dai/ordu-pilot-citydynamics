package com.sampas.socbs.core.kml.model;


/**
 * @author  ctosunoglu
 */
public class AtomAuthor {

	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="uri"
	 */
	private String uri;
	/**
	 * @uml.property  name="email"
	 */
	private String email;
	
	public AtomAuthor() {}
	
	public AtomAuthor(String name, String uri, String email) {
		this.name = name;
		this.uri = uri;
		this.email = email;
	}
	
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 * @uml.property  name="uri"
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri
	 * @uml.property  name="uri"
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return
	 * @uml.property  name="email"
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 * @uml.property  name="email"
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public AtomAuthor(String name) {
		this.name = name;
	}
	
	public void write(Kml kml) throws KmlException {
		kml.println("<atom:author>", 1);
		if (name != null) {
			kml.println("<atom:name>" + name + "</atom:name>");
		}
		if (uri != null) {
			kml.println("<atom:uri>" + uri + "</atom:uri>");
		}
		if (email != null) {
			kml.println("<atom:email>" + email + "</atom:email>");
		}
		kml.println(-1, "</atom:author>");
		kml.setAtomElementsIncluded(true);
	}
}
