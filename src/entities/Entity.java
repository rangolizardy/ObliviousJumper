package src.entities;

import src.util.Vec2d;

public abstract class Entity {
	protected Vec2d position;
	
	public Entity(double x, double y) {
		this(new Vec2d(x, y));
	}
	
	public Entity(Vec2d pos) {
		position = pos;
	}
	
}
