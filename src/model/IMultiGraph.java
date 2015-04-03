package model;

import java.util.List;

/**
 * A multigraph consists of nodes and edges. Nodes are connected together if
 * there is an edge between them, with there being multiple edges between the
 * same two nodes if required.
 * 
 * @author Aidan O'Grady
 * @since 0.3
 *
 */
public interface IMultiGraph {
	
	/**
	 * Attempts to add a node to the graph. If the node is already in the graph,
	 * or the node is not valid, then false is returned, otherwise true is.
	 * @param node - the node to be added
	 * @return if addition is successful
	 */
	public boolean addNode(INode node);
	
	/**
	 * Attempts to add an edge to the graph. If the edge is already in the
	 * graphic, or if the edge is invalid, then false is returned, otherwise
	 * true is returned.
	 * @param edge - the edge to be added
	 * @return if addition is successful
	 */
	public boolean addEdge(IEdge edge);
	
	/**
	 * Returns a list of all nodes in the graph.
	 * @return number of nodes in graph
	 */
	public List<INode> getNodes();
	
	/**
	 * Returns a list of all edges in the graph.
	 * @return list of edges in graph
	 */
	public List<IEdge> getEdges();
	
	/**
	 * Returns a list of edges that represent a path between the source node and
	 * destination node. If no such path exists, then null is returned
	 * @param source - the start of the path
	 * @param dest - the goal of the graph
	 * @return path of edges connection nodes if exists, otherwise null
	 */
	public List<IEdge> getPath(INode source, INode dest);
}
