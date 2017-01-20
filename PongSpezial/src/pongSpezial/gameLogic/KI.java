package pongSpezial.gameLogic;
import java.util.List;
import java.util.Vector;

import pongSpezial.dataModel.Ball;
import pongSpezial.dataModel.Bar;
import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.Player;

public class KI extends Player{
	
	private double difficulty; //Schwierigkeit einstellen
	//private int reactionTime; // Zeit,wie lange die KI zum Reagieren braucht
	//private final int DIFFICULTY_TIME = 100; // FInal variable mit dem Wert 100 
	private Player controllingPlayer;
	
	public KI(int ID)
	{
		super(ID, "KI-"+ID);
		controllingPlayer = new Player(ID, "KI-"+ID);
	}	
	
	public String moveAIBar(Ball ball)
	{
		Bar bar = GameManager.getBarForPlayer(controllingPlayer);
		bar.setVelocity(difficulty/100);
		
		if(controllingPlayer.getPlayerID() == 1 || controllingPlayer.getPlayerID() == 2) // Wenn Player oben oder unten am Bildschrim sind
		{
			if(bar.getPosition().getY() > ball.getPosition().getY())		//Wenn die x-Koordinate der KI Bar größer als die des Balls ist
			{
				return "DOWN";												// Links bewegen
			}
			else if(bar.getPosition().getY() < ball.getPosition().getY()) //Wenn die x-Koordinate der KI Bar kleiner als die des Balls ist
			{
				return "UP";												//Rechts bewegen
			}
			else
			{
				return "KEY_RELEASED";										// Wenn gleich dann bliebt die Bar stehen
			}
		}
		else
		{ 
			if(bar.getPosition().getX() > ball.getPosition().getX()) 		//Wenn die y-Koordinate der KI Bar größer als die des Balls ist
			{
				return "LEFT";												//Runter bewegen
			}
			else if(bar.getPosition().getX() < ball.getPosition().getX())	//Wenn die y-Koordinate der KI Bar kleiner als die des Balls ist
			{
				return "RIGHT";												//Hoch bewegen
			}
			else
			{
				return "KEY_RELEASED";										// Wenn gleich dann bliebt die Bar stehen
			}
		}
		
	}
}
