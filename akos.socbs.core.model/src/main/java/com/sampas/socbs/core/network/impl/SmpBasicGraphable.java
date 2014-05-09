package com.sampas.socbs.core.network.impl;
 
import java.io.Serializable;

import com.sampas.socbs.core.network.IGraphable;
/**
 * Basic implementation of Graphable. This class serves as the root in the 
 * hierarchy of basic graph components. <BR>
 * <BR>
 * Components in the basic hierarchy implement the Serializable interface. 
 * However serialization will fail if a Graphable object contains a reference
 * to a non serializable object.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/structure/basic/BasicGraphable.java $
 */
public abstract class SmpBasicGraphable implements IGraphable, Serializable {
  
    /** Used to generate id's for graph components */
    private static int id = 0;

    /** underlying object of component **/
    private Object m_obj;
    
    /** Flag to indicate wether the component has been visited */
    private boolean m_visited;

    /** A counter to track how many times a component has been visited */
    private int m_nvisited;
    
    /** Id for component. */
    private int m_id;

    /**
     * Constrcuts a new a graph component. Sets the visited flag to false,
     * counter to -1, and generates a new id.
     */
    public SmpBasicGraphable () {
       m_visited = false;
       m_nvisited = -1;
       m_id = id++;
    }
    
    /**
     * @see IGraphable#getID()
     */
    public int getID() {
      return(m_id);
    }
    
    /**
     * @see IGraphable#setID(int)
     */
    public void setID(int id) {
      m_id = id;  
    }
      
    /**
     * @see IGraphable#getObject()
     */
    public Object getObject() {
      return(m_obj);
    }
    
    /**
     * @see IGraphable#setObject(Object)
     */
    public void setObject(Object obj) {
      m_obj = obj;  
    }
    
    /**
     * @see IGraphable#isVisited()
     */
    public boolean isVisited() {
      return (m_visited);
    }

    /**
     * @see IGraphable#setVisited(boolean)
     */
    public void setVisited(boolean visited) {
      m_visited = visited;
    }

    /**
     * @see IGraphable#getCount()
     */
    public int getCount() {
      return (m_nvisited);
    }

    /**
     * @see IGraphable#setCount(int)
     */
    public void setCount(int count) {
      m_nvisited = count;
    }
    
    /**
     * Returns the id of the component as a string.
     * 
     * @see IGraphable#getID()
     */
    public String toString() {
      return(String.valueOf(m_id));
	  }
}
