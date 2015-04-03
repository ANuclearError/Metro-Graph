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
	private INode from;
	
	/**
	 * The node this line goes towards.
	 */
	private INode to;
	
	public Line(String label, INode from, INode to){
		this.label = label;
		this.from = from;
		this.to = to;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public INode getFromNode() {
		return from;
	}

	@Override
	public INode getToNode() {
		return to;
	}

}
