package pongSpezial.netController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.xml.bind.ValidationException;

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
	private ConnectionState state;
	private boolean isTransmitting;

	public Connection(int playerID, Socket socket)
	{
		this.state = ConnectionState.INIT;
		this.isTransmitting = false;
		this.playerID = playerID;
		this.socket = socket;
		super.setName("Connection");
	}
	
	@Override
	public void run()
	{
		while (false || socket != null && socket.isConnected())
		{
			switch (state)
			{
				case INIT:
					isTransmitting = true;
					init();
					isTransmitting = false;
					break;

				case PLAY:
					isTransmitting = true;
					play();
					isTransmitting = false;
					break;
				
				case IDLE:
					idle();
					break;
						
				default:
					close();
					break;
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
	
	public void setConnectionState(ConnectionState state)
	{
		this.state = state;
	}
	
	public ConnectionState getConnectionState()
	{
		return state;
	}
	
	public int getPassword()
	{
		return password;
	}

	public boolean isTransmitting()
	{
		return isTransmitting;
	}
	
	private void init()
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
	}
	
	private void play()
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
	
	private void idle()
	{
		try
		{
			sleep(1);
		}catch (Exception e)
		{
			System.out.println("Connection.class: " + e);
		}
	}
	
}
