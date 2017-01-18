package pongSpezial.netController;

import java.io.IOException;
import java.util.Dictionary;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import pongSpezial.dataModel.BoardState;
import pongSpezial.dataModel.Geometry;
//import pongSpezial.view.GUI;
import pongSpezial.view.State;

public class GUI {

	private final String SPLASH_SCREEN_PATH = "/pongSpezial/view/SplashScreen.fxml";
	private final String MAINMENU_SCREEN_PATH = "/pongSpezial/view/MainMenu.fxml";
	private final String NAMEENTRY_SCREEN_PATH = "/pongSpezial/view/Nameentry.fxml";
	private final String HOSTANDJOIN_SCREEN_PATH = "/pongSpezial/view/HostandJoin.fxml";
	private final String SPCONFIG_SCREEN_PATH = "/pongSpezial/view/LobbyGUIsp.fxml";// SpConfig.fxml
	private final String MPCONFIG_SCREEN_PATH = "/pongSpezial/view/LobbyGUImp.fxml";// MpConfig.fxml
	private final String MPJOIN_SCREEN_PATH = "/pongSpezial/view/LobbyGUImpJoin.fxml";// MpJoin.fxml
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
	private Button btn_quit;

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
	private PasswordField pwf_password;
	@FXML
	private TextField txf_ip;

	// LobbyGUIsp
	@FXML
	private Button btn_startGameSP;

	// LobbyGUImp
	private Button btn_startGameMP;

	

	// GameScreen
	private int xResolution;
	private  int yResolution;
	
	@FXML
	private Circle ball;
	@FXML
	private Rectangle sp1;
	@FXML
	private Rectangle sp2;
	@FXML
	private Rectangle sp3;
	@FXML
	private Rectangle sp4;
	@FXML
	private Rectangle balk1;
	@FXML
	private Rectangle balk2;
	@FXML
	private Rectangle balk3;
	@FXML
	private Rectangle balk4;
	@FXML
	private Rectangle co1;
	@FXML
	private Rectangle co11;
	@FXML
	private Rectangle co2;
	@FXML
	private Rectangle co21;
	@FXML
	private Rectangle co3;
	@FXML
	private Rectangle co31;
	@FXML
	private Rectangle co4;
	@FXML
	private Rectangle co41;
	@FXML
	private Button closeGamescreen;
	@FXML
	private ToggleButton soundToggle;
	
	private boolean firstStart;
	
	
	
	
	
	
	
	//
	
	
	
	
	
	private BoardState boeardstate;
	private State prevState;
	private State state;
	private boolean isSinglePlayer;
	private Dictionary<Integer, Color> playerColors;
	private String name;
	private final Client client;

	private FXMLLoader loader;

	public GUI() {
		this.client=null;
	}

