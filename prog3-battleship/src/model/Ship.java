package model;

import java.util.*;

public class Ship {
	
	private static int BOUNDING_SQUARE_SIZE;
	private static int HIT_VALUE;
	private static int CRAFT_VALUE;
	private static char symbol;
	private static String name;
	private static int[][] shape;
	
	public Ship(Orientation o,char s,String n)
	{
		
	}
	
	public Coordinate getPosition()
	{
		return null;
	}
	
	public void setPosition(Coordinate position)
	{
		
	}
	
	public String getName() { return null; }
	
	public Orientation getOrientation( ) { return null; }
	
	public char getSymbol() { return 'a'; }
	
	public int getShapeIndex(Coordinate c)
	{
		return 1;
	}
	
	public Set<Coordinate> getAbsolutePositions(Coordinate c) 
	{
		return null;
	}
	
	public Set<Coordinate> getAbsolutePositions() 
	{
		return null;
	}
	
	public boolean hit(Coordinate c)
	{
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
