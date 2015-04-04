package controller;

import java.io.IOException;

import model.IMultiGraph;
import model.Metro;
import model.parser.BadFileException;
import model.parser.MetroMapParser;

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
	 * 
	 * @param filename - the file to read the graph from
	 */
	public MetroSystem(String filename){
		metro = new Metro();
		start(filename);
	}
	
	/**
	 * Starts the system, loading the file into the graph.
	 * 
	 * @param filename - the file to read the gram from
	 */
	private void start(String filename){
		try {
			metro = MetroMapParser.generateGraphFromFile(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
