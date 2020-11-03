package model.aircraft;

import model.Craft;
import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Aircraft.
 */
public abstract class Aircraft extends Craft
{
	
	/**
	 * Instantiates a new aircraft.
	 *
	 * @param o the o
	 * @param s the s
	 * @param n the n
	 */
	public Aircraft(Orientation o,char s,String n)	// orientation N/E/S/W - name = nombre del barco - symbol = carácter representativo del barco - position = coords
	{
		super(o,s,n);
	}
}
