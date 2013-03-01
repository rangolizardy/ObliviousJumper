package src.entities.ai;

import src.entities.Mob;

public abstract class AI {

	protected Mob mob;
	//TODO: take in as parameter something that allows it to observe the world.
	
	protected int tickCount;
	
	public AI(Mob mob) {
		this.mob = mob;
	}
	
	public abstract void plan();
	public abstract void execute();
}
