package model.io;

import model.Board;
import model.Coordinate;
import model.exceptions.io.BattleshipIOExcepcion;

public interface IPlayer 
{
	public String getName();
	public void putCrafts(Board b) throws BattleshipIOExcepcion;
	public Coordinate nextShoot(Board b);
	
}
