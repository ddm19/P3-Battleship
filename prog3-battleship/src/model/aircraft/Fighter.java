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
	
	/** The value. */
	private static int value = 10;
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
