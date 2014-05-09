package com.sampas.socbs.core.network.impl;

import com.sampas.socbs.core.network.IGraphTraversal;
import com.sampas.socbs.core.network.IGraphVisitor;
import com.sampas.socbs.core.network.IGraphWalker;
import com.sampas.socbs.core.network.IGraphable;

/**
 * A simple implementation of GraphWalker that decorates a GraphVisitor.
 *
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/traverse/basic/SimpleGraphWalker.java $
 */
public class SmpSimpleGraphWalker implements IGraphWalker {
    
    /** Underlying visitor */
    private IGraphVisitor m_visitor;

    /**
     * Creates a GraphWalker from a preexising GraphVisitor.
     *
     * @param visitor The visitor to decorate
     */
    public SmpSimpleGraphWalker(IGraphVisitor visitor) {
        m_visitor = visitor;
    }

    /**
     * Returns the underlying visitor.
     * 
     * @return The visitor being decorated by the walker. 
     */
    public IGraphVisitor getVistor() {
      return(m_visitor);  
    }
    
    /**
     * Sets the underlying visitor.
     * 
     * @param visitor The visitor to be decorated by the walker.
     */
    public void setVisitor(IGraphVisitor visitor) {
      m_visitor = visitor;  
    }
    
    
    /**
     * Defers to the underlying visitor.
     *
     * @see IGraphWalker#visit(IGraphable, IGraphTraversal)
     */
    public int visit(IGraphable element, IGraphTraversal traversal) {
      return (m_visitor.visit(element));
    }

    /**
     * Does nothing.
     *
     * @see IGraphWalker#finish()
     */
    public void finish() {}
}
