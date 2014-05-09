package com.sampas.socbs.core.network.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.sampas.socbs.core.network.IGraphVisitor;
import com.sampas.socbs.core.network.IGraphable;
import com.sampas.socbs.core.network.INode;


public class SmpExhaustivePathFinder {
  public final static int CONTINUE_PATH = 0;
  public final static int END_PATH_AND_CONTINUE = 1;
  public final static int END_PATH_AND_STOP = 2;
  public final static int KILL_PATH = 3;
  
  private int m_maxitr;
  private int m_maxplen;
  
  public SmpExhaustivePathFinder() {
    this(Integer.MAX_VALUE, Integer.MAX_VALUE);     
  }
  
  public SmpExhaustivePathFinder(int maxitr, int maxplen) {
    m_maxitr = maxitr;
    m_maxplen = maxplen;
  }
  
  public SmpPath getPath(INode from, INode to) {
     	final INode dst = to;
     	IGraphVisitor visitor = new IGraphVisitor() {
     	  public int visit(IGraphable component) {
     		if (component.equals(dst)) return(END_PATH_AND_STOP);
     		return(CONTINUE_PATH);
     	  }
     	};
     	List paths = getPaths(from, visitor);
     	if (paths.isEmpty()) return(null);
     	return((SmpPath)paths.get(0));
  }
  
  public List getPaths(INode from, INode to) {
  	final INode dst = to;
    IGraphVisitor visitor = new IGraphVisitor() {
      public int visit(IGraphable component) {
	    if (component.equals(dst)) return(END_PATH_AND_CONTINUE);
	    return(CONTINUE_PATH);
	  }
    };
    return(getPaths(from, visitor));
  }
  
  public List getPaths(INode from, IGraphVisitor visitor) {
    ArrayList paths = new ArrayList();
    
    //create a map to maintain iterator state
    HashMap node2related = new HashMap();
    
    //create the stack and place start node on
    SmpIndexedStack stack = new SmpIndexedStack();
    stack.push(from);
    
    int iterations = 0;
 O: while(!stack.isEmpty() && (iterations++ < m_maxitr)) {
      //peek the stack
      INode top = (INode)stack.peek();
      
      switch(visitor.visit(top)) {
        case END_PATH_AND_CONTINUE:
          paths.add(new SmpPath(stack));
          stack.pop();
          continue;
        
        case END_PATH_AND_STOP:
          paths.add(new SmpPath(stack));
          break O;
        
        case KILL_PATH:
          stack.pop();
          continue;
          
        case CONTINUE_PATH:
        	
        
      }
      
      Iterator related = null;
      if ((related = (Iterator)node2related.get(top)) == null) {
        related = top.getRelated();
        node2related.put(top,related); 
      } 
      
      while(stack.size() < m_maxplen && related.hasNext()) {
        INode adj = (INode)related.next();
        if (stack.contains(adj)) continue;
        
        //push adjacent onto stack, and reset iterator
        stack.push(adj);
        node2related.put(adj, adj.getRelated());
      
        continue O;
      }
        
      //all adjacent have been processed or are in stack
      stack.pop();
    }   

    return(paths);
  }
}