package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;


// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
public abstract class Board {

	/** The hit symbol. */
	public static char HIT_SYMBOL = '•';
	/** The water symbol. */
	public static char WATER_SYMBOL = ' ';
	/** The notseen symbol. */
	public static char NOTSEEN_SYMBOL = '?';
	
	/** The board separator. */
	public static char BOARD_SEPARATOR = '|';
	/** The max board size. */
	private static int MAX_BOARD_SIZE = 20;
	/** The min board size. */
	private static int MIN_BOARD_SIZE = 5;
	/** The size. */
	private int size;
	/** The num crafts. */
	private int numCrafts;
	/** The destroyed crafts. */
	private int destroyedCrafts;
	/** The board. */
	private Map<Coordinate,Craft> board;
	/** The seen. */
	private Set<Coordinate> seen = new HashSet<Coordinate>();

	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 * @throws Exception the exception
	 */
	public Board(int size) 
	{
		
		board=new HashMap<Coordinate,Craft>();
		seen=new HashSet<Coordinate>();
		numCrafts=0;
		destroyedCrafts=0;
		
		if(size<=MAX_BOARD_SIZE && size>=MIN_BOARD_SIZE)
		{
			this.size=new Integer(size);
		}
		else
		{
			this.size=MIN_BOARD_SIZE;
			throw new IllegalArgumentException("Error! Tamaño del tablero fuera de límites, Asignando tamaño mínimo...");
		}
		
	}

	/**
	 * Gets the num crafts.
	 *
	 * @return the num crafts
	 */
	public int getNumCrafts() {
		return numCrafts;
	}

