package model.exceptions;
import model.Coordinate;

public class CoordinateAlreadyHitException extends BattleshipException {
	//@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	
	
	
	public CoordinateAlreadyHitException (Coordinate c)
	{
		super(c);
	}
	public String getmessage()
	{
		String mensaje = "Error! Coordenada ya impactada "+super.getC();
				
		return mensaje;
	}
}
