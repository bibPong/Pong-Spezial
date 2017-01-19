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
	
	
	
	public PowerUpManager(Player[] players)
	{
		timer = new StopWatch();
		this.players = players;
		isPowerUpActive = false;
		
		PowerUps[0] = new PowerUp(new Point2D(0,0), new Point2D(2,2),PowerUpType.TYP1);
		PowerUps[1] = new PowerUp(new Point2D(0,0), new Point2D(2,2),PowerUpType.TYP2);
	}
	
	
	//Die Parameter sind als im Klassendiagramm, da es so sinnvoller erschien
	public void startPowerUpEvent(BoardState boardstate)
	{
		
		isPowerUpActive = true;
		
		//ist so als wäre PowerUp in boardstate an dieser Position.
		PowerUp powerUp = (PowerUp)boardstate.getGeometries().get(boardstate.getGeometries().size()-1);
		List<Geometry>alle = boardstate.getGeometries();
		
		switch(powerUp.getType())
		{
			
			
			case TYP1:				
				// Führe PowerUp-Funktion vom Typ 1 für "player" aus.
				if(timer.startTimer(5000))
				{	
					
					
						for (Geometry g : alle) 
						{
							
							//wenn ball dann:
							//ball.controlling player, damit der ausgeschlossen wird
							if(g instanceof Ball)
							{	
								
								Ball tmp = (Ball)g;
								Player player = tmp.getControllingPlayer();
								for(Geometry geo : alle)
								{
									if(geo instanceof Bar)
									{
										Bar btmp = (Bar)geo;
										if(!(btmp.getControllingPlayer() == player))
										{
											
										}
									}
								
							}
							
						}
					}
					
					
					
					
					
				}
			break;
			
			case TYP2:
				// Führe PowerUp-Funktion vom Typ 2 für "player" aus.
				if(timer.startTimer(3000))
				{
					for (Geometry g : alle) 
					{
						if(g instanceof Ball)
						{	
							
							Ball tmp = (Ball)g;
							Player player = tmp.getControllingPlayer();
							for(Geometry geo : alle)
							{
								if(geo instanceof Bar)
								{
									Bar btmp = (Bar)geo;
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
		
		
		boardstate.getGeometries().remove(boardstate.getGeometries().size()-1);
		isPowerUpActive = false;
	}
	
	public void showPowerUp(BoardState boardstate)
	{
		PowerUp zufallPowerUp = PowerUps[zufall(0,1)];
		zufallPowerUp.setPosition(new Point2D(zufall(1,5),zufall(1,5)));
		zufallPowerUp.setPowerUpVisible(true);
		boardstate.getGeometries().add(PowerUps[zufall(0,1)]);
	}
	
	public PowerUp spawnPowerUp()
	{
		return PowerUps[zufall(0,1)];
	}
	
	private int zufall(int min, int max)
	{
		return (int) (Math.random()*(max-min+1)+min);
	}	
	
}
