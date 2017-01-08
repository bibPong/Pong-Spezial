package pongSpezial.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class GameScreen {

	@FXML
	private Button closeGamescreen;
	@FXML
	private ToggleButton soundToggle;
	
	
	@FXML
	private void switchOnOff()
	{
		
		
	}
	
	@FXML
	private void closeScreen()
	{
		
		
		   Stage stage = (Stage) closeGamescreen.getScene().getWindow();
		    stage.close();
	}
	
}
