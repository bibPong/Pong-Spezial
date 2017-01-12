package pongSpezial.netController;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkAddress 
{
	private InetAddress ipAddress;
	private int port;
	
	public NetworkAddress(byte[] ipAddress, int port) throws UnknownHostException
	{
		this.ipAddress = InetAddress.getByAddress(ipAddress);;
		this.port = port;
	}

	public NetworkAddress(int port) throws UnknownHostException
	{
		this.ipAddress = InetAddress.getByAddress(new byte[] {(byte)127, (byte)0, (byte)0, (byte)1});
		this.port = port;
	}
	
	public InetAddress getIpAddress()
	{
		return ipAddress;
	}

	public int getPort()
	{
		return port;
	}
	
}