package model;

/**
 * An edge is a directed connection from one node to another within a graph. It
 * consists of a source node and destination node as well as a label for
 * referring to. Labels do not need to be unique.
 * 
 * @author Aidan O'Grady
 * @since 0.3
 *
 */
public interface IEdge {
	
	/**
	 * Returns the label of this graph.
	 * @return
	 */
	public String getLabel();
	
	/**
	 * Returns the ID of the node that this comes from.
	 * @return formNode
	 */
	public int getFromNode();
	
	/**
	 * Returns the ID of the node that this goes to.
	 * @return toNode
	 */
	public int getToNode();
}
