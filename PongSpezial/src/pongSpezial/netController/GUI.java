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
import pongSpezial.view.State;

public class GUI
{
	
	//zum testsen
	@FXML
	private Button test;
	
	
	public void testFunction()
	{
		
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("TEST");
		alert.setHeaderText("Controller Class funktioniert");
		alert.setContentText("Alles funktioniert");
		
		alert.showAndWait();
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
