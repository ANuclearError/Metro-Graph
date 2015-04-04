package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.IMultiGraph;
import model.INode;
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
	public MetroSystem(){
		metro = new Metro();
	}
	
	/**
	 * Starts the system, loading the file into the graph.
	 * 
	 * @param filename - the file to read the gram from
	 */
	public void start(String filename){
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
	private void run(){
		while(true){
			menu();
			int response = Input.getInteger();
			switch(response){
			case 1:
				findStation();
				break;
			case 2:
				Output.print("Finding route");
				break;
			case 3:
				Output.print("Exiting");
				System.exit(0);
				break;
			default:
				Output.print("Invalid response, please re-enter.");
				break;
			}
			Output.line();
			Output.lineBreak();
			Output.line();
		}
	}
	
	/**
	 * Displays the main menu of the system.
	 */
	private void menu(){
		Output.print("Welcome to the Bostom Metro System. How may I help you?");
		Output.print("1. Find station.");
		Output.print("2. Find route.");
		Output.print("3. Exit\n");
		Output.lineBreak();
		Output.line();
	}
	
	/**
	 * Searches through the graph's lists of stations to display all stations
	 * that matches the user's input string.
	 */
	private void findStation(){
		Output.print("What is the name of the station you're looking for?");
		String name = Input.getString().replaceAll(" ", "");
		Output.line();
		
		// Find all stations that match the search parameter.
		List<INode> stations = new ArrayList<INode>();
		for(INode station : metro.getNodes()){
			if(station.getLabel().contains(name))
				stations.add(station);
		}
		
		// Display the results of the search.
		if(stations.isEmpty()){
			Output.print("No results found, please refine your search.");
		} else{
			Output.print("\tID\tName");
			Output.minorLineBreak(40);
			Output.printList(stations);
		}
	}
}
