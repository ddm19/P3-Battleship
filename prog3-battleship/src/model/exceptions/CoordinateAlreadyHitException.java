package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class CoordinateAlreadyHitException.
 */
public class CoordinateAlreadyHitException extends CoordinateException {
	
	/** The Constant serialVersionUID. */
	//@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * Instantiates a new coordinate already hit exception.
	 *
	 * @param c the c
	 */
	public CoordinateAlreadyHitException (Coordinate c)
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
		String mensaje = "Error! Coordenada ya impactada "+super.getC();
				
		return mensaje;
	}
}
