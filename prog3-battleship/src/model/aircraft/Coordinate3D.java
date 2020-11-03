package model.aircraft;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate3D.
 */
public class Coordinate3D extends Coordinate
{
	
	/**
	 * Instantiates a new coordinate 3 D.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Coordinate3D(int x,int y,int z)
	{
		super(3);
		set(0,x);
		set(1,y);
		set(2,z);
	}
	
	/**
	 * Instantiates a new coordinate 3 D.
	 *
	 * @param c the c
	 */
	public Coordinate3D(Coordinate3D c)
	{
		super(c);
	}
	
	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 */
	public Set<Coordinate> adjacentCoordinates()
	{
		Set<Coordinate> adjacintos = new HashSet<Coordinate>();    //Creo un Set de adyacentes
        Coordinate3D aux = new Coordinate3D(-99,-99,-99);
        int x,y,z;

        x=this.get(0);
        y=this.get(1);
        z=this.get(2);

        for(int i = -1; i<=1 ; i++)    //Recorre Filas
        {
            for(int j = -1; j<=1 ; j++)    //Recorre Columnas
            {
            	
               
                for(int k = -1; k<=1 ; k++)    //Recorre Columnas
                {
                	 aux.set(0,x+i);
                     aux.set(1,y+j);
                     aux.set(1,z+k);
                }
               
                
                if(!this.equals(aux))
                    adjacintos.add(aux.copy());
            }
        }
        return adjacintos; 
	}
	
	/**
	 * Copy.
	 *
	 * @return the coordinate 3 D
	 */
	public Coordinate3D copy()
	{
		 return new Coordinate3D(this);
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
