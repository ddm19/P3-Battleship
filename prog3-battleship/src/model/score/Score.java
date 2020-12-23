package model.score;

import model.io.IPlayer;

// TODO: Auto-generated Javadoc
/**
 * The Class Score.
 *
 * @param <T> the generic type
 */
public abstract class Score<T> implements Comparable<Score<T>>
{   
	
	/** The score. */
	protected int score;
	
	/** The player. */
	private IPlayer player ;
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public IPlayer getPlayer() { return player; }

	/**
	 * Instantiates a new score.
	 *
	 * @param player the player
	 */
	public Score(IPlayer player)
	{
		if(player == null)
			throw new NullPointerException("Error! Jugador nulo");
			this.player = player;
		score = 0;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() { return score;}
	
	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) { this.score += score;}

	
	/**
	 * Compare to.
	 *
	 * @param other the other
	 * @return the int
	 */
	public int compareTo(Score<T> other)
	{
		int comparo;
		
		if(getScore() < other.getScore() )	//this<other
			comparo = -1;
		else if(getScore() == other.getScore())	// this = other (Comparamos nombres)
			comparo = player.getName().compareTo(other.getPlayer().getName());
		else 									//this > other
			comparo = 1;
			
		return comparo;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(player.getName());
		sb.append(": ");
		sb.append(score);
		
		return sb.toString();
	}
	
	/**
	 * Score.
	 *
	 * @param sc the sc
	 */
	public abstract void score(T sc);

	


}