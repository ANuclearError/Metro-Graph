package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.IEdge;
import model.IMultiGraph;
import model.INode;
import model.Line;
import model.Metro;
import model.parser.BadFileException;
import model.parser.MetroMapParser;
import view.Input;
import view.Output;

/**
 * The MetroSystem controls the overall application. It retrieves information
 * from the model and communicates with the user via the view.
 * 
 * @author Aidan O'Grady
 * @since 0.5
 *
 */
public class MetroSystem {

	/**
	 * The metro map that will be searched for routes.
	 */
	private IMultiGraph metro;

	/**
	 * Constructor
	 */
	public MetroSystem() {
		metro = new Metro();
	}

	/**
	 * Starts the system, loading the file into the graph.
	 * 
	 * @param filename
	 *            - the file to read the graph from
	 */
	public void start(String filename) {
		try {
			metro = MetroMapParser.generateGraphFromFile(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run();
	}

	/**
	 * The primary loop of the system, goes through the menu until an exit
	 * condition is reached.
	 */
	private void run() {
		menu();
		boolean run = true;
		while (run) {
			findRoute();
			Output.line();
			Output.lineBreak();
			Output.line();
			
			Output.print("Do you want to continue? (Y/N)");
			run = Input.getYesNo();
			Output.line();
			Output.lineBreak();
			Output.line();

		}
		quit();
	}

	/**
	 * Displays the main menu of the system.
	 */
	private void menu() {
		Output.print("Welcome to the Bostom Metro System?");
		Output.line();
		Output.lineBreak();
		Output.line();
	}
	
	private void quit() {
		Output.print("Goodbye");
		System.exit(0);
	}

	
	private void findRoute() {
		Output.print("What is the name of the station you're coming from?");
		INode from = getStation();
		while (from == null) {
			from = getStation();
		}
		
		Output.print("What is the name of the station you're going to?");
		INode to = getStation();
		while (to == null) {
			to = getStation();
		}

		Output.print("Route: " + from.getLabel() + " to " + to.getLabel());
		List<IEdge> path = metro.getPath(from, to);
		Output.printList(trimPath(path));
	}
	
	/**
	 * Returns a station based on the user's input, if such a station does not
	 * exist, it will return null.
	 * 
	 * @return station
	 */
	private INode getStation() {
		String label = Input.getString();
		List<INode> stations = metro.getNodesWithLabel(label);

		if (stations.size() == 1) {
			return stations.get(0);
		}
		
		if (stations.size() > 1) {
			Output.print("Multiple stations with name found. Please enter ID.");
			Output.printList(stations);
			Output.line();
			
			while (true) {
				int id = Input.getInteger();
				
				for (INode station : stations){
					if (station.getID() == id)
						return station;
				}
				Output.print("ID does not match above results.");
			}
		}
		return null;
	}
	
	/**
	 * Returns a more human readable version of the graph's generated path.
	 * This is done by combining elements of the path that share the same label,
	 * thus presenting a more realistic path to the user's eyes.
	 * @param path
	 * @return
	 */
	private List<IEdge> trimPath(List<IEdge> path) {
		List<IEdge> trimmed = new ArrayList<IEdge>();
		
		// Get the starting point of this new path
		IEdge edge = path.get(0);
		INode start = edge.getFromNode();
		String label = edge.getLabel();
		
		for (int i = 1; i < path.size(); i++) {
			edge = path.get(i);
			
			// If there's a change in label, then we must update the trimmed
			// path.
			if (!edge.getLabel().equals(label)) {
				trimmed.add(new Line(label, start, edge.getFromNode()));
				label = edge.getLabel();
				start = edge.getFromNode();
			}
		}
		
		// Finishing off the list
		edge = path.get(path.size() - 1);
		trimmed.add(new Line(edge.getLabel(), start, edge.getToNode()));
		
		return trimmed;
	}
}
