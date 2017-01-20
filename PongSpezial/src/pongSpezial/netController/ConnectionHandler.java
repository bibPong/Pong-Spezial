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
		super.setName("ConnectionHandler");
	}
	
	public void close()
	{
		isRunning = false;
	}
	
	@Override
	public void run()
	{
		isRunning = false;
		
		while (isRunning)
		{
			for (int i = 0; i < connections.length; i++)
			{
				try
				{
					if (connections[i] == null || !connections[i].isConnected())
					{
						connections[i] = new Connection(i + 1, serverSocket.accept());
						connections[i].start();
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
				connection.close();
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
