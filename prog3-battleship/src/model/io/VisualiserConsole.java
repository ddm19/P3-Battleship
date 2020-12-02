package model.io;

import model.Game;

public class VisualiserConsole implements IVisualiser
{
	private Game game;
	
	public VisualiserConsole(Game g) 
	{
		if(g==null)
			throw new NullPointerException("Error! Se ha recibido un Game nulo");
		game = g;
	}

	@Override
	public void show() 
	{
		System.out.print(game.toString());
	}

	@Override
	public void close()
	{
		
	}
}
