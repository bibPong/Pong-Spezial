package pongSpezial;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import pongSpezial.netController.Client;
import pongSpezial.view.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		Client client = new Client();
		Thread thread = new Thread(client);
		thread.start();

		State state = State.SPLASH;
		GUI myGui = new GUI(state);
		myGui.start(primaryStage);
		
		
		
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent arg0)
			{
				client.stop();
			}
		});
		
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}