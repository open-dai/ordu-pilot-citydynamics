package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraphable;

/**
 * A GraphIterator that starts iteration from a specefied point.
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/basic/SourceGraphIterator.java $
 */
public abstract class SmpSourceGraphIterator extends SmpAbstractGraphIterator {
  
  /** source of the iteration **/
  private IGraphable m_source;
  
  /**
   * Sets the source for the iteration.
   * 
   * @param source The source of the iteration.
   */
  public void setSource(IGraphable source) {
    m_source = source;  
  }
  
  /**
   * Returns the source of the iteration.
   *  
   * @return The source of the iteration.
   */
  public IGraphable getSource() {
    return(m_source);  
  }
   
}
