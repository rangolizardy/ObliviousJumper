package src.entities.ai;

import java.util.Random;

import src.entities.Mob;

public class DumbAI extends AI {

	private Random ran;
	private double x, y;

	public DumbAI(Mob mob, Random random) {
		super(mob);
		this.ran = random;
	}

	@Override
	public void plan() {
		//TODO: Adjust planning to fit final game.
		int t = ran.nextInt(3);
		switch (t) {
		case 0:
			x = 1.0;
			break;
		case 1:
			x = 0.0;
			break;
		case 2:
			x = -1.0;
		}
		t = ran.nextInt(3);
		switch (t) {
		case 0:
			y = 1.0;
			break;
		case 1:
			y = 0.0;
			break;
		case 2:
			y = -1.0;
		}
	}

	public void execute() {
		mob.setVelocity(x, y);
	}

}
