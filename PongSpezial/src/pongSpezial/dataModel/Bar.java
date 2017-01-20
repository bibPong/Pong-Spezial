package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public class Bar extends DynamicGeometry
{
	private double width;
	
	public Bar(Point2D position, Point2D collisionSize, Point2D direction, double velocity, Player controllingPlayer,
			boolean collideCurrently)
	{
		super(position, collisionSize, direction, velocity, controllingPlayer, collideCurrently);
		
		if(controllingPlayer.getPlayerID() == 1 || controllingPlayer.getPlayerID() == 2 )
			this.width = getCollisionSize().getY();
		
		else if(controllingPlayer.getPlayerID() == 3 || controllingPlayer.getPlayerID() == 4)
			this.width = getCollisionSize().getX();
	}

	public void setWidth(double width) 
	{
		int playerId = controllingPlayer.getPlayerID();
		if(playerId == 1 || playerId == 2)
		{
			setCollisionSize(new Point2D(getCollisionSize().getX(),width));
			this.width = width;
		}
		else if(playerId == 3 || playerId == 4)
		{
			setCollisionSize(new Point2D(width,getCollisionSize().getY()));
			this.width = width;
		}
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
