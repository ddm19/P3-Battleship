package model;

import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Ship.
 */
public class Ship {
	
	/** The bounding square size. */
	private static int BOUNDING_SQUARE_SIZE=5;
	
	/** The hit value. */
	private static int HIT_VALUE=-1;
	
	/** The craft value. */
	private static int CRAFT_VALUE=1;
	
	/** The symbol. */
	private char symbol;
	
	/** The name. */
	private String name;
	
	/** The shape. */
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
          
	/** The position. */
	private Coordinate position=null;
	
	/** The orientation. */
	private Orientation orientation;
	
	/**
	 * Instantiates a new ship.
	 *
	 * @param o the o
	 * @param s the s
	 * @param n the n
	 */
	public Ship(Orientation o,char s,String n)	// orientation N/E/S/W - name = nombre del barco - symbol = carácter representativo del barco - position = coords
	{
		orientation=o;
		symbol = s;
		name = n;
		position=null;
	}
	
	
	
	/**
	 * Instantiates a new ship.
	 *
	 * @param ship the ship
	 */
	public Ship(Ship ship) 
	{
		orientation=ship.getOrientation();
		symbol = ship.getSymbol();
		name = new String(ship.getName());
		this.setPosition(ship.getPosition());
		
	}



	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Coordinate getPosition() { 
		Coordinate c = null;
	
	if(position!=null)
		c = position.copy();
		return c; }


	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Coordinate position) {
		if(position!=null)
			this.position = position.copy();
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() { return name; }
	
	/**
	 * Gets the orientation.
	 *
	 * @return the orientation
	 */
	public Orientation getOrientation( ) { return orientation; }
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() { return symbol; }
	
		
	
	/**
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public int[][] getShape() { return shape.clone(); }
	
	/**
	 * Numorientacion.
	 *
	 * @return the int
	 */
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
	
	/**
	 * Sets the shape.
	 *
	 * @param shape the new shape
	 */
	public void setShape(int[][] shape) {
		this.shape = shape.clone();
	}

	/**
	 * Gets the shape index.
	 *
	 * @param c the c
	 * @return the shape index
	 */
	public int getShapeIndex(Coordinate c)
	{
		int x=c.get(0);
		int y=c.get(1);
		
		return y*BOUNDING_SQUARE_SIZE+x;
	}
	
	/**
	 * Gets the absolute positions.
	 *
	 * @param c the c
	 * @return the absolute positions
	 */
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
					aux=aux.add(c);
					Posiciones.add(new Coordinate(aux));
				
				}
			}
		}
		

		
		
		return Posiciones;
	}
	
	/**
	 * Gets the absolute positions.
	 *
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions() //Igual que getabsolutepositions pero con las coordenadas del barco
	{
		Set<Coordinate> Posiciones = new HashSet<Coordinate>();
		Coordinate pbarco = getPosition();
		
		if(pbarco!=null)
			Posiciones=getAbsolutePositions(pbarco);
		
		
		return Posiciones;
	}

	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean hit(Coordinate c)
	{
		Set<Coordinate> posistrbarcoarco = new HashSet <Coordinate>(getAbsolutePositions());	// Creo un Set con los 1 en el mapa del barco
		int shapehit[][] = getShape();
		int hitcambio = -1,ori=numorientacion();
		boolean hiteado = false;
		
		if(posistrbarcoarco.contains(c) && !isHit(c))	// Si alguna de las coordenadas absolutas del barco contiene un 1 en c, cambiamos ese 1 por -1 y return true
		{
			
			c = c.subtract(getPosition());
			hitcambio = getShapeIndex(c); //Le pasamos c-la posi del barco para pasar la coord abs del barco y no la relativa
			shapehit[ori][hitcambio] = HIT_VALUE;
			hiteado = true;
			setShape(shapehit);
		}
		
		return hiteado;						//if(c.equals(position)) // Una vez teniendo las coordenadas absolutas (en el tablero) 
											//de nuestro barco llamamos a getshapeindex para
											//acceder a la matriz y ver si es 0,-1 o 1, en caso de que sea 1 lo actualizamos y devolvemos true
	}
	
	/**
	 * Checks if is shot down.
	 *
	 * @return true, if is shot down
	 */
	public boolean isShotDown()
	{	
		Set<Coordinate> posistrbarcoarco = this.getAbsolutePositions();
		boolean hundido = true;
		Coordinate posis[] = posistrbarcoarco.toArray(new Coordinate[posistrbarcoarco.size()]); //Transforma posistrbarcoarco en un array
		
		for(int i = 0; i<posistrbarcoarco.size() ; i++)	//para cada posición del barco comprueba que esté tocada (ishit), 
		{
			
			if(!this.isHit(posis[i]))	//Si no lo está, el barco no está hundido
			{
				hundido=false;
			}
		}
		
		return hundido;
	}
	
	/**
	 * Checks if is hit.
	 *
	 * @param c the c
	 * @return true, if is hit
	 */
	public boolean isHit(Coordinate c)
	{
		int shapeishit[][] = getShape();
		c = c.subtract(getPosition());
		int ori = numorientacion(),index = getShapeIndex(c);
		boolean hiteado = false;
		
		if(shapeishit[ori][index]==HIT_VALUE)	// Si en la matriz la coordenada c es un -1 ya ha sido alcanzada
			hiteado=true;
		
		return hiteado;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString()
	{		
		StringBuilder strbarco=new StringBuilder();
        int shapestringer[][]=getShape();
        int ori=numorientacion();
        int k = 0;
        strbarco.append(getName());
        strbarco.append(" (");
        strbarco.append(getOrientation());
        strbarco.append(")\n");
        strbarco.append(" ");
        for(int i=0;i<BOUNDING_SQUARE_SIZE;i++) {
            strbarco.append("-");
        }
        strbarco.append("\n");
        for(int i=0;i<BOUNDING_SQUARE_SIZE;i++) {
            strbarco.append("|");
            for(int j=0;j<BOUNDING_SQUARE_SIZE;j++) {
                if(shapestringer[ori][k]==0) {
                    strbarco.append(Board.WATER_SYMBOL);
                }
                if(shapestringer[ori][k]==CRAFT_VALUE) {
                    strbarco.append(getSymbol());
                }
                if(shapestringer[ori][k]==HIT_VALUE) {
                    strbarco.append(Board.HIT_SYMBOL);
                }
                k++;
            }
            strbarco.append("|\n");
        }
        strbarco.append(" ");
        for(int i=0;i<BOUNDING_SQUARE_SIZE;i++) {
            strbarco.append("-");
        }
        

        return strbarco.toString();
	}
}
