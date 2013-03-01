package src.gfx.drawables;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import src.Tickable;
import src.util.Vec2d;

public class Animation implements Tickable {

	public final String name;
	private boolean looping;
	private BufferedImage[] frames;
	private int currentFrame;
	private int ticksPerFrame;
	private int ticksSinceStart;

	public Animation(String name, boolean looping, BufferedImage[] frames, int ticksPerFrame) {
		this.name = name;
		this.looping = looping;
		this.frames = frames;
		this.ticksPerFrame = ticksPerFrame;
	}

	public void start() {
		ticksSinceStart = 0;
		currentFrame = 0;
	}

	public void stop() {
		// ?
	}

	public void tick() {
		ticksSinceStart++;
		if (looping) {
			currentFrame = (ticksSinceStart / ticksPerFrame - 1) % frames.length;
		} else {
			currentFrame = Math.min(ticksSinceStart / ticksPerFrame - 1, frames.length - 1);
		}
	}

	public void draw(Graphics2D g2d, Vec2d pos) {
		g2d.drawImage(frames[currentFrame], (int) pos.x, (int) pos.y, null);
	}

	public boolean isLooping() {
		return looping;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}
	
	

}
