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
	
	/** The value. */
	private static int value = 8;
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
