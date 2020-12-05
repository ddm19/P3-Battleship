package model.io;

import model.Game;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Visualiser objects.
 */
public class VisualiserFactory 
{
	
	/**
	 * Creates a new Visualiser object.
	 *
	 * @param n the n
	 * @param g the g
	 * @return the i visualiser
	 */
	public static IVisualiser createVisualiser(String n, Game g)
	{
		IVisualiser visualiser=null;
		
		if(n == "Console")
			visualiser = new VisualiserConsole(g);
		else if (n == "GIF")
			visualiser = new VisualiserGIF(g);
		
		return visualiser;
	}
}
