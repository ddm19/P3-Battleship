package model;

import model.ship.*;
import model.aircraft.*;

public class CoordinateFactory 
{
	public static Coordinate createCoordinate(int coords[])
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
