package com.sampas.socbs.core.network;
/**
 * Reads and writes features to and from some permanent form. 
 *  
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/io/GraphReaderWriter.java $
 */
public interface IGraphReaderWriter {
  
  /**
   * Sets a property for the reader/writer.
   * 
   * @param name Name of the property.
   * @param obj Value of the property.
   */
  public void setProperty(String name, Object obj);
  
  /**
   * Returns a property for the reader/writer. This method will return null
   * if the property has not been set with a call to setProperty(String,Object).
   * 
   * @param name Name of the property.
   * @return Value of the property or null if the property is undefined.
   */
  public Object getProperty(String name);
  
  /**
   * Creates a Graph from some permanent representation.
   * 
   * @return The represented graph.
   * 
   * @throws Exception
   */
  public IGraph read() throws Exception;
  
  /**
   * Writes the graph to a permanent representation.
   * 
   * @param g The graph to be 
   * @throws Exception
   */
  public void write(IGraph g) throws Exception; 

}
