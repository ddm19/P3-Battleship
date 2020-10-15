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
	
	
	
	public int getNumCrafts() {
		return numCrafts;
	}

	public int getDestroyedCrafts() {
		return destroyedCrafts;
	}

	public Board(int size)	//DONE
	{
		board=new HashMap<Coordinate,Ship>();
		seen=new TreeSet<Coordinate>();
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
			//2. Ver si está la posición ocupada por otro barco
			//3. getNeighborhood no están ocupadas
		
		Set<Coordinate> absolutasset = ship.getAbsolutePositions(position); 
		Set<Coordinate> vecinos = getNeighborhood(ship,position);
		Coordinate[] absolutas = absolutasset.toArray(new Coordinate[absolutasset.size()]);
		Coordinate[] vecinosarr = new Coordinate[vecinos.size()];
		boolean dentro1 = true,dentro2=true,dentro3=true,comoHaIdo=false;
		
		for(int i = 0 ; i<absolutasset.size() ; i++)	// 1. Comprobación de si las posis están dentro
		{
			if(checkCoordinate(absolutas[i]) == false)
				dentro1=false;
			
		}
		
		for(int i = 0 ; i<absolutasset.size() ; i++)			// 2. Comprobación Barco Ocupada
		{
			if(board.containsKey(absolutas))
				dentro2=false;
		}
		
		for(int i = 0 ; i<vecinos.size() ; i++)			// 3. Comprobación Vecinos Ocupada
		{
			if(board.containsKey(vecinosarr[i]))
				dentro3=false;
		}
		
		if(!dentro1)
			System.out.println("Una de las posiciones está fuera del tablero");	// Error1
		if(!dentro2)
			System.out.println("Una de las posiciones está ocupada por otro barco"); //Error2
		if(!dentro3)
			System.out.println("Una de las posiciones vecinas está ocupada por otro barco"); //Error3
		if(!dentro1 && !!dentro2 && !!dentro3)
		{
			board.put(new Coordinate(position),new Ship(ship));	//Añado el Barco
			comoHaIdo=true;
			numCrafts++;
		}
		return comoHaIdo;
	}
	
	public Ship getShip(Coordinate c)
	{
		Ship s=null;
		
		if(board.containsKey(c))	// 
			s=board.get(c);
		
		return s;
	}
	
	public boolean isSeen(Coordinate c)
	{
		boolean visto = false;
		
		if(seen.contains(c);
			visto=true;
		return visto;
		
	}
	
	public CellStatus hit(Coordinate c)
	{
		
	}
	
	public boolean areAllCraftsDestroyed() 
	{
		boolean destruidos = false;
		
		if(getDestroyedCrafts()==getNumCrafts())
			destruidos=true;
		return destruidos;
		
	}
	
	public Set<Coordinate> getNeighborhood(Ship ship,Coordinate position)
	{
		Set<Coordinate> vecinos = new HashSet<Coordinate>();
		Set<Coordinate> abspos = new HashSet<Coordinate>();
		Coordinate arrayabs [] = null;
		Set<Coordinate> vecinosfinal = new HashSet<Coordinate>();
		
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
	
	public Set<Coordinate> getNeighborhood(Ship ship)
	{
		Set<Coordinate> vecinos = new HashSet<Coordinate>();
		Coordinate c = ship.getPosition();
		
		vecinos = getNeighborhood(ship,c);
		
		return vecinos;
		
	}
	
	String show(boolean unveil)
	{
		
	}
	
	String toString()
	{
		
	}
}
