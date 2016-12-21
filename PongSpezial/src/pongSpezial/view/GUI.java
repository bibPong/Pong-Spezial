package pongSpezial.view;
import java.util.Dictionary;
import pongSpezial.dataModel.BoardState;

public class GUI
{
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
