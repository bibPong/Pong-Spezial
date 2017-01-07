<<<<<<< HEAD
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
=======
package pongSpezial.netController;

import javafx.scene.input.KeyEvent;
import pongSpezial.dataModel.BoardState;
import pongSpezial.gameLogic.InputHandler;

public class Client implements Runnable
{

	private boolean running;
	private BoardState boardState;
	private NetworkAddress serverIP;
	private InputHandler inputHandler;

	public Client(BoardState boardState, NetworkAddress serverIP, int playerID)
	{
		this.running = true;
		this.boardState = boardState;
		this.serverIP = serverIP;
		this.inputHandler = new InputHandler(playerID);
	}

	// Zum testen
	public Client()
	{
		this.running = true;
		this.inputHandler = new InputHandler(1);
	}

	@Override
	public void run()
	{
		try
		{
			while (running)
			{
				System.out.println("Player " + inputHandler.getPlayerID() + ": " + inputHandler.getDirection());
				updateGUI();
				Thread.sleep(10);
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void shutdown()
	{
		this.running = false;
		System.out.println("Player " + inputHandler.getPlayerID() + " client has been shutdown.");
	}

	public void validateInput(KeyEvent event)
	{
		switch (event.getEventType().getName())
		{
		case "KEY_PRESSED":
			switch (event.getCode())
			{
			case LEFT:
				inputHandler.setDirection(1);
				break;
			case RIGHT:
				inputHandler.setDirection(2);
				break;
			case UP:
				inputHandler.setDirection(1);
				break;
			case DOWN:
				inputHandler.setDirection(2);
				break;
			default:
				break;
			}
			break;

		case "KEY_RELEASED":
			inputHandler.setDirection(0);
			break;
		default:
			break;
		}
	}

	public void updateGUI()
	{
		// In BoardState geändert
		// boardState = BoardState.getBoardstate();
		System.out.println("BoardState: " + boardState.getGeometries());
	}

}
>>>>>>> branch 'master' of https://github.com/fortytwoish/Pong-Spezial.git
