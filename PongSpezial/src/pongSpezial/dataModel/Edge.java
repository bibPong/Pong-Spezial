package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public class Edge extends StaticGeometry
{
	private EdgeType type;
	private boolean edgeVisible;
	private EdgeOrientation orientation;
	
	public EdgeOrientation getOrientation() 
	{
		return orientation;
	}

	public void setOrientation(EdgeOrientation orientation) 
	{
		this.orientation = orientation;
	}

	public Edge(Point2D position, Point2D collisionSize, EdgeType type, boolean edgeVisible, EdgeOrientation orientation) 
	{
		super(position, collisionSize);
		this.type = type;
		this.edgeVisible=edgeVisible;
		this.orientation=orientation; 
		// TODO Auto-generated constructor stub
	}

	public EdgeType getType() 
	{
		return type;
	}

	public void setType(EdgeType type) 
	{
		this.type = type;
	}

	public boolean isEdgeVisible() 
	{
		return edgeVisible;
	}

	public void setEdgeVisible(boolean edgeVisible) 
	{
		this.edgeVisible = edgeVisible;
	}	
}
