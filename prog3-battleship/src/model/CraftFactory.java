package model;


import model.aircraft.*;
import model.ship.*;

public class CraftFactory 
{
	public static Craft createCraft(String type, Orientation o)
	{
		Craft nave=null;
		
		switch(type)
		{
			case "Bomber":
				nave = new Bomber(o);	//	AIRCRAFT
				break;
			case "Fighter":
				nave = new Fighter(o);			//	AIRCRAFT
				break;
			case "Transport":
				nave = new Transport(o);				//	AIRCRAFT
				break;
			case "Battleship":			//	SHIP
				nave = new Battleship(o);
				break;
			case "Carrier":						// SHIP
				nave = new Carrier(o);
				break;
			case "Cruiser":								//SHIP
				nave = new Cruiser(o);
				break;
			case "Destroyer":									//SHIP
				nave = new Destroyer(o);
				break;
		}
		
		return nave;
	}
}
