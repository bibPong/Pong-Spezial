package pongSpezial;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import pongSpezial.view.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {

		
		
		GUI myGui = new GUI();
		
		myGui.showScreenType(primaryStage, "LobbyGUImp.fxml", "SplashScreen");
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}