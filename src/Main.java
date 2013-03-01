package src;
import java.awt.Dimension;

import src.util.Log;
import src.util.IO.*;

//TODO: Shove into Thread or something.

public class Main{

	public static final int DISPLAY_WIDTH = 640;
	public static final int DISPLAY_HEIGHT = 480;
	public static final Dimension DISPLAY_DIM = new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT);
	public static final int TICKS_PER_SECOND = 60;
	public static final double NS_PER_TICK = 1_000_000_000 / TICKS_PER_SECOND;
	
	private boolean running;
	private long originTime;
	private long tickCount = 0;
	private int fps = 0;
	private int ups = 0;
	private int ips = 0;
	private long fpsTotal = 0;
	private long upsTotal = 0;
	private long ipsTotal = 0;
	private int fpsAvg = 0;
	private int upsAvg = 0;
	private int ipsAvg = 0;
	
	
    IOQueue IQueue;
    Log Logger;
	public Main() {
		//TODO init
		running = true; //Move as fits
		
		
		//Put modules here?
		
		
		this.IQueue = new IOQueue();
		
		this.Logger = new Log(); //First indentifier in .log files.
		this.Logger.setLevel("SEVERE"); //Only let severe's pass.
		//Run this, ^Todo at the top.
		run();
		
	}
	
	public void run() {
		long timeOfLastTick = System.nanoTime();
		long timeOfLastRender = System.nanoTime();
		long timeOfLastCounterUpdate = System.currentTimeMillis();
		int framesThisSecond = 0;
		int updatesThisSecond = 0;
		int idleThisSecond = 0;
		boolean shouldRender = false;
		originTime = System.currentTimeMillis();
		
		while(running) {
			long deltaTime = System.nanoTime() - timeOfLastRender;
			double delta = deltaTime / NS_PER_TICK;
			
			while(delta > 1) {
				//INPUT
				//UPDATE
				delta -= 1;
				shouldRender = true;
			}

			
			if(shouldRender) {
				//TODO rendering
				
				//I put test's here for now, Nice refresh rate :)
				System.out.println("Main Thread,Childstatus:"+IQueue.status());
			}
			
			if(System.currentTimeMillis() - 999 > timeOfLastCounterUpdate) {
				timeOfLastCounterUpdate = System.currentTimeMillis();
				
				fps = framesThisSecond;
				fpsTotal += framesThisSecond;
				framesThisSecond = 0;
				
				ups = updatesThisSecond;
				upsTotal += updatesThisSecond;
				updatesThisSecond = 0;
				
				ips = idleThisSecond;
				ipsTotal += idleThisSecond;
				idleThisSecond = 0;
				
				long seconds = (timeOfLastCounterUpdate - originTime) / 1000;
				fpsAvg = (int) (fpsTotal / seconds);
				upsAvg = (int) (upsTotal / seconds);
				ipsAvg = (int) (ipsTotal / seconds);
			}
			
			try {
				long sleepTime = (long) ((1.0 - delta) * NS_PER_TICK / 1000);
				idleThisSecond += sleepTime;
				Thread.sleep(sleepTime);
			} catch(Exception e) { //Specify which later. Don't remember off hand.
				Logger.getLogger().severe(e.getMessage());
			}
		}
	}
	
	public long getTickCount() {
		return tickCount;
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}
	
}