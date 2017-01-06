package pongSpezial.dataModel;

import java.util.List;

public class BoardState
{
	
	private BoardState()
	{}
	
	private static List<Geometry>Geometries;
	
	public static List<Geometry> getGeometries() {
		return Geometries;
	}

	public void setGeometries(List<Geometry> geometries) {
		Geometries = geometries;
	}

	/*public static BoardState getBoardstate() {
		return boardstate;
	}*/

}

