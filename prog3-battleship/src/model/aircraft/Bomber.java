package model.aircraft;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Bomber.
 */
public class Bomber extends Aircraft 
{
	
	/** The symbol. */
	private static char symbol = '⇶';
	
	/** The name. */
	private static String name = "Bomber";
			
	/**
	 * Instantiates a new bomber.
	 *
	 * @param o the o
	 */
	public Bomber(Orientation o)
	{
		super(o,symbol,name);
		
		shape = new int[][] 
			{
		      { 0, 0, 0, 0, 0,
			    0, 0, 1, 0, 0,	
			    1, 1, 1, 1, 1,	
			    1, 0, 1, 0, 1,
			    0, 0, 1, 0, 0
		      },
		      { 0, 1, 1, 0, 0,
		  		0, 0, 1, 0, 0,	
				1, 1, 1, 1, 0,	
				0, 0, 1, 0, 0,
				0, 1, 1, 0, 0
		      },
		      { 0, 0, 1, 0, 0,
		  		1, 0, 1, 0, 1,	
				1, 1, 1, 1, 1,	
				0, 0, 1, 0, 0,
				0, 0, 0, 0, 0
		      },
		      { 0, 0, 1, 1, 0,
		  		0, 0, 1, 0, 0,	
				0, 1, 1, 1, 1,	
				0, 0, 1, 0, 0,
				0, 0, 1, 1, 0
		      }
		    };
	}
}
