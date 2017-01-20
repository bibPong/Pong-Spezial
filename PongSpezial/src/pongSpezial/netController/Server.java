package pongSpezial.netController;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;

import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.GameConfig;
import pongSpezial.dataModel.Player;
import pongSpezial.gameLogic.InputHandler;


public class Server extends Thread
{

	private Dictionary<Player, NetworkAddress> clientConnection;
	private BoardState boardState;
	private int password;
	private NetworkAddress clientIPS;
	private Boolean[] readyPlayers;
	private int hostID;
	private InputHandler[] inputHandleArray;  // <----- hier sind immer die aktuellen inputs und ids aller clients
	private GameConfig gameConfig;
	private boolean isRunning;
	private NetworkAddress networkAddress;
	private ConnectionHandler connectionHandler;

	public Server(BoardState boardState, NetworkAddress networkAddress) throws IOException
	{
		this.isRunning = true;
		this.boardState = boardState;
		this.networkAddress = networkAddress;
		super.setName("Server");
		init();
	}

	public void init()
	{
		try
		{
			connectionHandler = new ConnectionHandler(networkAddress);
		} catch (IOException e)
		{
			System.out.println("Server.class " + e);
		}
	}

	public void setUpConnection()
	{
		connectionHandler.start();
	}

	public BoardState synchronizeBoardState()
	{
		return boardState;
	}

	public void sendInputToGameManager()
	{
		// Inputhandler an gamemanager
	}

	public void close()
	{
		connectionHandler.close();
		isRunning = false;
		System.out.println("Server has been shutdown.");
	}

	@Override
	public void run()
	{
		while (isRunning)
		{
			ArrayList<InputHandler> list = new ArrayList<InputHandler>();
			
			for (Connection connection : connectionHandler.getConnections())
			{	
				connection.setBoardState(boardState);
				list.add(connection.getInputHandler());
			}
			
			inputHandleArray = list.toArray(new InputHandler[list.size()]);
			
			try
			{
				sleep(1);
			} catch (Exception e)
			{
				System.out.println("Server.class: " + e);
			}
        }
	}
	
}
