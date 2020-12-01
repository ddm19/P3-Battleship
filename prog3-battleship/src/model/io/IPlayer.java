package model.io;

import model.Board;
import model.Coordinate;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOExcepcion;

public interface IPlayer 
{
	public String getName();
	public void putCrafts(Board b) throws BattleshipIOExcepcion,InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException;
	public Coordinate nextShoot(Board b) throws BattleshipIOExcepcion,InvalidCoordinateException, CoordinateAlreadyHitException ;
	
}
