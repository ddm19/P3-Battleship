package model.exceptions.io;

import model.exceptions.BattleshipException;

public class BattleshipIOExcepcion extends BattleshipException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	public BattleshipIOExcepcion(String m)
	{
		message = m;
	}
	
	public String getMessage()
	{		
		return new String(message);
	}
}
