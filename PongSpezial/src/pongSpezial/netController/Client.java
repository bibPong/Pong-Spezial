package pongSpezial.netController;

import pongSpezial.dataModel.*;


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
		Ball ball = new Ball(2);
		Edge sp1 = new Edge(new Point2D(2,3), PLAYERGOALEDGE);
		Edge sp2 = new Edge(new Point2D(2,5), PLAYERGOALEDGE);
		Edge sp3 = new Edge(new Point2D(2,4), PLAYERGOALEDGE);
		Edge sp4 = new Edge(new Point2D(1,3), PLAYERGOALEDGE);
		Edge co1 = new Edge(new Point2D(1,3), CORNEREDGE);
		Edge co2 = new Edge(new Point2D(2,3), CORNEREDGE);
		Edge co3 = new Edge(new Point2D(3,3), CORNEREDGE);
		Edge co4 = new Edge(new Point2D(2,1), CORNEREDGE);
		Bar balk1 = new Bar(3);
		Bar balk2 = new Bar(3);
		Bar balk3 = new Bar(3);
		Bar balk4 = new Bar(3);
		
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
		teststates.add(co1);
		teststates.add(co2);
		teststates.add(co3);
		teststates.add(co4);
		
		
		boardState.setGeometries(teststates);
		System.out.println(boardState.getGeometries());
	}
	
	
	
}
