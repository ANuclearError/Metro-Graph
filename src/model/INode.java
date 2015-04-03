package model;

/**
 * A node is an element within a multigraph, it consists of an ID and a label,
 * this interface defines the requirements of such a node. While IDs should be
 * unique in a graph, the labels do not need to be so.
 * 
 * @author Aidan O'Grady
 * @since 0.3
 *
 */
public interface INode {
	
	/**
	 * Returns the label of this node
	 * @return label
	 */
	public String getLabel();
	
	/**
	 * Returns the ID of this node
	 * @return id
	 */
	public int getID();
}
