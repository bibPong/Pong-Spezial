package pongSpezial.gameLogic;

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
	
	

}
