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

	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	public int getPlayerID()
	{
		return playerID;
	}

}
