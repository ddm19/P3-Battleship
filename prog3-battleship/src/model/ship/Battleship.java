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
	
	/** The value. */
	private static int value = 6;
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
