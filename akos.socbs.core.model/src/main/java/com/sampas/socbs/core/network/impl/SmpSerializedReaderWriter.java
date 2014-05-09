package com.sampas.socbs.core.network.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import com.sampas.socbs.core.network.IEdge;
import com.sampas.socbs.core.network.IGraph;
import com.sampas.socbs.core.network.IGraphBuilder;
import com.sampas.socbs.core.network.INode;


/**
 * An implementation of GraphReaderWriter that uses java serialization to 
 * read and write graph objects. During the graph serialization process
 * edges are written to the object output stream. Along with the edges, 
 * the two nodes incident to the edge are also written. However, edge adjacency 
 * lists of nodes are <B>not</B> written to the output stream in order to 
 * prevent deep recursive calls that often result in a stack overflow. Therefore
 * it is important that any implementation of the Node interface declare its
 * edge adjacecny list (if any) as transient in order to support graph 
 * serializability. <BR>
 * Because edge adjacency lists are not serialized, they must be reconstructed
 * upon deserialization in order to preserve the original graph structure.<BR>
 * 
 * @author Justin Deoliveira, Refractions Research Inc, jdeolive@refractions.net
 *
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/extension/graph/src/main/java/org/geotools/graph/io/standard/SerializedReaderWriter.java $
 */
public class SmpSerializedReaderWriter extends SmpAbstractReaderWriter
  implements SmpFileReaderWriter {

  /**
   * Deserializes the graph and reconstructs the original structure.
   * 
   * @see IGraphReaderWriter#read()
   */
  public IGraph read() throws Exception {
    //read builder property
    IGraphBuilder builder = (IGraphBuilder)getProperty(BUILDER);
  
    //create file input stream
    ObjectInputStream objin = new ObjectInputStream(
      new BufferedInputStream(
        new FileInputStream((String)getProperty(FILENAME))
      ) 
    );
    
    //read header
    int nnodes = objin.readInt();
    int nedges = objin.readInt();
    
    //rebuild edge collection, upon reading an edge, at the edge to the
    // adjacency list of each of its nodes
    int count = 0;
    while(count++ < nedges) {
      IEdge e = (IEdge)objin.readObject();
      e.getNodeA().setVisited(false);
      e.getNodeB().setVisited(false);
      builder.addEdge(e);
    }
    
    //rebuild node collection
    for (
      Iterator itr = builder.getGraph().getEdges().iterator(); itr.hasNext();
    ) {
      IEdge e = (IEdge)itr.next();
    
      if (!e.getNodeA().isVisited()) {
        e.getNodeA().setVisited(true);
        builder.addNode(e.getNodeA());  
      }  
      
      if (!e.getNodeB().isVisited()) {
        e.getNodeB().setVisited(true);
        builder.addNode(e.getNodeB()); 
      }
    }
    
    //check if object stream is empty, if not, there are nodes of degree 0 
    // in the graph
    try {
      INode n;
      
      while((n = (INode)objin.readObject()) != null) {
        builder.addNode(n);  
      }  
    }
    catch(EOFException ignore) {}
    
    return(builder.getGraph());
  }

  /**
	 * Serializes the graph by writing each edge in the graph to an object 
	 * output stream. If there any nodes of degree 0 in the graph, then they are 
	 * appended to the end of the object output stream.
	 * 
	 * @see IGraphReaderWriter#write()
	 */
  public void write(IGraph graph) throws Exception {
    //create file output stream
    ObjectOutputStream objout = new ObjectOutputStream(
      new BufferedOutputStream(
        new FileOutputStream((String)getProperty(FILENAME))
      )
    );
    
    //create header
    // 1. number of nodes
    // 2. number of edges
    objout.writeInt(graph.getNodes().size());
    objout.writeInt(graph.getEdges().size());
    
    //write out edges (note: nodes do not write out adjacent edges)
    for (Iterator itr = graph.getEdges().iterator(); itr.hasNext();) {
      IEdge e = (IEdge)itr.next();
      objout.writeObject(e);
    }
    
    //write out any nodes that have no adjacent edges
    for (Iterator itr = graph.getNodesOfDegree(0).iterator(); itr.hasNext();) {
      INode n = (INode)itr.next();
      objout.writeObject(n);
    }
    
    objout.flush();
    objout.close();
  }
}
