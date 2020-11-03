package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Battleship.
 */
public class Battleship extends Ship 
{
	
	/** The symbol. */
	private static char symbol = 'O';
	
	/** The name. */
	private static String name = "Battleship";
			
	/**
	 * Instantiates a new battleship.
	 *
	 * @param o the o
	 */
	public Battleship(Orientation o)
	{
		super(o,symbol,name);
		
		shape = new int[][] 
			{
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0
		      },
		      { 0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0,	
		    	0, 1, 1, 1, 1,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0
		      },
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0
		      },
		      { 0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0,	
		    	0, 1, 1, 1, 1,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0
		      }
		    };
	}
}
