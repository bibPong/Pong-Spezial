package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public class Ball extends DynamicGeometry
{
	private double radius;

	public Ball(Point2D position,
				Point2D collisionSize,
				Point2D direction,
				double velocity,
				Player controllingPlayer,
				boolean collideCurrently,
				double radius)
	{
		super(position, collisionSize, direction, velocity, controllingPlayer, collideCurrently);
		this.radius = radius;
		
	}

	



	
	
	public double getRadius() 
	{
		return radius;
	}

	public void setRadius(double radius) 
	{
		this.radius = radius;
	}
}
