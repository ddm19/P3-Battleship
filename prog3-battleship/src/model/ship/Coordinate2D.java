package model.ship;

import model.Coordinate;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate2D.
 */
public class Coordinate2D extends Coordinate
{
	
	/**
	 * Instantiates a new coordinate 2 D.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinate2D(int x,int y)
	{
		super(2);
		set(0,x);
		set(1,y);
	}
	
	/**
	 * Instantiates a new coordinate 2 D.
	 *
	 * @param c the c
	 */
	public Coordinate2D(Coordinate2D c)
	{
		super(c);
	}
	
	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 */
	@Override
	public Set<Coordinate> adjacentCoordinates()
	{
		Set<Coordinate> adjacintos = new HashSet<Coordinate>();    //Creo un Set de adyacentes
        Coordinate2D aux = new Coordinate2D(-99,-99);
        int x,y;

        x=this.get(0);
        y=this.get(1);

        for(int i = -1; i<=1 ; i++)    //Recorre Filas
        {
            for(int j = -1; j<=1 ; j++)    //Recorre Columnas
            {
            	
                aux.set(0,x+i);
                aux.set(1,y+j);
                if(!this.equals(aux))
                    adjacintos.add(aux.copy());
            }
        }
        return adjacintos; 
	}
	
	/**
	 * Copy.
	 *
	 * @return the coordinate 2 D
	 */
	public Coordinate2D copy()
	{
		  return new Coordinate2D(this);
	}
		
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString()
	    {
			int components[] = getComponents();
	        StringBuilder concatenation = new StringBuilder();
	        concatenation.append("(");
	        for (int i=0;i<components.length;i++)
	        {
	            concatenation.append(components[i]);
	            if (i<components.length-1) // no es la última
	                concatenation.append(", ");
	        }
	        concatenation.append(")");
	        return concatenation.toString();
	    }
	
	/**
	 * Sets the.
	 *
	 * @param component the component
	 * @param value the value
	 */
	protected void set(int component,int value)
	{
		super.set(component, value);
	}
	}

