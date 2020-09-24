package model; 

public class Coordinate //Constructor
{
	
	int components[];
	int dim;
	
	public Coordinate(int x, int y)
	{
		dim=2;
		components = new int[dim];
		components[0]=x;
		components[1]=y; 
	}
	
	public Coordinate(Coordinate c)
	{
		dim=2;
		components = new int[dim];
		
		for(int i = 0; i<dim ;i++)
		{
			components[i]=c.components[i];
		}
		
	}
	
	public int get(int component)
	{
		if (component>=0 && component<dim)
		{
			return components[component];
		}
		else
			System.out.println("Error in Coordinate.get, component "+component+" is out of range");

		return -1;
		
	}
	public boolean equals(final Coordinate c)
	{
		for (int i=0;i<dim;i++)
			if (components[i] != c.components[i]) return false;
		return true;
		
	}
	public String toString()
	{
		StringBuilder concatenation = new StringBuilder();
		concatenation.append("(");
		for (int i=0;i<dim;i++)
		{
			concatenation.append(components[i]);
			if (i<dim-1) // no es la última
				concatenation.append(",");
		}
		concatenation.append(")");
		return concatenation.toString();
	}
	public Coordinate add(final Coordinate c) 
	{
		Coordinate new_c = new Coordinate(this);
		for (int i=0; i<dim; i++)
			new_c.set(i, new_c.get(i) + c.get(i));
                
		return new_c;
	}
	public final Coordinate substract(final Coordinate c) 
	{
		Coordinate new_c = new Coordinate(this); 
        
		for (int i=0; i<dim; i++)
			new_c.set(i, new_c.get(i) - c.get(i));        
		return new_c;
	}
	
	protected void set(int component,int value)
	{
		
		if (component>=0 && component<dim) {
			components[component] = value;
		}
		else
			System.out.println("Error in Coordinate.set, component "+component+" is out of range");
			}
};

	
	
		
		
	


