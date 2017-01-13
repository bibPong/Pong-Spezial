package pongSpezial.gameLogic;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javafx.geometry.Point2D;
import pongSpezial.dataModel.Ball;
import pongSpezial.dataModel.Bar;
import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.Edge;
import pongSpezial.dataModel.EdgeType;
import pongSpezial.dataModel.Geometry;
import pongSpezial.dataModel.InputState;
import pongSpezial.dataModel.Player;
import pongSpezial.dataModel.StopWatch;
import pongSpezial.netController.Server;
import pongSpezial.dataModel.Player;

public class GameManager implements Runnable
{
	
	private boolean running;
	private Player[] players;
	private KI[] kis;
	//static BoardState boardstate;
	private static BoardState boardstate=BoardState.instance;
	private InputState inputState;
	private StopWatch stopWatch;
	public Server server;
	
	public GameManager()
	{
		inputState = new InputState();
		stopWatch = new StopWatch();
	}
	
	public void init(Player[] player, double boardsize)
	{
		List<Geometry> teststates = new ArrayList<Geometry>();
		
		Ball ball = new Ball(new Point2D(boardsize/2,boardsize/2),new Point2D(2,2),new Point2D(0,0),0.0,null,false,11);
		
		//2 players
		if(player[2]== null)
		{
			//PLAYERGOALEDGE
			Edge sp1 = new Edge(new Point2D(0,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,true);
			Edge sp1b = new Edge(new Point2D(-20,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,false);
			Edge sp2 = new Edge(new Point2D(boardsize-20,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,true);
			Edge sp2b = new Edge(new Point2D(boardsize+20,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,false);
			Edge sp3 = new Edge(new Point2D(100,0),new Point2D(400,20), EdgeType.PLAYERGOALEDGE,false);
			Edge sp3b = new Edge(new Point2D(100,0),new Point2D(400,-20), EdgeType.PLAYERGOALEDGE,false);
			Edge sp4 = new Edge(new Point2D(100,boardsize-20),new Point2D(400,20), EdgeType.PLAYERGOALEDGE,false);
			Edge sp4b = new Edge(new Point2D(100,boardsize+20),new Point2D(400,20), EdgeType.PLAYERGOALEDGE,false);
			
			//Playerbar
			Bar bar1 = new Bar(new Point2D(0,(boardsize/20-60)) , new Point2D(20,120), new Point2D(0,0), 0, player[0],
					false);
			Bar bar2 = new Bar(new Point2D(boardsize-20,(boardsize/20-60)) , new Point2D(20,120), new Point2D(0,0), 0, player[1],
					false);
			
			teststates.add(sp1);
			teststates.add(sp1b);
			teststates.add(sp2);
			teststates.add(sp2b);
			teststates.add(sp3);
			teststates.add(sp3b);
			teststates.add(sp4);
			teststates.add(sp4b);
			teststates.add(bar1);
			teststates.add(bar2);
		}
		
		//3 players
		else if(player[3]== null)
		{
			//PLAYERGOALEDGE
			Edge sp1 = new Edge(new Point2D(0,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,true);
			Edge sp1b = new Edge(new Point2D(-20,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,false);
			Edge sp2 = new Edge(new Point2D(boardsize-20,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,true);
			Edge sp2b = new Edge(new Point2D(boardsize+20,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,false);
			Edge sp3 = new Edge(new Point2D(100,0),new Point2D(400,20), EdgeType.PLAYERGOALEDGE,true);
			Edge sp3b = new Edge(new Point2D(100,0),new Point2D(400,-20), EdgeType.PLAYERGOALEDGE,false);
			Edge sp4 = new Edge(new Point2D(100,boardsize-20),new Point2D(400,20), EdgeType.PLAYERGOALEDGE,false);
			Edge sp4b = new Edge(new Point2D(100,boardsize+20),new Point2D(400,20), EdgeType.PLAYERGOALEDGE,false);
			
			//Playerbar
			Bar bar1 = new Bar(new Point2D(0,(boardsize/20-60)) , new Point2D(20,120), new Point2D(0,0), 0, player[0],
					false);
			Bar bar2 = new Bar(new Point2D(boardsize-20,(boardsize/20-60)) , new Point2D(20,120), new Point2D(0,0), 0, player[1],
					false);
			Bar bar3 = new Bar(new Point2D((boardsize/20-60),0) , new Point2D(120,20), new Point2D(0,0), 0, player[2],
					false);
			
			teststates.add(sp1);
			teststates.add(sp1b);
			teststates.add(sp2);
			teststates.add(sp2b);
			teststates.add(sp3);
			teststates.add(sp3b);
			teststates.add(sp4);
			teststates.add(sp4b);
			teststates.add(bar1);
			teststates.add(bar2);
			teststates.add(bar3);

		}
		
		//4 players
		else
		{
			//PLAYERGOALEDGE
			Edge sp1 = new Edge(new Point2D(0,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,true);
			Edge sp1b = new Edge(new Point2D(-20,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,false);
			Edge sp2 = new Edge(new Point2D(boardsize-20,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,true);
			Edge sp2b = new Edge(new Point2D(boardsize+20,100),new Point2D(20,400), EdgeType.PLAYERGOALEDGE,false);
			Edge sp3 = new Edge(new Point2D(100,0),new Point2D(400,20), EdgeType.PLAYERGOALEDGE,true);
			Edge sp3b = new Edge(new Point2D(100,0),new Point2D(400,-20), EdgeType.PLAYERGOALEDGE,false);
			Edge sp4 = new Edge(new Point2D(100,boardsize-20),new Point2D(400,20), EdgeType.PLAYERGOALEDGE,true);
			Edge sp4b = new Edge(new Point2D(100,boardsize+20),new Point2D(400,20), EdgeType.PLAYERGOALEDGE,false);
			
			//Playerbar
			Bar bar1 = new Bar(new Point2D(0,(boardsize/20-60)) , new Point2D(20,120), new Point2D(0,0), 0, player[0],
					false);
			Bar bar2 = new Bar(new Point2D(boardsize-20,(boardsize/20-60)) , new Point2D(20,120), new Point2D(0,0), 0, player[1],
					false);
			Bar bar3 = new Bar(new Point2D((boardsize/20-60),0) , new Point2D(120,20), new Point2D(0,0), 0, player[2],
					false);
			Bar bar4 = new Bar(new Point2D((boardsize/20-60),boardsize-20) , new Point2D(120,20), new Point2D(0,0), 0, player[3],
					false);
			
			teststates.add(sp1);
			teststates.add(sp1b);
			teststates.add(sp2);
			teststates.add(sp2b);
			teststates.add(sp3);
			teststates.add(sp3b);
			teststates.add(sp4);
			teststates.add(sp4b);
			teststates.add(bar1);
			teststates.add(bar2);
			teststates.add(bar3);
			teststates.add(bar4);
		}

		//Corneredges
		Edge co1 = new Edge(new Point2D(0,0),new Point2D(20,100), EdgeType.CORNEREDGE,false);
		Edge co11 = new Edge(new Point2D(0,boardsize - 100),new Point2D(20,100), EdgeType.CORNEREDGE,false);
		Edge co2 = new Edge(new Point2D(boardsize - 20,0),new Point2D(20,100), EdgeType.CORNEREDGE,false);
		Edge co21 = new Edge(new Point2D(boardsize - 20,boardsize - 100),new Point2D(20,100), EdgeType.CORNEREDGE,false);
		Edge co3 = new Edge(new Point2D(0,0),new Point2D(100,20), EdgeType.CORNEREDGE,false);
		Edge co31 = new Edge(new Point2D(0,boardsize - 100),new Point2D(100,20), EdgeType.CORNEREDGE,false);
		Edge co4 = new Edge(new Point2D(boardsize - 100,boardsize - 100),new Point2D(100,20), EdgeType.CORNEREDGE,false);
		Edge co41 = new Edge(new Point2D(boardsize - 100,boardsize - 100),new Point2D(100,20), EdgeType.CORNEREDGE,false);

		teststates.add(ball);
		teststates.add(co1);
		teststates.add(co2);
		teststates.add(co3);
		teststates.add(co4);
		teststates.add(co11);
		teststates.add(co21);
		teststates.add(co31);
		teststates.add(co41);
		
		boardstate.setGeometries(teststates);
	}
	
	@Override
	public void run()
	{
		
		
		try
		{
			while(running)
			{
				Player player = new Player(0, "Testplayer");
				
				inputState.getCurrentInputs().put(player, 1);
				
				for(Player  p : inputState.getCurrentInputs().keySet())
				{
					int direction=inputState.getCurrentInputs().get(p);
					int index = BoardState.instance.getGeometries().indexOf(p);
					if(p.getPlayerID()==0||p.getPlayerID()==2)
					{
						Bar playerbar = ((Bar)BoardState.instance.getGeometries().get(index));
						double velocityxdirection=playerbar.getVelocity()*inputState.getCurrentInputs().get(p);
						Point2D newPosition=playerbar.getPosition().add(velocityxdirection,0);
						BoardState.instance.getGeometries().get(index).setPosition(newPosition);
					}
					else
					{
						Bar playerbar = ((Bar)BoardState.instance.getGeometries().get(index));
						double velocityxdirection=playerbar.getVelocity()*inputState.getCurrentInputs().get(p);
						Point2D newPosition=playerbar.getPosition().add(0,velocityxdirection);
						BoardState.instance.getGeometries().get(index).setPosition(newPosition);
					}
					
				}	
				Thread.sleep(200);
				
				
				//System.out.println(BoardState.instance.getGeometries().get(5));
				
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
	
	public void updateBoardState()
	{
		for (int i = 0; i < kis.length; i++) 
		{
			//kis[i].moveAIBar();
		}
	}
	
	public static Bar getBarForPlayer(Player player)
	{
		for(int i = 0; i < boardstate.getGeometries().size();i++)
		{
			if(boardstate.getGeometries().get(i).equals(Bar.class))
			{
				Bar tmp = (Bar)boardstate.getGeometries().get(i);
				if(tmp.controllingPlayer.equals(player))
				{
					return tmp;
				}
			}
		}
		return null;
	}
	

}

