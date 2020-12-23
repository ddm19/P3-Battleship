package model.io;

import java.io.BufferedReader;
import java.io.IOException;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerFile.
 */
public class PlayerFile implements IPlayer
{
	
	/** The br. */
	private BufferedReader br;
	
	/** The last shot status. */
	private CellStatus lastShotStatus;

	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new player file.
	 *
	 * @param name the name
	 * @param br the br
	 */
	public PlayerFile(String name,BufferedReader br)
	{
		if(br==null)
			throw new NullPointerException();
		this.br=br;
		this.name=name;
	}
	
	/**
	 * Gets the buffer reader.
	 *
	 * @return the buffer reader
	 */
	public BufferedReader getBufferReader() 
	{
		return br;
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
	 * Put crafts.
	 *
	 * @param b the b
	 * @throws BattleshipIOException the battleship IO exception
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws OccupiedCoordinateException the occupied coordinate exception
	 * @throws NextToAnotherCraftException the next to another craft exception
	 */
	@Override
	public void putCrafts(Board b) throws BattleshipIOException, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException
	{
		if(b==null)
			throw new NullPointerException("Error! EL tablero pasado por argumento es nulo");
		
		String comandos[]=null;
		String linea;
		Craft nave;
		BufferedReader br = getBufferReader();
		do
		{
			try
			{
				linea = br.readLine();
				if(linea!=null)
				{

					comandos=linea.split("\\s+");
					
					if(!comandos[0].equals("put") && !comandos[0].equals("endput") && !comandos[0].equals("exit"))
						throw new BattleshipIOException("Error! Comando desconocido"+" "+comandos[0]);
					
					if(comandos[0].equals("put"))				//Poner Barco
					{
								if(comandos.length < 5 || comandos.length > 6) // comandos de 5 a 6 parámetros [ put Type Orientation c1 c2 (c3) ]
									throw new BattleshipIOException("Error! La Cantidad de Parámetros no es Correcta");
								int[] coords;
								if(comandos.length == 6)
									coords = new int[3];
								else
									coords = new int[2];
							
								try 
								{
									 nave = CraftFactory.createCraft(comandos[1],Orientation.valueOf(comandos[2]));//Creo nave con "Type" y Orientation
								}
								catch(IllegalArgumentException e)
								{
									throw new BattleshipIOException("Error! Orientación Desconocida");
								}
								
								try
								{
									for(int i = 3 ; i < comandos.length ; i++)	// a partir del 4º comando metemos las coords
									{
										coords[i-3] = Integer.parseInt(comandos[i]);		
										
									}
									Coordinate posicion = CoordinateFactory.createCoordinate(coords);
									
										b.addCraft(nave,posicion);
									
									
										
									
									
								}
								catch(NumberFormatException e)
								{
									throw new BattleshipIOException("Error! Alguna de las coordenadas no son números");
								}
								
						}
					
				}
				
			}
			catch(IOException e)
			{
				throw new BattleshipIOException("Error! No se ha podido acceder al archivo");
			}
			
		}while(linea != null && !comandos[0].equals("endput") && !comandos[0].equals("exit"));
		
		
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
		String linea;
		String comandos[]=null;
		int coords[];
		BufferedReader br = getBufferReader();
		Coordinate usada=null;
		
		try									//Lee línea y separa en comandos
		{
			linea = br.readLine();	
			if(linea!=null)
				comandos=linea.split("\\s+");
			
		}
		catch(IOException e)
		{
			throw new BattleshipIOException("Error! No se ha podido acceder al archivo");
		}
		if(comandos!=null)
		{

			if(comandos.length == 4)
				coords = new int[3];
			else
				coords = new int[2];
			
			if(!comandos[0].equals("shoot") && !comandos[0].equals("exit"))
				throw new BattleshipIOException("Error! Comando Desconocido : "+comandos[0]);
			if(!comandos[0].equals("exit") && comandos != null)
			{
				if(comandos.length < 3 || comandos.length > 4)
					throw new BattleshipIOException("Error! La Cantidad de Parámetros no es Correcta");

				try
				{
					for(int i = 1 ; i < comandos.length ; i++)	// a partir del 2º comando metemos las coords
					{
						coords[i-1] = Integer.parseInt(comandos[i]);		

					}
				}
				catch(NumberFormatException e)
				{
					throw new BattleshipIOException("Error! Alguna de las coordenadas no son números");
				}
				
				usada = CoordinateFactory.createCoordinate(coords);
				setLastShotStatus(b.hit(usada));										//Hiteamos con la coord y almacenamos en lastShotStatus
			}
			
		}
		if(usada == null)			// Si no se han hecho disparos
			setLastShotStatus(null);
		return usada;
		
	}

	/**
	 * Gets the last shot status.
	 *
	 * @return the last shot status
	 */
	@Override
	public CellStatus getLastShotStatus() { return lastShotStatus; }
	
	/**
	 * Sets the last shot status.
	 *
	 * @param lastShotStatus the new last shot status
	 */
	public void setLastShotStatus(CellStatus lastShotStatus) { this.lastShotStatus = lastShotStatus; }
	
}
