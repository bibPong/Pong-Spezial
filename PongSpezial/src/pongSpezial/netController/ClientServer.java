package pongSpezial.netController;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientServer extends Thread
{
	private NetworkAddress networkAddress;
	private ServerSocket serverSocket;
	private Socket socket;

	public ClientServer(NetworkAddress networkAddress)
	{
		this.networkAddress = networkAddress;
	}

	public Socket getSocket() throws ConnectException
	{
		if (socket == null)
			throw new ConnectException("Client ist noch nicht verbunden");
		return socket;
	}
	
	public void interrupt()
	{
		try
		{
			serverSocket.close();
		} catch (IOException e)
		{
			System.out.println("ClientServer.class: Connection closed");
		}
	}
	
	@Override
	public void run()
	{
		try
		{
			serverSocket = new ServerSocket(networkAddress.getPort());
			socket = serverSocket.accept();
		} catch (Exception e) 
		{
			System.out.println("ClientServer.class: " + e);
		}
	}

}
