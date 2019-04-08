package application;

import org.pdfsam.ui.*;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
public class Timer
{
	private AnchorPane AP;
	private RingProgressIndicator r;
	public Timer(AnchorPane AP)
	{
		r= new RingProgressIndicator();
		r.setRingWidth(10);
		r.setLayoutX(149);
		r.setLayoutY(0);
		//r.makeIndeterminate();
		AP.getChildren().add(r);
		this.AP=AP;
	}
	
	public void startTimer()
	{
		WorkerThread w = new WorkerThread(r);
		w.start();
	}
	public RingProgressIndicator getRingProgressIndicator()
	{
		return r;
	}
}
class WorkerThread extends Thread
{
	RingProgressIndicator rpi;
	int progress=100;
	AnchorPane AP;
	public WorkerThread(RingProgressIndicator rpi) 
	{
		this.rpi=rpi;
		this.rpi.setProgress(progress);
	}	
	@Override
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Platform.runLater(()->{
				rpi.setProgress(progress);
			});
			
			progress-=20;
			if(progress<0)
			{
				break;
			}
		}
	}
}	