package model;
import java.util.*;

public class Board {

	public static char HIT_SYMBOL;
	public static char WATER_SYMBOL;
	public static char NOTSEEN_SYMBOL;
	private static int MAX_BOARD_SIZE;
	private static int MIN_BOARD_SIZE;
	private int size;
	private int numCrafts;
	private int destroyedCrafts;
	private Map<Coordinate,Ship> board;
	private Set<Coordinate> seen;
	
	public Board(int size)	//DONE
	{
		board=null;
		seen=null;
		numCrafts=0;
		destroyedCrafts=0;
		
		if(size<=MAX_BOARD_SIZE && size>=MIN_BOARD_SIZE)
		{
			this.size=size;
		}
		else
		{
			this.size=MIN_BOARD_SIZE;
			System.err.println(size);	// MIRAR ESTO
		}
	}
	
	public int getSize() { return size; }
	
	public boolean checkCoordinate(Coordinate c)
	{
		boolean dentro=false;
		int x=c.get(1),y=c.get(2);
		
		if(x>=0 && y>=0 && x<getSize() && y<getSize() )
		{
			dentro=true;
		}
		
		return dentro;
	}
	
	public boolean addShip(Ship ship,Coordinate position)
	{
		//Esquina izquierda superior = (0,0) del shape
		//COMRPROBAR:
			//1. checkCoordinate a todas las posis absolutas de position
			//2. Ver si está la posición ocupada - Crear Función que compruebe si alguno de los barcos coincide en posiciones absolutas con position
			//3. getNeighborhood no están ocupadas
		
		Set<Coordinate> absolutasset = ship.getAbsolutePositions(position); 
		Coordinate[] absolutas = absolutasset.toArray(new Coordinate[absolutasset.size()]);
		boolean dentro = true;
		
		for(int i = 0 ; i<absolutasset.size() ; i++)	// 1. Comprobación
		{
			if(checkCoordinate(absolutas[i]) == false)
				dentro=false;
			
		}
		
		
	}
	
	public Ship(Coordinate c)
	{
		
	}
	
	public boolean isSeen(Coordinate c)
	{
		
	}
	
	public CellStatus hit(Coordinate c)
	{
		
	}
	
	public boolean areAllCraftsDestroyed() 
	{
		
	}
	
	public Set<Coordinate> getNeighborhood(Ship ship,Coordinate position)
	{
		
	}
	
	public Set<Coordinate> getNeighborhood(Ship ship)
	{
		
	}
	
	String show(boolean unveil)
	{
		
	}
	
	String toString()
	{
		
	}
}