	public GUI(State state, Client client, FXMLLoader loader) {
		this.state = state;
		this.client = client;
		this.loader = loader;
		System.out.println(client.toString());

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
	public void quit() {
		System.exit(0);

	}

	@FXML
	public void click(ActionEvent e) throws IOException {
		Control c = (Button) e.getSource();
		Stage primaryStage = (Stage) c.getScene().getWindow();

		prevState = state;

		if (c.getId().toString().equals("btn_firstStart")) {
			state = State.NAMEENTRY;

		}

		if (c.getId().toString().equals("btn_nameConfirm")) {
			state = State.MAINMENU;
		}

		if (c.getId().toString().equals("btn_sp")) {
			state = State.SPCONFIG;
		}

		if (c.getId().toString().equals("btn_mp")) {
			state = State.HOSTANDJOIN;
		}

		if (c.getId().toString().equals("btn_selectHost")) {
			state = State.MPCONFIG;
		}

		if (c.getId().toString().equals("btn_startGameMP")) {

			state = State.GAME;

		}

		if (c.getId().toString().equals("btn_startGameSP")) {
			state = State.GAME;

		}

		switchScreen(primaryStage);

	}

	public void switchScreen(Stage primaryStage) {
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

				showScreenType(primaryStage, SPCONFIG_SCREEN_PATH, "Singleplayer");

				break;

			case MPCONFIG:

				showScreenType(primaryStage, MPCONFIG_SCREEN_PATH, "Multiplayer");

				break;

			case MPJOIN:

				showScreenType(primaryStage, MPJOIN_SCREEN_PATH, "title");

				break;

			case LOBBY:

				showScreenType(primaryStage, LOBBY_SCREEN_PATH, "title");

				if (prevState != state.MPJOIN) {
					// Man ist entweder MPHost oder SinglePlayer
					// Server muss gestartet werden
					client.startServer();
				}

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

		FXMLLoader loader = new FXMLLoader(getClass().getResource(screenPath));
		Parent root = loader.load();
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle(title);
		primaryStage.show();

		// Bind Key Events - example

		if (state == State.GAME)
			keyInput(primaryStage);

	}

	public void keyInput(Stage primaryStage) {
		System.out.println("In methode");

		// Stage primaryStage = (Stage) closeGamescreen.getScene().getWindow();

		System.out.println(primaryStage.toString());

		primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("press");
				Client.instance.validateInput(event);// hier werden Nullppinter geworfen
			}
		});

		primaryStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("release");
				Client.instance.validateInput(event);// hier werden Nullppinter geworfen

			}
		});

	}
	
	public void drawGameScreen(List<Geometry> list)
	{
		
		if(firstStart){
			for (Geometry geometry : list)
			{
				Point2D test = geometry.getPosition();
				
				switch (geometry.getClass().toString())
				{
				case "balk1":
					
					if(test.getX()==0)
					{
						fadeOutBars(balk1);
						
					}
					else
					{
						fadeOutBars(sp1);
						balk1.setLayoutX(test.getX());
						balk1.setLayoutY(test.getY());
					}
					break;
					
				case "balk2":
					if(test.getX()==0)
					{
						fadeOutBars(balk2);
						
					}
					else
					{
						fadeOutBars(sp2);
						balk2.setLayoutX(test.getX());
						balk2.setLayoutY(test.getY());
					}
					break;
					
				case "balk3":
					if(test.getY()==0)
					{
						fadeOutBars(balk3);
						
					}
					else
					{
						fadeOutBars(sp3);
						balk3.setLayoutX(test.getX());
						balk3.setLayoutY(test.getY());
					}
					break;
					
				case "balk4":
					if(test.getY()==0)
					{
						fadeOutBars(balk4);
						
					}
					else
					{
						fadeOutBars(sp4);
						balk4.setLayoutX(test.getX());
						balk4.setLayoutY(test.getY());
					}
					break;
					
				case "ball":
						ball.setLayoutX(test.getX());
						ball.setLayoutY(test.getY());
					break;

				default:
					break;
				}

				
			}
				firstStart = false;
			}
			else
			{
				for (Geometry geometry : list)
				{
					Point2D test = geometry.getPosition();
					
					switch (geometry.getClass().toString())
					{
					case "balk1":
						
						if(test.getX()==0)
						{
							fadeOutBars(balk1);
							fadeInBars(sp1);
						}
						else
						{
							balk1.setLayoutY(test.getY());
						}
						break;
						
					case "balk2":
						if(test.getX()==0)
						{
							fadeOutBars(balk2);
							fadeInBars(sp2);
						}
						else
						{
							balk2.setLayoutY(test.getY());
						}
						break;
						
					case "balk3":
						if(test.getY()==0)
						{
							fadeOutBars(balk3);
							fadeInBars(sp3);
						}
						else
						{
							balk3.setLayoutX(test.getX());
						}
						break;
						
					case "balk4":
						if(test.getY()==0)
						{
							fadeOutBars(balk4);
							fadeInBars(sp4);
						}
						else
						{
							balk4.setLayoutX(test.getX());
						}
						break;
						
					case "ball":
							ball.setLayoutX(test.getX());
							ball.setLayoutY(test.getY());
						break;

					default:
						break;
					}
				}
			}
			
		
	}
	
	public void fadeOutBars(Rectangle name)
	{
		FadeTransition fade = new FadeTransition(Duration.millis(1000), name);
		fade.setFromValue(1.0);
		fade.setToValue(0);
		fade.setAutoReverse(true);

		fade.play();
	}
	
	public void fadeInBars(Rectangle name)
	{
		FadeTransition fade = new FadeTransition(Duration.millis(1000), name);
		fade.setFromValue(0);
		fade.setToValue(1.0);
		fade.setAutoReverse(true);

		fade.play();
	}

	
	
	public void start(Stage stage)
	{
		scale();
	}
	
	
	private void switchOnOff()
	{
		
		
	}
	
	
	private void closeScreen()
	{
		Stage stage = (Stage) closeGamescreen.getScene().getWindow();
		stage.close();
	}
	
	
	
	public void scale()
	{
		this.xResolution = (int) Screen.getPrimary().getBounds().getMaxX();
		this.yResolution = (int) (Screen.getPrimary().getBounds().getMaxY() * 0.8);
		
		this.xResolution = this.yResolution;
	}
	

}
