package pongSpezial.dataModel;

public class StopWatch 
{
	long timeStart;
	long timeStop;
	
	public StopWatch()
	{
		timeStart = 0;
		timeStop = 0;
	}
	
	public void start()
	{
		timeStart = System.currentTimeMillis();
	}
	
	public void stop()
	{
		timeStop = System.currentTimeMillis();
	}
		
	public void setTimeStart(long timeStart)
	{
		this.timeStart = timeStart;
	}
	
	public boolean startTimer(long runTime)
	{
		timeStop = runTime;
		boolean timerEnd = false;
		timeStart = System.currentTimeMillis();
		while(timerEnd==false)
		{
			if(timeStart >= runTime)
			{
				timerEnd = true;
				return timerEnd;
			}
		}
		return timerEnd;		
	}
	
	public long getTime()
	{
		return timeStop - timeStart;
	}
}
