package pongSpezial.gameLogic;

import java.util.List;

import javafx.geometry.Point2D;
import pongSpezial.dataModel.Ball;
import pongSpezial.dataModel.Bar;
import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.Geometry;
import pongSpezial.dataModel.Player;
import pongSpezial.dataModel.PowerUp;
import pongSpezial.dataModel.PowerUpType;
import pongSpezial.dataModel.StopWatch;

public class PowerUpManager 
{
	private StopWatch timer;
	private PowerUp[] PowerUps;
	private boolean isPowerUpActive;
	private Player[] players;
	private final int MINTIME = 5000;
	private final int MAXTIME = 10000;
	
	
	
	
	public PowerUpManager(Player[] players)
	{
		timer = new StopWatch();
		this.players = players;
		isPowerUpActive = false;
		
		PowerUps = new PowerUp[PowerUpType.values().length];
		
		PowerUps[0] = new PowerUp(new Point2D(0,0), new Point2D(2,2),PowerUpType.TYP1);
		PowerUps[1] = new PowerUp(new Point2D(0,0), new Point2D(2,2),PowerUpType.TYP2);
	}
	

	public void startPowerUpEvent(BoardState boardstate)
	{
		
		isPowerUpActive = true;
		Point2D barColl = new Point2D(0,0);
		double barWidth = 0;
		
	
		PowerUp powerUp = (PowerUp)boardstate.getGeometries().get(boardstate.getGeometries().size()-1);
		List<Geometry>all = boardstate.getGeometries();
		
		switch(powerUp.getType())
		{
			case TYP1:				
				
				if(timer.startTimer(zufall(MINTIME,MAXTIME)))
				{	
						for (Geometry g : all) 
						{
							if(g instanceof Ball)
							{	
								Ball tmp = (Ball)g;
								Player player = tmp.getControllingPlayer();
								for(Geometry geo : all)
								{
									if(geo instanceof Bar)
									{
										Bar btmp = (Bar)geo;
										barColl = btmp.getCollisionSize();
										barWidth = btmp.getWidth();
										
										if(!(btmp.getControllingPlayer() == player))
										{
											btmp.setCollisionSize(new Point2D(btmp.getCollisionSize().getX()/2, btmp.getCollisionSize().getY()/2));
											btmp.setWidth(btmp.getWidth()/2);
										}
									}
								
								}
							
							}
						}
				}
			break;
			
			case TYP2:
				// beide powerUps machen im Moment dasselbe. das zweite ist also sozusagen ein Platzhalter
				if(timer.startTimer(zufall(MINTIME,MAXTIME)))
				{
					for (Geometry g : all) 
					{
						if(g instanceof Ball)
						{	
							
							Ball tmp = (Ball)g;
							Player player = tmp.getControllingPlayer();
							for(Geometry geo : all)
							{
								if(geo instanceof Bar)
								{
									Bar btmp = (Bar)geo;
									barColl = btmp.getCollisionSize();
									barWidth = btmp.getWidth();
									if(!(btmp.getControllingPlayer() == player))
									{
										btmp.setCollisionSize(new Point2D(btmp.getCollisionSize().getX()/2, btmp.getCollisionSize().getY()/2));
										btmp.setWidth(btmp.getWidth()/2);
									}
								}
							
						}
						
					}
				}
				}	
			break;
			
			
		}
		for(Geometry geo : all)
		{
			if(geo instanceof Bar)
			{
				Bar btmp = (Bar)geo;
				
				btmp.setCollisionSize(barColl);
				btmp.setWidth(barWidth);
				
			}
		
		}
		
		boardstate.getGeometries().remove(boardstate.getGeometries().size()-1);
		spawnPowerUp();
		isPowerUpActive = false;
	}
	
	public void showPowerUp(BoardState boardstate)
	{
		PowerUp tmp =spawnPowerUp();
		boardstate.getGeometries().add(tmp);
	}
	
	public PowerUp spawnPowerUp()
	{	
		PowerUp zufallPowerUp = PowerUps[zufall(0,1)];
		
		if(timer.startTimer(zufall(MINTIME,MAXTIME)))
		{
			
			zufallPowerUp.setPosition(new Point2D(zufall(1,5),zufall(1,5)));
		}
		zufallPowerUp.setPowerUpVisible(true);
		return zufallPowerUp;
	}
	
	private int zufall(int min, int max)
	{
		return (int) (Math.random()*(max-min+1)+min);
	}	
	
}
