package pongSpezial.gameLogic;

import pongSpezial.dataModel.Player;
import pongSpezial.dataModel.PowerUp;
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
	public void startPowerUpEvent(PowerUp powerUp, Player player)
	{
		isPowerUpActive = true;
		
		switch(powerUp.getType())
		{
			case TYP1:				
				// Führe PowerUp-Funktion vom Typ 1 für "player" aus.
				if(timer.startTimer(5000))
				{
					// Beende die Funktion des PowerUps.
				}
			break;
			
			case TYP2:
				// Führe PowerUp-Funktion vom Typ 2 für "player" aus.
				if(timer.startTimer(3000))
				{
					// Beende die Funktion des PowerUps.
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
		return PowerUps[1];
	}
	
}
