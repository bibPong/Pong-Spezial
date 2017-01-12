package pongSpezial.gameLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.javafx.geom.Edge;

import javafx.geometry.Point2D;
import pongSpezial.dataModel.Ball;
import pongSpezial.dataModel.Bar;
import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.EdgeType;
import pongSpezial.dataModel.Geometry;
import pongSpezial.dataModel.InputState;
import pongSpezial.dataModel.Player;

public class GameManager implements Runnable
{
	
	private boolean running;
	private BoardState boardstate=BoardState.instance;
	private InputState inputstate;
	
	public GameManager()
	{
		running=true;
		
	}
	
	@Override
	public void run()
	{
		
		TestDaten();
		try
		{
			while(running)
			{
				Player player = new Player();
				
				inputstate.getCurrentInputs().put(player, 1);
				
				for(Player  p : inputstate.getCurrentInputs().keySet())
				{
					int direction=inputstate.getCurrentInputs().get(p);
					int index = BoardState.instance.getGeometries().indexOf(p);
					if(p.getPlayerID()==0||p.getPlayerID()==2)
					{
						Bar playerbar = ((Bar)BoardState.instance.getGeometries().get(index));
						double velocityxdirection=playerbar.getVelocity()*inputstate.getCurrentInputs().get(p);
						Point2D newPosition=playerbar.getPosition().add(velocityxdirection,0);
						BoardState.instance.getGeometries().get(index).setPosition(newPosition);
					}
					else
					{
						Bar playerbar = ((Bar)BoardState.instance.getGeometries().get(index));
						double velocityxdirection=playerbar.getVelocity()*inputstate.getCurrentInputs().get(p);
						Point2D newPosition=playerbar.getPosition().add(0,velocityxdirection);
						BoardState.instance.getGeometries().get(index).setPosition(newPosition);
					}
					
				}	
				Thread.sleep(200);
				
				
				System.out.println(BoardState.instance.getGeometries().get(5));
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
	}

	public void shutdown()
	{
		this.running=false;
		System.out.println("GameManager has been shutdown");
		System.exit(0);
		
		
	}
	
	
	public void TestDaten()
	{
		Player player = new Player();
		// In BoardState geändert
		//boardState  = BoardState.getBoardstate();
		Ball ball = new Ball(new Point2D(0, 0), new Point2D(0, 0),new Point2D(0, 0) , 200,player , false, 50);
		pongSpezial.dataModel.Edge sp1 = new pongSpezial.dataModel.Edge(new Point2D(0, 0), new Point2D(0, 0), "TEST", EdgeType.PLAYERGOALEDGE, false);
		pongSpezial.dataModel.Edge sp2 = new pongSpezial.dataModel.Edge(new Point2D(0, 0), new Point2D(0, 0), "TEST", EdgeType.PLAYERGOALEDGE, false);
		pongSpezial.dataModel.Edge sp3 = new pongSpezial.dataModel.Edge(new Point2D(0, 0), new Point2D(0, 0), "TEST", EdgeType.PLAYERGOALEDGE, false);
		pongSpezial.dataModel.Edge sp4 = new pongSpezial.dataModel.Edge(new Point2D(0, 0), new Point2D(0, 0), "TEST", EdgeType.PLAYERGOALEDGE, false);
		pongSpezial.dataModel.Edge co1 = new pongSpezial.dataModel.Edge(new Point2D(0, 0), new Point2D(0, 0), "TEST", EdgeType.CORNEREDGE, false);
		pongSpezial.dataModel.Edge co2 = new pongSpezial.dataModel.Edge(new Point2D(0, 0), new Point2D(0, 0), "TEST", EdgeType.CORNEREDGE, false);
		pongSpezial.dataModel.Edge co3 = new pongSpezial.dataModel.Edge(new Point2D(0, 0), new Point2D(0, 0), "TEST", EdgeType.CORNEREDGE, false);
		pongSpezial.dataModel.Edge co4 = new pongSpezial.dataModel.Edge(new Point2D(0, 0), new Point2D(0, 0), "TEST", EdgeType.CORNEREDGE, false);
		Bar balk1 = new Bar(new Point2D(0, 3 ), new Point2D(0, 0),new Point2D(0, 0) , 200,player , false, 50);
		Bar balk2 = new Bar(new Point2D(0, 0), new Point2D(0, 0),new Point2D(0, 0) , 200,player , false, 50);
		Bar balk3 = new Bar(new Point2D(0, 0), new Point2D(0, 0),new Point2D(0, 0) , 200,player , false, 50);
		Bar balk4 = new Bar(new Point2D(0, 0), new Point2D(0, 0),new Point2D(0, 0) , 200,player , false, 50);
		
		
		
		List<Geometry> teststates = new ArrayList<Geometry>();
		teststates.add(ball);
		teststates.add(sp1);
		teststates.add(sp2);
		teststates.add(sp3);
		teststates.add(sp4);
		teststates.add(balk1);
		teststates.add(balk2);
		teststates.add(balk3);
		teststates.add(balk4);
		teststates.add(co1);
		teststates.add(co2);
		teststates.add(co3);
		teststates.add(co4);
		
		
		
		
		BoardState.instance.setGeometries(teststates);
		//System.out.println(BoardState.instance.getGeometries());
	}

}
