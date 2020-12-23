package model.exceptions.score;


import model.exceptions.BattleshipException;

public class EmptyRankingException extends BattleshipException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public EmptyRankingException(String m) 
	{
		message = m;
		// TODO Auto-generated constructor stub
		
	}
	public String getMessage(){ return new String(message);}
}
