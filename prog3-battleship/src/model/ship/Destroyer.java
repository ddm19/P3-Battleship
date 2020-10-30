package model.ship;

import model.Orientation;

public class Destroyer extends Ship 
{
	private static char symbol = 'Ω';
	private static String name = "Destroyer";
			
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
