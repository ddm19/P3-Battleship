package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class OccupiedCoordinateException.
 */
public class OccupiedCoordinateException extends CoordinateException {
	
	/** The Constant serialVersionUID. */
	//@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * Instantiates a new occupied coordinate exception.
	 *
	 * @param c the c
	 */
	public OccupiedCoordinateException (Coordinate c)
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
		String mensaje = "Error! Coordenada Ocupada "+super.getC();
				
		return mensaje;
	}
}
