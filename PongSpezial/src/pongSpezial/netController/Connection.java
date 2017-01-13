package pongSpezial.netController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import pongSpezial.dataModel.BoardState;
import pongSpezial.gameLogic.InputHandler;

public class Connection extends Thread
{
	private Socket socket;
	private BoardState boardState;
	private InputHandler inputHandler;
	
	public Connection(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run()
	{
		while (socket.isConnected())
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
			} catch (Exception e)
			{
				System.out.println("Connection.class: " + e);
			}
		}
		
		System.out.println("Client " + inputHandler.getPlayerID() + " connection closed");
	}

	public void close() throws IOException
	{
		socket.close();
	}
	
	public boolean isConnected()
	{
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
