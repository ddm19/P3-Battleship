package model; 
import java.util.*;
/**
 * The Class Coordinate.
 * 
 * @author Daniel Domenech Moreno ddm19 - 54207039H
 */
public class Coordinate //Constructor
{
	
	/** The components. */
	private int components[];
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinate(int x, int y)
	{
		components = new int[2];
		components[0]=x;
		components[1]=y; 
	}
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param c the c
	 */
	public Coordinate(Coordinate c)
	{
		components = new int[2];
		
		for(int i = 0; i<components.length ;i++)
		{
			components[i]=c.components[i];
		}
		
	}
	
	/**
	 * Gets the.
	 *
	 * @param component the component
	 * @return the int
	 */
	public int get(int component)
	{
		if (component>=0 && component<components.length)
		{
			return components[component];
		}
		else
			System.err.println("Error in Coordinate.get, component "+component+" is out of range");

		return -1;
		
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString()
	{
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
	 * Adds the.
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate add(final Coordinate c) 
	{
		Coordinate new_c = new Coordinate(this);
		for (int i=0; i<components.length; i++)
			new_c.set(i, new_c.get(i) + c.get(i));
                
		return new_c;
	}
	
	/**
	 * Subtract.
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public final Coordinate subtract(final Coordinate c) 
	{
		Coordinate new_c = new Coordinate(this); 
        
		for (int i=0; i<components.length; i++)
			new_c.set(i, new_c.get(i) - c.get(i));        
		return new_c;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (!Arrays.equals(components, other.components))
			return false;
		return true;
	}

	/**
	 * Sets the.
	 *
	 * @param component the component
	 * @param value the value
	 */
	protected void set(int component,int value)
	{
		
		if (component>=0 && component<components.length) {
			components[component] = value;
		}
		else
			System.err.println("Error in Coordinate.set, component "+component+" is out of range");
	}
	
	public Coordinate copy()
	{
		return new Coordinate(this);
	}
	
	public Set<Coordinate> adjacentCoordinates()
	{
		Set<Coordinate> adjacintos = new HashSet<Coordinate>();	//Creo un Set de adyacentes
		Coordinate aux = new Coordinate(-99,-99);
		int x,y;
		
		x=this.get(0);
		y=this.get(1);
		
		for(int i = -1; i<=1 ; i++)	//Recorre Filas
		{
			for(int j = -1; j<=1 ; j++)	//Recorre Columnas
			{
				
				aux.set(0,x+i);
				aux.set(1,y+j);
				if(!this.equals(aux))
					adjacintos.add(new Coordinate(aux));
			}
			
				
		}
	return adjacintos;
	}
	
};

	
	
		
		
	


