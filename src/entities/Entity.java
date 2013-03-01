package src.entities;

import java.awt.Graphics2D;

import src.Tickable;
import src.gfx.drawables.Drawable;
import src.gfx.drawables.Sprite;
import src.util.Vec2d;

public abstract class Entity implements Drawable, Tickable{
	protected Vec2d position;
	protected Sprite sprite;
	protected int tickCount;
	
	public Entity(double x, double y, Sprite sprite) {
		this(new Vec2d(x, y), sprite);
	}
	
	public Entity(Vec2d pos, Sprite sprite) {
		this.position = pos;
		this.sprite = sprite;
	}
	
	public void draw(Graphics2D g2d) {
		sprite.draw(g2d, position);
	}
	
	public void tick() {
		tickCount++;
	}
	
}
