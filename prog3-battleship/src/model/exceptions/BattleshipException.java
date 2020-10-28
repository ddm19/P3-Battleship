package model.exceptions;
import model.Coordinate;

public abstract class BattleshipException extends java.lang.Exception
{
	//@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	private Coordinate c;
		
	public BattleshipException(Coordinate c)
	{
		this.c=c;
	}
	 
	public String getmessage()
	{
		String mensaje = "Error de coordenada"+getC();
		
		return mensaje;
	}
	public Coordinate getC() {
		return c;
	}
}
