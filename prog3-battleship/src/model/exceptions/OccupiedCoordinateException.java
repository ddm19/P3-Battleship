package model.exceptions;
import model.Coordinate;

public class OccupiedCoordinateException extends BattleshipException {
	//@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	
	
	
	public OccupiedCoordinateException (Coordinate c)
	{
		super(c);
	}
	public String getmessage()
	{
		String mensaje = "Error! Coordenada Ocupada "+super.getC();
				
		return mensaje;
	}
}
