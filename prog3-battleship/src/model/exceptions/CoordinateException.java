package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class BattleshipException.
 */
public abstract class CoordinateException extends BattleshipException
{
	
	/** The Constant serialVersionUID. */
	//@SuppressWarnings("serial")
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The c. */
	private Coordinate c;
		
	/**
	 * Instantiates a new battleship exception.
	 *
	 * @param c the c
	 */
	public CoordinateException(Coordinate c)
	{
		this.c=c;
	}
	 
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage()
	{
		String mensaje = "Error de coordenada"+getC();
		
		return mensaje;
	}
	
	/**
	 * Gets the c.
	 *
	 * @return the c
	 */
	public Coordinate getC() {
		return c;
	}
}
