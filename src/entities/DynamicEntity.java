package src.entities;

import src.util.Vec2d;

public class DynamicEntity extends Entity {

	protected Vec2d velocity;
	protected double speed;
	
	public DynamicEntity(double x, double y, double vx, double vy, double speed) {
		super(x, y);
		this(new vec2d(vx, vy), speed;
	}

	public DynamicEntity(Vec2d pos, Vec2d vel, double speed) {
		super(pos);
		velocity = vel;
		this.speed = speed;
		vel.normalize().mult(speed);
	}
	
	public void move() {
		position.addEquals(velocity);
	}

}
