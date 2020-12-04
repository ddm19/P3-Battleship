package model.io;


import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.aircraft.Board3D;
import model.exceptions.*;
import model.exceptions.io.BattleshipIOException;

public class PlayerRandom implements IPlayer
{
	private String name;
	private Random random;
	
	public PlayerRandom(String name , long seed)
	{
		this.name = name;
		this.random = new Random(seed);
		
	}
	@Override
	public String getName() //throws BattleshipIOExcepcion
	{
		String nombre = name+" ("+getClass().getSimpleName()+")";
		
		return nombre;
	}
	private int genRandomInt(int min, int max) 
	{ 
	    return random.nextInt(max-min)+min;
	}
	private Coordinate genRandomCoordinate(Board b, int offset)
	{
		Coordinate c = null;
		if(b instanceof Board3D)
		{
			c = CoordinateFactory.createCoordinate( genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()) , genRandomInt(0-offset, b.getSize()) ); //Coordinate3D
		}
		else
		{
			c = CoordinateFactory.createCoordinate( genRandomInt(0-offset, b.getSize()) , genRandomInt(0-offset, b.getSize()));	// Coordinate2D
		}
			
		return c;
	}
	private Orientation getRandomOrientation()
	{
		Orientation o = Orientation.values()[genRandomInt(0, Orientation.values().length)];
		
		return o;
	}
	private void ColoBarco(String type,Board b)
	{
		Craft nave = CraftFactory.createCraft(type,getRandomOrientation());	// Creo Nave del tipo con Orientación aleatoria
		int i = 0;
		boolean puesto = false;
		
			do 
			{	try
				{
					puesto = b.addCraft(nave,genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE));
				}catch(InvalidCoordinateException | NextToAnotherCraftException | OccupiedCoordinateException e)
				{
					i++;
				}
			}while(!puesto && i<=100);	// La intento colocar en rndm coord hasta que pueda o intentado 100 veces
		
	}
	@Override
	public void putCrafts(Board b)  
	{
		ColoBarco("Battleship",b);
		ColoBarco("Carrier",b);
		ColoBarco("Cruiser",b);		//Añado 1 barco de cada tipo 2D
		ColoBarco("Destroyer",b);
		
		if(b instanceof Board3D)
		{
			ColoBarco("Bomber",b);
			ColoBarco("Fighter",b);	//Añado 1 barco de cada tipo 3D
			ColoBarco("Transport",b);
		}
	}
	@Override
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException 
	{
		Coordinate c = genRandomCoordinate(b, 0);
		
		b.hit(c);
		
		return c;
	}
	
}
