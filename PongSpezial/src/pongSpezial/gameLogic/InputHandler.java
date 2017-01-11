package pongSpezial.gameLogic;

public class InputHandler
{

	private int direction;
	private int playerID;

	public InputHandler(int playerID)
	{
		this.direction = 0;
		this.playerID = playerID;
	}

	public int getDirection()
	{
		return direction;
	}

	public void setDirection(String direction)
	{
			switch (direction) 
			{			
				case "LEFT":
					if(playerID == 1)
					{
						this.direction=-1;
					}					
					else if (playerID == 3)
					{
						this.direction=1;
					}
					break;
				case "RIGHT":
					if(playerID == 1)
					{
						this.direction=1;
					}					
					else if (playerID == 3)
					{
						this.direction=-1;
					}
					break;
				case "UP":
					if(playerID == 2)
					{
						this.direction=1;
					}					
					else if (playerID == 4)
					{
						this.direction=-1;
					}
		
					break;
				case "DOWN":
					if(playerID == 2)
					{
						this.direction=-1;
					}					
					else if (playerID == 4)
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

}
