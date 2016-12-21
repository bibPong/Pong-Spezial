package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public abstract class DynamicGeometry extends Geometry
{
	private Point2D direction;
	private double velocity;
	private Player ControllingPlayer;
	private boolean collideCurrently;
	
	
	public Point2D getDirection() 
	{
		return direction;
	}
	
	public void setDirection(Point2D direction) 
	{
		this.direction = direction;
	}
	
	public double getVelocity() 
	{
		return velocity;
	}
	
	public void setVelocity(double velocity) 
	{
		this.velocity = velocity;
	}
	
	public Player getControllingPlayer() 
	{
		return ControllingPlayer;
	}
	
	public void setControllingPlayer(Player controllingPlayer) 
	{
		ControllingPlayer = controllingPlayer;
	}
	
	public boolean isCollideCurrently() 
	{
		return collideCurrently;
	}
	
	public void setCollideCurrently(boolean collideCurrently) 
	{
		this.collideCurrently = collideCurrently;
	}
	
	
}
