package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Destroyer.
 */
public class Destroyer extends Ship 
{
	
	/** The symbol. */
	private static char symbol = 'Ω';
	
	/** The name. */
	private static String name = "Destroyer";
			
	/**
	 * Instantiates a new destroyer.
	 *
	 * @param o the o
	 */
	public Destroyer(Orientation o)
	{
		super(o,symbol,name);
		
		shape = new int[][] 
				{
		      		{ 
		      			0, 0, 0, 0, 0,
		    	    	0, 0, 1, 0, 0,	
		    	    	0, 0, 1, 0, 0,	
		    	    	0, 0, 0, 0, 0,
		    	    	0, 0, 0, 0, 0
		      		},
		      		{
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0,	
		      			0, 1, 1, 0, 0,	
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0
		      		},
		      		{ 
		      			0, 0, 0, 0, 0,
		      			0, 0, 1, 0, 0,	
		      		    0, 0, 1, 0, 0,	
		      		    0, 0, 0, 0, 0,
		      		    0, 0, 0, 0, 0
		      		},
		      		{ 
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0,	
		      			0, 1, 1, 0, 0,	
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0
		      		}
		      		};
	}
}
