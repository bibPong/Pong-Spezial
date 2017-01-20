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
import pongSpezial.netController.Client;
import pongSpezial.netController.Server;
import pongSpezial.dataModel.Player;

public class GameManager implements Runnable
{
	private static final int THREAD_SLEEP_TIME_MS = 1;
	private static final double INITIAL_BALL_SPD = THREAD_SLEEP_TIME_MS * 0.1;
	private static final double KI_SPEED = THREAD_SLEEP_TIME_MS * 0.1;
	private static final double PL_SPEED = THREAD_SLEEP_TIME_MS * 0.15;
	
	
	private boolean running;
	private static Player[] players;
	public static Player[] kis;
	//static BoardState boardstate;
	private static BoardState boardState=BoardState.instance;
	private InputState inputState;
	private StopWatch stopWatch;
	private PowerUpManager powerUpManager;
	private static double boardsize;
	public Server server;
	
	
	//PROTOTYPE ONLY
	public static int pl0Input = 0;
	private Geometry ball_lastCollidedGeom = null;
	
	
	final static double SIZE_BAR_BIG    = 100;		//Size and Position from long site of CornerEdge 
	final static double SIZE_BAR_SMALL  = 8;		//Size or Position from short site of CornerEdge/Bar/Goal
	final static double SIZE_PLAYER_BAR = 120;		//Size from long site Bar
	      static double size_goal;					//Size from long site PLAYERGOALEDGE
	final static double RADIUS_BALL     = 4;		//Ball radius
	
	public GameManager()
	{
		inputState = new InputState();
		stopWatch = new StopWatch();
		
	}
	
