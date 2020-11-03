package model.ship;
import java.util.*;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
public class Board2D extends Board {

	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 * @throws Exception the exception
	 */
	public Board2D(int size) throws Exception	//DONE
	{
		super(size);
	}
	
	/**
	 * Check coordinate.
	 *
	 * @param c the c
	 * @return true, if successful
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	@Override
	public boolean checkCoordinate(Coordinate c) throws IllegalArgumentException
	{
		if(!(c instanceof Coordinate2D))
			throw new IllegalArgumentException("Error! La coordenada no es una coordenada 2D");
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
	 * @throws Exception the exception
	 */
	@Override
	public String show(boolean unveil) throws Exception
	{
		String tablero="";		
		int coords[] = {-99,-99};
		Map<Coordinate, Craft> board = getBoard();
		Set<Coordinate> seen = getSeen();
		Coordinate2D c = (Coordinate2D)CoordinateFactory.createCoordinate(coords);
		
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
							tablero += getCraft(c).getSymbol(); // NO Alcanzada -> coloco en el tablero el símbolo del barco
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
							if(getCraft(c).isShotDown())	// y está hundido
								tablero += getCraft(c).getSymbol();	//Símbolo del barco
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
