package model;

/**
 * A station represents a train station in its metro map. It has a unique ID and
 * a name. It can be connected to and from other nodes in the station.
 * 
 * @author Aidan O'Grady
 * @since 0.4
 *
 */
public class Station implements INode{
	
	/**
	 * The ID of this station.
	 */
	private int id;
	
	/**
	 * The name of this station.
	 */
	private String label;
	
	/**
	 * Constructor
	 * @param id - the ID to be given to this station
	 * @param label - the label to be given to this station
	 */
	public Station(int id, String label){
		this.id = id;
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public int getID() {
		return id;
	}
	
	@Override
	public String toString(){
		return label;
	}
}
