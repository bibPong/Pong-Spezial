package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public class Bar extends DynamicGeometry
{
	private double width;
	
	public Bar(Point2D position, Point2D collisionSize, Point2D direction, double velocity, Player controllingPlayer,
			boolean collideCurrently, double width) {
		super(position, collisionSize, direction, velocity, controllingPlayer, collideCurrently);
		this.width = width;
	
	}

	public void setWidth(double width) 
	{
		this.width = width;
	}

	@Override
	public String toString()
	{
		return "Bar position" + super.getPosition();
	}	
	
	
}
