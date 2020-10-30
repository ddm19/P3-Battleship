package model;

import java.util.HashSet;
import java.util.Set;

import model.exceptions.CoordinateAlreadyHitException;
import model.Coordinate;
import model.CoordinateFactory;

public abstract class Craft {

	/** The bounding square size. */
	private static int BOUNDING_SQUARE_SIZE = 5;
	/** The hit value. */
	private static int HIT_VALUE = -1;
	/** The craft value. */
	private static int CRAFT_VALUE = 1;
	/** The symbol. */
	protected char symbol;
	/** The name. */
	protected String name;
	/** The shape. */
	protected int shape[][];		//Se inicializa en el constructor de las subclases
	/** The position. */
	protected Coordinate position = null;
	/** The orientation. */
	protected Orientation orientation;

	public Craft(Orientation o,char s,String n)	// orientation N/E/S/W - name = nombre del barco - symbol = carácter representativo del barco - position = coords
	{
		orientation=o;
		symbol = s;
		name = n;
		position=null;
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
	public Orientation getOrientation() { return orientation; }

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
	public int numorientacion() {
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
	public int getShapeIndex(Coordinate c) {
		if(c==null)
		{
			throw new NullPointerException("Error! La Coordenada es Nula");
		}
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
	public Set<Coordinate> getAbsolutePositions(Coordinate c) 
	{
		if(c==null)
		{
			throw new NullPointerException("Error! La Coordenada es Nula");
		}
		Set<Coordinate> Posiciones = new HashSet<Coordinate>();
		int ori=numorientacion(),numshape=-1;
		int tam[] = c.getComponents();
		Coordinate aux= CoordinateFactory.createCoordinate(tam); 
		int shape2[][] = getShape();
		
				
		//Posiciones=getposiciones(shape2,CRAFT_VALUE,c)
		
		for(int i = 0; i<BOUNDING_SQUARE_SIZE ;i++)
		{
			for(int j = 0; j<BOUNDING_SQUARE_SIZE ;j++)
			{
				aux.set(0, i);
				aux.set(1, j);
				if(tam.length==3)		// Si la coordenada es 3D iguala Z para no perder la del barco
					aux.set(2, tam[2]);
				numshape= getShapeIndex(aux);
				if(shape2[ori][numshape]==CRAFT_VALUE || shape2[ori][numshape]==HIT_VALUE)
				{
					aux=aux.add(c);
					Posiciones.add(aux.copy());
				
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
	public Set<Coordinate> getAbsolutePositions() {
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
	public boolean hit(Coordinate c) throws Exception
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
		else if(isHit(c))
		{
			throw new CoordinateAlreadyHitException(c);
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
	public boolean isShotDown() {	
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
	public boolean isHit(Coordinate c) {
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
	public String toString() {		
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