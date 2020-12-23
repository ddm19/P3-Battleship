package model.io;


import java.util.Random;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.aircraft.Board3D;
import model.aircraft.Bomber;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.exceptions.*;
import model.exceptions.io.BattleshipIOException;
import model.ship.Battleship;
import model.ship.Board2D;
import model.ship.Carrier;
import model.ship.Cruiser;
import model.ship.Destroyer;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerRandom.
 */
public class PlayerRandom implements IPlayer
{
	
	/** The name. */
	private String name;
	
	private CellStatus lastShotStatus;
	
	

	/** The random. */
	private Random random;
	
	/**
	 * Instantiates a new player random.
	 *
	 * @param name the name
	 * @param seed the seed
	 */
	public PlayerRandom(String name , long seed)
	{
		this.name = name;
		this.random = new Random(seed);
		
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Override
	public String getName() //throws BattleshipIOExcepcion
	{
		String nombre = name+" ("+getClass().getSimpleName()+")";
		
		return nombre;
	}
	
	/**
	 * Gen random int.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	private int genRandomInt(int min, int max) 
	{ 
		//System.err.println("GEN");
	    return random.nextInt(max-min)+min;
	}
	
	/**
	 * Gen random coordinate.
	 *
	 * @param b the b
	 * @param offset the offset
	 * @return the coordinate
	 */
	private Coordinate genRandomCoordinate(Board b, int offset)
	{
		Coordinate c = null;
		if(b instanceof Board3D)
		{
			c = CoordinateFactory.createCoordinate( genRandomInt(0-offset, b.getSize()), genRandomInt(0-offset, b.getSize()) , genRandomInt(0-offset, b.getSize()) ); //Coordinate3D
		}
		else if (b instanceof Board2D)
		{
			c = CoordinateFactory.createCoordinate( genRandomInt(0-offset, b.getSize()) , genRandomInt(0-offset, b.getSize()) );	// Coordinate2D
		}
			//System.err.println(c);
		return c;
	}
	
	/**
	 * Gets the random orientation.
	 *
	 * @return the random orientation
	 */
	private Orientation getRandomOrientation()
	{
		//System.err.println("ORIENTATION");
		Orientation o = Orientation.values()[genRandomInt(0, Orientation.values().length)];
		
		return o;
	}
	
	/**
	 * Colo barco.
	 *
	 * @param type the type
	 * @param b the b
	 */
	private void ColoBarco(String type,Board b)
	{
		Craft nave = CraftFactory.createCraft(type,getRandomOrientation());	// Creo Nave del tipo con Orientación aleatoria
		int i = 0;
		Coordinate c = null;
		boolean puesto = false;
			do 
			{	
				try
				{
					c = genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE);
					i++;
					b.addCraft(nave,c);
					puesto = true;
				}catch(InvalidCoordinateException | NextToAnotherCraftException | OccupiedCoordinateException e) {}
			}while(!puesto && i<100);	// La intento colocar en rndm coord hasta que pueda o intentado 100 veces
		//System.err.println("YESYESYESYES");
	}
	
	/**
	 * Put crafts.
	 *
	 * @param b the b
	 */
	@Override
	public void putCrafts(Board b)  
	{
		ColoBarco("ship."+Battleship.class.getSimpleName(),b);
		ColoBarco("ship."+Carrier.class.getSimpleName(),b);
		ColoBarco("ship."+Cruiser.class.getSimpleName(),b);		//Añado 1 barco de cada tipo 2D
		ColoBarco("ship."+Destroyer.class.getSimpleName(),b);
		
		
		if(b instanceof Board3D)
		{
			ColoBarco("aircraft."+Bomber.class.getSimpleName(),b);
			ColoBarco("aircraft."+Fighter.class.getSimpleName(),b);	//Añado 1 barco de cada tipo 3D
			ColoBarco("aircraft."+Transport.class.getSimpleName(),b);
		}
	}
	
	/**
	 * Next shoot.
	 *
	 * @param b the b
	 * @return the coordinate
	 * @throws BattleshipIOException the battleship IO exception
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
	 */
	@Override
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException 
	{
		Coordinate c = genRandomCoordinate(b, 0);
		
		setLastShotStatus(b.hit(c));
		
		return c;
	}

	@Override
	public CellStatus getLastShotStatus() { return lastShotStatus; }
	
	public void setLastShotStatus(CellStatus lastShotStatus) { this.lastShotStatus = lastShotStatus; }
	
}
