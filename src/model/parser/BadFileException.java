package model.parser;

/**
 * The BadFileException class is required for when the setup of a text file
 * being parsed into a graph is not valid for various reasons, such as missing
 * information.
 * 
 * @author Aidan O'Grady
 * @since 0.2
 *
 */
public class BadFileException extends Exception{

	private static final long serialVersionUID = -6176310730203165505L;

	/**
	 * Constructor
	 * @param message - the error message created.
	 */
	public BadFileException(String message){
		super(message);
	}
}
