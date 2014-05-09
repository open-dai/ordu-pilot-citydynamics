package com.sampas.socbs.core.network.impl;

import java.util.HashMap;

public class SmpIndexedStack extends java.util.Stack {
  private HashMap m_index; //object to index in stack 
  
  public SmpIndexedStack() {
    super();
    m_index = new HashMap();  
  }
  
  public Object push(Object item) {
    m_index.put(item, new Integer(size()));
    return super.push(item);
  }

  public Object pop() {
    Object value = super.pop();
    m_index.remove(value);
    return(value);
  }

  public boolean contains(Object elem) {
    return(m_index.get(elem) != null);
  }

}