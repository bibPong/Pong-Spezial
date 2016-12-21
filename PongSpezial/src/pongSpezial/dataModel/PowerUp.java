package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public class PowerUp extends StaticGeometry
{
	private PowerUpType type;
	private boolean powerUpVisible;
	
	
	public PowerUp(Point2D POSITION, PowerUpType type)
	{
		this.POSITION = POSITION;
		this.type = type;
	}


	public PowerUpType getType() 
	{
		return type;
	}

	public void setType(PowerUpType type) 
	{
		this.type = type;
	}

	public boolean isPowerUpVisible() 
	{
		return powerUpVisible;
	}

	public void setPowerUpVisible(boolean powerUpVisible) 
	{
		this.powerUpVisible = powerUpVisible;
	}	
}
