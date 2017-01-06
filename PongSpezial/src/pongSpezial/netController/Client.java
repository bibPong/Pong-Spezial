package pongSpezial.netController;

import pongSpezial.dataModel.BoardState;

public class Client implements Runnable
{

	private BoardState boardState;
	private NetworkAddress serverIP;
	
	public Client(BoardState boardState, NetworkAddress serverIP)
	{
		this.boardState = boardState;
		this.serverIP = serverIP;
	}

	@Override
	public void run()
	{
		try
		{
			validateInput();
			System.out.println("Input");
			Thread.sleep(10);
		} catch (Exception e)
		{
			System.out.println(e);
		}
		
		updateGUI();
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
