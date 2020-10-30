package model.ship;

import model.Craft;
import model.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class Ship.
 */
public abstract class Ship extends Craft {
	
	/**
	 * Instantiates a new ship.
	 *
	 * @param o the o
	 * @param s the s
	 * @param n the n
	 */
	public Ship(Orientation o,char s,String n)	// orientation N/E/S/W - name = nombre del barco - symbol = carácter representativo del barco - position = coords
	{
		super(o,s,n);
	}
	
	
	
}
