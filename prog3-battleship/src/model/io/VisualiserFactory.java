package model.io;

import model.Game;

public class VisualiserFactory 
{
	IVisualiser createVisualiser(String n, Game g)
	{
		IVisualiser visualiser=null;
		
		if(n == "Console")
			visualiser = new VisualiserConsole(g);
		else if (n == "GIF")
			visualiser = new VisualiserGif(g);
		
		return visualiser;
	}
}
