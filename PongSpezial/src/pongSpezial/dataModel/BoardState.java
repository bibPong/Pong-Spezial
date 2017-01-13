package pongSpezial.dataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoardState implements Serializable
{

	private static List<Geometry>geometries;
	
	public BoardState()
	{
		geometries = new ArrayList<Geometry>();
		
	}
	
	
	public List<Geometry> getGeometries() {
		return geometries;
	}

	public void setGeometries(List<Geometry> geometries) {
		geometries = geometries;
	}

	/*public static BoardState getBoardstate() {
		return boardstate;
	}*/

}

