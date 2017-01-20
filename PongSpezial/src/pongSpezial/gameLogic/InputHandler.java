package pongSpezial.gameLogic;

import java.io.Serializable;

public class InputHandler implements Serializable
{

	private int direction;
	private int playerID;
	private int inverter;

	public InputHandler()
	{
		this.direction = 0;
		this.playerID = 0;
	}

	public int getDirection()
	{
		return direction;
	}

	public void setDirection(String direction)
	{
		// es wird immer davon augegangen, dass die jeweilige Bar sich unten befindet
			switch (direction) 
			{			
				case "LEFT":
					if(playerID == 3)
					{
						this.direction=1;
					}					
					else if (playerID == 4)
					{
						this.direction=-1;
					}
					break;
					
				case "RIGHT":
					if(playerID == 3)
					{
						this.direction=-1;
					}					
					else if (playerID == 4)
					{
						this.direction=1;
					}
					break;
					
				case "UP":
					if(playerID == 2)
					{
						this.direction=1;
					}					
					else if (playerID == 1)
					{
						this.direction=-1;
					}
		
					break;
					
				case "DOWN":
					if(playerID == 2)
					{
						this.direction=-1;
					}					
					else if (playerID == 1)
					{
						this.direction=1;
					}
					break;
					
				case "KEY_RELEASED" :
					this.direction=0;
					break;
					
				default:
					break;
			}
		
	}

	public int getPlayerID()
	{
		return playerID;
	}
	
	public void setPlayerID(int playerID)
	{
		this.playerID = playerID;
	}

}
