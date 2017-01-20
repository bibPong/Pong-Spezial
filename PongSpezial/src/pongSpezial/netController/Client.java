package pongSpezial.netController;

import pongSpezial.dataModel.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pongSpezial.dataModel.BoardState;
import pongSpezial.gameLogic.GameManager;
import pongSpezial.gameLogic.InputHandler;

public class Client
{

	private boolean running;
	public BoardState boardState;
	private NetworkAddress networkAddress;
	private InputHandler inputHandler;
	private Socket socket;
	private int password;
	private Server server;
	private GUI gui;
	public double boardsize;
	
	public Thread clientThread;

	public void init(NetworkAddress networkAddress, int password, GUI gui) throws IOException
	{
		this.running = true;
		this.networkAddress = networkAddress;
		this.password = password;
		this.inputHandler = new InputHandler();
		
		this.gui = gui;
	}
	
	public static Client instance = new Client();
	
	/***
	 * Launched when the GAME State is entered
	 */
	public void run(Stage primaryStage)
	{
		this.boardState = BoardState.instance;
		
//		this.boardState = GameManager.testBoardState(
//				new Player[]{ new Player(1, "A"),
//							  new Player(2, "B"),
//							  new Player(3, "C"),
//							  new Player(4, "D"),
//							}, boardsize);
		
		Task task = new Task<Void>()
		{
			  @Override
			  public Void call() throws Exception
			  {

			    while (true)
			    {
			      Platform.runLater(new Runnable()
			      {
			        @Override
			        public void run() {
			        	System.out.println("GUI updating...");
			        	gui.updateGUI(boardState, primaryStage);
			        }
			      });
			      
			      Thread.sleep(16);
			    }
			  }
		};
			
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();

	}

	public void shutdown()
	{
		this.running = false;
	}

	public void validateInput(KeyEvent event)
	{
		switch (event.getEventType().getName())
		{
		case "KEY_PRESSED":
			switch (event.getCode())
			{
			case LEFT:
				inputHandler.setDirection("LEFT");
				break;
			case RIGHT:
				inputHandler.setDirection("RIGHT");
				break;
			case UP:
				GameManager.pl0Input = -1; //PROTOTYPE ONLY
				inputHandler.setDirection("UP");
				break;
			case DOWN:
				GameManager.pl0Input = 1; //PROTOTYPE ONLY
				inputHandler.setDirection("DOWN");
				break;
			default:
				break;
			}
			break;

		case "KEY_RELEASED":
			GameManager.pl0Input = 0; //PROTOTYPE ONLY
			inputHandler.setDirection("KEY_RELEASED");
			break;
		default:
			break;
		}
	}
	
	// Was macht das hier? Steht nicht im klassen Diagramm
	public void startServer()
	{
		try
		{
			server = new Server(boardState, new NetworkAddress(9898));
		} catch (IOException e)
		{
			//System.out.println(e);
		}
	}
	
	
	
}

