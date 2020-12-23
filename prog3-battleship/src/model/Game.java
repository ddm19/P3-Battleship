package model;

import model.Board;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;
import model.score.CraftScore;
import model.score.HitScore;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public class Game 
{
	
	/** The vacio. */
	private boolean gameStarted,vacio;
	
	/** The next to shoot. */
	private int nextToShoot;
	
	/** The shoot counter. */
	private int shootCounter;
	
	/** The player 2. */
	private IPlayer p,player2;
	
	/** The b. */
	private Board board1,b;
	
	/** The hit score 1. */
	private HitScore hitScore1;
	
	/** The hit score 2. */
	private HitScore hitScore2;

	/** The craft score 1. */
	private CraftScore craftScore1;

	/** The craft score 2. */
	private CraftScore craftScore2;
	
	/** The noiniciado. */
	private static String NOINICIADO = "=== GAME NOT STARTED ===\n";
	
	/** The encurso. */
	private static String ENCURSO = "=== ONGOING GAME ===\n";
	
	/** The terminado. */
	private static String TERMINADO = "=== GAME ENDED ===\n";
	
	/** The hitscore. */
	private static String HITSCORE = "HitScore: ";
	
	/** The craftscore. */
	private static String CRAFTSCORE = "CraftScore: ";
	/** The separador. */
	private static String SEPARADOR = "==================================\n";
	
	/** The scoreseparator. */
	private static String SCORESEPARATOR = "--------------";
	/**
	 * Instantiates a new game.
	 *
	 * @param b1 the b 1
	 * @param b2 the b 2
	 * @param p1 the p 1
	 * @param p2 the p 2
	 */
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
		vacio=false;
		hitScore1 = new HitScore(p1);
		hitScore2 = new HitScore(p2);
		craftScore1 = new CraftScore(p1);
		craftScore2 = new CraftScore(p2);
		
	}
	
	
	
	/**
	 * Gets the player 1.
	 *
	 * @return the player 1
	 */
	public IPlayer getPlayer1() 
	{
		return p;
	}



	/**
	 * Gets the player 2.
	 *
	 * @return the player 2
	 */
	public IPlayer getPlayer2() 
	{
		return player2;
	}


	/**
	 * Gets the hit score player 1.
	 *
	 * @return the hit score player 1
	 */
	public HitScore getHitScorePlayer1() {
		return hitScore1;
	}

	/**
	 * Gets the hit score player 2.
	 *
	 * @return the hit score player 2
	 */
	public HitScore getHitScorePlayer2() {
		return hitScore2;
	}

	/**
	 * Gets the craft score player 1.
	 *
	 * @return the craft score player 1
	 */
	public CraftScore getCraftScorePlayer1() {
		return craftScore1;
	}

	/**
	 * Gets the craft score player 2.
	 *
	 * @return the craft score player 2
	 */
	public CraftScore getCraftScorePlayer2() {
		return craftScore2;
	}

	/**
	 * Gets the board 1.
	 *
	 * @return the board 1
	 */
	public Board getBoard1() 
	{
		return board1;
	}



	/**
	 * Gets the board 2.
	 *
	 * @return the board 2
	 */
	public Board getBoard2()
	{
		return b;
	}

	/**
	 * Start.
	 */
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
	
	/**
	 * Game ended.
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Shoot.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	
	private void actualizaScore(Coordinate c)
	{
		CellStatus lastShotStatus = null;
		HitScore hitscore = null;
		CraftScore craftscore = null;
		Board b = null;
		
		if(nextToShoot == 1)
		{
			lastShotStatus=getPlayer1().getLastShotStatus();	// Recogemos status
			hitscore = getHitScorePlayer1();							//hitscore
			craftscore = getCraftScorePlayer1();						// craft score
			b = getBoard2();									// board donde disparó
			
		}
		else if (nextToShoot == 2)
		{
			lastShotStatus = getPlayer2().getLastShotStatus();	//Recogemos status
			hitscore = getHitScorePlayer2();							//hitscore
			craftscore = getCraftScorePlayer2();						// craft score
			b = getBoard1();									// board donde disparó
		}
		if(nextToShoot != 0)
			hitscore.score(lastShotStatus);	//hitscore++
			
		if(lastShotStatus == CellStatus.DESTROYED)			// si destruido obtenemos barco y actualizamos craftscore
			craftscore.score(b.getCraft(c));
			
		
	}
	
	/**
	 * Gets the score info.
	 *
	 * @return the score info
	 */
	public String getScoreInfo()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Player 1\n");
		sb.append(HITSCORE+getHitScorePlayer1().toString()+"\n");
		sb.append(CRAFTSCORE+getCraftScorePlayer1().toString()+"\n");
		sb.append(SCORESEPARATOR+"\n");
		sb.append("Player 2\n");
		sb.append(HITSCORE+getHitScorePlayer2().toString()+"\n");
		sb.append(CRAFTSCORE+getCraftScorePlayer2().toString());
		
		return sb.toString();
	}
	
	/**
	 * Shoot.
	 *
	 * @param p the p
	 * @param b the b
	 * @param next the next
	 * @return true, if successful
	 */
	private boolean shoot(IPlayer p,Board b,int next)
	{
		
			boolean shooteado = false;
			Coordinate c = null;
			
			try 
			{
				c=p.nextShoot(b);
				actualizaScore(c);		// llamo antes de NextToShoot para actualizar según el player del último disparo
				nextToShoot = next;		//Disparo ok pasa playerx,+1disparados
				
				if(c!=null)
				{
					shooteado = true;
					
					shootCounter++;
				}
				else
				{
					shooteado=false;
					vacio = true;
					
				}
	
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
		
		
		
		return shooteado;
	}
	
	/**
	 * Play next.
	 *
	 * @return true, if successful
	 */
	public boolean playNext()
	{
		boolean shooteado = false;
		
		if(!vacio)	//Si el anterior disparo no ha sido el último 
		{
			if(nextToShoot == 1 || nextToShoot == 0)	//Dispara jugador1
			{
				shooteado=shoot(getPlayer1(),getBoard2(),2);	//Disparo player1
			}
			else if(nextToShoot == 2)
			{
				shooteado=shoot(getPlayer2(),getBoard1(),1);	//Disparo player2
			}
		}
		

		return shooteado;
	}
	
	/**
	 * Gets the player last shoot.
	 *
	 * @return the player last shoot
	 */
	public IPlayer getPlayerLastShoot()
	{
		IPlayer ultimo = null;
		
		if(nextToShoot == 1)
			ultimo = getPlayer2();
		else if (nextToShoot == 2)
			ultimo = getPlayer1();
		
		return ultimo;
	}
	
	/**
	 * Play game.
	 *
	 * @param visualiser the visualiser
	 */
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
	
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
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
		sb.append(getPlayer1().getName()+'\n');	//Jugador1
		sb.append(SEPARADOR);
		sb.append(getBoard1().show(false)+'\n');				//Tablero1
		sb.append(SEPARADOR);
		sb.append(getPlayer2().getName() +'\n');	//Jugador2
		sb.append(SEPARADOR);
		sb.append(getBoard2().show(false)+'\n');				//Tablero2
		sb.append(SEPARADOR);
		sb.append("Number of shots: "+shootCounter+'\n');
		
		if(gameEnded())
			sb.append(getPlayerLastShoot().getName()+" wins");
		
		return sb.toString();
	}

}
