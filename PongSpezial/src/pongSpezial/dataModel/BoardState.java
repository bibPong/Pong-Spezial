package pongSpezial.dataModel;

import java.util.ArrayList;
import java.util.List;

public class BoardState
{

	private static List<Geometry>geometries;
	public static BoardState instance = new BoardState();
	
	private BoardState()
	{
		geometries = new ArrayList<Geometry>();
		
	}
	
	
	public static List<Geometry> getGeometries() {
		return geometries;
	}

	public void setGeometries(List<Geometry> geometries) {
		this.geometries = geometries;
	}


	@Override
	public String toString()
	{
		return "BoardState:"+geometries;
	}

	/*public static BoardState getBoardstate() {
		return boardstate;
	}*/

}

