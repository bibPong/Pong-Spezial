package pongSpezial.netController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import javafx.beans.binding.IntegerExpression;
import pongSpezial.dataModel.BoardState;
import pongSpezial.gameLogic.InputHandler;

public class Connection extends Thread
{
	private Socket socket;
	private BoardState boardState;
	private InputHandler inputHandler;
	private int playerID;
	private int password;

	public Connection(int playerID, Socket socket)
	{
		this.playerID = playerID;
		this.socket = socket;
		super.setName("Connection");
	}
	
	@Override
	public void run()
	{
		try
		{
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(playerID);
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			int password = in.readInt();
			this.password = password;
		} catch (IOException e)
		{
			System.out.println("Connection.class: " + e);
		}
		
		
		while (socket != null && socket.isConnected())
		{
			try
			{
				// Server out
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    	        out.writeObject(boardState);
    	        
    	        // Client in
    	        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				Object obj = in.readObject();
				if (obj instanceof InputHandler)
					inputHandler = (InputHandler)obj;
			} catch (SocketException e)
			{
				close();
			}catch (Exception e)
			{
				System.out.println("Connection.class: " + e);
			}
		}
		
		System.out.println("Client " + inputHandler.getPlayerID() + ": connection closed");
	}

	public void close()
	{
		try
		{
			socket.close();
			this.socket = null;
		} catch (IOException e)
		{
			System.out.println("Client " + inputHandler.getPlayerID() + ": " + e);
		}
	}
	
	public boolean isConnected()
	{
		if (socket == null)
			return false;
		return socket.isConnected();
	}
	
	public void setBoardState(BoardState boardState)
	{
		this.boardState = boardState;
	}
	
	public InputHandler getInputHandler()
	{
		return inputHandler;
	}
	
}
