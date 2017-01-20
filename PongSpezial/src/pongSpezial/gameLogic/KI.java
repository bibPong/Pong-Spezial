package pongSpezial.gameLogic;
import java.util.List;
import java.util.Vector;

import pongSpezial.dataModel.Ball;
import pongSpezial.dataModel.Bar;
import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.Player;

public class KI{
	
	private double difficulty; //Schwierigkeit einstellen
	//private int reactionTime; // Zeit,wie lange die KI zum Reagieren braucht
	//private final int DIFFICULTY_TIME = 100; // FInal variable mit dem Wert 100 
	private Player controllingPlayer;
	private Bar bar;
	
	public KI(double difficulty, Player controllingPlayer)
	{
		this.difficulty=difficulty;
		this.controllingPlayer=controllingPlayer;
		this.bar = GameManager.getBarForPlayer(controllingPlayer);
		//reactionTime = DIFFICULTY_TIME / difficulty;
		this.bar.setVelocity(difficulty/100);//Reaktionzeit ausrechnen
		
		
	}	
	
	public String moveAIBar(Ball ball)
	{
		if(controllingPlayer.getPlayerID() == 1 || controllingPlayer.getPlayerID() == 2) // Wenn Player oben oder unten am Bildschrim sind
		{
			if(bar.getPosition().getY() > ball.getPosition().getY())		//Wenn die x-Koordinate der KI Bar gr��er als die des Balls ist
			{
				/*
				try {
					Thread.sleep((long)reactionTime);						// Reaktionszeit
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				return "DOWN";												// Links bewegen
			}
			else if(bar.getPosition().getY() < ball.getPosition().getY()) //Wenn die x-Koordinate der KI Bar kleiner als die des Balls ist
			{
				/*
				try {
					Thread.sleep((long)reactionTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				return "UP";												//Rechts bewegen
			}
			else
			{
				/*
				try {
					Thread.sleep((long)reactionTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				return "KEY_RELEASED";										// Wenn gleich dann bliebt die Bar stehen
			}
		}
		else
		{ 
			if(bar.getPosition().getX() > ball.getPosition().getX()) 		//Wenn die y-Koordinate der KI Bar gr��er als die des Balls ist
			{
				/*
				try {
					Thread.sleep((long)reactionTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				return "LEFT";												//Runter bewegen
			}
			else if(bar.getPosition().getX() < ball.getPosition().getX())	//Wenn die y-Koordinate der KI Bar kleiner als die des Balls ist
			{
				/*
				try {
					Thread.sleep((long)reactionTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				return "RIGHT";												//Hoch bewegen
			}
			else
			{
				/*
				try {
					Thread.sleep((long)reactionTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				return "KEY_RELEASED";										// Wenn gleich dann bliebt die Bar stehen
			}
		}
		
	}
}
