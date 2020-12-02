package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.exceptions.io.BattleshipIOException;

public class PlayerFactory 
{
	IPlayer createPlayer (String name, String s) throws BattleshipIOException
	{
		IPlayer player = null;
		
		if(s.contains(".") || s.contains("/") || s.contains("\\"))
		{
			BufferedReader br;
			try 
			{
				br = Files.newBufferedReader(Paths.get(s));			//Creo un BR (leer archivo desde ruta) para pasarlo a Player File
				 player = new PlayerFile(name,br);			

			} 
			catch (IOException e) 
			{
				throw new BattleshipIOException("Error! No se ha podido abrir el archivo" );
			}
		}
		else if(Long.parseLong(s)>0)
		{
			player = new PlayerRandom(name, Long.parseLong(s));
		}
		
		return player;
	}
	
	
}
