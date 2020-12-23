package model.aircraft;

import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Transport.
 */
public class Transport extends Aircraft 
{
	
	/** The symbol. */
	private static char symbol = '⇋';
	
	/** The value. */
	private static int value = 18;
	/** The name. */
	private static String name = "Transport";
			
	/**
	 * Instantiates a new transport.
	 *
	 * @param o the o
	 */
	public Transport(Orientation o)
	{
		super(o,symbol,name);
		
		shape = new int[][] 
			{
		      { 0, 0, 1, 0, 0,
			    0, 0, 1, 0, 0,	
			    0, 1, 1, 1, 0,	
			    1, 0, 1, 0, 1,
			    0, 0, 1, 0, 0
		      },
		      { 0, 1, 0, 0, 0,
		  		0, 0, 1, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 1, 0, 0,
				0, 1, 0, 0, 0
		      },
		      { 0, 0, 1, 0, 0,
		  		1, 0, 1, 0, 1,	
				0, 1, 1, 1, 0,	
				0, 0, 1, 0, 0,
				0, 0, 1, 0, 0
		      },
		      { 0, 0, 0, 1, 0,
		  		0, 0, 1, 0, 0,	
				1, 1, 1, 1, 1,	
				0, 0, 1, 0, 0,
				0, 0, 0, 1, 0
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
