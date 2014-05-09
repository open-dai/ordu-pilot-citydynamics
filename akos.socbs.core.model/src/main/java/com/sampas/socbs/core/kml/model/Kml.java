package com.sampas.socbs.core.kml.model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author  ctosunoglu
 */
public class Kml {

	/**
	 * @uml.property  name="networkLinkControl"
	 * @uml.associationEnd  
	 */
	protected NetworkLinkControl networkLinkControl;
	/**
	 * @uml.property  name="feature"
	 * @uml.associationEnd  
	 */
	protected Feature feature;
	/**
	 * @uml.property  name="celestialData"
	 */
	protected boolean celestialData = false;
	/**
	 * @uml.property  name="atomElementsIncluded"
	 */
	protected boolean atomElementsIncluded = false;
	/**
	 * @uml.property  name="generateObjectIds"
	 */
	protected boolean generateObjectIds = true;

	private PrintWriter printWriter;
	private int indentLevel = 0;
	/**
	 * @uml.property  name="xmlIndent"
	 */
	private boolean xmlIndent = false;

	public Kml() {}

	public Kml(NetworkLinkControl networkLinkControl, Feature feature) {
		this.networkLinkControl = networkLinkControl;
		this.feature = feature;
	}

	/**
	 * @param xmlIndent
	 * @uml.property  name="xmlIndent"
	 */
	public void setXmlIndent(boolean xmlIndent) {
		this.xmlIndent = xmlIndent;
	}

	/**
	 * @return
	 * @uml.property  name="xmlIndent"
	 */
	public boolean getXmlIndent() {
		return xmlIndent;
	}

	/**
	 * @return
	 * @uml.property  name="networkLinkControl"
	 */
	public NetworkLinkControl getNetworkLinkControl() {
		return networkLinkControl;
	}

	/**
	 * @param networkLinkControl
	 * @uml.property  name="networkLinkControl"
	 */
	public void setNetworkLinkControl(NetworkLinkControl networkLinkControl) {
		this.networkLinkControl = networkLinkControl;
	}

	/**
	 * @return
	 * @uml.property  name="feature"
	 */
	public Feature getFeature() {
		return feature;
	}

	/**
	 * @param feature
	 * @uml.property  name="feature"
	 */
	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	/**
	 * @return
	 * @uml.property  name="celestialData"
	 */
	public boolean isCelestialData() {
		return celestialData;
	}

	/**
	 * @param celestialData
	 * @uml.property  name="celestialData"
	 */
	public void setCelestialData(boolean celestialData) {
		this.celestialData = celestialData;
	}

	/**
	 * @return
	 * @uml.property  name="atomElementsIncluded"
	 */
	public boolean isAtomElementsIncluded() {
		return atomElementsIncluded;
	}

	/**
	 * @param atomElementsIncluded
	 * @uml.property  name="atomElementsIncluded"
	 */
	public void setAtomElementsIncluded(boolean atomElementsIncluded) {
		this.atomElementsIncluded = atomElementsIncluded;
	}

	/**
	 * @return
	 * @uml.property  name="generateObjectIds"
	 */
	public boolean isGenerateObjectIds() {
		return generateObjectIds;
	}

	/**
	 * @param generateObjectIds
	 * @uml.property  name="generateObjectIds"
	 */
	public void setGenerateObjectIds(boolean generateObjectIds) {
		this.generateObjectIds = generateObjectIds;
	}

	public void print(String string) {
		print(string, 0);
	}

	public void println(String string) {
		println(string, 0);
	}

	public void print(String string, int indentChangeAfter) {
		printIndents();
		indentLevel += indentChangeAfter;
		printWriter.print(string);
	}

	public void println(String string, int indentChangeAfter) {
		printIndents();
		indentLevel += indentChangeAfter;
		printWriter.println(string);
	}

	public void print(int indentChangeBefore, String string) {
		indentLevel += indentChangeBefore;
		printIndents();
		printWriter.print(string);
	}

	public void println(int indentChangeBefore, String string) {
		indentLevel += indentChangeBefore;
		printIndents();
		printWriter.println(string);
	}

	private void printIndents() {
		if (xmlIndent) {
			for (int i = 0; i < indentLevel; i++) {
				printWriter.print("\t");
			}
		}
	}

	public void write(Kml kml) throws KmlException {
		kml.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\"" + (celestialData ? " hint=\"target=sky\"" : "") + (atomElementsIncluded ? " xmlns:atom=\"http://www.w3.org/2005/Atom\"" : "") + ">", 1);
		if (networkLinkControl != null) {
			networkLinkControl.write(kml);
		}
		if (feature != null) {
			feature.write(kml);
		}
		kml.println(-1, "</kml>");
	}

	public void createKml(String fileName) throws KmlException, IOException {
		createKml(new PrintWriter(new BufferedWriter(new FileWriter(fileName))));
	}

	public void createKml(PrintWriter printWriter) throws KmlException, IOException {
		this.printWriter = printWriter;
		println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		write(this);
		this.printWriter.close();
	}

	public void createKmz(String fileName) {
		try {
			String entryFileName;
			if (fileName.toLowerCase().endsWith(".kmz")) {
				entryFileName = fileName.substring(0, fileName.length() - 4) + ".kml";
			} else {
				entryFileName = fileName + ".kml";
				fileName = fileName + ".kmz";
			}
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(fileName));
			out.putNextEntry(new ZipEntry(entryFileName));
			out.write(this.toString().getBytes());
			out.closeEntry();
			out.close();
		} catch (IOException e) {
			System.out.println("Error creating file - " + e.toString());
		}
	}

	public String toString() {
		StringWriter strw = new StringWriter();
		try {
			createKml(new PrintWriter(strw));
		} catch (KmlException ex) {
			Logger.getLogger(Kml.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Kml.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return strw.toString();
	}

}
