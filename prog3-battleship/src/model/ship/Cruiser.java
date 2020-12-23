package model.ship;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Cruiser.
 */
public class Cruiser extends Ship 
{
	
	/** The symbol. */
	private static char symbol = 'Ø';
	
	/** The value. */
	private static int value = 5;
	/** The name. */
	private static String name = "Cruiser";
			
	/**
	 * Instantiates a new cruiser.
	 *
	 * @param o the o
	 */
	public Cruiser(Orientation o)
	{
		super(o,symbol,name);
		shape = new int[][] 
				{
		      		{ 
		      			0, 0, 0, 0, 0,
		    	    	0, 0, 1, 0, 0,	
		    	    	0, 0, 1, 0, 0,	
		    	    	0, 0, 1, 0, 0,
		    	    	0, 0, 0, 0, 0
		      		},
		      		{
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0,	
		      			0, 1, 1, 1, 0,	
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0
		      		},
		      		{ 
		      			0, 0, 0, 0, 0,
		      			0, 0, 1, 0, 0,	
		      			0, 0, 1, 0, 0,	
		      			0, 0, 1, 0, 0,
		      			0, 0, 0, 0, 0
		      		},
		      		{ 
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0,	
		      			0, 1, 1, 1, 0,	
		      			0, 0, 0, 0, 0,
		      			0, 0, 0, 0, 0
		      		}
		      		};
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}
}
