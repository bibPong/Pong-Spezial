package pongSpezial.gameLogic;

import pongSpezial.dataModel.BoardState;
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
	}
	
	
	//Die Parameter sind als im Klassendiagramm, da es so sinnvoller erschien
	public void startPowerUpEvent(BoardState boardstate)
	{
		isPowerUpActive = true;
		
		//ist so als wäre PowerUp in boardstate an dieser Position.
		PowerUp powerUp = (PowerUp)boardstate.getGeometries().get(8);
		
		
		switch(powerUp.getType())
		{
			case TYP1:				
				// Führe PowerUp-Funktion vom Typ 1 für "player" aus.
				if(timer.startTimer(5000))
				{
					// Hier wird dann BoardState geändert und zurückgegeben
				}
			break;
			
			case TYP2:
				// Führe PowerUp-Funktion vom Typ 2 für "player" aus.
				if(timer.startTimer(3000))
				{
					// Hier wird dann BoardState geändert und zurückgegeben
				}
			break;
		}
		
		
		isPowerUpActive = false;
	}
	
	public void showPowerUp()
	{
		
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
