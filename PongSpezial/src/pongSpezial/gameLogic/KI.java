package pongSpezial.gameLogic;
import pongSpezial.dataModel.Ball;
import pongSpezial.dataModel.Bar;
import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.Geometry;
import pongSpezial.dataModel.Player;
import javafx.geometry.Point2D;

public class KI{
	
	private int difficulty;
	private Player controllingPlayer;
	
	public KI(int difficulty, Player controllingPlayer)
	{
		this.difficulty=difficulty;
		this.controllingPlayer=controllingPlayer;
	}	
	
	public String moveAIBar(Ball ball, Bar bar)
	{
		if(ball.getPosition().getY() >  bar.getPosition().getX())
		{
			 
		}
		return null;
	}
}
