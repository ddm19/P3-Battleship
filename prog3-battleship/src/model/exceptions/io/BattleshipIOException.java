package model.exceptions.io;

import model.exceptions.BattleshipException;

public class BattleshipIOException extends BattleshipException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	public BattleshipIOException(String m)
	{
		message = m;
	}
	
	public String getMessage()
	{		
		return new String(message);
	}
}
