package com.sampas.socbs.core.network.impl;

import java.util.HashMap;

import com.sampas.socbs.core.network.IGraphReaderWriter;
/**
 * An abstract implementation of the GraphReaderWriter interface.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/io/standard/AbstractReaderWriter.java $
 */
public abstract class SmpAbstractReaderWriter implements IGraphReaderWriter {
  private HashMap m_properties;
  
  /** GraphGenerator property key **/
  public static final String GENERATOR = "GENERATOR";
  
  /** GraphBuilder property key **/
  public static final String BUILDER = "BUILDER";
  
  /** Node write / read flag **/
  public static final String NODES = "NODES";
  
  /** Edge write / read flag **/
  public static final String EDGES = "EDGES";
  
  /**
   * Constructs an AbstractReaderWriter.
   */
  public SmpAbstractReaderWriter() {
    m_properties = new HashMap();  
  }
  
  /**
   * Sets a property. Some properties dont have values associated with them, 
   * they are just set, and unset.
   * 
   * @param name Name of property
   */
  public void setProperty(String name) {
    setProperty(name, new Object());  
  }
  
  /**
   * @see IGraphReaderWriter#setProperty(String, Object)
   */
  public void setProperty(String name, Object obj) {
    m_properties.put(name, obj);    
  }

  /**
   * @see IGraphReaderWriter#getProperty(String)
   */
  public Object getProperty(String name) {
    return(m_properties.get(name));
  } 
}
