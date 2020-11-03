package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Carrier.
 */
public class Carrier extends Ship 
{
	
	/** The symbol. */
	private static char symbol = '®';
	
	/** The name. */
	private static String name = "Carrier";
			
	/**
	 * Instantiates a new carrier.
	 *
	 * @param o the o
	 */
	public Carrier(Orientation o)
	{
		super(o,symbol,name);
		
		shape = new int[][] 
				{
		      		{ 
		      			0, 0, 1, 0, 0,
		      			0, 0, 1, 0, 0,	
		      			0, 0, 1, 0, 0,	
		      			0, 0, 1, 0, 0,
		      			0, 0, 1, 0, 0
		      		},
		      		{
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0,	
		      			1, 1, 1, 1, 1,	
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0
		      		},
		      		{ 
		      			0, 0, 1, 0, 0,
		      			0, 0, 1, 0, 0,	
		      			0, 0, 1, 0, 0,	
		      			0, 0, 1, 0, 0,
		      			0, 0, 1, 0, 0
		      		},
		      		{ 
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0,	
		      			1, 1, 1, 1, 1,	
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0
		      		}
		      		};
		
		
	}
}
