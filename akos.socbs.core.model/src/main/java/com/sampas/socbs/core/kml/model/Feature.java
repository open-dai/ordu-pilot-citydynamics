package com.sampas.socbs.core.kml.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  ctosunoglu
 */
public abstract class Feature extends KmlObject {
	
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	private Boolean visibility;
	private Boolean open;
	/**
	 * @uml.property  name="atomAuthor"
	 * @uml.associationEnd  
	 */
	private AtomAuthor atomAuthor;
	/**
	 * @uml.property  name="atomLink"
	 * @uml.associationEnd  
	 */
	private AtomLink atomLink;
	/**
	 * @uml.property  name="address"
	 */
	private String address;
	/**
	 * @uml.property  name="xalAddressDeatails"
	 */
	private String xalAddressDeatails;
	/**
	 * @uml.property  name="phoneNumber"
	 */
	private String phoneNumber;
	/**
	 * @uml.property  name="snippet"
	 */
	private String snippet;
	/**
	 * @uml.property  name="snippetMaxLines"
	 */
	private Integer snippetMaxLines;
	/**
	 * @uml.property  name="description"
	 */
	private String description;
	/**
	 * @uml.property  name="abstractView"
	 * @uml.associationEnd  
	 */
	private AbstractView abstractView;
	/**
	 * @uml.property  name="timePrimitive"
	 * @uml.associationEnd  
	 */
	private TimePrimitive timePrimitive;
	/**
	 * @uml.property  name="styleUrl"
	 */
	private String styleUrl;
	/**
	 * @uml.property  name="styleSelectors"
	 */
	private List<StyleSelector> styleSelectors;
	/**
	 * @uml.property  name="region"
	 * @uml.associationEnd  
	 */
	private Region region;
	/**
	 * @uml.property  name="extendedData"
	 * @uml.associationEnd  
	 */
	private ExtendedData extendedData;
	
