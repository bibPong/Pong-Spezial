package pongSpezial.gameLogic;

import javafx.geometry.Point2D;
import pongSpezial.dataModel.Ball;
import pongSpezial.dataModel.Bar;
import pongSpezial.dataModel.Edge;
import pongSpezial.dataModel.Player;

public class GameManager implements Runnable
{
	
	private boolean running;
	private Player[] players;
	private KI[] kis;
	
	public GameManager()
	{
		running=true;
		
	}
	
	@Override
	public void run()
	{
		
		try
		{
			while(running)
			{
				
				//System.out.println("Hallo");
				Thread.sleep(200);
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
		System.exit(0);
		System.out.println("GameManager has been shutdown");
		
	}
	
	public void updateBoardState()
	{
		for (int i = 0; i < kis.length; i++) 
		{
			//kis[i].moveAIBar();
		}
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
			//Da die Bar sich nicht weiter in die jeweilige Richtung bewegen soll, wenn sie mit einer CornerEdge kollidiert, 
			//wird die Velocity der Bar auf 0 gesetzt. Damit die Bar allerdings nicht an der CornerEdge "kleben bleibt", 
			//muss geprüft werden, ob sich die Bar in Richtung der Edge bewegt. Ist die Bewegungsrichtung umgekehrt, muss die Bar sich "weg" bewegen können.
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
			//Trifft der Ball auf eine CornerEdge, wird zunächst die ausrichtung der Edge abgefragt, damit die korrekte 
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
	

}
