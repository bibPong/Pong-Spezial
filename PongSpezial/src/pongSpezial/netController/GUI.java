package pongSpezial.netController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class GUI
{
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
