package model;

import java.util.*;

public class Ship {
	
	private static int BOUNDING_SQUARE_SIZE=5;
	private static int HIT_VALUE=-1;
	private static int CRAFT_VALUE=1;
	private static char symbol;
	private static String name;
	private int shape[][] = new int[][] {
        { 0, 0, 0, 0, 0,               // NORTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // EAST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // SOUTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // WEST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0}};             //          ·····
          
	private Coordinate position=null;
	private Orientation orientation;
	
	public Ship(Orientation o,char s,String n)	// orientation N/E/S/W - name = nombre del barco - symbol = carácter representativo del barco - position = coords
	{
		orientation=o;
		symbol = s;
		name = n;
		
	}
	
	public Coordinate getPosition() { return new Coordinate(position);}



	public void setPosition(Coordinate position) {
		Coordinate position2 = new Coordinate(position);	//Copia defensiva para evitar cambios en las referencias a objetos
		this.position = position2;
	}



	public String getName() { return null; }
	
	public Orientation getOrientation( ) { return null; }
	
	public char getSymbol() { return 'a'; }
	
	public int numorientacion()
	{
		
	}
	
	public int[][] getShape() { return shape; }

	public int getShapeIndex(Coordinate c)
	{
		int x=c.get(0);
		int y=c.get(1);
		
		return y*BOUNDING_SQUARE_SIZE+x;
	}
	
	public Set<Coordinate> getAbsolutePositions(Coordinate c) 
	{
		Set<Coordinate> Posiciones = new HashSet<Coordinate>();
		int ori=-1,numshape=-1;
		Coordinate aux= new Coordinate(-1,-1);
		int shape2[][] = getShape();
				
		for(int i = 0; i<BOUNDING_SQUARE_SIZE ;i++)
		{
			for(int j = 0; j<BOUNDING_SQUARE_SIZE ;i++)
			{
				aux.set(0, i);
				aux.set(1, j);
				numshape= getShapeIndex(aux);
				if(shape2[ori][numshape]==CRAFT_VALUE)
				{
					aux.add(c);
					Posiciones.add(new Coordinate(aux));
				}
			}
		}
		
		
		return Posiciones;
	}
	
	public Set<Coordinate> getAbsolutePositions() 
	{
		Set<Coordinate> Posiciones = new HashSet<Coordinate>();
		Coordinate pbarco = getPosition();
		
		Posiciones=getAbsolutePositions(pbarco);
		
		
		return Posiciones;
	}
	
	public static void setSymbol(char symbol) {
		Ship.symbol = symbol;
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public boolean hit(Coordinate c)
	{
		//boolean alcanzado=false;
		
		//if(c.equals(position)) // Una vez teniendo las coordenadas absolutas (en el tablero) de nuestro barco llamamos a getshapeindex para
								//acceder a la matriz y ver si es 0,-1 o 1, en caso de que sea 1 lo actualizamos y devolvemos true
		
		return false;
	}
	
	public boolean isShotDown()
	{
		return false;
	}
	
	public boolean isHit(Coordinate c)
	{
		// función común con hit para devolver que hay en esa coordenada (-1,0 o 1)
		return false;
	}
	
	public String toString()
	{
		return null;
	}
}
