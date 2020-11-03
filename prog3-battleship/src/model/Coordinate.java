package model; 
import java.util.*;
// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate.
 * 
 * @author Daniel Domenech Moreno ddm19 - 54207039H
 */
public abstract class Coordinate //Constructor
{
	
	/** The components. */
	private int components[];
	
	/**
	 * Gets the components.
	 *
	 * @return the components
	 */
	public int[] getComponents() {
		return components;
	}

	/**
	 * Instantiates a new coordinate.
	 *
	 * @param dimensiones the dimensiones
	 */
	protected Coordinate(int dimensiones)
	{
		components = new int[dimensiones];
		
	}
		
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param c the c
	 */
	protected Coordinate(Coordinate c)
	{
		components = new int[c.components.length];
		
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
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public int get(int component) throws IllegalArgumentException
	{
		if (component>=0 && component<components.length)
		{
			return components[component];
		}
		else
			throw new IllegalArgumentException("Error in Coordinate.get, fuera de rango");
			//System.err.println("Error in Coordinate.get, component "+component+" is out of range");

		
		
	}

	/**
	 * To string.
	 *
	 * @param c the c
	 * @return the string
	 * @throws Exception the exception
	 */
	
	
	/**
	 * Adds the.
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate add(final Coordinate c) throws Exception
	{
		Coordinate new_c = this.copy();
		int tam = -1;
		if(c==null)
			throw new NullPointerException("Error en Coordinate.add, La coordenada recibida es nula");
		if (c.components.length == components.length)
		{
			tam=components.length;
		}
		else
		{
			tam = 2;
		}
		
		for (int i=0; i<tam; i++)
				new_c.set(i, new_c.get(i) + c.get(i));
		
                
		return new_c;
	}
	
	/**
	 * Subtract.
	 *
	 * @param c the c
	 * @return the coordinate
	 * @throws Exception the exception
	 */
	public final Coordinate subtract(final Coordinate c) throws Exception
	{
		Coordinate new_c = this.copy(); 
		int tam = -1;
		if(c==null)
			throw new NullPointerException("Error en Coordinate.add, La coordenada recibida es nula");
		if (c.components.length == components.length)
		{
			tam=components.length;
		}
		else
		{
			tam = 2;
		}
		for (int i=0; i<tam; i++)
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
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	protected void set(int component,int value) throws IllegalArgumentException
	{
		
		if (component>=0 && component<components.length) {
			components[component] = value;
		}
		else
			throw new IllegalArgumentException("Error in Coordinate.get, fuera de rango");
			//System.err.println("Error in Coordinate.set, component "+component+" is out of range");
	}
	
	/**
	 * Copy.
	 *
	 * @return the coordinate
	 */
	public abstract Coordinate copy();
	
	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 */
	public abstract Set<Coordinate> adjacentCoordinates();
	
	
};

	
	
		
		
	


