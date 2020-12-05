package model.io;

import model.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class VisualiserConsole.
 */
public class VisualiserConsole implements IVisualiser
{
	
	/** The game. */
	private Game game;
	
	/**
	 * Instantiates a new visualiser console.
	 *
	 * @param g the g
	 */
	public VisualiserConsole(Game g) 
	{
		if(g==null)
			throw new NullPointerException("Error! Se ha recibido un Game nulo");
		game = g;
	}

	/**
	 * Show.
	 */
	@Override
	public void show() 
	{
		System.out.print(game.toString());
	}

	/**
	 * Close.
	 */
	@Override
	public void close()
	{
		
	}
}
