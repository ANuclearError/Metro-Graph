package controller;

import java.io.IOException;

import model.IMultiGraph;
import model.Metro;
import model.parser.BadFileException;
import model.parser.MetroMapParser;

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
	
	private IMultiGraph metro;
	
	public MetroSystem(String filename){
		metro = new Metro();
		start(filename);
	}
	
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
		
		Output.print(metro.getNodes().size());
		Output.print(metro.getEdges().size());
	}
}
