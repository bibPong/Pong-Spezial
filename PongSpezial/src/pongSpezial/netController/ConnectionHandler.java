package pongSpezial.netController;

import java.awt.List;
import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionHandler extends Thread
{
	private ServerSocket serverSocket;
	private Connection[] connections;
	private boolean isRunning;

	public ConnectionHandler(NetworkAddress networkAddress) throws IOException
	{
		this.serverSocket = new ServerSocket(networkAddress.getPort());
		this.connections = new Connection[4];
		this.isRunning = true;
	}
	
	public void close()
	{
		isRunning = false;
	}
	
	@Override
	public void run()
	{
		isRunning = true;
		
		while (isRunning)
		{
			for (Connection connection : connections)
			{
				try
				{
					if (connection == null || !connection.isConnected())
					{
						connection = new Connection(serverSocket.accept());
						connection.start();
					}
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// Close all connections
		for (Connection connection : connections)
			if (connection != null)
				try
				{
					connection.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public Connection[] getConnections()
	{
		ArrayList<Connection> validConnections = new ArrayList<Connection>();
		
		for (Connection connection : connections)
		{
			if (connection != null && connection.isConnected())
				validConnections.add(connection);
		}
		
		return validConnections.toArray(new Connection[validConnections.size()]);
	}

}
