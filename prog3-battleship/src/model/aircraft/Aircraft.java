package model.aircraft;

import model.Craft;
import model.Orientation;

public abstract class Aircraft extends Craft
{
	public Aircraft(Orientation o,char s,String n)	// orientation N/E/S/W - name = nombre del barco - symbol = carácter representativo del barco - position = coords
	{
		super(o,s,n);
	}
}