	public Feature() {}
	
	
	public Feature(String name, Boolean visibility, Boolean open, AtomAuthor atomAuthor, AtomLink atomLink, String address, String xalAddressDetails, String phoneNumber, String snippet, Integer snippetMaxLines,String description, AbstractView abstractView, TimePrimitive timePrimitive, String styleUrl, List<StyleSelector> styleSelectors, Region region, ExtendedData extendedData) {
		this.name = name;
		this.visibility = visibility;
		this.open = open;
		this.atomAuthor = atomAuthor;
		this.atomLink = atomLink;
		this.address = address;
		this.xalAddressDeatails = xalAddressDetails;
		this.phoneNumber = phoneNumber;
		this.snippet = snippet;
		this.snippetMaxLines = snippetMaxLines;
		this.description = description;
		this.abstractView = abstractView;
		this.timePrimitive = timePrimitive;
		this.styleUrl = styleUrl;
		this.styleSelectors = styleSelectors;
		this.region = region;
		this.extendedData = extendedData;;
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
	
	public Boolean isVisible() {
		return visibility;
	}
	
	/**
	 * @param visibility
	 * @uml.property  name="visibility"
	 */
	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	/**
	 * @return
	 * @uml.property  name="atomAuthor"
	 */
	public AtomAuthor getAtomAuthor() {
		return atomAuthor;
	}

	/**
	 * @param atomAuthor
	 * @uml.property  name="atomAuthor"
	 */
	public void setAtomAuthor(AtomAuthor atomAuthor) {
		this.atomAuthor = atomAuthor;
	}

	/**
	 * @return
	 * @uml.property  name="atomLink"
	 */
	public AtomLink getAtomLink() {
		return atomLink;
	}

	/**
	 * @param link
	 * @uml.property  name="atomLink"
	 */
	public void setAtomLink(AtomLink link) {
		this.atomLink = link;
	}

	/**
	 * @return
	 * @uml.property  name="address"
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address
	 * @uml.property  name="address"
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return
	 * @uml.property  name="xalAddressDeatails"
	 */
	public String getXalAddressDeatails() {
		return xalAddressDeatails;
	}

	/**
	 * @param xalAddressDeatails
	 * @uml.property  name="xalAddressDeatails"
	 */
	public void setXalAddressDeatails(String xalAddressDeatails) {
		this.xalAddressDeatails = xalAddressDeatails;
	}

	/**
	 * @return
	 * @uml.property  name="phoneNumber"
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @param phoneNumber
	 * @uml.property  name="phoneNumber"
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return
	 * @uml.property  name="snippet"
	 */
	public String getSnippet() {
		return snippet;
	}

	/**
	 * @param snippet
	 * @uml.property  name="snippet"
	 */
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	/**
	 * @return
	 * @uml.property  name="snippetMaxLines"
	 */
	public Integer getSnippetMaxLines() {
		return snippetMaxLines;
	}

	/**
	 * @param snippetMaxLines
	 * @uml.property  name="snippetMaxLines"
	 */
	public void setSnippetMaxLines(Integer snippetMaxLines) {
		this.snippetMaxLines = snippetMaxLines;
	}

	/**
	 * @return
	 * @uml.property  name="description"
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) {
		this.description = description;
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

	/**
	 * @return
	 * @uml.property  name="styleUrl"
	 */
	public String getStyleUrl() {
		return styleUrl;
	}
	
	/**
	 * @param styleUrl
	 * @uml.property  name="styleUrl"
	 */
	public void setStyleUrl(String styleUrl) {
		this.styleUrl = styleUrl;
	}
	
	/**
	 * @return
	 * @uml.property  name="styleSelectors"
	 */
	public List<StyleSelector> getStyleSelectors() {
		return styleSelectors;
	}

	/**
	 * @param styleSelectors
	 * @uml.property  name="styleSelectors"
	 */
	public void setStyleSelectors(List<StyleSelector> styleSelectors) {
		this.styleSelectors = styleSelectors;
	}
	
	public void addStyleSelector(StyleSelector styleSelector) {
		if (styleSelectors == null) {
			styleSelectors = new ArrayList<StyleSelector>();
		}
		styleSelectors.add(styleSelector);
	}

	/**
	 * @return
	 * @uml.property  name="timePrimitive"
	 */
	public TimePrimitive getTimePrimitive() {
		return timePrimitive;
	}

	/**
	 * @param timePrimitive
	 * @uml.property  name="timePrimitive"
	 */
	public void setTimePrimitive(TimePrimitive timePrimitive) {
		this.timePrimitive = timePrimitive;
	}

	/**
	 * @return
	 * @uml.property  name="region"
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region
	 * @uml.property  name="region"
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return
	 * @uml.property  name="extendedData"
	 */
	public ExtendedData getExtendedData() {
		return extendedData;
	}

	/**
	 * @param extendedData
	 * @uml.property  name="extendedData"
	 */
	public void setExtendedData(ExtendedData extendedData) {
		this.extendedData = extendedData;
	}
	
	
	public void writeInner(Kml kml) throws KmlException {
		if (name != null) {
			kml.println("<name>" + name + "</name>");
		}
		if (visibility != null) {
			kml.println("<visibility>" + booleanToInt(visibility) + "</visibility>");
		}
		if (open != null) {
			kml.println("<open>" + booleanToInt(open) + "</open>");
		}
		if (atomAuthor != null) {
			atomAuthor.write(kml);
		}
		if (atomLink != null) {
			atomLink.write(kml);
		}
		if (address != null) {
			kml.println("<address>" + address + "</address>");
		}
		if (xalAddressDeatails != null) {
			kml.println("<xal:AddressDetails>" + xalAddressDeatails + "</xal:AddressDetails>");
		}
		if (phoneNumber != null) {
			kml.println("<phoneNumber>" + phoneNumber + "</phoneNumber>");
		}
		if (snippet != null) {
			kml.println("<snippet maxLines=\"" + (snippetMaxLines != null ? snippetMaxLines : "2" ) + "\">" + snippet + "</snippet>");
		}
		if (description != null) {
			kml.println("<description>" + description + "</description>");
		}
		if (abstractView != null) {
			abstractView.write(kml);
		}
		if (timePrimitive != null) {
			timePrimitive.write(kml);
		}
		if (styleUrl!= null) {
			kml.println("<styleUrl>" + styleUrl + "</styleUrl>");
		}
		if (styleSelectors != null) {
			for (StyleSelector styleSelector : styleSelectors) {
				styleSelector.write(kml);				
			}
		}
		if (region != null) {
			region.write(kml);
		}
		if (extendedData != null) {
			extendedData.write(kml);
		}
	}

}
