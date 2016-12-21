package pongSpezial.dataModel;

public class Ball extends DynamicGeometry
{
	private double radius;

	
	public Ball(double radius)
	{
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
