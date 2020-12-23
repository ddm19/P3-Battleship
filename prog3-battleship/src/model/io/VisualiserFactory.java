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
		Class<?> tipovis = null;
		IVisualiser visualiser = null;
		try 
		{
			tipovis = Class.forName("model.io.Visualiser"+n);
			visualiser = (IVisualiser) tipovis.getConstructor(Game.class).newInstance(g);
		}
		catch (Throwable e) 
		{
			
		}	
		
		return visualiser;
	}
}
