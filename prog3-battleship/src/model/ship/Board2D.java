package model.ship;
import java.util.*;

import model.Board;
import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
public class Board2D extends Board {

	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board2D(int size)	//DONE
	{
		super();
	}
	
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
	
}
