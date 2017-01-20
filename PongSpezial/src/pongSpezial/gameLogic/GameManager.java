package pongSpezial.gameLogic;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import pongSpezial.dataModel.Ball;
import pongSpezial.dataModel.Bar;
import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.Edge;
import pongSpezial.dataModel.EdgeOrientation;
import pongSpezial.dataModel.EdgeType;
import pongSpezial.dataModel.Geometry;
import pongSpezial.dataModel.InputState;
import pongSpezial.dataModel.Player;
import pongSpezial.dataModel.PowerUp;
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
	private PowerUpManager powerUpManager;
	private double boardsizeSafe;
	public Server server;
	
	public GameManager()
	{
		inputState = new InputState();
		stopWatch = new StopWatch();
		
	}
	
	public void init(Player[] player, double boardsize)
	{
		players = player;
		
		double sizeBarBig = 100; 		//Size and Position from long site of Corner/Edge 
		double sizeBarSmall = 20; 		//Size or Position from short site of Corner/Edge/Bar/Goal
		double sizeGoal = 400; 			//Size from long site PLAYERGOALEDGE
		double playerBarSize = 120;		//Size from long site Bar
		double ballRadius = 11;			//Ball radius
		
		boardsize = boardsize * 9000 + 1000;
		boardsizeSafe = boardsize;
		
		List<Geometry> teststates = new ArrayList<Geometry>();
		
		Ball ball = new Ball(new Point2D(boardsize/2,boardsize/2),new Point2D(ballRadius*2,ballRadius*2),new Point2D(0,0),0.0,null,false,ballRadius);
		
		//2 players
		if(player[2]== null)
		{
			//PLAYERGOALEDGE
			Edge sp1 = new Edge(new Point2D(0,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp1b = new Edge(new Point2D(-sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp2 = new Edge(new Point2D(boardsize-sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp2b = new Edge(new Point2D(boardsize+sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp3 = new Edge(new Point2D(sizeBarBig,0),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERFILL,true, EdgeOrientation.VERTICAL);
			Edge sp3b = new Edge(new Point2D(sizeBarBig,0),new Point2D(sizeGoal,-sizeBarSmall), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp4 = new Edge(new Point2D(sizeBarBig,boardsize-sizeBarSmall),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERFILL,true, EdgeOrientation.VERTICAL);
			Edge sp4b = new Edge(new Point2D(sizeBarBig,boardsize+sizeBarSmall),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			
			//Playerbar
			Bar bar1 = new Bar(new Point2D(0,(boardsize/sizeBarSmall-playerBarSize/2)) , new Point2D(sizeBarSmall,playerBarSize), new Point2D(0,0), 0, player[0],
					false);
			Bar bar2 = new Bar(new Point2D(boardsize-sizeBarSmall,(boardsize/sizeBarSmall-playerBarSize/2)) , new Point2D(sizeBarSmall,playerBarSize), new Point2D(0,0), 0, player[1],
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
			Edge sp1 = new Edge(new Point2D(0,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL );
			Edge sp1b = new Edge(new Point2D(-sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp2 = new Edge(new Point2D(boardsize-sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp2b = new Edge(new Point2D(boardsize+sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp3 = new Edge(new Point2D(sizeBarBig,0),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp3b = new Edge(new Point2D(sizeBarBig,0),new Point2D(sizeGoal,-sizeBarSmall), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp4 = new Edge(new Point2D(sizeBarBig,boardsize-sizeBarSmall),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERFILL,true, EdgeOrientation.VERTICAL);
			Edge sp4b = new Edge(new Point2D(sizeBarBig,boardsize+sizeBarSmall),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			
			//Playerbar
			Bar bar1 = new Bar(new Point2D(0,(boardsize/sizeBarSmall-playerBarSize/2)) , new Point2D(sizeBarSmall,playerBarSize), new Point2D(0,0), 0, player[0],
					false);
			Bar bar2 = new Bar(new Point2D(boardsize-sizeBarSmall,(boardsize/sizeBarSmall-playerBarSize/2)) , new Point2D(sizeBarSmall,playerBarSize), new Point2D(0,0), 0, player[1],
					false);
			Bar bar3 = new Bar(new Point2D((boardsize/sizeBarSmall-playerBarSize/2),0) , new Point2D(playerBarSize,sizeBarSmall), new Point2D(0,0), 0, player[2],
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
			Edge sp1 = new Edge(new Point2D(0,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp1b = new Edge(new Point2D(-sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp2 = new Edge(new Point2D(boardsize-sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp2b = new Edge(new Point2D(boardsize+sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp3 = new Edge(new Point2D(sizeBarBig,0),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp3b = new Edge(new Point2D(sizeBarBig,0),new Point2D(sizeGoal,-sizeBarSmall), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp4 = new Edge(new Point2D(sizeBarBig,boardsize-sizeBarSmall),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp4b = new Edge(new Point2D(sizeBarBig,boardsize+sizeBarSmall),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			
			//Playerbar
			Bar bar1 = new Bar(new Point2D(0,(boardsize/sizeBarSmall-playerBarSize/2)) , new Point2D(sizeBarSmall,playerBarSize), new Point2D(0,0), 0, player[0],
					false);
			Bar bar2 = new Bar(new Point2D(boardsize-sizeBarSmall,(boardsize/sizeBarSmall-playerBarSize/2)) , new Point2D(sizeBarSmall,playerBarSize), new Point2D(0,0), 0, player[1],
					false);
			Bar bar3 = new Bar(new Point2D((boardsize/sizeBarSmall-playerBarSize/2),0) , new Point2D(playerBarSize,sizeBarSmall), new Point2D(0,0), 0, player[2],
					false);
			Bar bar4 = new Bar(new Point2D((boardsize/sizeBarSmall-playerBarSize/2),boardsize-sizeBarSmall) , new Point2D(playerBarSize,sizeBarSmall), new Point2D(0,0), 0, player[3],
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
		Edge co1 = new Edge(new Point2D(0,0),new Point2D(sizeBarSmall,sizeBarBig), EdgeType.CORNEREDGE,true, EdgeOrientation.VERTICAL);
		Edge co11 = new Edge(new Point2D(0,boardsize - sizeBarBig),new Point2D(sizeBarSmall,sizeBarBig), EdgeType.CORNEREDGE,true, EdgeOrientation.HORIZONTAL);
		Edge co2 = new Edge(new Point2D(boardsize - sizeBarSmall,0),new Point2D(sizeBarSmall,sizeBarBig), EdgeType.CORNEREDGE,true, EdgeOrientation.VERTICAL);
		Edge co21 = new Edge(new Point2D(boardsize - sizeBarSmall,boardsize - sizeBarBig),new Point2D(sizeBarSmall,100), EdgeType.CORNEREDGE,true, EdgeOrientation.HORIZONTAL);
		Edge co3 = new Edge(new Point2D(0,0),new Point2D(sizeBarBig,sizeBarSmall), EdgeType.CORNEREDGE,true, EdgeOrientation.VERTICAL);
		Edge co31 = new Edge(new Point2D(0,boardsize - sizeBarBig),new Point2D(sizeBarBig,sizeBarSmall), EdgeType.CORNEREDGE,true, EdgeOrientation.HORIZONTAL);
		Edge co4 = new Edge(new Point2D(boardsize - sizeBarBig,boardsize - sizeBarBig),new Point2D(sizeBarBig,sizeBarSmall), EdgeType.CORNEREDGE,true, EdgeOrientation.VERTICAL);
		Edge co41 = new Edge(new Point2D(boardsize - sizeBarBig,boardsize - sizeBarBig),new Point2D(sizeBarBig,sizeBarSmall), EdgeType.CORNEREDGE,true, EdgeOrientation.HORIZONTAL);

		teststates.add(ball);
		teststates.add(co1);
		teststates.add(co2);
		teststates.add(co3);
		teststates.add(co4);
		teststates.add(co11);
		teststates.add(co21);
		teststates.add(co31);
		teststates.add(co41);
		
		
		powerUpManager = new PowerUpManager(players);
		PowerUp powerUp = powerUpManager.spawnPowerUp();
		
		teststates.add(powerUp);
		boardstate.setGeometries(teststates);
	}
	
	public static BoardState testBoardState(Player[] player, double boardsize)
	{
		double sizeBarBig    = 100; 						//Size and Position from long site of CornerEdge 
		double sizeBarSmall  = 20; 							//Size or Position from short site of CornerEdge/Bar/Goal
		double playerBarSize = 120;							//Size from long site Bar
		double sizeGoal      = boardsize - 2 * sizeBarBig; 	//Size from long site PLAYERGOALEDGE
		double ballRadius    = 11;							//Ball radius
		
		List<Geometry> teststates = new ArrayList<Geometry>();
		
		Ball ball = new Ball( new Point2D(boardsize/2 - ballRadius/2, boardsize/2 - ballRadius/2),
							  new Point2D(ballRadius*2,ballRadius*2),
							  new Point2D(0,0),
							  0.0,
							  null,
							  false,
							  ballRadius);
		
		//2 players
		if(player[2]== null)
		{
			//PLAYERGOALEDGE
			Edge sp1  = new Edge( 	new Point2D(0,sizeBarBig),
									new Point2D(sizeBarSmall,sizeGoal),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
					
			Edge sp1b = new Edge( 	new Point2D(-sizeBarSmall,sizeBarBig),
									new Point2D(sizeBarSmall,sizeGoal),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
								
			Edge sp2  = new Edge( 	new Point2D(boardsize-sizeBarSmall,sizeBarBig),
									new Point2D(sizeBarSmall,sizeGoal),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
								
			Edge sp2b = new Edge( 	new Point2D(boardsize+sizeBarSmall,sizeBarBig),
									new Point2D(sizeBarSmall,sizeGoal),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
								
			Edge sp3  = new Edge( 	new Point2D(sizeBarBig,0),
									new Point2D(sizeGoal,sizeBarSmall),
									EdgeType.PLAYERFILL,
									true,
									EdgeOrientation.VERTICAL);
								
			Edge sp3b = new Edge( 	new Point2D(sizeBarBig,0),
									new Point2D(sizeGoal,-sizeBarSmall),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
								
			Edge sp4  = new Edge( 	new Point2D(sizeBarBig,boardsize-sizeBarSmall),
									new Point2D(sizeGoal,sizeBarSmall),
									EdgeType.PLAYERFILL,
									true,
									EdgeOrientation.VERTICAL);
								
			Edge sp4b = new Edge( 	new Point2D(sizeBarBig,boardsize+sizeBarSmall),
									new Point2D(sizeGoal,sizeBarSmall),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);

			//Playerbar
			Bar bar1 = 	new Bar(	new Point2D(0,(boardsize/sizeBarSmall - playerBarSize/2)),
									new Point2D(sizeBarSmall,playerBarSize),
									new Point2D(0,0),
									0,
									player[0],
									false);
			
			Bar bar2 = 	new Bar(	new Point2D(boardsize-sizeBarSmall,(boardsize/sizeBarSmall-playerBarSize/2)),
									new Point2D(sizeBarSmall,playerBarSize),
									new Point2D(0,0),
									0,
									player[1],
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
			Edge sp1 = new Edge(new Point2D(0,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL );
			Edge sp1b = new Edge(new Point2D(-sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp2 = new Edge(new Point2D(boardsize-sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp2b = new Edge(new Point2D(boardsize+sizeBarSmall,sizeBarBig),new Point2D(sizeBarSmall,sizeGoal), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp3 = new Edge(new Point2D(sizeBarBig,0),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp3b = new Edge(new Point2D(sizeBarBig,0),new Point2D(sizeGoal,-sizeBarSmall), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp4 = new Edge(new Point2D(sizeBarBig,boardsize-sizeBarSmall),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERFILL,true, EdgeOrientation.VERTICAL);
			Edge sp4b = new Edge(new Point2D(sizeBarBig,boardsize+sizeBarSmall),new Point2D(sizeGoal,sizeBarSmall), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			
			//Playerbar
			Bar bar1 = new Bar(new Point2D(0,(boardsize/sizeBarSmall-playerBarSize/2)) , new Point2D(sizeBarSmall,playerBarSize), new Point2D(0,0), 0, player[0],
					false);
			Bar bar2 = new Bar(new Point2D(boardsize-sizeBarSmall,(boardsize/sizeBarSmall-playerBarSize/2)) , new Point2D(sizeBarSmall,playerBarSize), new Point2D(0,0), 0, player[1],
					false);
			Bar bar3 = new Bar(new Point2D((boardsize/sizeBarSmall-playerBarSize/2),0) , new Point2D(playerBarSize,sizeBarSmall), new Point2D(0,0), 0, player[2],
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
			Edge sp1  = new Edge( 	new Point2D(0, sizeBarBig),
									new Point2D(sizeBarSmall, sizeGoal),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
			
			Edge sp1b = new Edge( 	new Point2D(-sizeBarSmall, sizeBarBig),
									new Point2D( sizeBarSmall, sizeGoal),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
			
			Edge sp2  = new Edge( 	new Point2D(boardsize-sizeBarSmall, sizeBarBig),
									new Point2D(sizeBarSmall, sizeGoal),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
			
			Edge sp2b = new Edge( 	new Point2D(boardsize, sizeBarBig),
									new Point2D(sizeBarSmall, sizeGoal),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
			
			Edge sp3  = new Edge( 	new Point2D(sizeBarBig, 0),
									new Point2D(sizeGoal, sizeBarSmall),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
			
			Edge sp3b = new Edge( 	new Point2D(sizeBarBig, -sizeBarSmall),
									new Point2D(sizeGoal,    sizeBarSmall),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
			
			Edge sp4  = new Edge( 	new Point2D(sizeBarBig,boardsize-sizeBarSmall),
									new Point2D(sizeGoal,sizeBarSmall),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
			
			Edge sp4b = new Edge( 	new Point2D(sizeBarBig, boardsize),
									new Point2D(sizeGoal,sizeBarSmall),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
			
			//Playerbar
			Bar bar1 = 	new Bar( new Point2D(0, (boardsize/2 - playerBarSize/2)),
								 new Point2D(sizeBarSmall, playerBarSize),
								 new Point2D(0,0),
								 0,
								 player[0],
							 	 false);
			
			Bar bar2 = 	new Bar( new Point2D(0, (boardsize/2 - playerBarSize/2)),
								 new Point2D(sizeBarSmall,playerBarSize),
								 new Point2D(0,0),
								 0,
								 player[1],
								 false);
			
			Bar bar3 = 	new Bar( new Point2D((boardsize/2 - playerBarSize/2), 0),
								 new Point2D(playerBarSize, sizeBarSmall),
								 new Point2D(0,0),
								 0,
								 player[2],
							 	 false);
			
			Bar bar4 = 	new Bar( new Point2D((boardsize/2 - playerBarSize/2), 0),
								 new Point2D(playerBarSize, sizeBarSmall),
								 new Point2D(0,0),
								 0,
								 player[3],
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
		Edge co1  = new Edge(	new Point2D(0,0),
								new Point2D(sizeBarSmall, sizeBarBig),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.VERTICAL);
		
		Edge co11 = new Edge(	new Point2D(0, boardsize - sizeBarBig),
								new Point2D(sizeBarSmall, sizeBarBig),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.VERTICAL);
		
		Edge co2  = new Edge(	new Point2D(0, 0),
								new Point2D(sizeBarBig, sizeBarSmall),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.HORIZONTAL);
		
		Edge co21 = new Edge(	new Point2D(boardsize - sizeBarBig, 0),
								new Point2D(sizeBarBig, sizeBarSmall),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.HORIZONTAL);
		
		Edge co3  = new Edge(	new Point2D(boardsize - sizeBarSmall, 0),
								new Point2D(sizeBarSmall, sizeBarBig),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.VERTICAL);
		
		Edge co31 = new Edge(	new Point2D(boardsize - sizeBarSmall, boardsize - sizeBarBig),
								new Point2D(sizeBarSmall, sizeBarBig),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.VERTICAL);
		
		Edge co4  = new Edge(	new Point2D(0, boardsize),
								new Point2D(sizeBarBig, sizeBarSmall),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.HORIZONTAL);
		
		Edge co41 = new Edge(	new Point2D(boardsize - sizeBarBig, boardsize),
								new Point2D(sizeBarBig, sizeBarSmall),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.HORIZONTAL);

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
		
		return boardstate;
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
						double velocityxdirection=playerbar.getVelocity()*direction;
						Point2D newPosition=playerbar.getPosition().add(velocityxdirection,0);
						BoardState.instance.getGeometries().get(index).setPosition(newPosition);
					}
					else
					{
						Bar playerbar = ((Bar)BoardState.instance.getGeometries().get(index));
						double velocityxdirection=playerbar.getVelocity()*direction;
						Point2D newPosition=playerbar.getPosition().add(0,velocityxdirection);
						BoardState.instance.getGeometries().get(index).setPosition(newPosition);
					}
					
				}	
				
				
				// Bewegung/Update des Balls 
				for(Geometry g : boardstate.getGeometries()) // Ball wird aus dem Boardstate gesucht
				{
					if(g instanceof Ball) // Pr�fung, ob es sich um ein Ball-Objekt handelt
					{
						// Das eigentliche bewegen
						Ball ball = (Ball) g;
										
						Point2D unit = unitVector(ball.getDirection());
						
						ball.setPosition(ball.getPosition().add(unit.multiply(ball.getVelocity())));
					}
				}
				
				
				Thread.sleep(200);
				
				
				//Warten aus vollst�ndige Implementation des Servers
				sendBoardStateToServer();
				
				
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
	public void sendBoardStateToServer()
	{
		
		
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
	
	
	//public void calculateCollission()
		//{
			//Dieser Methode muss mitgeteilt werden, welche Objekte an der Kollision beteiligt sind.
			//Je nachdem, welche Objekte beteiligt sind, wird die entsprechende Kollisions-Methode aufgerufen,
			//wie z.B. cornerEdgeBarCollision(Bar bar) oder cornerEdgeBallCollision(Ball ball).
			//Der jeweiligen Kollisions-Methode wird das Objekt (oder die Objekte), auf das Einfluss genommen wird, �bergeben.
		//}
	
	private void cornerEdgeBarCollision(Bar bar, Edge cornerEdge)
	{
		//Da die Bar sich nicht weiter in die jeweilige Richtung bewegen soll, wenn sie mit einer CornerEdge kollidiert, 
		//wird die Velocity der Bar auf 0 gesetzt. Damit die Bar allerdings nicht an der CornerEdge "kleben bleibt", 
		//muss gepr�ft werden, ob sich die Bar in Richtung der Edge bewegt. Ist die Bewegungsrichtung umgekehrt, muss die Bar sich "weg" bewegen k�nnen.
		if(cornerEdge.getOrientation().equals("HORIZONTAL"))
		{
			if(cornerEdge.getPosition().getY() > bar.getPosition().getY())
			{
				if(bar.getDirection().getY() > 0) bar.setVelocity(0);		
			}
			else
			{
				if(bar.getDirection().getY() < 0) bar.setVelocity(0);
			}
		}
		else
		{
			if(cornerEdge.getPosition().getX() > bar.getPosition().getX())
			{
				if(bar.getDirection().getX() > 0) bar.setVelocity(0);		
			}
			else
			{
				if(bar.getDirection().getX() < 0) bar.setVelocity(0);
			}
		}
	}
	
	private void cornerEdgeBallCollision(Ball ball, Edge cornerEdge)
	{
		//Trifft der Ball auf eine CornerEdge, wird zun�chst die ausrichtung der Edge abgefragt, damit die korrekte 
		//Invertierung der Ball-Bewegungsrichtung angewand wird.
		if(cornerEdge.getOrientation().equals("HORIZONTAL"))
		{
			ball.setDirection(new Point2D(
					ball.getDirection().getX(),
					ball.getDirection().getY()-1
					));
		}
		else
		{
			ball.setDirection(new Point2D(
					ball.getDirection().getX()-1,
					ball.getDirection().getY()
					));
		}
	}
	
	private void ballPowerUpCollision()
	{
		powerUpManager.startPowerUpEvent(boardstate);
	}
	
	
	private void initBall(double boardsize)
	{	
		// Zahlen �bernommen als festwerte f�r radius und collisionsize wie sie in Boardstate sind
		Ball ball = new Ball(new Point2D(boardsize/2,boardsize/2),new Point2D(22,22),new Point2D(0,0),0.0,null,false,11);
		
		if(stopWatch.startTimer(5000))
		{	
			//Platzhalter noch nicht richtige Zufallswerte
			double x = zufall(-1,1);
			double y = zufall(-1,1);
			ball.setDirection(new Point2D(x,y));
			ball.setVelocity(1);
		}
	}
	
	private void playerBarBallCollision(Ball ball, Bar bar)
	{
		if(bar.getControllingPlayer().getPlayerID() == 1 || bar.getControllingPlayer().getPlayerID() == 2 )
		{
		double x = ball.getDirection().getX();
		double y = ball.getDirection().getY()*(-1);	
		ball.setDirection(new Point2D(y,x));
		}
		else
		{
		double x = ball.getDirection().getX()*(-1);
		double y = ball.getDirection().getY();	
		ball.setDirection(new Point2D(y,x));
		}
	}
	
	private void ballPlayerEdgeCollision(Ball ball, Edge playerEdge, double boardsize)
	{
		
		initBall(boardsize);	
		
		Player player = new Player(0, null);
		
		for(Geometry geo : boardstate.getGeometries())
		{
			if(geo instanceof Edge)
			{
				if(((Edge) geo).getType() == EdgeType.PLAYERGOALEDGE)
				{
					if(((Edge) geo).getOrientation() == EdgeOrientation.HORIZONTAL)
					{
						if(geo.getPosition().getX() < 10)
						{
							player = players[0];
						}
						else
						{
							player = players[1];
						}
					}
					else
					{
						if(geo.getPosition().getY() < 10)
						{
							player = players[2];
						}
						else 
						{
							player = players[3];
						}
					}
		
				}
			}
		}
		
		player.setLifes(player.getLifes()-1);
		
		if(player.getLifes() == 0)
		{
			if(playerEdge.getOrientation().equals("HORIZONTAL")) //wie weit verschieben
			{
				if(playerEdge.getPosition().getX() > 10)
				{
					Edge edge = (Edge)boardstate.getGeometries().get(0);
					edge.setEdgeVisible(true);
				}
				else
				{
					Edge edge = (Edge)boardstate.getGeometries().get(2);
					edge.setEdgeVisible(true);
				}
			}
			
			if(playerEdge.getOrientation().equals("VERTICAL"))
					{
						if(playerEdge.getPosition().getY() > 10)
						{
							Edge edge = (Edge)boardstate.getGeometries().get(4);
							edge.setEdgeVisible(true);
						}
						else
						{
							Edge edge = (Edge)boardstate.getGeometries().get(6);
							edge.setEdgeVisible(true);
						}
					}
			
		}
	
	}
		
	public void isCollided()
	{
		List<Bar> playerBars = new ArrayList<Bar>();
		Ball ball=null;
		for(int i = 0; i < boardstate.getGeometries().size();i++)
		{
			if(boardstate.getGeometries().get(i).equals(Ball.class))
			{
				ball = (Ball)boardstate.getGeometries().get(i); // get the Ball
			}
			else if(boardstate.getGeometries().get(i).equals(Bar.class))
			{
				playerBars.add((Bar)boardstate.getGeometries().get(i)); //get the player Bars
			}
		}
		
		Rectangle2D rectBall = new Rectangle2D(ball.getPosition().getX(), ball.getPosition().getY(), ball.getCollisionSize().getX(), ball.getCollisionSize().getY());
		for(Geometry g : boardstate.getGeometries())
		{
			if(g instanceof Bar) //if the object is a Bar
			{
				Bar bar = (Bar) g;
				Rectangle2D rectBar = new Rectangle2D(bar.getPosition().getX(), bar.getPosition().getY(), bar.getCollisionSize().getX(), bar.getCollisionSize().getY());	
				
				if(rectBall.intersects(rectBar)) // if ball and bar collide
				{
					playerBarBallCollision(ball, bar);
				}			
			}
			else if(g instanceof Edge) //if the object is a Edge
			{
				Edge edge = (Edge) g;
				Rectangle2D rectEdge = new Rectangle2D(edge.getPosition().getX(), edge.getPosition().getY(), edge.getCollisionSize().getX(), edge.getCollisionSize().getY());
				if(edge.getType() == EdgeType.CORNEREDGE) // if the edge is a Corneredge
				{
					if(rectBall.intersects(rectEdge)) //if ball and edge
					{
						cornerEdgeBallCollision(ball, edge);
					}
					for (int i = 0; i < playerBars.size(); i++)// going through the playerBars List
					{
						Rectangle2D rectBar = new Rectangle2D(playerBars.get(i).getPosition().getX(), playerBars.get(i).getPosition().getY(), playerBars.get(i).getCollisionSize().getX(), playerBars.get(i).getCollisionSize().getY());
						if(rectBar.intersects(rectBar)) // if edge and bar collide
						{
							cornerEdgeBarCollision(playerBars.get(i), edge);
						}
					}
				}
				else if(edge.getType() == EdgeType.PLAYERFILL) // id the edge is a Playerfill edge
				{
					if(edge.isEdgeVisible()) // if edge is visible
					{
						cornerEdgeBallCollision(ball, edge); 
					}
				}
				
				else if(edge.getType() == EdgeType.PLAYERGOALEDGE)
				{
					ballPlayerEdgeCollision(ball, edge,boardsizeSafe); 
				}
				
			}
			else if(g instanceof PowerUp) //if object is a PowerUp
			{
				PowerUp powerUp= (PowerUp) g;
				Rectangle2D rectPowerUp = new Rectangle2D(powerUp.getPosition().getX(), powerUp.getPosition().getY(), powerUp.getCollisionSize().getX(), powerUp.getCollisionSize().getY());
				
				if(powerUp.isPowerUpVisible()) //if PowerUp is visible
				{
					if(rectBall.intersects(rectPowerUp)) //if ball and PowerUp collide
					{
						ballPowerUpCollision();
					}
				}
					
			}
			
		}

	}
	
	private int zufall(int min, int max)
	{
		return (int) (Math.random()*(max-min+1)+min);
	}	
	
	private Point2D unitVector(Point2D direction)
	{
		// Berechnung des Einheitsvektors
		double vectorLength = Math.sqrt( (direction.getX()*direction.getX()) + (direction.getY()*direction.getY()) );
		double x = direction.getX() * (1/vectorLength);
		double y = direction.getY() * (1/vectorLength); 
		return new Point2D(x,y);
	}
}


