package model.ship;

import model.Orientation;

public class Carrier extends Ship 
{
	private static char symbol = '®';
	private static String name = "Carrier";
			
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
