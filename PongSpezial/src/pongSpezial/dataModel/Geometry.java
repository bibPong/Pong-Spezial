package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public abstract class Geometry 
{
	private Point2D position;
	private Point2D collisionSize;

	public Geometry(Point2D position, Point2D collisionSize)
	{
		this.position=position;
		this.collisionSize=collisionSize;
	}
	
	public Point2D getPosition() 
	{
		return position;
	}
	
	public void setPosition(Point2D position) 
	{
		this.position = position;
	}
	
	public Point2D getCollisionSize() 
	{
		return collisionSize;
	}
	
	public void setCollisionSize(Point2D collisionSize) 
	{
		this.collisionSize = collisionSize;
	}		
}
