package pongSpezial.dataModel;

public class Sound 
{
	private Dictionary<String, Media> sounds;

	public void sound()
	{
		init();
	}

	public void startSound(String sound)
	{
	}

	public void stopSound(String sound)
	{
	}

	private void init()
	{
		Media background = new Media(new File("background.mp3").toURI().toString());
		Media barCollision = new Media(new File("barCollision.mp3").toURI().toString());
		Media powerUp = new Media(new File("powerUp.mp3").toURI().toString());
		Media click = new Media(new File("click.mp3").toURI().toString());
		Media countdown = new Media(new File("countdown.mp3").toURI().toString());
		Media win = new Media(new File("win.mp3").toURI().toString());
		Media lose = new Media(new File("lose.mp3").toURI().toString());

		sounds.put("background", background);
		sounds.put("barCollision", barCollision);
		sounds.put("powerUp", powerUp);
		sounds.put("click", click);
		sounds.put("countdown", countdown);
		sounds.put("win", win);
		sounds.put("lose", lose);
	}
}
