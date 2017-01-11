package pongSpezial.view;

import java.util.List;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import pongSpezial.dataModel.Geometry;


public class GameScreen extends Application {
	
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
	
	
	public static void main(String[] args) 
	{
	    launch(args);
	} 


	
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
		this.xResolution = (int) Screen.getPrimary().getBounds().getMaxX();
		this.yResolution = (int) (Screen.getPrimary().getBounds().getMaxY() * 0.8);
		
		this.xResolution = this.yResolution;
	}
}
