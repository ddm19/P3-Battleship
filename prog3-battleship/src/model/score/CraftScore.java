package model.score;

import model.Craft;
import model.io.IPlayer;

public class CraftScore extends Score<Craft>
{
	public CraftScore (IPlayer player)
	{
		super(player);
	}
	@Override
	public void score(Craft sc)
	{
		super.setScore(sc.getValue());
	}
	
		
	
}
