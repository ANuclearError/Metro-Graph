package model;

/**
 * A line is a track between two stations in a metro system. It will run from
 * one station to another, and has a designated label associated with it.
 * 
 * @author Aidan O'Grady
 * @since 0.4 
 *
 */
public class Line implements IEdge{
	
	/**
	 * The label of this line.
	 */
	private String label;
	
	/**
	 * The node this line comes from.
	 */
	private int from;
	
	/**
	 * The node this line goes towards.
	 */
	private int to;
	
	/**
	 * Constructor
	 * @param label - the label of this edge
	 * @param from - the ID of the from node
	 * @param to - the ID of the to node
	 */
	public Line(String label, int from, int to){
		this.label = label;
		this.from = from;
		this.to = to;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public int getFromNode() {
		return from;
	}

	@Override
	public int getToNode() {
		return to;
	}
}
