package pongSpezial;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
		pongSpezial.view.GUI startGui = new GUI(state, client);
		startGui.start(primaryStage);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent arg0)
			{
				client.shutdown();
			}
		});
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}