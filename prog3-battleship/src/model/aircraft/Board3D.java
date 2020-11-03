package model.aircraft;

import java.util.Map;
import java.util.Set;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

// TODO: Auto-generated Javadoc
/**
 * The Class Board3D.
 */
public class Board3D extends Board
{
		
		/**
		 * Instantiates a new board.
		 *
		 * @param size the size
		 * @throws Exception the exception
		 */
		public Board3D(int size) throws Exception	//DONE
		{
			super(size);
		}
		
		/**
		 * Check coordinate.
		 *
		 * @param c the c
		 * @return true, if successful
		 */
		@Override
		public boolean checkCoordinate(Coordinate c)
		{
			if(!(c instanceof Coordinate3D))
				throw new IllegalArgumentException("Error! La coordenada no es una coordenada 3D");
			boolean dentro=false;
			int x=c.get(0),y=c.get(1),z=c.get(2);
			
			if(x>=0 && y>=0 && x<getSize() && y<getSize() && z>=0 && z<getSize())
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
			int coords[] = {-99,-99,-99};
			Map<Coordinate, Craft> board = getBoard();
			Set<Coordinate> seen = getSeen();
			Coordinate3D c = (Coordinate3D)CoordinateFactory.createCoordinate(coords);
			
			for(int j = 0 ; j<getSize() ; j++)	// Recorro todas las Coordenadas y del mapa
			{
				for(int k = 0 ; k<getSize() ; k++)	// Recorro todas las Coordenadas z del mapa
				{
					for(int i = 0 ; i<getSize() ; i++)	// Recorro todas las Coordenadas x del mapa
					{
						c.set(0,j);
						c.set(1,k);
						c.set(2,i);
						
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
					if(k<getSize()-1)
						tablero += BOARD_SEPARATOR;
				
				
				}
				if(j<getSize()-1)
					tablero += '\n';
			
			}
			
			return tablero;
			
			
			
		}
		
	}


