package pongSpezial.dataModel;

import javafx.geometry.Point2D;

public class Edge extends StaticGeometry
{
	private EdgeType type;
	private boolean edgeVisible;
	
	
	public Edge(Point2D POSITION, EdgeType type)
	{
		this.POSITION = POSITION;
		this.type = type;
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
