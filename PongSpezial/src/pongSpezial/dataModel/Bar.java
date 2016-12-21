package pongSpezial.dataModel;

public class Bar extends DynamicGeometry
{
	private double width;

	
	public Bar(double width)
	{
		this.width = width;
	}
	
	
	public double getWidth() 
	{
		return width;
	}

	public void setWidth(double width) 
	{
		this.width = width;
	}	
}
