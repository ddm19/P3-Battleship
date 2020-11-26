package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidCoordinateException.
 */
public class InvalidCoordinateException extends CoordinateException {
	
	/** The Constant serialVersionUID. */
	//@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * Instantiates a new invalid coordinate exception.
	 *
	 * @param c the c
	 */
	public InvalidCoordinateException (Coordinate c)
	{
		super(c);
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage()
	{
		String mensaje = "Error! Coordenada Inválida " + getC();
				
		return mensaje;
	}
}
