package pongSpezial.gameLogic;
import java.util.List;
import java.util.Vector;

import pongSpezial.dataModel.Ball;
import pongSpezial.dataModel.Bar;
import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.Player;

public class KI{
	
	private int difficulty;
	private Player controllingPlayer;
	private Bar bar;
	
	public KI(int difficulty, Player controllingPlayer)
	{
		this.difficulty=difficulty;
		this.controllingPlayer=controllingPlayer;
		this.bar = GameManager.getBarForPlayer(controllingPlayer);
	}	
	
	public String moveAIBar(Ball ball)
	{
		if(controllingPlayer.getPlayerID() == 1 || controllingPlayer.getPlayerID() == 3)
		{
			if(bar.getPosition().getX() > ball.getPosition().getX())
			{
				return "LEFT";
			}
			else if(bar.getPosition().getX() < ball.getPosition().getX())
			{
				return "RIGHT";
			}
			else
			{
				return "KEY_RELEASED";
			}
		}
		else
		{
			if(bar.getPosition().getY() > ball.getPosition().getY())
			{
				return "DOWN";
			}
			else if(bar.getPosition().getY() < ball.getPosition().getY())
			{
				return "UP";
			}
			else
			{
				return "KEY_RELEASED";
			}
		}
		
	}
}
