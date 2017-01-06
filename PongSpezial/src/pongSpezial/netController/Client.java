package pongSpezial.netController;

import pongSpezial.dataModel.BoardState;

public class Client implements Runnable
{
	
	private boolean running;
	private BoardState boardState;
	private NetworkAddress serverIP;
	
	
	public Client(BoardState boardState, NetworkAddress serverIP)
	{
		this.running = true;
		this.boardState = boardState;
		this.serverIP = serverIP;
	}
	
	public Client()
	{
		this.running = true;
	}

	@Override
	public void run()
	{
		try
		{
			while (running)
			{
				validateInput();
				System.out.println("Input");
				Thread.sleep(10);
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		
		updateGUI();
	}

	public void stop()
	{
		this.running = false;
	}
	
	public void validateInput()
	{
		
	}
	
	public void updateGUI()
	{
		// In BoardState geändert
		//boardState  = BoardState.getBoardstate();
		System.out.println(boardState.getGeometries());
	}
	
	
	
}
