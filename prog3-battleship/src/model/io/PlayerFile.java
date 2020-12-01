package model.io;

import java.io.BufferedReader;
import java.io.IOException;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOExcepcion;

public class PlayerFile implements IPlayer
{
	private BufferedReader br;
	private String name;
	
	public PlayerFile(String name,BufferedReader br)
	{
		if(br==null)
			throw new NullPointerException();
		this.br=br;
		this.name=name;
	}
	
	public BufferedReader getBufferReader() 
	{
		return new BufferedReader(br);
	}
	
	@Override
	public String getName() //throws BattleshipIOExcepcion
	{
		String nombre = name+"("+getClass()+")";
		
		return nombre;
	}

	@Override
	public void putCrafts(Board b) throws BattleshipIOExcepcion, InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException
	{
		if(b==null)
			throw new NullPointerException("Error! EL tablero pasado por argumento es nulo");
		
		String comandos[];
		String linea;
		Craft nave;
		BufferedReader br = getBufferReader();
		do
		{
			try
			{
				linea = br.readLine();
				comandos=linea.split("\\s+");
				
				if(comandos[0]!="put" && comandos[0]!= "endput" && comandos[0]!="exit")
					throw new BattleshipIOExcepcion("Error! Comando desconocido");
				
				if(comandos[0]=="put")				//Poner Barco
				{
							if(comandos.length < 5 || comandos.length > 6) // comandos de 5 a 6 parámetros [ put Type Orientation c1 c2 (c3) ]
								throw new BattleshipIOExcepcion("Error! La Cantidad de Parámetros no es Correcta");
							int coords[]= {};
							
						
							try 
							{
								 nave = CraftFactory.createCraft(comandos[1],Orientation.valueOf(comandos[2]));//Creo nave con "Type" y Orientation
							}
							catch(IllegalArgumentException e)
							{
								throw new BattleshipIOExcepcion("Error! Orientación Desconocida");
							}
							
							try
							{
								for(int i = 3 ; i < comandos.length ; i++)	// a partir del 4º comando metemos las coords
								{
									coords[i] = Integer.parseInt(comandos[i]);		
									
								}
								Coordinate posicion = CoordinateFactory.createCoordinate(coords);
								
									b.addCraft(nave,posicion);
								
								
									
								
								
							}
							catch(NumberFormatException e)
							{
								throw new BattleshipIOExcepcion("Error! Alguna de las coordenadas no son números");
							}
							
					}
				
			}
			catch(IOException e)
			{
				throw new BattleshipIOExcepcion("Error! No se ha podido acceder al archivo");
			}
			
		}while(linea != null && comandos[0]!= "endput" && comandos[0]!= "exit");
		
		
	}

	@Override
	public Coordinate nextShoot(Board b) throws BattleshipIOExcepcion, InvalidCoordinateException, CoordinateAlreadyHitException 
	{	
		String linea;
		String comandos[];
		int coords[]= {};
		BufferedReader br = getBufferReader();
		Coordinate usada=null;
		
		try									//Lee línea y separa en comandos
		{
			linea = br.readLine();			
			comandos=linea.split("\\s+");
		}
		catch(IOException e)
		{
			throw new BattleshipIOExcepcion("Error! No se ha podido acceder al archivo");
		}
		
		if(comandos[0]!="shoot" || comandos[0]!="exit")
			throw new BattleshipIOExcepcion("Error! Comando Desconocido");
		if(comandos.length < 3 || comandos.length > 4)
			throw new BattleshipIOExcepcion("Error! La Cantidad de Parámetros no es Correcta");
		
		try
		{
			for(int i = 1 ; i < comandos.length ; i++)	// a partir del 2º comando metemos las coords
			{
				coords[i] = Integer.parseInt(comandos[i]);		
			
			}
		}
		catch(NumberFormatException e)
		{
			throw new BattleshipIOExcepcion("Error! Alguna de las coordenadas no son números");
		}
		usada = CoordinateFactory.createCoordinate(coords);
		b.hit(usada);										//Hiteamos con la coord
		
		return usada;
		
	}
	
}
