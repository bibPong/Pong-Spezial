package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public class PowerUp extends StaticGeometry
{
	private PowerUpType type;
	private boolean powerUpVisible;
	
	public PowerUp(Point2D position, Point2D collisionSize,PowerUpType type) {
		super(position, collisionSize);
		this.type = type;
		// TODO Auto-generated constructor stub
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
