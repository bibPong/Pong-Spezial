package pongSpezial.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameScreen extends Application{

	private  int xResolution;
	private  int yResolution;
	
	public void start(Stage stage)
	{
		scale();
	}
	
	
	public static void main(String[] args) 
	{
	    launch(args);
	} 

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

	public void scale()
	{
		GridPane grid = new GridPane();

		int screenWidth = (int) Screen.getPrimary().getBounds().getMaxX();
		int screenHeight =(int) Screen.getPrimary().getBounds().getMaxY();
		
//		System.out.println(screenWidth);
//		System.out.println(screenHeight);
		xResolution = (int)(screenWidth * 0.35);
		yResolution = (int)(screenHeight * 0.55);
			
		if(xResolution > yResolution)
		{
			Scene scene = new Scene(grid, xResolution, yResolution);
			grid.setRotate(0.0);
//			System.out.println(xResolution + " x " + xResolution);	
			Stage stage = new Stage();
			stage.setTitle("Pong-Spezial");
			stage.setResizable(false);
			
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			Scene scene = new Scene(grid, xResolution, xResolution);
			grid.setRotate(0.0);
//			System.out.println(xResolution + " x " + xResolution);	
			Stage stage = new Stage();
			stage.setTitle("Pong-Spezial");
			stage.setResizable(false);
			
			stage.setScene(scene);
			stage.show();
		}
	}
}
