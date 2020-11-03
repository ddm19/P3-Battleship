package model.aircraft;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Fighter.
 */
public class Fighter extends Aircraft 
{
	
	/** The symbol. */
	private static char symbol = '⇄';
	
	/** The name. */
	private static String name = "Fighter";
			
	/**
	 * Instantiates a new fighter.
	 *
	 * @param o the o
	 */
	public Fighter(Orientation o)
	{
		super(o,symbol,name);
		
		shape = new int[][] 
			{
		      { 0, 0, 0, 0, 0,
			    0, 0, 1, 0, 0,	
			    0, 1, 1, 1, 0,	
			    0, 0, 1, 0, 0,
			    0, 0, 1, 0, 0
		      },
		      { 0, 0, 0, 0, 0,
		  		0, 0, 1, 0, 0,	
				1, 1, 1, 1, 0,	
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0
		      },
		      { 0, 0, 1, 0, 0,
		  		0, 0, 1, 0, 0,	
				0, 1, 1, 1, 0,	
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0
		      },
		      { 0, 0, 0, 0, 0,
		  		0, 0, 1, 0, 0,	
				0, 1, 1, 1, 1,	
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0
		      }
		    };
	}
}
