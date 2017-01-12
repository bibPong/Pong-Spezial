package pongSpezial.netController;

import java.util.Dictionary;

import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.GameConfig;
import pongSpezial.dataModel.Player;
import pongSpezial.gameLogic.InputHandler;

public class Server {

	private Dictionary<Player, NetworkAddress> clientConnection;
	private BoardState boardState;
	private int password;
	private NetworkAddress clientIPS;
	private Boolean[] readyPlayers;
	private int hostID;
	private InputHandler[] inputHandleArray;
	private GameConfig gameConfig;
	
	public Server()
	{
		init();
	}
	
	public void init()
	{
		
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
	
}
