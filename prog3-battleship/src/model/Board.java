package model;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
public class Board {

	/** The hit symbol. */
	public static char HIT_SYMBOL = '•';
	
	/** The water symbol. */
	public static char WATER_SYMBOL = ' ';
	
	/** The notseen symbol. */
	public static char NOTSEEN_SYMBOL = '?';
	
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
	private Map<Coordinate,Ship> board;
	
	/** The seen. */
	private Set<Coordinate> seen = new HashSet<Coordinate>();
	
	
	
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
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board(int size)	//DONE
	{
		board=new HashMap<Coordinate,Ship>();
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
			System.err.println(size);	// MIRAR ESTO
		}
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() { return new Integer(size); }
	
	/**
	 * Check coordinate.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean checkCoordinate(Coordinate c)
	{
		boolean dentro=false;
		int x=c.get(0),y=c.get(1);
		
		if(x>=0 && y>=0 && x<getSize() && y<getSize() )
		{
			dentro=true;
		}
		
		return dentro;
	}
	
	
	/**
	 * Adds the ship.
	 *
	 * @param ship the ship
	 * @param position the position
	 * @return true, if successful
	 */
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
			System.out.println("Una de las posiciones está fuera del tablero");	// Error1
		if(!dentro2)
			System.out.println("Una de las posiciones está ocupada por otro barco"); //Error2
		if(!dentro3)
			System.out.println("Una de las posiciones vecinas está ocupada por otro barco"); //Error3
		if(dentro1 && dentro2 && dentro3)
		{
			numCrafts++;
			
			ship.setPosition(position.copy());
			for(int i = 0;i<absolutas.length;i++)
			{
				
				board.put(absolutas[i].copy(),ship);	//Añado el Barco
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
	public Ship getShip(Coordinate c)
	{
		
		
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
	public boolean isSeen(Coordinate c)
	{
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
	 */
	private void nuevoVisto(Coordinate c,boolean hundido,Ship barco)
	{
			Set<Coordinate> vecinos = new HashSet<Coordinate>();
			
			if(barco!=null)
				vecinos = getNeighborhood(barco);
			
		if(hundido)
		{
			vecinos.add(c);		//Añado la coordenada del hit a sus vecinos
			seen.addAll(vecinos);	//y lo añado todo a seen
		}
		else
			seen.add(c);
	}
	
	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return the cell status
	 */
	public CellStatus hit(Coordinate c)
	{
	/* 3 Comprobaciones : 
	 * 1-Dentro del tablero 
	 * 2-Si hay o no barco (hit o water) 
	 * 	2.1- Si hay barco, si está hit o destroyed
	 */
		CellStatus estado=CellStatus.WATER;
		//Ship barco = getShip(c);

		Ship barco = getShip(c);
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
			System.err.println("Error, La casilla deleccionada está fuera del tablero");
			
		}
		return estado;
		
	}
	
	/**
	 * Are all crafts destroyed.
	 *
	 * @return true, if successful
	 */
	public boolean areAllCraftsDestroyed() 
	{
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
	 */
	public Set<Coordinate> getNeighborhood(Ship ship,Coordinate position)
	{
		Set<Coordinate> vecinos = new HashSet<Coordinate>();
		Set<Coordinate> abspos = new HashSet<Coordinate>();
		Coordinate arrayabs [] = null;
		Set<Coordinate> vecinosfinal = new HashSet<Coordinate>();
		
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
	 */
	public Set<Coordinate> getNeighborhood(Ship ship)
	{
		Set<Coordinate> vecinos = new HashSet<Coordinate>();
		Coordinate c = ship.getPosition();
	if(c!=null)	
		vecinos = getNeighborhood(ship,c);
		
		return vecinos;
		
	}
	
	/**
	 * Show.
	 *
	 * @param unveil the unveil
	 * @return the string
	 */
	public String show(boolean unveil)
	{
		String tablero="";		
		Coordinate c = new Coordinate(-1,-1);
		
		for(int i = 0 ; i<getSize() ; i++)	// Recorro todas las Coordenadas x del mapa
		{
			for(int j = 0 ; j<getSize() ; j++)	// Recorro todas las Coordenadas y del mapa
			{
				c.set(0,j);
				c.set(1,i);
				
				if(unveil)	//TABLERO COMPLETO
				{					
					if(board.containsKey(c))				// Si en c hay un barco
					{
						if(seen.contains(c))	//Si el adversario la ha visto (alcanzada)
							tablero += HIT_SYMBOL;
						else
							tablero += getShip(c).getSymbol(); // NO Alcanzada -> coloco en el tablero el símbolo del barco
					}
					else
					{
						tablero += WATER_SYMBOL;
					}
											
				}
				else // TABLERO ADVERSARIO
				{
					if(seen.contains(c))	// Si lo ha visto
					{
						if(board.containsKey(c))
						{
							if(getShip(c).isShotDown())	// y está hundido
								tablero += getShip(c).getSymbol();	//Símbolo del barco
							else 
								tablero += HIT_SYMBOL;	// NO Hundido-> símbolo de tocado
						}
						else
						{
							tablero += WATER_SYMBOL;
						}
					}
					else					// NO lo ha visto
						tablero += NOTSEEN_SYMBOL; 	
				}
				
			}
			if(i<getSize()-1)
				tablero += '\n';
			
			
		}
		
		return tablero;
		
		
		
	}

	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString()
	{
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
