package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public class Edge extends StaticGeometry
{
	private EdgeType type;
	private boolean edgeVisible;
	
	public Edge(Point2D position, Point2D collisionSize, String designation, EdgeType type, boolean edgeVisible) {
		super(position, collisionSize);
		this.type = type;
		this.edgeVisible=edgeVisible;
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
