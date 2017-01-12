package pongSpezial.gameLogic;

import java.util.Vector;

import pongSpezial.dataModel.Bar;
import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.Player;

public class GameManager implements Runnable
{
	
	private boolean running;
	private Player[] players;
	private KI[] kis;
	static BoardState boardstate;
	
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
