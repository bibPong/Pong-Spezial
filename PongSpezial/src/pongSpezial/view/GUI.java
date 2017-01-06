package pongSpezial.view;
import java.io.IOException;
import java.util.Dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pongSpezial.dataModel.BoardState;

public class GUI extends Application
{

	private final String SPLASH_SCREEN_PATH = "SplashScreen.fxml"; // DS
	private final String MAINMENU_SCREEN_PATH = "MainMenu.fxml"; // DS
	private final String NAMEENTRY_SCREEN_PATH = "Nameentry.fxml"; // DS
	private final String HOSTANDJOIN_SCREEN_PATH = "HostandJoin.fxml"; //DS
	private final String SPCONFIG_SCREEN_PATH = "SpConfig.fxml";
	private final String MPCONFIG_SCREEN_PATH = "MpConfig.fxml";
	private final String MPJOIN_SCREEN_PATH = "MpJoin.fxml"; 
	private final String LOBBY_SCREEN_PATH = "LobbyScreen.fxml";
	private final String GAME_SCREEN_PATH = "GameScreen.fxml";
	
	private BoardState boeardstate;
	private State state ;
	private boolean isSinglePlayer;
	private Dictionary<Integer, Color> playerColors;
	private String name;
	
	

	public GUI(State state)
	{
		this.state = state;
		System.out.println("Im Konstruktor");
		
	}

	@Override
	public void start(Stage primaryStage)
	{
		System.out.println("In start methode");
		try
		{

			switch (state)
			{
				case SPLASH :

					showScreenType(primaryStage, SPLASH_SCREEN_PATH , "Splashscreen");

					break;

				case MAINMENU :

					showScreenType(primaryStage, MAINMENU_SCREEN_PATH, "Mainmenu");

					break;

				case NAMEENTRY :

					showScreenType(primaryStage, NAMEENTRY_SCREEN_PATH,"title");

					break;

				case HOSTANDJOIN :

					showScreenType(primaryStage, HOSTANDJOIN_SCREEN_PATH,"title");

					break;

				case SPCONFIG :

					showScreenType(primaryStage, SPCONFIG_SCREEN_PATH, "title");

					break;

				case MPCONFIG :

					showScreenType(primaryStage, MPCONFIG_SCREEN_PATH, "title");

					break;

				case MPJOIN :

					showScreenType(primaryStage, MPJOIN_SCREEN_PATH, "title");

					break;

				case LOBBY :

					showScreenType(primaryStage, LOBBY_SCREEN_PATH, "title");

					break;

				case GAME :

					showScreenType(primaryStage, GAME_SCREEN_PATH, "title");

					break;

				default :
					break;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	
	public void showScreenType(Stage primaryStage, String screenPath,String title) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(screenPath));
		Parent root = loader.load();
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle(title);
		primaryStage.show();

	}

}
