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
		Ball ball = new Ball();
		Edge sp1 = new Edge();
		Edge sp2 = new Edge();
		Edge sp3 = new Edge();
		Edge sp4 = new Edge();
		Bar balk1 = new Bar();
		Bar balk2 = new Bar();
		Bar balk3 = new Bar();
		Bar balk4 = new Bar();
		
		List<Geometry> teststates = new List<Geometry>();
		teststates.add(ball);
		teststates.add(sp1);
		teststates.add(sp2);
		teststates.add(sp3);
		teststates.add(sp4);
		teststates.add(balk1);
		teststates.add(balk2);
		teststates.add(balk3);
		teststates.add(balk4);
		
		
		
		boardState.setGeometries(teststates);
		System.out.println(boardState.getGeometries());
	}
	
	
	
}
