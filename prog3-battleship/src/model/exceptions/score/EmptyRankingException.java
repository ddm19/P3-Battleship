package model.exceptions.score;


import model.exceptions.BattleshipException;

// TODO: Auto-generated Javadoc
/**
 * The Class EmptyRankingException.
 */
public class EmptyRankingException extends BattleshipException
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new empty ranking exception.
	 *
	 * @param m the m
	 */
	public EmptyRankingException(String m) 
	{
		message = m;
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage(){ return new String(message);}
}
