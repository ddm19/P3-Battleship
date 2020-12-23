package model.score;

import java.util.SortedSet;
import java.util.TreeSet;

import model.exceptions.score.EmptyRankingException;

public class Ranking<ScoreType extends Score<?>> 
{
	private SortedSet<ScoreType> scoreSet;
	
	 public Ranking ()
	 {
		 scoreSet = new TreeSet<>();
	 }
	 
	 public void addScore(ScoreType st)
	 {
		 scoreSet.add(st);
	 }
	 
	 public SortedSet<ScoreType> getSortedRanking(){ return scoreSet; }
	 
	 public ScoreType getWinner() throws EmptyRankingException
	 {
		 if(scoreSet.isEmpty())
			 throw new EmptyRankingException("Error! El ranking está vacío");
		 
		 return scoreSet.last();
	 }
}