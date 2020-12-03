package model.io;

import java.awt.Color;
import java.io.File;

import model.Board;
import model.Game;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;

public class VisualiserGIF implements IVisualiser
{
	private AnimatedGIF agif;
	private Game game;
	
	public VisualiserGIF(Game g) 
	{
		if(g==null)
			throw new NullPointerException("Error! Se ha recibido un Game nulo");
		game = g;
		agif = new AnimatedGIF();
	}

	private void SacaFrames (String b,FrameGIF frame)	// Coloca los frames adecuados según los símbolos de b (String de show de Board)
	{
		char novisto = Board.NOTSEEN_SYMBOL;
		char agua = Board.WATER_SYMBOL;
		char hit = Board.HIT_SYMBOL;
		char separator = Board.BOARD_SEPARATOR;
		int fila = 0;
		
		for(int i = 0; i<b.length() ; i++)
		{
			if( b.charAt(i) == novisto)	//gris claro
			{
				try {
					frame.printSquare(i, fila, Color.LIGHT_GRAY);
				} catch (BattleshipIOException e) {
					throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
				}
			}
			else if (b.charAt(i) == agua)	//azul
			{
				try {
					frame.printSquare(i, fila, Color.BLUE);
				} catch (BattleshipIOException e) {
					throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
				}
			}
			else if (b.charAt(i) == hit)	//rojo
			{
				try {
					frame.printSquare(i, fila, Color.RED);
				} catch (BattleshipIOException e) {
					throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
				}
			}
			else if (b.charAt(i) == separator) 	// Naranja
			{
				try {
					frame.printSquare(i, fila, Color.ORANGE);
				} catch (BattleshipIOException e) {
					throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
				}
			}
			else // Salto de línea '\n'
			{
				fila++;
			}
		}
	}
	
	@Override
	public void show()  
	{
		String b1 = game.getBoard1().show(false);		// Tableros en forma de cadena
		String b2 = game.getBoard2().show(false);
		
		FrameGIF frame = new FrameGIF(b1.length(), b1.split("\n").length*2+1);	// Creo un nuevo frame con el tamaño del string b1 de ancho y 2*( Nº Saltos en b1)+1
		
		SacaFrames(b1, frame);	// IMprimo b1
		for(int i = 0 ; i<b1.length() ; i++)	// Espacios Grises 
			try {
				frame.printSquare(i, b1.split("\n").length+1, Color.LIGHT_GRAY);
			} catch (BattleshipIOException e) {
				throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
			}
		SacaFrames(b2, frame);	// IMprimo b2
		try {
			agif.addFrame(frame);		// Añado el Frame
		} catch (BattleshipIOException e) {
			throw new RuntimeException("Error! Algo ha salido mal en GIF Show");
		}

	}

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
