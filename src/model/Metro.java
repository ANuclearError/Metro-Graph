package model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
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
		Deque<List<IEdge>> paths = new LinkedList<List<IEdge>>();
		List<IEdge> visited = new ArrayList<IEdge>();
		List<IEdge> path;
		
		for (IEdge line : getLinesFrom(source)) {
			path = new ArrayList<IEdge>();
			path.add(line);
			paths.add(path);
		}
		
		List<IEdge> current;
		IEdge last;
		int size;
		
		// Breadth first search implementation, with a check on visited edges
		while (!paths.isEmpty()) {
			current = paths.pollFirst();
			size = current.size();
			last = current.get(size - 1);
			
			if (last.getToNode().equals(dest))
				return current;
			
			for (List<IEdge> extend : extendPath(current)) {
				if(!visited.contains(extend.get(extend.size() - 1)))
					paths.addLast(extend);
			}
			visited.add(last);
		}

		return new ArrayList<IEdge>();
	}

	/**
	 * Given an existing path within the graph, returns a list of all the
	 * possible extensions of that path.
	 * 
	 * @param path - the path to be extended
	 * @return list of all extensions
	 */
	private List<List<IEdge>> extendPath(List<IEdge> path) {
		List<List<IEdge>> extendedPath = new ArrayList<List<IEdge>>();
		
		int last = path.size() - 1;
		IEdge next = path.get(last);
		
		List<IEdge> nextStates = getLinesFrom(next.getToNode());
		
		for(IEdge result : nextStates){
			List<IEdge> newPath = new ArrayList<IEdge>(path);
			newPath.add(result);
			extendedPath.add(newPath);
		}
		
		return extendedPath;
	}

	/**
	 * Given a station, returns all the lines that go from that station
	 * @param source - the station the lines come from
	 * @return list of lines starting from that station
	 */
	private List<IEdge> getLinesFrom(INode source) {
		List<IEdge> lines = new ArrayList<IEdge>();
		
		for (IEdge line : edges) {
			if (line.getFromNode().equals(source))
				lines.add(line);
		}
		return lines;
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
		label = label.toLowerCase();
		List<INode> stations = new ArrayList<INode>();
		String stationName;
		for(INode station : nodes){
			stationName = station.getLabel().toLowerCase();
			if(stationName.contains(label))
				stations.add(station);
		}
		return stations;
	}
}
