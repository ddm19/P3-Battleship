package model.aircraft;

public class Coordinate3D extends model.Coordinate
{
	public Coordinate3D(int x,int y,int z)
	{
		
	}
	
	public Coordinate3D(Coordinate3D c)
	{
		
	}
	
	public Set<Coordinate> adjacentCoordinates
	{
		
	}
	
	public Coordinate3D copy()
	{
		
	}
		
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

}
