package pongSpezial;

import java.io.IOException;

import javax.sound.midi.ControllerEventListener;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pongSpezial.dataModel.BoardState;
import pongSpezial.gameLogic.GameManager;
import pongSpezial.gameLogic.InputHandler;
import pongSpezial.netController.Client;
import pongSpezial.netController.GUI;
import pongSpezial.netController.NetworkAddress;
import pongSpezial.netController.Server;
import pongSpezial.view.State;


public class Main extends Application {
	
	FXMLLoader loader;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{	
		/*
		 *  Server -> Client test
		 */
		NetworkAddress address = new NetworkAddress(new byte[] {(byte)127, (byte)0, (byte)0, (byte)1}, 9898);
		
		Server server = new Server(BoardState.instance, address);
		server.setUpConnection();
		server.start();
		
		GUI startGui = new GUI(State.SPLASH, Client.instance, loader, primaryStage);
		
		Client.instance.init(address, 1, startGui);
		
		
		
		

//		
//		Client client2 = new Client(address, 1);
//		Thread clientThread2 = new Thread(client2);
//		clientThread2.start();
//		
//		Client client3 = new Client(address, 1);
//		Thread clientThread3 = new Thread(client3);
//		clientThread3.start();
//		
//		Client client4 = new Client(address, 1);
//		Thread clientThread4 = new Thread(client4);
//		clientThread4.start();
		
		
		GameManager gm = new GameManager();
		//Thread thread2 = new Thread(gm);
		//thread2.start();
		
		
		
		startGui.switchScreen(primaryStage);
				
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent arg0)
			{
//				client.shutdown();
//				gm.shutdown();
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) 
	{
		
		launch(args);
	}
	
}