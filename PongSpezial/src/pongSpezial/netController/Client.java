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
