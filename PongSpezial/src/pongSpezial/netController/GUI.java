package pongSpezial.netController;

import java.io.IOException;
import java.util.Dictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pongSpezial.dataModel.BoardState;
//import pongSpezial.view.GUI;
import pongSpezial.view.State;

public class GUI  
{

	private final String SPLASH_SCREEN_PATH = "/pongSpezial/view/SplashScreen.fxml"; // DS
	private final String MAINMENU_SCREEN_PATH = "/pongSpezial/view/MainMenu.fxml"; // DS
	private final String NAMEENTRY_SCREEN_PATH = "/pongSpezial/view/Nameentry.fxml"; // DS
	private final String HOSTANDJOIN_SCREEN_PATH = "/pongSpezial/view/HostandJoin.fxml"; // DS
	private final String SPCONFIG_SCREEN_PATH = "/pongSpezial/view/SpConfig.fxml";
	private final String MPCONFIG_SCREEN_PATH = "/pongSpezial/view/MpConfig.fxml";
	private final String MPJOIN_SCREEN_PATH = "/pongSpezial/view/MpJoin.fxml";
	private final String LOBBY_SCREEN_PATH = "/pongSpezial/view/LobbyScreen.fxml";
	private final String GAME_SCREEN_PATH = "/pongSpezial/view/GameScreen.fxml";

	// SplashScreen
	@FXML
	private Button btn_firstStart;

	// MainMenu
	@FXML
	private Button btn_sp;
	@FXML
	private Button btn_mp;
	@FXML
	private Button btn_close;

	// NameEntry
	@FXML
	private Button btn_nameConfirm;
	@FXML
	private TextField txf_nameEntry;

	// HostAndJoin
	@FXML
	private Button btn_selectHost;
	@FXML
	private Button btn_selectJoin;

	@FXML
	private Button test2;

	@FXML
	private Button closeGamescreen;

	@FXML
	private ToggleButton soundToggle;

	private BoardState boeardstate;
	private State state;
	private boolean isSinglePlayer;
	private Dictionary<Integer, Color> playerColors;
	private String name;
	private Client client;
	private Stage primaryStage;
	private FXMLLoader loader;

	public GUI()
	{}



	public GUI(State state, Client client,FXMLLoader loader,Stage primaryStage) {
		this.state = state;
		this.client = client;
		this.loader= loader;
		this.primaryStage =primaryStage;
	}

	@FXML
	public void closeScreen(ActionEvent event) {
		// Close the game and switch to another Screen
	}

	@FXML
	public void switchOnOff(ActionEvent event) {
		// Turn the sound on or off
	}
	@FXML
	public void click() throws IOException
	{
		 loader.getClass().getResource(MAINMENU_SCREEN_PATH);
			Parent root = loader.load();
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("PongSpezial");
			primaryStage.show();
				
	}

	public void switchScreen(Stage primaryStage) 
	{
		try {

			switch (state) {
			case SPLASH:

				showScreenType(primaryStage, SPLASH_SCREEN_PATH, "Splashscreen");

				break;

			case MAINMENU:

				showScreenType(primaryStage, MAINMENU_SCREEN_PATH, "Mainmenu");

				break;

			case NAMEENTRY:

				showScreenType(primaryStage, NAMEENTRY_SCREEN_PATH, "title");

				break;

			case HOSTANDJOIN:

				showScreenType(primaryStage, HOSTANDJOIN_SCREEN_PATH, "title");

				break;

			case SPCONFIG:

				showScreenType(primaryStage, SPCONFIG_SCREEN_PATH, "title");

				break;

			case MPCONFIG:

				showScreenType(primaryStage, MPCONFIG_SCREEN_PATH, "title");

				break;

			case MPJOIN:

				showScreenType(primaryStage, MPJOIN_SCREEN_PATH, "title");

				break;

			case LOBBY:

				showScreenType(primaryStage, LOBBY_SCREEN_PATH, "title");

				break;

			case GAME:

				showScreenType(primaryStage, GAME_SCREEN_PATH, "title");

				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showScreenType(Stage primaryStage, String screenPath, String title) throws IOException {
		 
		//loader = new FXMLLoader(getClass().getResource(screenPath));
		loader.getClass().getResource(screenPath);
		Parent root = loader.load();
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle(title);
		primaryStage.show();

		// Controller ist GUI aus package pongSpezial.netController

		// Bind Key Events - example
		primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				client.validateInput(event);
			}
		});

		primaryStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {

				client.validateInput(event);

			}
		});

		// pongSpezial.netController.GUI controller = loader.getController();

	}

}
