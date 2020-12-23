package model.score;

import model.CellStatus;
import model.io.IPlayer;

// TODO: Auto-generated Javadoc
/**
 * The Class HitScore.
 */
public class HitScore extends Score<CellStatus> 
{
	
	/**
	 * Instantiates a new hit score.
	 *
	 * @param player the player
	 */
	public HitScore(IPlayer player)
	{
		super(player);
	}
	
	/**
	 * Score.
	 *
	 * @param sc the sc
	 */
	@Override
	public void score(CellStatus sc)
	{
		if(sc == CellStatus.DESTROYED || sc == CellStatus.HIT)
			super.setScore(1);
	}
}
