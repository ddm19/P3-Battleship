package model;

import model.Board;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;

public class Game 
{
	private boolean gameStarted;
	private int nextToShoot;
	private int shootCounter;
	private IPlayer p,player2;
	private Board board1,b;
	private static String NOINICIADO = "=== GAME NOT STARTED ===\n";
	private static String ENCURSO = "=== ONGOING GAME ===\n";
	private static String TERMINADO = "=== ONGOING GAME ===\n";
	private static String SEPARADOR = "==================================\n";
	
	public Game(Board b1,Board b2 , IPlayer p1, IPlayer p2)
	{
		if(b1 == null || b2 == null)
		{
			throw new NullPointerException("Error! Uno de los tableros es nulo");
		}
		if(p1 == null || p2 == null)
		{
			throw new NullPointerException("Error! Uno de los jugadores es nulo");
		}
		board1 = b1;
		b = b2;
		p = p1;
		player2 = p2;
		gameStarted = false;
	}
	
	
	
	public IPlayer getPlayer1() 
	{
		return p;
	}



	public IPlayer getPlayer2() 
	{
		return player2;
	}



	public Board getBoard1() 
	{
		return board1;
	}



	public Board getBoard2()
	{
		return b;
	}

	public void start()
	{
		try 
		{
			p.putCrafts(board1);
			player2.putCrafts(b);

		} 
		catch (Throwable e)
		{
			throw new RuntimeException(e);
		} 
	
		gameStarted = true;
		shootCounter = 0;
		nextToShoot = 0; // player1
	}
	
	public boolean gameEnded()
	{
		boolean terminado = false;
		if(gameStarted==true)	//SI el juego ha comenzado
		{
			if(getBoard1().areAllCraftsDestroyed() || getBoard2().areAllCraftsDestroyed())	// Si alguno de los jugadores ha destruido todos los barcos
			{
				terminado = true;
			}

		}
		
		return terminado;
	}
	
	private boolean shoot(IPlayer p,Board b,int next)
	{
		boolean shooteado = false;
		Coordinate c = null;
		try 
		{
			c=p.nextShoot(b);
			shooteado = true;
			nextToShoot = next;			//Disparo ok pasa playerx,+1disparados
			shootCounter++;

		} 
		catch (CoordinateAlreadyHitException e) 
		{
			System.out.println("Action by"+p.getName()+e.getMessage());
			shooteado = true;
			nextToShoot = next;		//Disparo ok (ya hiteado)
			shootCounter++;
		} 
		catch (InvalidCoordinateException | BattleshipIOException e) 
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		if(c==null)
			shooteado=false;
		
		return shooteado;
	}
	
	public boolean playNext()
	{
		boolean shooteado = false;
		
		if(nextToShoot == 1 || nextToShoot == 0)	//Dispara jugador1
		{
			shooteado=shoot(getPlayer1(),getBoard2(),2);	//Disparo player1
		}
		else
		{
			shooteado=shoot(getPlayer2(),getBoard1(),1);	//Disparo player2
		}
		

		return shooteado;
	}
	
	public IPlayer getPlayerLastShoot()
	{
		IPlayer ultimo = null;
		
		if(nextToShoot == 1)
			ultimo = getPlayer2();
		else if (nextToShoot == 2)
			ultimo = getPlayer1();
		
		return ultimo;
	}
	
	public void playGame(IVisualiser visualiser)
	{
		start(); //Inicia la partida
		boolean siguejugando = true;
		
		visualiser.show();
		
		while(siguejugando)
		{
			if(!playNext())
			{
				siguejugando = false;
			}
			else
				visualiser.show();
			if(gameEnded())
			{
				siguejugando = false;
			}
		}
		visualiser.close();
	}
	
	public String toString()
	{
		StringBuilder sb= new StringBuilder();
		
		if(!gameStarted)
			sb.append(NOINICIADO);
		else if (gameEnded())			//Estado
			sb.append(TERMINADO);
		else
			sb.append(ENCURSO);
		
		sb.append(SEPARADOR);
		sb.append(getPlayer1().getName());	//Jugador1
		sb.append(SEPARADOR);
		sb.append(getBoard1());				//Tablero1
		sb.append(SEPARADOR);
		sb.append(getPlayer2().getName());	//Jugador2
		sb.append(SEPARADOR);
		sb.append(getBoard2());				//Tablero2
		sb.append(SEPARADOR);
		sb.append("Number of shots: "+shootCounter);
		
		if(gameEnded())
			sb.append("\n"+getPlayerLastShoot().getName()+" wins");
		
		return sb.toString();
	}

}
