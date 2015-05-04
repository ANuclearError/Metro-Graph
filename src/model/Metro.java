package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A metro is a subway system that connects many stations together through
 * one or more lines. 
 * 
 * @author Aidan O'Grady
 * @since 0.4
 *
 */
public class Metro implements IMultiGraph{
	
	/**
	 * All the nodes that are part of this metro system.
	 */
	private List<INode> nodes;
	
	/**
	 * All the edges that connect the nodes in this metro system.
	 */
	private List<IEdge> edges;
	
	/**
	 * Constructor.
	 */
	public Metro(){
		nodes = new ArrayList<INode>();
		edges = new ArrayList<IEdge>();
	}

	@Override
	public boolean addNode(INode node) {
		if(nodes == null || nodes.contains(node))
			return false;
		return nodes.add(node);
	}

	@Override
	public boolean addEdge(IEdge edge) {
		if(edge == null || edges.contains(edge))
			return false;
		return edges.add(edge);
	}

	@Override
	public List<IEdge> getPath(INode source, INode dest) {
		return null;
	}

	@Override
	public List<INode> getNodes() {
		return nodes;
	}

	@Override
	public List<IEdge> getEdges() {
		return edges;
	}
	
	@Override
	public INode getNodeByID(int id){
		for(INode station : nodes){
			if(station.getID() == id)
				return station;
		}
		return null;
	}

	@Override
	public List<INode> getNodesWithLabel(String label) {
		List<INode> stations = new ArrayList<INode>();
		for(INode station : nodes){
			if(station.getLabel().equalsIgnoreCase(label))
				stations.add(station);
		}
		return stations;
	}
}
