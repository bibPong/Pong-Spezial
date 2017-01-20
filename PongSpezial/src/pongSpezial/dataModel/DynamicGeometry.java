package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public abstract class DynamicGeometry extends Geometry
{
	private Point2D direction;
	private double velocity;
	public Player controllingPlayer;
	private boolean collideCurrently;
	
	public DynamicGeometry(Point2D position, Point2D collisionSize,Point2D direction, double velocity, Player controllingPlayer,boolean collideCurrently) {
		super(position, collisionSize);
		this.direction = direction;
		this.velocity = velocity;
		this.controllingPlayer = controllingPlayer;
		this.collideCurrently = collideCurrently;
	}
	
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
		return controllingPlayer;
	}
	
	public void setControllingPlayer(Player controllingPlayer) 
	{
		controllingPlayer = controllingPlayer;
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
