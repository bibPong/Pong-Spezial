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
import pongSpezial.gameLogic.GameManager;
import pongSpezial.netController.Client;
import pongSpezial.netController.GUI;
import pongSpezial.view.State;


public class Main extends Application {
	
	FXMLLoader loader;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{	
		
		
		
		Client client = new Client();
		Thread thread = new Thread(client);
		thread.start();
		
		GameManager gm = new GameManager();
		Thread thread2 = new Thread(gm);
		thread2.start();
		
		State state = State.SPLASH;
	
		createFXMLLoader(primaryStage);
		
		GUI startGui = new GUI(state, client,loader);
		
		
		
		
		System.out.println(loader.getController().toString());
		
		
		
		
		
		
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
	
	public void createFXMLLoader(Stage primaryStage) throws IOException
	{
		 loader = new FXMLLoader(getClass().getResource("/pongSpezial/view/Splashscreen.fxml"));
		Parent root = loader.load();
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("PongSpezial");
		primaryStage.show();
		
		
	}
	
	
}