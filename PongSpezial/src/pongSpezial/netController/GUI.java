package pongSpezial.netController;


import java.util.Dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pongSpezial.dataModel.BoardState;
//import pongSpezial.view.GUI;
import pongSpezial.view.State;

public class GUI
{
	//MainMenu
	//zum testsen
	@FXML
	private Button test;
	
	@FXML
	private Button test2;
	
	State testState;
	Stage primaryStage = new Stage();
	
	
	
	// Testfunktion um zwischen GUI's zu wechseln
		public void setTestStateOnClickTestButton()
		{
			testState = State.GAME;
			guiSwitchTest();
		}	
		
		public void setTestStateOnClickTest2Button()
		{
			testState = State.MAINMENU;	
			guiSwitchTest();
		}
	
		public void guiSwitchTest()
		{
			pongSpezial.view.GUI gui = new pongSpezial.view.GUI(testState);
			gui.start(primaryStage);
		}
		
		
		
		
		
	//zum testen
	
	
	@FXML 
	private Button closeGamescreen;
	
	@FXML
	private ToggleButton soundToggle;
	
	@FXML
	public void closeScreen(ActionEvent event)
	{
		//Close the game and switch to another Screen
	}
	
	@FXML
	public void switchOnOff(ActionEvent event)
	{
		//Turn the sound on or off
	}
	
	
	private BoardState boeardstate;
	private State state;
	private boolean isSinglePlayer;
	private Dictionary<Integer, Color> playerColors;
	private String name;
	
	public GUI()
	{
		this.state = State.SPLASH;
	}
}
