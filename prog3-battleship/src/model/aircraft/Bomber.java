package model.aircraft;

import model.Orientation;

public class Bomber extends Aircraft 
{
	private static char symbol = '⇶';
	private static String name = "Bomber";
			
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
