import java.awt.Dimension;

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
	private int fpsTotal = 0;
	private int upsTotal = 0;
	private int fpsAvg = 0;
	private int upsAvg = 0;
	
	
	public Main() {
		//TODO init
		running = true; //Move as fits
	}
	
	public void run() {
		long timeOfLastTick = System.nanoTime();
		long timeOfLastRender = System.nanoTime();
		long timeofLastCounterUpdate = System.currentTimeMillis();
		int framesThisSecond = 0;
		int updatesThisSecond = 0;
		boolean shouldRender = false;
		originTime = System.currentTimeMillis();
		
		while(running) {
			long deltaTime = System.nanoTime() - timeOfLastRender;
			double delta = deltaTime / NS_PER_TICK;
			
			
			while(delta > 1) {
				//INPUT
				//UPDATE
				delta -= 1;
			}
			
			if(shouldRender) {
				//TODO rendering
			}
			
			if(System.currentTimeMillis() - 999 > timeofLastCounterUpdate) {
				timeOfLastCounterUpdate = System.currentTimeMillis();
				
				fps = framesThisSecond;
				fpsTotal += framesThisSecond;
				framesThisSecond = 0;
				ups = updatesThisSecond;
				upsTotal += updatesThisSecond;
				updatesThiSSecond = 0;
				
				fpsAvg = fpsTotal / ((timeOfLastCounterUpdate - originTime) / 1000);
				upsAvg = upsTotal / ((timeOfLastCounterUpdate - originTime) / 1000);
			}
			
			//Calculate sleep time, and sleep.
			//TODO: Shove into Thread or something.
		}
	}
	
	public long getTickCount() {
		return tickCount;
	}
	
	public static void Main(String[] args) {
		Main main = new Main();
	}
	
}