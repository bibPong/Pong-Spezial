package pongSpezial.dataModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

public class Sound 
{
	private HashMap<String, Media> sounds;										//HashMap with the name and the sound
	private Vector<MediaPlayer> mediaPlayers = new Vector<MediaPlayer>();		// Vector with MediaPlayers

	public void sound()
	{
		init();
	}

	public void startSound(String sound)
	{
		for (int i = 0; i < mediaPlayers.size(); i++)							// search sound in the vector mediaPlayers and start it
		{
			if(mediaPlayers.elementAt(i).equals(sound))
			{
				mediaPlayers.elementAt(i).play();
			}
		}		
	}

	public void stopSound(String sound)
	{
		for (int i = 0; i < mediaPlayers.size(); i++)							// search sound in the vector mediaPlayers and stop it
		{
			if(mediaPlayers.elementAt(i).equals(sound))
			{
				mediaPlayers.elementAt(i).stop();
			}
		}
	}
	
	public void startAll(String sound)
	{
		for (int i = 0; i < mediaPlayers.size(); i++)							// start all sounds
		{		
			mediaPlayers.elementAt(i).play();	
		}
	}
	
	public void stopAll(String sound)
	{
		for (int i = 0; i < mediaPlayers.size(); i++)							// stop all sounds
		{		
			mediaPlayers.elementAt(i).stop();	
		}
	}

	private void init()
	{
		//Create the Files
		Media background = new Media(new File("background.mp3").toURI().toString());
		Media barCollision = new Media(new File("barCollision.mp3").toURI().toString());
		Media powerUp = new Media(new File("powerUp.mp3").toURI().toString());
		Media click = new Media(new File("click.mp3").toURI().toString());
		Media countdown = new Media(new File("countdown.mp3").toURI().toString());
		Media win = new Media(new File("win.mp3").toURI().toString());
		Media lose = new Media(new File("lose.mp3").toURI().toString());

		//Add Files
		sounds.put("background", background);
		sounds.put("barCollision", barCollision);
		sounds.put("powerUp", powerUp);
		sounds.put("click", click);
		sounds.put("countdown", countdown);
		sounds.put("win", win);
		sounds.put("lose", lose);
		
		//Add MediaPlayer
		for (String sound: sounds.keySet())
		{
			mediaPlayers.add(new MediaPlayer(sounds.get(sound)));
		}
	}
}
