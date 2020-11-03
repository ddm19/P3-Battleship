package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class BattleshipException.
 */
public abstract class BattleshipException extends java.lang.Exception
{
	
	/** The Constant serialVersionUID. */
	//@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	
	/** The c. */
	private Coordinate c;
		
	/**
	 * Instantiates a new battleship exception.
	 *
	 * @param c the c
	 */
	public BattleshipException(Coordinate c)
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
