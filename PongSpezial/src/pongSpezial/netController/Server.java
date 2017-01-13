package pongSpezial.netController;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Dictionary;

import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.GameConfig;
import pongSpezial.dataModel.Player;
import pongSpezial.gameLogic.InputHandler;

public class Server implements Runnable
{

	private Dictionary<Player, NetworkAddress> clientConnection;
	private BoardState boardState;
	private int password;
	private NetworkAddress clientIPS;
	private Boolean[] readyPlayers;
	private int hostID;
	private InputHandler[] inputHandleArray;
	private GameConfig gameConfig;
	private boolean running;
	private NetworkAddress networkAddress;
	private ClientServer[] clientServerList;

	public Server(BoardState boardState, NetworkAddress networkAddress) throws IOException
	{
		this.running = true;
		this.boardState = boardState;
		this.networkAddress = networkAddress;
		init();
	}

	public void init()
	{
		clientServerList = new ClientServer[4];

		for (int i = 0; i < clientServerList.length; i++)
		{
			clientServerList[i] = new ClientServer(networkAddress);
			clientServerList[i].start();
		}
	}

	public void setUpConnection()
	{

	}

	public BoardState synchronizeBoardState()
	{
		return boardState;

	}

	public void sendInputToGameManager()
	{

	}

	public void shutdown()
	{
		running = false;
		System.out.println("Server has been shutdown.");
	}

	@Override
	public void run()
	{
		while (running) 
		{
			for (ClientServer clientServer : clientServerList)
			{	
	            try
				{
	            	Socket socket = clientServer.getSocket();
	    			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	    	        out.writeObject(boardState);
				} catch (Exception e)
				{
					System.out.println("Server.class: " + e);
				}
			}
			
			try
			{
				Thread.sleep(1000);
			} catch (Exception e)
			{
				System.out.println("Server.class: " + e);
			}
        }
	}
	
}
