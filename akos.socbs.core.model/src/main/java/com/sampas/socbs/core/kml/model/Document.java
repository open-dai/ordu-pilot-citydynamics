package com.sampas.socbs.core.kml.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  ctosunoglu
 */
public class Document extends Container {

	/**
	 * @uml.property  name="schemas"
	 */
	private List<Schema> schemas;
	
	private List<Folder> folders;
	
	public Document() {}
	
	public Document(String name, Boolean visibility, Boolean open, AtomAuthor atomAuthor, AtomLink atomLink, String address, String xalAddressDetails, String phoneNumber, String snippet, Integer snippetMaxLines,String description, AbstractView abstractView, TimePrimitive timePrimitive, String styleUrl, List<StyleSelector> styleSelectors, Region region, ExtendedData extendedData, List<Feature> feauters, List<Schema> schemas) {
		super(name, visibility, open, atomAuthor, atomLink, address, xalAddressDetails, phoneNumber, snippet, snippetMaxLines, description, abstractView, timePrimitive, styleUrl, styleSelectors, region, extendedData, feauters);
		this.schemas = schemas;
	}
	
	/**
	 * @return
	 * @uml.property  name="schemas"
	 */
	public List<Schema> getSchemas() {
		return schemas;
	}

	/**
	 * @param schemas
	 * @uml.property  name="schemas"
	 */
	public void setSchemas(List<Schema> schemas) {
		this.schemas = schemas;
	}
	
	public void addSchema(Schema schema) {
		if (schemas == null) {
			schemas = new ArrayList<Schema>();
		}
		schemas.add(schema);
	}
	
	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}
	
	public void addFolder(Folder folder) {
		if (folders == null) {
			folders = new ArrayList<Folder>();
		}
		folders.add(folder);
	}
	
	public void write(Kml kml) throws KmlException {
		kml.println("<Document" + getIdAndTargetIdFormatted(kml) + ">", 1);
		writeInner(kml);
		if (schemas != null) {
			for (Schema schema: schemas)
			schema.write(kml);
		}
		if (folders != null) {
			for (Folder folder: folders)
			folder.write(kml);
		}
		kml.println(-1, "</Document>");
	}
	
	public void writeDelete(Kml kml) throws KmlException {
		kml.println("<Document" + getIdAndTargetIdFormatted(kml) + "></>");
	}


	
}
