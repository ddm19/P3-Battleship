package model.io;

import java.awt.Color;
import java.io.File;

import model.Board;
import model.Game;
import model.aircraft.Board3D;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;
import model.ship.Board2D;

// TODO: Auto-generated Javadoc
/**
 * The Class VisualiserGIF.
 */
public class VisualiserGIF implements IVisualiser
{
	
	/** The agif. */
	private AnimatedGIF agif;
	
	/** The game. */
	private Game game;
	
	/**
	 * Instantiates a new visualiser GIF.
	 *
	 * @param g the g
	 */
	public VisualiserGIF(Game g) 
	{
		if(g==null)
			throw new NullPointerException("Error! Se ha recibido un Game nulo");
		game = g;
		agif = new AnimatedGIF();
	}

	/**
	 * Saca frames.
	 *
	 * @param b the b
	 * @param frame the frame
	 * @param sitio the sitio
	 */
	private void SacaFrames (String b,FrameGIF frame,int sitio)	// Coloca los frames adecuados según los símbolos de b (String de show de Board)
	{
		char novisto = Board.NOTSEEN_SYMBOL;
		char agua = Board.WATER_SYMBOL;
		char hit = Board.HIT_SYMBOL;
		char separator = Board.BOARD_SEPARATOR;
		
		int columna = 0;
		int fila = sitio;
		
		for(int i = 0; i<b.length() ; i++)
		{
				if( b.charAt(i) == novisto)	//Gris claro
				{
					try {
						frame.printSquare(columna, fila, Color.LIGHT_GRAY);
					} catch (BattleshipIOException e) {
						throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
					}
				}
				else if (b.charAt(i) == agua)	//azul
				{
					try {
						frame.printSquare(columna, fila, Color.BLUE);
					} catch (BattleshipIOException e) {
						throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
					}
				}
				else if (b.charAt(i) == hit)	//rojo
				{
					try {
						frame.printSquare(columna, fila, Color.RED);
					} catch (BattleshipIOException e) {
						throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
					}
				}
				else if (b.charAt(i) == separator) 	// Naranja
				{
					try {
						frame.printSquare(columna, fila, Color.ORANGE);
					} catch (BattleshipIOException e) {
						throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
					}
				}
				else if (b.charAt(i) == '\n')// Salto de línea '\n'
				{
					fila++;
					columna=0;
				}
				else	//Destruidos
				{
					try {
						frame.printSquare(columna, fila, Color.RED);
					} catch (BattleshipIOException e) {
						throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
					}
				}
			if(b.charAt(i) !='\n' )
				columna++;
		}
	}
	
	/**
	 * Show.
	 */
	@Override
	public void show()  
	{
		String b1 = game.getBoard1().show(false);		// Tableros en forma de cadena
		String b2 = game.getBoard2().show(false);
		int tam = game.getBoard1().getSize();
		int tamx=0,tamy=0;
		FrameGIF frame=null;
		
		if(game.getBoard1() instanceof Board3D)
		{
			tamx = tam*tam+tam-1;
			tamy = tam*2+1;
		}
		else if(game.getBoard1() instanceof Board2D)
		{
			tamx = tam;
			tamy = tam*2+1;
		}
		frame = new FrameGIF(tamx, tamy);	// Creo un nuevo frame con el tamaño del string b1 de ancho y 2*( Nº Saltos en b1)+1

		
		SacaFrames(b1, frame,0);	// IMprimo b1
		for(int i = 0 ; i<tamx ; i++)	// Espacios Grises 
			try {
				frame.printSquare(i, tam, Color.DARK_GRAY);
			} catch (BattleshipIOException e) {
				throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
			}
		SacaFrames(b2, frame,tam+1);	// IMprimo b2
		try {
			agif.addFrame(frame);		// Añado el Frame
		} catch (BattleshipIOException e) {
			throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
		}

	}

	/**
	 * Close.
	 */
	@Override
	public void close() 
	{
		try {
			agif.saveFile(new File("files/output.gif"));
		} catch (BattleshipIOException e)
		{
			throw new NullPointerException("Error! Se ha recibido un Game nulo");
		}

	}
	
	
}
