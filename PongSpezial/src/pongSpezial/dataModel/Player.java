package pongSpezial.dataModel;

public class Player 
{	
	private int playerID;
	private String name;
	private int lifes;
	
	
	
	public void setPlayerID(int playerID)
	{
		this.playerID = playerID;
	}


	public Player(int playerID,String name)
	{
		this.playerID = playerID;
		this.name = name;
		lifes = 3;
	}

	public int getPlayerID() {
		return playerID;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

}
