package model;

import model.ship.*;
import model.aircraft.*;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Coordinate objects.
 */
public class CoordinateFactory 
{
	
	/**
	 * Creates a new Coordinate object.
	 *
	 * @param coords the coords
	 * @return the coordinate
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public static Coordinate createCoordinate(int coords[]) throws IllegalArgumentException
	{
		if(coords.length!=2 && coords.length!=3)
		{
			throw new IllegalArgumentException("Error! La coordenada tiene que ser de 2 o 3 dimensiones"); 
		}
		else
		{
			if(coords.length==2)
			{
				return new Coordinate2D(coords[0],coords[1]);	// Construye coordenada 2D
			}
			else
			{
				return new Coordinate3D(coords[0],coords[1],coords[2]);	//Construye coordenada 3D 
			}	
			
		}
		
	}
}
