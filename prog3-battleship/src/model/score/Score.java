package model.score;

import model.io.IPlayer;

public abstract class Score<T> implements Comparable<Score<T>>
{   
	protected int score;
	private IPlayer player ;
	
	public IPlayer getPlayer() { return player; }

	public Score(IPlayer player)
	{
		if(player == null)
			throw new NullPointerException("Error! Jugador nulo");
			this.player = player;
		score = 0;
	}
	
	public int getScore() { return score;}
	
	
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
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(player.getName());
		sb.append(": ");
		sb.append(score);
		
		return sb.toString();
	}
	
	public abstract void score(T sc);


}