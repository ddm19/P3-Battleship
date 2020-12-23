package model.score;

import model.Craft;
import model.io.IPlayer;

// TODO: Auto-generated Javadoc
/**
 * The Class CraftScore.
 */
public class CraftScore extends Score<Craft>
{
	
	/**
	 * Instantiates a new craft score.
	 *
	 * @param player the player
	 */
	public CraftScore (IPlayer player)
	{
		super(player);
	}
	
	/**
	 * Score.
	 *
	 * @param sc the sc
	 */
	@Override
	public void score(Craft sc)
	{
		super.setScore(sc.getValue());
	}
	
		
	
}
