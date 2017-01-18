package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public class Bar extends DynamicGeometry
{
	private double width;
	public Player controllingPlayer;
	
	public Bar(Point2D position, Point2D collisionSize, Point2D direction, double velocity, Player controllingPlayer,
			boolean collideCurrently) {
		super(position, collisionSize, direction, velocity, controllingPlayer, collideCurrently);
		this.width = width;
	
	}

	public void setWidth(double width) 
	{
		this.width = width;
	}
	
	public double getWidth() {
		return width;
	}

	@Override
	public String toString()
	{
		return "Bar position" + super.getPosition();
	}	
	
	
}
