package model.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import model.IMultiGraph;
import model.Line;
import model.Metro;
import model.Station;

/**
 * This class reads a text description of a metro subway system and generates a
 * graph representation of the metro.
 *
 * Students should feel free to modify this code as needed to complete this
 * exercise.
 *
 * <p>
 *
 * The grammar for the file is described below in BNF. A typical line in the
 * file looks like this :
 *
 * <code> 20 NorthStation   Green 19 22  Orange 15 22  </code>
 *
 * where : 20 is the StationID NorthStation is the StationName Green 19 22 Green
 * is the LineName 19 is the StationID of the outbound station 22 is the
 * StationID of the inbound station Orange 15 22 is a LineID in which : Orange
 * is the LineName 15 is the StationID of the outbound station 22 is the
 * StationID of the inbound station
 *
 * Therefore, NorthStation has two outgoing lines.
 *
 * note : 0 denotes the end of a line : i.e. in this case, OakGrove would be at
 * the end of the line, as there is no other outbound station.
 *
 * <p>
 * metro-map ::= station-spec* <BR>
 * station-spec ::= station-id station-name station-line+ <BR>
 * station-id ::= (positive integer) <BR>
 * station-name ::= string <BR>
 * station-line ::= line-name station-id station-id <BR>
 * 
 * @author MIT
 * @since 0.2
 *
 */

public class MetroMapParser {
	
	/**
	 * @effects: parses the file, and generates a graph from it, unless there is
	 *           a problem reading the file, or there is a problem with the
	 *           format of the file.
	 *
	 * @throws java.io.IOException
	 *             if there is a problem reading the file
	 * @throws ex3.BadFileException
	 *             if there is a problem with the format of the file
	 *
	 * @returns the Graph generated by the file
	 */

	public static IMultiGraph generateGraphFromFile(String filename)
			throws IOException, BadFileException {

		IMultiGraph graph = new Metro();
		BufferedReader fileInput = new BufferedReader(new FileReader(filename));
		String line = fileInput.readLine();
		StringTokenizer st;
		
		// TODO  actually create the graph using these
		int stationID;
		String stationName;
		String lineName;
		int outboundID, inboundID;

		while (line != null) {

			// STUDENT :
			//
			// in this loop, you must collect the information necessary to
			// construct your graph, and you must construct your graph as well.
			// how and where you do this will depend on the design of your
			// graph.
			//

			// StringTokenizer is a java.util Class that can break a string into
			// tokens
			// based on a specified delimiter. The default delimiter is
			// " \t\n\r\f" which
			// corresponds to the space character, the tab character, the
			// newline character,
			// the carriage-return character and the form-feed character.
			st = new StringTokenizer(line);

			// We want to handle empty lines effectively, we just ignore them!
			if (!st.hasMoreTokens()) {
				line = fileInput.readLine();
				continue;
			}

			// from the grammar, we know that the Station ID is the first token
			// on the line
			stationID = Integer.parseInt(st.nextToken());

			if (!st.hasMoreTokens()) {
				fileInput.close();
				throw new BadFileException("Station name not found");
			}

			// from the grammar, we know that the Station Name is the second
			// token on the line.
			stationName = st.nextToken();

			if (!st.hasMoreTokens()) {
				fileInput.close();
				throw new BadFileException("Station has no line");
			}
			
			graph.addNode(new Station(stationID, stationName));
						
			while (st.hasMoreTokens()) {
				lineName = st.nextToken();

				if (!st.hasMoreTokens()) {
					fileInput.close();
					throw new BadFileException("Invalid line format");
				}

				outboundID = Integer.parseInt(st.nextToken());

				if (!st.hasMoreTokens()) {
					fileInput.close();
					throw new BadFileException(
							"Invalid line format on adjacent stations");
				}

				inboundID = Integer.parseInt(st.nextToken());
				
				graph.addEdge(new Line(lineName, outboundID, inboundID));
			}
			line = fileInput.readLine();
		}
		fileInput.close();
		return graph;
	}
}