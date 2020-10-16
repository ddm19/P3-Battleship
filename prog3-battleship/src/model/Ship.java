package model;

import java.util.*;

public class Ship {
	
	private static int BOUNDING_SQUARE_SIZE=5;
	private static int HIT_VALUE=-1;
	private static int CRAFT_VALUE=1;
	private static char symbol;
	private String name;
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
	
	
	
	public Ship(Ship ship) 
	{
		orientation=ship.getOrientation();
		symbol = ship.getSymbol();
		name = new String(ship.getName());
	}



	public Coordinate getPosition() { return new Coordinate(position);}


	public void setPosition(Coordinate position) {
		Coordinate position2 = new Coordinate(position);	//Copia defensiva para evitar cambios en las referencias a objetos
		this.position = position2;
	}

	public String getName() { return name; }
	
	public Orientation getOrientation( ) { return orientation; }
	
	public char getSymbol() { return symbol; }
	
		
	
	public int[][] getShape() { return shape.clone(); }
	
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
		this.shape = shape.clone();
	}

	public int getShapeIndex(Coordinate c)
	{
		int x=c.get(0);
		int y=c.get(1);
		
		return y*BOUNDING_SQUARE_SIZE+x;
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
			for(int j = 0; j<BOUNDING_SQUARE_SIZE ;j++)
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

	public boolean hit(Coordinate c)
	{
		Set<Coordinate> posisbarco = new HashSet <Coordinate>(getAbsolutePositions());	// Creo un Set con los 1 en el mapa del barco
		int shapehit[][] = getShape();
		int hitcambio = -1,ori=numorientacion();
		boolean hiteado = false;
		
		if(posisbarco.contains(c))	// Si alguna de las coordenadas absolutas del barco contiene un 1 en c, cambiamos ese 1 por -1 y return true
		{
			hitcambio = getShapeIndex(c.subtract(getPosition())); //Le pasamos c-la posi del barco para pasar la coord abs del barco y no la relativa
			shapehit[ori][hitcambio] = HIT_VALUE;
			hiteado = true;
			setShape(shapehit);
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
		int ori = numorientacion(),index = getShapeIndex(c.subtract(getPosition()));
		boolean hiteado = false;
		
		if(shapeishit[ori][index]==HIT_VALUE)	// Si en la matriz la coordenada c es un -1 ya ha sido alcanzada
			hiteado=true;
		
		return hiteado;
	}
	
	public String toString()
	{		
		StringBuilder sb=new StringBuilder();
        int shape2[][]=getShape();
        int orientacion=numorientacion();
        sb.append(getName());
        sb.append("(");
        sb.append(getOrientation());
        sb.append(")\n");
        sb.append(" ");
        for(int i=0;i<BOUNDING_SQUARE_SIZE;i++) {
            sb.append("-");
        }
        sb.append("\n");
        for(int i=0;i<BOUNDING_SQUARE_SIZE;i++) {
            sb.append("|");
            for(int j=0;j<BOUNDING_SQUARE_SIZE;j++) {
                if(shape2[orientacion][j]==0) {
                    sb.append(Board.WATER_SYMBOL);
                }
                if(shape2[orientacion][j]==CRAFT_VALUE) {
                    sb.append(getSymbol());
                }
                if(shape2[orientacion][j]==HIT_VALUE) {
                    sb.append(Board.HIT_SYMBOL);
                }
            }
            sb.append("|\n");
        }
        sb.append(" ");
        for(int i=0;i<BOUNDING_SQUARE_SIZE;i++) {
            sb.append("-");
        }
        sb.append(" ");

        return sb.toString();
	}
}
