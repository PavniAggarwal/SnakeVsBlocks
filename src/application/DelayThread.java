package application;

public class DelayThread extends Thread{
	int time;
	public DelayThread(int time)
	{
		this.time=time;
	}
	public void run()
	{
		try {
			Thread.sleep((time*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
