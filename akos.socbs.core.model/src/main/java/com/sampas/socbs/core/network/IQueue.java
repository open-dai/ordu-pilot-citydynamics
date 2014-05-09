package com.sampas.socbs.core.network;

public interface IQueue {
 
  public void enq(Object object);
  
  public Object deq();
  
  public boolean isEmpty();
  
  public void clear(); 
}