package model.exceptions;
import model.Coordinate;

public class InvalidCoordinateException extends BattleshipException {
	//@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	
	
	
	public InvalidCoordinateException (Coordinate c)
	{
		super(c);
	}
	public String getmessage()
	{
		String mensaje = "Error! Coordenada Inválida "+super.getC();
				
		return mensaje;
	}
}
