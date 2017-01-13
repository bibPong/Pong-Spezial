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
		NetworkAddress address = new NetworkAddress(new byte[] {(byte)127, (byte)0, (byte)0, (byte)1}, 9898);
		Client client = new Client(BoardState.instance, address, 1);
		Server server = new Server(address);
//		Thread clientThread = new Thread(client);
//		Thread serverThread = new Thread(server);
//		clientThread.start();
//		serverThread.start();
		
		GameManager gm = new GameManager();
		Thread thread2 = new Thread(gm);
		thread2.start();
		
		
		GUI startGui = new GUI(State.SPLASH, client,loader);
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