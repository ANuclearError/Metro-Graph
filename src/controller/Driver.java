package controller;

import view.Output;

/**
 * The Driver class contains the main method for the project, initialising the
 * required classes and starting the application. It also displays the initial
 * introductory data of the system, namely the program title, author and
 * version.
 * 
 * @author Aidan O'Grady
 * @since 0.0
 */
public class Driver {

	private static final String TITLE = "Metro Graph";
	private static final String VERSION = "0.0";
	private static final String PROJECT_MESSAGE = TITLE + " v" + VERSION;
	private static final String AUTHOR = "Created by Aidan O'Grady";
	
	public static void main(String[] args) {
		welcome();
		MetroSystem system = new MetroSystem();
		system.start("bostonmetro.txt");
	}

	private static void welcome() {
		Output.print(PROJECT_MESSAGE);
		Output.print(AUTHOR);
		Output.line();
		Output.lineBreak();
		Output.line();
	}
}
