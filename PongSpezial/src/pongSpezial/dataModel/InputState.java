package pongSpezial.dataModel;

import java.util.Dictionary;
import java.util.HashMap;

import javafx.scene.media.Media;

public class InputState {

	private HashMap<Player, Integer> currentInputs;
	
	public InputState()
	{
	
	}

	public HashMap<Player, Integer> getCurrentInputs()
	{
		return currentInputs;
	}

	public void setCurrentInputs(HashMap<Player, Integer> currentInputs)
	{
		this.currentInputs = currentInputs;
	}
	
	
}
