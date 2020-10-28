package model.exceptions;
import model.Coordinate;

public class NextToAnotherCraftException extends BattleshipException {
	//@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	
	
	
	public NextToAnotherCraftException (Coordinate c)
	{
		super(c);
	}
	public String getmessage()
	{
		String mensaje = "Error! Coordenada en la Vecindad de un barco "+super.getC();
				
		return mensaje;
	}
}
