package src.entities;

import src.entities.ai.AI;
import src.gfx.drawables.Sprite;
import src.util.Vec2d;

public class Mob extends DynamicEntity {
	
	protected AI ai;

	public Mob(double x, double y, double vx, double vy, double speed, Sprite sprite) {
		this(new Vec2d(x, y), new Vec2d(vx, vy), speed, sprite);
	}

	public Mob(Vec2d pos, Vec2d vel, double speed, Sprite sprite) {
		super(pos, vel, speed, sprite);
	}
	
	public void addAI(AI ai) {
		if (this.ai == null) {
			this.ai = ai;
		}
	}
	
	//TODO: Remove this
	public void tick() {
		super.tick();
		ai.plan();
		ai.execute();
	}
	
}
