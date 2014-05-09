package com.sampas.socbs.core.network.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SmpParseErrorHandler 
  extends DefaultHandler implements Serializable {
 
  ArrayList m_parseErrors = null;
  
  public SmpParseErrorHandler() {
    super();       
    m_parseErrors = new ArrayList();
  }
  
  public void error(SAXParseException e) throws SAXException {
    super.error(e);
    m_parseErrors.add(e);
  }
  
  public void fatalError(SAXParseException e) throws SAXException {
	  super.fatalError(e);
    m_parseErrors.add(e);
  }
  
  public void reset() {
    m_parseErrors.clear();    
  }

  public boolean noErrors() {
    return(m_parseErrors.size() == 0);    
  }
  
  public void printErrors() {
    for(Iterator itr = m_parseErrors.iterator(); itr.hasNext();) {
      SAXParseException e = (SAXParseException)itr.next();
      System.out.println(e.getMessage());           
    }
  }
  
  public String toString() {
    StringBuffer out = new StringBuffer();
    for (Iterator itr = m_parseErrors.iterator(); itr.hasNext();) {
      SAXParseException e = (SAXParseException)itr.next();
      out.append(e.getMessage());               
    }
    
    return(out.toString());
  }
}