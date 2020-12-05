package model.exceptions.io;

import model.exceptions.BattleshipException;

// TODO: Auto-generated Javadoc
/**
 * The Class BattleshipIOException.
 */
public class BattleshipIOException extends BattleshipException 
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The message. */
	String message;
	
	/**
	 * Instantiates a new battleship IO exception.
	 *
	 * @param m the m
	 */
	public BattleshipIOException(String m)
	{
		message = m;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage()
	{		
		return new String(message);
	}
}
