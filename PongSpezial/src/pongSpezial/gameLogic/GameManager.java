package pongSpezial.gameLogic;

public class GameManager implements Runnable
{
	
	private boolean running;
	
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

}
