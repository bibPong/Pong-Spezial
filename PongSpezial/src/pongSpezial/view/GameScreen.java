package pongSpezial.view;

<<<<<<< HEAD

=======
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
>>>>>>> branch 'master' of https://github.com/fortytwoish/Pong-Spezial.git

<<<<<<< HEAD
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;


public class GameScreen {
	
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
	
	public void drawScreen()
	{
		
		
	
		
	}
	
	public void fadeOutBars(Rectangle name)
	{
		FadeTransition fade = new FadeTransition(Duration.millis(1000), name);
		fade.setFromValue(1.0);
		fade.setToValue(0);
		fade.setAutoReverse(true);

		fade.play();
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
	
=======
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
>>>>>>> branch 'master' of https://github.com/fortytwoish/Pong-Spezial.git
}
