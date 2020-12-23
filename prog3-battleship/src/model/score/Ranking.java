package model.score;

import java.util.SortedSet;
import java.util.TreeSet;

import model.exceptions.score.EmptyRankingException;

// TODO: Auto-generated Javadoc
/**
 * The Class Ranking.
 *
 * @param <ScoreType> the generic type
 */
public class Ranking<ScoreType extends Score<?>> 
{
	
	/** The score set. */
	private SortedSet<ScoreType> scoreSet;
	
	 /**
 	 * Instantiates a new ranking.
 	 */
 	public Ranking ()
	 {
		 scoreSet = new TreeSet<>();
	 }
	 
	 /**
 	 * Adds the score.
 	 *
 	 * @param st the st
 	 */
 	public void addScore(ScoreType st)
	 {
		 scoreSet.add(st);
	 }
	 
	 /**
 	 * Gets the sorted ranking.
 	 *
 	 * @return the sorted ranking
 	 */
 	public SortedSet<ScoreType> getSortedRanking(){ return scoreSet; }
	 
	 /**
 	 * Gets the winner.
 	 *
 	 * @return the winner
 	 * @throws EmptyRankingException the empty ranking exception
 	 */
 	public ScoreType getWinner() throws EmptyRankingException
	 {
		 if(scoreSet.isEmpty())
			 throw new EmptyRankingException("Error! El ranking está vacío");
		 
		 return scoreSet.last();
	 }
}