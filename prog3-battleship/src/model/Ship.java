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
	
	public Orientation getOrientation( ) { return orientation; }
	
	public char getSymbol() { return 'a'; }
	
		
	public int[][] getShape() { return shape; }
	
	public int numorientacion()
	{
		int ori=-1;
		Orientation o = getOrientation();
		switch (o) 
		{
			case NORTH:
				ori = 0;
				break;
			case EAST:
				ori = 1;
				break;
			case SOUTH:
				ori = 2;
				break;
			case WEST:
				ori = 3;
				break;
		}
		return ori;
			
		
	}

	/*public Set<Coordinate> cogeposiciones() // Devuelve un Set
	{
		Coordinate aux = new Coordinate (-1,-1);
		int shapecoge[][] = getShape();
		int numshape = -1,ori = numorientacion();
		for(int i = 0; i<BOUNDING_SQUARE_SIZE ;i++)
		{
			for(int j = 0; j<BOUNDING_SQUARE_SIZE ;i++)
			{
				aux.set(0, i);
				aux.set(1, j);
				numshape= getShapeIndex(aux);
				if(shapecoge[ori][numshape]==CRAFT_VALUE)
				{
					aux.add(c);
					Posiciones.add(new Coordinate(aux));
				}
			}
		}
	}*/
	
	public void setShape(int[][] shape) {
		this.shape = shape;
	}

	public int getShapeIndex(Coordinate c)
	{
		int x=c.get(0);
		int y=c.get(1);
		
		return y*BOUNDING_SQUARE_SIZE+x;
	}
		
	public Set<Coordinate> getposiciones(int shapeget[][] ,int tipo, Coordinate c)
	{
		Set<Coordinate> posis = new HashSet<Coordinate>();
		
		int ori=numorientacion(),numshape=-1;
		Coordinate aux= new Coordinate(-1,-1);
		
		for(int i = 0; i<BOUNDING_SQUARE_SIZE ;i++)
		{
			for(int j = 0; j<BOUNDING_SQUARE_SIZE ;i++)
			{
				aux.set(0, i);
				aux.set(1, j);
				numshape= getShapeIndex(aux);
				if(shapeget[ori][numshape]==tipo)
				{
					aux.add(c);
					posis.add(new Coordinate(aux));
				}
			}
		}
	
		return posis;
	}
	
	public Set<Coordinate> getAbsolutePositions(Coordinate c) // Devuelve un Set con las coordenadas en el tablero de una coordenada de un barco (Los 1 y -1 del Barco)
	{
		Set<Coordinate> Posiciones = new HashSet<Coordinate>();
		int ori=numorientacion(),numshape=-1;
		Coordinate aux= new Coordinate(-1,-1);
		int shape2[][] = getShape();
				
		//Posiciones=getposiciones(shape2,CRAFT_VALUE,c)
		
		
		for(int i = 0; i<BOUNDING_SQUARE_SIZE ;i++)
		{
			for(int j = 0; j<BOUNDING_SQUARE_SIZE ;i++)
			{
				aux.set(0, i);
				aux.set(1, j);
				numshape= getShapeIndex(aux);
				if(shape2[ori][numshape]==CRAFT_VALUE || shape2[ori][numshape]==HIT_VALUE)
				{
					aux.add(c);
					Posiciones.add(new Coordinate(aux));
				}
			}
		}
		
		
		return Posiciones;
	}
	
	public Set<Coordinate> getAbsolutePositions() //Igual que getabsolutepositions pero con las coordenadas del barco
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
		Set<Coordinate> posisbarco = new HashSet <Coordinate>(getAbsolutePositions());	// Creo un Set con los 1 en el mapa del barco
		int shapehit[][] = getShape();
		int hitcambio = -1,ori=numorientacion();
		boolean hiteado = false;
		
		if(posisbarco.contains(c))	// Si alguna de las coordenadas absolutas del barco contiene un 1 en c, cambiamos ese 1 por -1 y return true
		{
			hitcambio = getShapeIndex(c);
			shapehit[ori][hitcambio] = HIT_VALUE;
			hiteado = true;
		}
		
		return hiteado;						//if(c.equals(position)) // Una vez teniendo las coordenadas absolutas (en el tablero) 
											//de nuestro barco llamamos a getshapeindex para
											//acceder a la matriz y ver si es 0,-1 o 1, en caso de que sea 1 lo actualizamos y devolvemos true
	}
	
	public boolean isShotDown()
	{	
		Set<Coordinate> posisbarco = getAbsolutePositions();
		boolean hundido = true;
		Coordinate posis[] = posisbarco.toArray(new Coordinate[posisbarco.size()]); //Transforma posisbarco en un array
		
		for(int i = 0; i<posisbarco.size() ; i++)	//para cada posición del barco comprueba que esté tocada (ishit), 
		{
			if(!this.isHit(posis[i]))	//Si no lo está, el barco no está hundido
				hundido=false;
		}
		return hundido;
	}
	
	public boolean isHit(Coordinate c)
	{
		int shapeishit[][] = getShape();
		int ori = numorientacion(),index = getShapeIndex(c);
		boolean hiteado = false;
		
		if(shapeishit[ori][index]==1)	// Si en la matriz la coordenada c es un -1 ya ha sido alcanzada
			hiteado=true;
		
		return hiteado;
	}
	
	public String toString()
	{
		return null;
	}
}
