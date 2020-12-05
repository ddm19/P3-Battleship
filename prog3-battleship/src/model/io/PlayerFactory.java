package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.exceptions.io.BattleshipIOException;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Player objects.
 */
public class PlayerFactory 
{
	
	/**
	 * Creates a new Player object.
	 *
	 * @param name the name
	 * @param s the s
	 * @return the i player
	 * @throws BattleshipIOException the battleship IO exception
	 */
	public static IPlayer createPlayer (String name, String s) throws BattleshipIOException
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
		else 
		{
			
			try
			{
				if(Long.parseLong(s)>0)
					player = new PlayerRandom(name, Long.parseLong(s));
			}
			catch(NumberFormatException e)
			{
				player=null;
			}
		}
		
		return player;
	}
	
	
}