	/**
	 * Gets the destroyed crafts.
	 *
	 * @return the destroyed crafts
	 */
	public int getDestroyedCrafts() {
		return destroyedCrafts;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() { return new Integer(size); }

	/**
	 * Adds the ship.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	
	public abstract boolean checkCoordinate(Coordinate c);
	
	/**
	 * Adds the craft.
	 *
	 * @param craft the craft
	 * @param position the position
	 * @return true, if successful
	 * @throws Exception the exception
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws OccupiedCoordinateException the occupied coordinate exception
	 * @throws NextToAnotherCraftException the next to another craft exception
	 */
	public boolean addCraft(Craft craft, Coordinate position) throws InvalidCoordinateException,OccupiedCoordinateException,NextToAnotherCraftException
	{
		//Esquina izquierda superior = (0,0) del shape
		//COMRPROBAR:
			//1. checkCoordinate a todas las posis absolutas de position
			//2. Ver si está la posición ocupada por otro barco
			//3. getNeighborhood no están ocupadas
	
		Set<Coordinate> absolutasset = craft.getAbsolutePositions(position); 
		Set<Coordinate> vecinos = getNeighborhood(craft,position);
		Coordinate[] absolutas = absolutasset.toArray(new Coordinate[absolutasset.size()]);
		Coordinate[] vecinosarr = vecinos.toArray(new Coordinate[vecinos.size()]);
		boolean dentro1 = true,dentro2=true,dentro3=true,comoHaIdo=false;
		
		for(int i = 0 ; i<absolutasset.size() ; i++)	// 1. Comprobación de si las posis están dentro
		{
			if(checkCoordinate(absolutas[i]) == false)
				dentro1=false;
			
		}
		
		for(int i = 0 ; i<absolutasset.size() ; i++)			// 2. Comprobación Barco Ocupada
		{
			if(board.containsKey(absolutas[i]))
				dentro2=false;
		}
		
		for(int i = 0 ; i<vecinos.size() ; i++)			// 3. Comprobación Vecinos Ocupada
		{
			if(board.containsKey(vecinosarr[i]))
				dentro3=false;
		}
		
		if(!dentro1)
			throw new InvalidCoordinateException(position);	// Error1
		if(!dentro2)
			throw new OccupiedCoordinateException(position); //Error2
		if(!dentro3)
			throw new NextToAnotherCraftException(position); //Error3
		if(dentro1 && dentro2 && dentro3)
		{
			numCrafts++;
			
			craft.setPosition(position.copy());
			for(int i = 0;i<absolutas.length;i++)
			{
				
				board.put(absolutas[i].copy(),craft);	//Añado el Barco
			}
			
	
			comoHaIdo=true;
			
		}
		return comoHaIdo;
	}

	/**
	 * Gets the ship.
	 *
	 * @param c the c
	 * @return the ship
	 */
	public Craft getCraft(Coordinate c) {
		
		
		if(board.containsKey(c))	// 
		{
			return board.get(c);
		}
		
		return null;
		
			
	}

	/**
	 * Checks if is seen.
	 *
	 * @param c the c
	 * @return true, if is seen
	 */
	public boolean isSeen(Coordinate c) {
		boolean visto = false;
		
		if(seen.contains(c))
			visto=true;
			
		return visto;
		
	}

	/**
	 * Nuevo visto.
	 *
	 * @param c the c
	 * @param hundido the hundido
	 * @param barco the barco
	 * @throws Exception the exception
	 */
	private void nuevoVisto(Coordinate c, boolean hundido, Craft barco) {
			Set<Coordinate> vecinos = new HashSet<Coordinate>();
			
			if(barco!=null)
				vecinos = getNeighborhood(barco);
			
		if(hundido)
		{
			vecinos.add(c.copy());		//Añado la coordenada del hit a sus vecinos
			seen.addAll(vecinos);	//y lo añado todo a seen
		}
		else
			seen.add(c.copy());
	}

	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return the cell status
	 * @throws Exception the exception
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws CoordinateAlreadyHitException 
	 */
	public CellStatus hit(Coordinate c) throws InvalidCoordinateException, CoordinateAlreadyHitException  {
	/* 3 Comprobaciones : 
	 * 1-Dentro del tablero 
	 * 2-Si hay o no barco (hit o water) 
	 * 	2.1- Si hay barco, si está hit o destroyed
	 */
		CellStatus estado=CellStatus.WATER;
		//Ship barco = getCraft(c);
	
		Craft barco = getCraft(c);
		boolean hundido=false;
	
		if(checkCoordinate(c))	//1 SI está dentro
		{
			
			if(board.containsKey(c))	//2 SI Hay Barco
			{
				
				
				if(barco.hit(c)) //Si ship.hit no da problemas
				{
					
					if(barco.isShotDown())
					{
						
						estado=CellStatus.DESTROYED; 
						hundido=true;
						destroyedCrafts++;
					}
					else 
						estado=CellStatus.HIT;
					
					
				} 
				else
				{
					estado=CellStatus.WATER;
				}
				
			}
			nuevoVisto(c,hundido,barco);	//Añado las coordenadas a seen en función de hundido
		}
		else //1 NO
		{
			throw new InvalidCoordinateException(c);
			
		}
		return estado;
		
	}

	/**
	 * Are all crafts destroyed.
	 *
	 * @return true, if successful
	 */
	public boolean areAllCraftsDestroyed() {
		boolean destruidos = false;
		
		if(getDestroyedCrafts()==getNumCrafts())
			destruidos=true;
		return destruidos;
		
	}

	/**
	 * Gets the neighborhood.
	 *
	 * @param ship the ship
	 * @param position the position
	 * @return the neighborhood
	 * @throws Exception the exception
	 */
	public Set<Coordinate> getNeighborhood(Craft ship, Coordinate position) {
		Set<Coordinate> vecinos = new HashSet<Coordinate>();
		Set<Coordinate> abspos = new HashSet<Coordinate>();
		Coordinate arrayabs [] = null;
		Set<Coordinate> vecinosfinal = new HashSet<Coordinate>();
		
		if(ship==null || position == null)
			throw new NullPointerException("Error! El barco o la posición son Nulas");
		
		if(position!=null)
			abspos = ship.getAbsolutePositions(position); //Posis del barco
		
		arrayabs = abspos.toArray(new Coordinate[abspos.size()]);	//Posis del barco (array)
		
		for(int i = 0; i<abspos.size() ; i++)	// Añadimos a TODOS Los vecinos de cada coordenada
		{
			vecinos.addAll(arrayabs[i].adjacentCoordinates());
		}
		arrayabs = vecinos.toArray(new Coordinate[vecinos.size()]); //Todos los vecinos en array
		
		for(int i = 0; i<arrayabs.length ; i++)	// Quitamos las de fuera del tablero y del propio barco
		{
			if(checkCoordinate(arrayabs[i])) 	// Está dentro del tablero? SI
				if(!abspos.contains(arrayabs[i])) 		// Pertenece a mi barco? NO
					vecinosfinal.add(arrayabs[i]); 			//Añado a vecinos final
		}
		
		return vecinosfinal;
	}

	/**
	 * Gets the neighborhood.
	 *
	 * @param ship the ship
	 * @return the neighborhood
	 * @throws Exception the exception
	 */
	public Set<Coordinate> getNeighborhood(Craft ship) {
		Set<Coordinate> vecinos = new HashSet<Coordinate>();
		Coordinate c = ship.getPosition();
	if(c==null)	
	{
		throw new NullPointerException("Error! El barco es Nulo");
	}
		vecinos = getNeighborhood(ship,c);
		
		return vecinos;
		
	}

	/**
	 * To string.
	 *
	 * @param unveil the unveil
	 * @return the string
	 * @throws Exception the exception
	 */
	
	public abstract String show(boolean unveil) ;
	
	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public Map<Coordinate, Craft> getBoard() {
		return board;
	}

	/**
	 * Gets the seen.
	 *
	 * @return the seen
	 */
	public Set<Coordinate> getSeen() {
		return seen;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Board ");
		sb.append(getSize());
		sb.append("; ");
		sb.append("crafts: ");
		sb.append(getNumCrafts());
		sb.append("; ");
		sb.append("destroyed: ");
		sb.append(getDestroyedCrafts());
		
		return sb.toString();
	}

}