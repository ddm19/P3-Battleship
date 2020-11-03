package model.exceptions;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class NextToAnotherCraftException.
 */
public class NextToAnotherCraftException extends BattleshipException {
	
	/** The Constant serialVersionUID. */
	//@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * Instantiates a new next to another craft exception.
	 *
	 * @param c the c
	 */
	public NextToAnotherCraftException (Coordinate c)
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
		String mensaje = "Error! Coordenada en la Vecindad de un barco "+super.getC();
				
		return mensaje;
	}
}
