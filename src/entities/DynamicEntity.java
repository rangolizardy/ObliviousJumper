package src.entities;

import src.gfx.drawables.Sprite;
import src.util.Vec2d;

public abstract class DynamicEntity extends Entity {

	protected Vec2d velocity;
	protected double speed;
	
	public DynamicEntity(double x, double y, double vx, double vy, double speed, Sprite sprite) {
		this(new Vec2d(x, y), new Vec2d(vx, vy), speed, sprite);
	}

	public DynamicEntity(Vec2d pos, Vec2d vel, double speed, Sprite sprite) {
		super(pos, sprite);
		velocity = vel;
		this.speed = speed;
		vel.normalize().mult(speed);
	}
	
	public void move() {
		position.addEquals(velocity);
	}
	
	public void setVelocity(double x, double y) {
		this.velocity.x = x;
		this.velocity.y = y;
		this.velocity.normalize();
	}
	
	public void tick() {
		super.tick();
		move();
	}

}
