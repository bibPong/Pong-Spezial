package pongSpezial.dataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BoardState implements Serializable
{

	private static List<Geometry>geometries;
	public static BoardState instance = new BoardState();
	
	public Edge sp1fill;
	public Edge sp1goal;
	public Edge sp2fill; 
	public Edge sp2goal;
	public Edge sp3fill; 
	public Edge sp3goal;
	public Edge sp4fill; 
	public Edge sp4goal;
	
	public Edge co1;
	public Edge co11;
	public Edge co2;
	public Edge co21;
	public Edge co3;
	public Edge co31;
	public Edge co4;
	public Edge co41;
	
	public Bar bar1;
	public Bar bar2;
	public Bar bar3;
	public Bar bar4;
	
	public Ball ball;
	
	private BoardState()
	{
		geometries = new ArrayList<Geometry>();
	}
	
	
	public List<Geometry> getGeometries() {
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

