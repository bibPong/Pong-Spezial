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

import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import pongSpezial.dataModel.BoardState;
import pongSpezial.gameLogic.InputHandler;

public class Client implements Runnable
{

	private boolean running;
	private BoardState boardState;
	private NetworkAddress networkAddress;
	private InputHandler inputHandler;
	private Socket socket;
	private int password;
	private Server server;

	public Client(NetworkAddress networkAddress, int password) throws IOException
	{
		this.running = true;
		this.networkAddress = networkAddress;
		this.password = password;
		this.inputHandler = new InputHandler();
	}

	@Override
	public void run()
	{
		while (running && this.socket == null)
		{
			try
			{
				this.socket = new Socket(this.networkAddress.getIpAddress(), this.networkAddress.getPort());
				Thread.sleep(1000);
			} catch (Exception e) 
			{
				System.out.println("Client.class: " + e);
			}
		}

		try
		{
			DataInputStream in = new DataInputStream(socket.getInputStream());
			int playerID = in.readInt();
			inputHandler.setPlayerID(playerID);
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeInt(password);
		} catch (IOException e)
		{
			System.out.println("Client.class: " + e);
		}
		
		while (running)
		{
			try
			{
				updateGUI();

				// Client out
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    	        out.writeObject(inputHandler);
				
    	        // Server in
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				Object obj = in.readObject();
//				if (obj instanceof BoardState)
//					System.out.println("Client " + inputHandler.getPlayerID() + ": " + obj);
//				else
//					System.out.println("Client " + inputHandler.getPlayerID() + ": Server -> in : error");
			} catch (Exception e)          
			{
				System.out.println("Client.class: " + e);
			}
		}
	}

	public void shutdown()
	{
		this.running = false;
		System.out.println("Player " + inputHandler.getPlayerID() + " client has been shutdown.");
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
				inputHandler.setDirection("UP");
				break;
			case DOWN:
				inputHandler.setDirection("DOWN");
				break;
			default:
				break;
			}
			break;

		case "KEY_RELEASED":
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
			System.out.println(e);
		}
	}
	
	public void updateGUI()
	{
		// In BoardState geändert
		//boardState  = BoardState.getBoardstate();
//		Ball ball = new Ball(2);
//		Edge sp1 = new Edge(new Point2D(2,3), EdgeType.PLAYERGOALEDGE);
//		Edge sp2 = new Edge(new Point2D(2,5), EdgeType.PLAYERGOALEDGE);
//		Edge sp3 = new Edge(new Point2D(2,4), EdgeType.PLAYERGOALEDGE);
//		Edge sp4 = new Edge(new Point2D(1,3), EdgeType.PLAYERGOALEDGE);
//		Edge co1 = new Edge(new Point2D(1,3), EdgeType.CORNEREDGE);
//		Edge co2 = new Edge(new Point2D(2,3), EdgeType.CORNEREDGE);
//		Edge co3 = new Edge(new Point2D(3,3), EdgeType.CORNEREDGE);
//		Edge co4 = new Edge(new Point2D(2,1), EdgeType.CORNEREDGE);
//		Bar balk1 = new Bar(3);
//		Bar balk2 = new Bar(3);
//		Bar balk3 = new Bar(3);
//		Bar balk4 = new Bar(3);
//		
//		
//		
//		List<Geometry> teststates = new ArrayList<Geometry>();
//		teststates.add(ball);
//		teststates.add(sp1);
//		teststates.add(sp2);
//		teststates.add(sp3);
//		teststates.add(sp4);
//		teststates.add(balk1);
//		teststates.add(balk2);
//		teststates.add(balk3);
//		teststates.add(balk4);
//		teststates.add(co1);
//		teststates.add(co2);
//		teststates.add(co3);
//		teststates.add(co4);
//		
//		
//		
//		
//		boardState.setGeometries(teststates);
//		System.out.println(boardState.getGeometries());
	}
	
	
	
}

