package model.io;

import java.io.BufferedReader;
import java.io.IOException;

import model.Board;
import model.Coordinate;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
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
	public void putCrafts(Board b) throws BattleshipIOExcepcion
	{
		String comandos[];
		String linea;
		BufferedReader br = getBufferReader();
		do
		{
			try
			{
				linea = br.readLine();
				comandos=linea.split("\\s+");
				
				switch(comandos[0])
					{
						case "put":	//Poner Barco
							if(comandos.length < 5 || comandos.length > 6)
								throw new BattleshipIOExcepcion("Error! La Cantidad de Parámetros no es Correcta");
							int coords[]=null;
							Craft nave = CraftFactory.createCraft(comandos[1],Orientation.valueOf(comandos[2]));//Creo nave con "Type" y Orientation
							
							for(int i = 4 ; i < comandos.length ; i++)	//
							{
								try
								{
									coords[i] = Integer.parseInt(comandos[i]);		
								}
								catch(NumberFormatException e)
								{
									throw new BattleshipIOExcepcion("Error! Alguna de las coordenadas no son números");
								}
							}
					}
				
			}
			catch(IOException e)
			{
				throw new BattleshipIOExcepcion("Error! No se ha podido acceder al archivo");
			}
			
		}while(linea != null);
		
		
	}

	@Override
	public Coordinate nextShoot(Board b) 
	{
				
	}
	
}
