package pongSpezial;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pongSpezial.netController.Client;
import pongSpezial.netController.GUI;
import pongSpezial.view.State;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Client client = new Client();
		Thread thread = new Thread(client);
		thread.start();

		State state = State.SPLASH;
		GUI startGui = new GUI(state, client);
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