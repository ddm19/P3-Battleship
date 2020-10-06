package model;

import java.util.*;

public class Ship {
	
	private static int BOUNDING_SQUARE_SIZE=5;
	private static int HIT_VALUE=-1;
	private static int CRAFT_VALUE=1;
	private static char symbol;
	private static String name;
	private static int[][] shape;
	private Coordinate position=null;
	private Orientation orientation;
	
	public Ship(Orientation o,char s,String n)	// orientation N/E/S/W - name = nombre del barco - symbol = carácter representativo del barco - position = coords
	{
		orientation=o;
		symbol = s;
		name = n;
		
	}
	
	public Coordinate getPosition() { return new Coordinate(position);
	}



	public void setPosition(Coordinate position) {
		this.position = position;
	}



	public String getName() { return null; }
	
	public Orientation getOrientation( ) { return null; }
	
	public char getSymbol() { return 'a'; }
	
	public int getShapeIndex(Coordinate c)
	{
		int x=c.get(0);
		int y=c.get(1);
		
		return y*BOUNDING_SQUARE_SIZE+x;
	}
	
	public Set<Coordinate> getAbsolutePositions(Coordinate c) 
	{
		for()
		
		return null;
	}
	
	public Set<Coordinate> getAbsolutePositions() 
	{
		
		return null;
	}
	
	public static void setSymbol(char symbol) {
		Ship.symbol = symbol;
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public boolean hit(Coordinate c)
	{
		boolean alcanzado=false;
		
		if(c.equals(position)) 
		
		return false;
	}
	
	public boolean isShotDown()
	{
		return false;
	}
	
	public boolean isHit()
	{
		return false;
	}
	
	public String toString()
	{
		return null;
	}
}