	public static BoardState testBoardState(Player[] player, double _boardsize)
	{
		boardsize = _boardsize;
		size_goal = _boardsize - 2 * SIZE_BAR_BIG; 	
		
		players = player;
		
		List<Geometry> teststates = new ArrayList<Geometry>();
		
		Ball ball = new Ball( new Point2D(boardsize/2 - RADIUS_BALL/2, boardsize/2 - RADIUS_BALL/2),
							  new Point2D(RADIUS_BALL*2,RADIUS_BALL*2),
							  new Point2D(0,0),
							  0.0,
							  null,
							  false,
							  RADIUS_BALL);
		
		boardState.ball = ball;
		
		//2 players
		if(player[2]== null)
		{
			//PLAYERGOALEDGE
			Edge sp1  = new Edge( 	new Point2D(0,SIZE_BAR_BIG),
									new Point2D(SIZE_BAR_SMALL,size_goal),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
					
			Edge sp1b = new Edge( 	new Point2D(-SIZE_BAR_SMALL,SIZE_BAR_BIG),
									new Point2D(SIZE_BAR_SMALL,size_goal),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
								
			Edge sp2  = new Edge( 	new Point2D(boardsize-SIZE_BAR_SMALL,SIZE_BAR_BIG),
									new Point2D(SIZE_BAR_SMALL,size_goal),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
								
			Edge sp2b = new Edge( 	new Point2D(boardsize+SIZE_BAR_SMALL,SIZE_BAR_BIG),
									new Point2D(SIZE_BAR_SMALL,size_goal),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
								
			Edge sp3  = new Edge( 	new Point2D(SIZE_BAR_BIG,0),
									new Point2D(size_goal,SIZE_BAR_SMALL),
									EdgeType.PLAYERFILL,
									true,
									EdgeOrientation.VERTICAL);
								
			Edge sp3b = new Edge( 	new Point2D(SIZE_BAR_BIG,0),
									new Point2D(size_goal,-SIZE_BAR_SMALL),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
								
			Edge sp4  = new Edge( 	new Point2D(SIZE_BAR_BIG,boardsize-SIZE_BAR_SMALL),
									new Point2D(size_goal,SIZE_BAR_SMALL),
									EdgeType.PLAYERFILL,
									true,
									EdgeOrientation.VERTICAL);
								
			Edge sp4b = new Edge( 	new Point2D(SIZE_BAR_BIG,boardsize+SIZE_BAR_SMALL),
									new Point2D(size_goal,SIZE_BAR_SMALL),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);

			//Playerbar
			Bar bar1 = 	new Bar(	new Point2D(0,(boardsize/SIZE_BAR_SMALL - SIZE_PLAYER_BAR/2)),
									new Point2D(SIZE_BAR_SMALL,SIZE_PLAYER_BAR),
									new Point2D(0,0),
									0,
									player[0],
									false);
			
			Bar bar2 = 	new Bar(	new Point2D(boardsize-SIZE_BAR_SMALL,(boardsize/SIZE_BAR_SMALL-SIZE_PLAYER_BAR/2)),
									new Point2D(SIZE_BAR_SMALL,SIZE_PLAYER_BAR),
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
			Edge sp1 = new Edge(new Point2D(0,SIZE_BAR_BIG),new Point2D(SIZE_BAR_SMALL,size_goal), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL );
			Edge sp1b = new Edge(new Point2D(-SIZE_BAR_SMALL,SIZE_BAR_BIG),new Point2D(SIZE_BAR_SMALL,size_goal), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp2 = new Edge(new Point2D(boardsize-SIZE_BAR_SMALL,SIZE_BAR_BIG),new Point2D(SIZE_BAR_SMALL,size_goal), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp2b = new Edge(new Point2D(boardsize+SIZE_BAR_SMALL,SIZE_BAR_BIG),new Point2D(SIZE_BAR_SMALL,size_goal), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp3 = new Edge(new Point2D(SIZE_BAR_BIG,0),new Point2D(size_goal,SIZE_BAR_SMALL), EdgeType.PLAYERFILL,false, EdgeOrientation.VERTICAL);
			Edge sp3b = new Edge(new Point2D(SIZE_BAR_BIG,0),new Point2D(size_goal,-SIZE_BAR_SMALL), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			Edge sp4 = new Edge(new Point2D(SIZE_BAR_BIG,boardsize-SIZE_BAR_SMALL),new Point2D(size_goal,SIZE_BAR_SMALL), EdgeType.PLAYERFILL,true, EdgeOrientation.VERTICAL);
			Edge sp4b = new Edge(new Point2D(SIZE_BAR_BIG,boardsize+SIZE_BAR_SMALL),new Point2D(size_goal,SIZE_BAR_SMALL), EdgeType.PLAYERGOALEDGE,true, EdgeOrientation.HORIZONTAL);
			
			//Playerbar
			Bar bar1 = new Bar(new Point2D(0,(boardsize/SIZE_BAR_SMALL-SIZE_PLAYER_BAR/2)) , new Point2D(SIZE_BAR_SMALL,SIZE_PLAYER_BAR), new Point2D(0,0), 0, player[0],
					false);
			Bar bar2 = new Bar(new Point2D(boardsize-SIZE_BAR_SMALL,(boardsize/SIZE_BAR_SMALL-SIZE_PLAYER_BAR/2)) , new Point2D(SIZE_BAR_SMALL,SIZE_PLAYER_BAR), new Point2D(0,0), 0, player[1],
					false);
			Bar bar3 = new Bar(new Point2D((boardsize/SIZE_BAR_SMALL-SIZE_PLAYER_BAR/2),0) , new Point2D(SIZE_PLAYER_BAR,SIZE_BAR_SMALL), new Point2D(0,0), 0, player[2],
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
			Edge sp1  = new Edge( 	new Point2D(0, SIZE_BAR_BIG),
									new Point2D(SIZE_BAR_SMALL, size_goal),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
			
			Edge sp1b = new Edge( 	new Point2D(-SIZE_BAR_SMALL, SIZE_BAR_BIG),
									new Point2D( SIZE_BAR_SMALL, size_goal),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
			
			Edge sp2  = new Edge( 	new Point2D(boardsize-SIZE_BAR_SMALL, SIZE_BAR_BIG),
									new Point2D(SIZE_BAR_SMALL, size_goal),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
			
			Edge sp2b = new Edge( 	new Point2D(boardsize, SIZE_BAR_BIG),
									new Point2D(SIZE_BAR_SMALL, size_goal),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
			
			Edge sp3  = new Edge( 	new Point2D(SIZE_BAR_BIG, 0),
									new Point2D(size_goal, SIZE_BAR_SMALL),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
			
			Edge sp3b = new Edge( 	new Point2D(SIZE_BAR_BIG, -SIZE_BAR_SMALL),
									new Point2D(size_goal,    SIZE_BAR_SMALL),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
			
			Edge sp4  = new Edge( 	new Point2D(SIZE_BAR_BIG,boardsize-SIZE_BAR_SMALL),
									new Point2D(size_goal,SIZE_BAR_SMALL),
									EdgeType.PLAYERFILL,
									false,
									EdgeOrientation.VERTICAL);
			
			Edge sp4b = new Edge( 	new Point2D(SIZE_BAR_BIG, boardsize),
									new Point2D(size_goal,SIZE_BAR_SMALL),
									EdgeType.PLAYERGOALEDGE,
									true,
									EdgeOrientation.HORIZONTAL);
			
			//Playerbar
			Bar bar1 = 	new Bar( new Point2D(0, (boardsize/2 - SIZE_PLAYER_BAR/2)),
								 new Point2D(SIZE_BAR_SMALL, SIZE_PLAYER_BAR),
								 new Point2D(0,0),
								 0,
								 player[0],
							 	 false);
			
			Bar bar2 = 	new Bar( new Point2D(boardsize - SIZE_BAR_SMALL, (boardsize/2 - SIZE_PLAYER_BAR/2)),
								 new Point2D( SIZE_BAR_SMALL, SIZE_PLAYER_BAR),
								 new Point2D(0,0),
								 0,
								 player[1],
								 false);
			
			Bar bar3 = 	new Bar( new Point2D((boardsize/2 - SIZE_PLAYER_BAR/2), 0),
								 new Point2D(SIZE_PLAYER_BAR, SIZE_BAR_SMALL),
								 new Point2D(0,0),
								 0,
								 player[2],
							 	 false);
			
			Bar bar4 = 	new Bar( new Point2D((boardsize/2 - SIZE_PLAYER_BAR/2), boardsize - SIZE_BAR_SMALL),
								 new Point2D(SIZE_PLAYER_BAR, SIZE_BAR_SMALL),
								 new Point2D(0,0),
								 0,
								 player[3],
							 	 false);
			
			boardState.bar1 = bar1;
			boardState.bar2 = bar2;
			boardState.bar3 = bar3;
			boardState.bar4 = bar4;
			
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
			
			boardState.sp1fill = sp1;
			boardState.sp1goal = sp1b;
			boardState.sp2fill = sp2;
			boardState.sp2goal = sp2b;
			boardState.sp3fill = sp3;
			boardState.sp3goal = sp3b;
			boardState.sp4fill = sp4;
			boardState.sp4goal = sp4b;
		}

		//Corneredges
		Edge co1  = new Edge(	new Point2D(0,0),
								new Point2D(SIZE_BAR_SMALL, SIZE_BAR_BIG),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.VERTICAL);
		
		Edge co11 = new Edge(	new Point2D(0, boardsize - SIZE_BAR_BIG),
								new Point2D(SIZE_BAR_SMALL, SIZE_BAR_BIG),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.VERTICAL);
		
		Edge co2  = new Edge(	new Point2D(0, 0),
								new Point2D(SIZE_BAR_BIG, SIZE_BAR_SMALL),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.HORIZONTAL);
		
		Edge co21 = new Edge(	new Point2D(boardsize - SIZE_BAR_BIG, 0),
								new Point2D(SIZE_BAR_BIG, SIZE_BAR_SMALL),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.HORIZONTAL);
		
		Edge co3  = new Edge(	new Point2D(boardsize - SIZE_BAR_SMALL, 0),
								new Point2D(SIZE_BAR_SMALL, SIZE_BAR_BIG),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.VERTICAL);
		
		Edge co31 = new Edge(	new Point2D(boardsize - SIZE_BAR_SMALL, boardsize - SIZE_BAR_BIG),
								new Point2D(SIZE_BAR_SMALL, SIZE_BAR_BIG),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.VERTICAL);
		
		Edge co4  = new Edge(	new Point2D(0, boardsize - SIZE_BAR_SMALL),
								new Point2D(SIZE_BAR_BIG, SIZE_BAR_SMALL),
								EdgeType.CORNEREDGE,
								true,
								EdgeOrientation.HORIZONTAL);
		
		Edge co41 = new Edge(	new Point2D(boardsize - SIZE_BAR_BIG, boardsize - SIZE_BAR_SMALL),
								new Point2D(SIZE_BAR_BIG, SIZE_BAR_SMALL),
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
		
		boardState.co1  = co1; 
		boardState.co2  = co2; 
		boardState.co3  = co3; 
		boardState.co4  = co4; 
		boardState.co11 = co11;
		boardState.co21 = co21;
		boardState.co31 = co31;
		boardState.co41 = co41;

		boardState.setGeometries(teststates);	
		
		return boardState;
	}
	
	
	@Override
	public void run()
	{
		running = true;
		
		initBall();
		
		try
		{
			while(running)
			{

				/*
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
				*/
				updateBoardState();
				
				Thread.sleep(THREAD_SLEEP_TIME_MS);
				
				
				//Warten aus vollständige Implementation des Servers
				sendBoardStateToServer();
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
	
	private void moveBar(Bar b, double deltaX, double deltaY)
	{
		double newXPos = b.getPosition().getX() + deltaX;
		double newYPos = b.getPosition().getY() + deltaY;
		
		if( 	newXPos < boardsize - SIZE_BAR_BIG - SIZE_PLAYER_BAR
			&& 	newXPos > SIZE_BAR_BIG)
		{
			b.setPosition(new Point2D(newXPos, b.getPosition().getY()));
		}
		
		if( 		newYPos < boardsize - SIZE_BAR_BIG - SIZE_PLAYER_BAR
				&& 	newYPos > SIZE_BAR_BIG)
		{
			b.setPosition(new Point2D(b.getPosition().getX(), newYPos));
		}
	}
	
	
	
	public void updateBoardState()
	{
		//Update Player(s)
		Bar plBar = GameManager.getBarForPlayer(players[0]);
		moveBar(plBar, 0, pl0Input * PL_SPEED);
		
		//update AIs
		for (int i = 0; i < kis.length; i++) 
		{
			Bar bar = GameManager.getBarForPlayer(kis[i]);
			
			if(kis[i].getPlayerID() == 1 || kis[i].getPlayerID() == 2) 					
			{
				if(bar.getPosition().getY() + bar.getWidth()/2 > boardState.ball.getPosition().getY())		
				{
					moveBar(bar, 0, -KI_SPEED);			
				}
				else if(bar.getPosition().getY() + bar.getWidth()/2 < boardState.ball.getPosition().getY())
				{
					moveBar(bar, 0, KI_SPEED);					
				}
			}
			else
			{ 
				if(bar.getPosition().getX() + bar.getWidth()/2 > boardState.ball.getPosition().getX()) 	
				{
					moveBar(bar, -KI_SPEED, 0);						
				}
				else if(bar.getPosition().getX() + bar.getWidth()/2 < boardState.ball.getPosition().getX())
				{
					moveBar(bar, KI_SPEED, 0);				
				}
			}
		}
		
		//udpate ball

		Ball ball = boardState.ball;
						
		Point2D unit = unitVector(ball.getDirection());
		
		System.out.println(unit);
		 
		ball.setPosition(ball.getPosition().add(unit.multiply(ball.getVelocity())));
		
		System.out.println("Moved ball to (" + ball.getPosition().getX() + ", " + ball.getPosition().getY() + ")");
		
		//Check whether ball is out-of-bounds
		
		Rectangle2D ballRect = new Rectangle2D( ball.getPosition().getX() - ball.getRadius(), ball.getPosition().getY() - ball.getRadius(),
												ball.getCollisionSize().getX(),               ball.getCollisionSize().getY());
		
		Rectangle2D boardRect = new Rectangle2D(0, 0, boardsize, boardsize);

		if(!ballRect.intersects(boardRect))
		{
			initBall();
		}
		
		//Check for collisions
		isCollided();
	}
	
	public static Bar getBarForPlayer(Player player)
	{
		for(int i = 0; i < boardState.getGeometries().size();i++)
		{
			if( boardState.getGeometries().get(i) instanceof Bar )
			{
				Bar tmp = (Bar)boardState.getGeometries().get(i);
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
			//Der jeweiligen Kollisions-Methode wird das Objekt (oder die Objekte), auf das Einfluss genommen wird, übergeben.
		//}
	
	private void cornerEdgeBarCollision(Bar bar, Edge cornerEdge)
	{
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
		//Don't collide with the same object twice in a row
		if(cornerEdge == ball_lastCollidedGeom)
		{
			return;
		}
		
		ball_lastCollidedGeom = cornerEdge;
		
		//Trifft der Ball auf eine CornerEdge, wird zunächst die Ausrichtung der Edge abgefragt, damit die korrekte 
		//Invertierung der Ball-Bewegungsrichtung angewandt wird.
		
		if(cornerEdge.getOrientation() == EdgeOrientation.HORIZONTAL)
		{
			ball.setDirection(new Point2D(
								ball.getDirection().getX(),
								ball.getDirection().getY() * (-1)
							));
		}
		else if(cornerEdge.getOrientation() == EdgeOrientation.VERTICAL)
		{
			ball.setDirection(new Point2D(
								ball.getDirection().getX() * (-1),
								ball.getDirection().getY()
							));
		}
		
		ball.setVelocity(ball.getVelocity() * 1.1);
	}
	
	private void ballPowerUpCollision()
	{
		powerUpManager.startPowerUpEvent(boardState);
	}
	
	
	private void initBall()
	{	
		
		System.out.println("Initiating Ball");
		
			
		boardState.ball.setPosition(new Point2D(boardsize/2, boardsize/2));
		
		if(stopWatch.startTimer(5000))
		{	
			double x = random(-1,1);
			double y = random(-1,1);
			boardState.ball.setDirection(unitVector(new Point2D(x,y)));
			boardState.ball.setVelocity(INITIAL_BALL_SPD);
		}
		
	}
	
	private void playerBarBallCollision(Ball ball, Bar bar)
	{
		//Don't collide with the same object twice in a row
		if(bar == ball_lastCollidedGeom)
		{
			return;
		}
		
		ball_lastCollidedGeom = bar;
		
		if(bar.getControllingPlayer().getPlayerID() <= 2)
		{
			//Vertical Collision - Flip horizontal axis
			
			double x = ball.getDirection().getX() * (-1);
			double y = ball.getDirection().getY();	
			ball.setDirection(new Point2D(x, y));
		}
		else
		{
			//Horizontal Collision - Flip vertical axis
			
			double x = ball.getDirection().getX();
			double y = ball.getDirection().getY() * (-1);	
			ball.setDirection(new Point2D(x, y));
		}
		
		ball.setVelocity(ball.getVelocity() * 1.2);
	}
	
	/***
	 * Obsolete
	private void ballPlayerEdgeCollision(Ball ball, Edge playerEdge)
	{
		
		initBall();	
		
		Player player = new Player(0, null);
		
		if(playerEdge.getOrientation() == EdgeOrientation.HORIZONTAL)
		{
			if(playerEdge.getPosition().getX() < 10)
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
			if(playerEdge.getPosition().getY() < 10)
			{
				player = players[2];
			}
			else 
			{
				player = players[3];
			}
		}
		
		player.setLifes(player.getLifes()-1);
		
		if(player.getLifes() == 0)
		{
			if(playerEdge.getOrientation().equals("HORIZONTAL")) //wie weit verschieben
			{
				if(playerEdge.getPosition().getX() > 10)
				{
					Edge edge = (Edge)boardState.getGeometries().get(0);
					edge.setEdgeVisible(true);
				}
				else
				{
					Edge edge = (Edge)boardState.getGeometries().get(2);
					edge.setEdgeVisible(true);
				}
			}
			
			if(playerEdge.getOrientation().equals("VERTICAL"))
					{
						if(playerEdge.getPosition().getY() > 10)
						{
							Edge edge = (Edge)boardState.getGeometries().get(4);
							edge.setEdgeVisible(true);
						}
						else
						{
							Edge edge = (Edge)boardState.getGeometries().get(6);
							edge.setEdgeVisible(true);
						}
					}
			
		}
	
	}
	*/	
	
	public void isCollided()
	{
		Ball b = boardState.ball;
		
		Rectangle2D ballRect = new Rectangle2D( b.getPosition().getX() - b.getRadius(), b.getPosition().getY() - b.getRadius(),
				  							    b.getCollisionSize().getX(),            b.getCollisionSize().getY());
		
		for(Geometry otherGeom : boardState.getGeometries())
		{
			Rectangle2D otherRect = new Rectangle2D( otherGeom.getPosition()     .getX(), otherGeom.getPosition()     .getY(),
													 otherGeom.getCollisionSize().getX(), otherGeom.getCollisionSize().getY());
			
			if(ballRect.intersects(otherRect))
			{
				//Categorize the collision
				
				// Ball Bar Collision
				if(otherGeom instanceof Bar)
				{
					playerBarBallCollision(b, (Bar)otherGeom);
				}
				
				//Ball PowerUp Collision
				else if( otherGeom instanceof PowerUp )
				{
					ballPowerUpCollision();
				}
				
				//Ball PlayerGoalEdge Collision - Obsolete
//				else if(otherGeom instanceof Edge && ((Edge)otherGeom).getType() == EdgeType.PLAYERGOALEDGE)
//				{
//					ballPlayerEdgeCollision(b, (Edge)otherGeom);
//				}
				
				//Ball CornerEdge Collision
				else if(otherGeom instanceof Edge && ((Edge)otherGeom).getType() == EdgeType.CORNEREDGE)
				{
					cornerEdgeBallCollision(b, (Edge)otherGeom);
				}
				/*
				//CornerEdge Bar Collision
				else if(   (g1 instanceof Bar && g2 instanceof Edge && ((Edge)g2).getType() == EdgeType.CORNEREDGE)
						|| (g2 instanceof Bar && g1 instanceof Edge && ((Edge)g1).getType() == EdgeType.CORNEREDGE) )
				{
					cornerEdgeBarCollision( (g1 instanceof Bar) ? (Bar) g1 : (Bar) g2,
											(g1 instanceof Bar) ? (Edge)g2 : (Edge)g1);
				}
				*/
			}
			
		}

	}
	
	private double random(int min, int max)
	{
		return Math.random()*(max-min+1)+min;
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


