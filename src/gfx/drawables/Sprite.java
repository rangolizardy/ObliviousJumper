package src.gfx.drawables;

import java.awt.Graphics2D;
import java.util.List;

import src.Tickable;
import src.util.Vec2d;

public class Sprite implements Tickable {

	public final String name;
	private List<Animation> animations;
	private int currentAnimation;

	public Sprite(String name, List<Animation> animations, int currentAnimation) {
		this.name = name;
		this.animations = animations;
		this.currentAnimation = currentAnimation;
	}

	@Override
	public void tick() {
		animations.get(currentAnimation).tick();
	}

	public boolean setAnimation(String name) {
		for (int i = 0; i < animations.size(); i++) {
			if (animations.get(i).name.equalsIgnoreCase(name)) {
				animations.get(currentAnimation).stop();
				animations.get(i).start();
				currentAnimation = i;
				return true;
			}
		}
		return false;
	}
	
	public void draw(Graphics2D g2d, Vec2d pos) {
		animations.get(currentAnimation).draw(g2d, pos);
	}

}
