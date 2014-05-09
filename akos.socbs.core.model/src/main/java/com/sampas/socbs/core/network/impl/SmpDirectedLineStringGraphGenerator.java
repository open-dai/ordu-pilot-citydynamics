package com.sampas.socbs.core.network.impl;



public class SmpDirectedLineStringGraphGenerator extends SmpLineStringGraphGenerator {

  public SmpDirectedLineStringGraphGenerator() {
    super();
    setGraphBuilder(new SmpBasicDirectedGraphBuilder());
  }
	
  	
}