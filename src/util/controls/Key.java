package src.util.controls;

import src.Tickable;

public class Key implements Tickable {
	protected String name;
	protected int keycode;
	protected KeyState state;
	protected int numPresses;
	protected int ticksHeld;
	protected int totalTicksHeld;
	protected int ticksSinceLastPress;
	protected int ticksSinceLastDown;

	public Key(String name, int keycode) {
		this.name = name;
		this.keycode = keycode;
		numPresses = 0;
		ticksHeld = 0;
		totalTicksHeld = 0;
		ticksSinceLastPress = 0;
		ticksSinceLastDown = 0;
		state = KeyState.UP;
	}

	public void press() {
		state = KeyState.DOWN;
		numPresses++;
		ticksSinceLastPress = 0;
	}

	public void release() {
		state = KeyState.UP;
		ticksSinceLastDown = 0;
	}

	public void tick() {
		switch (state) {
		case UP:
			ticksSinceLastPress++;
			ticksSinceLastDown++;
		case DOWN:
			if (ticksHeld > 0) {
				state = KeyState.HELD;
			}
		case HELD:
			ticksHeld++;
			totalTicksHeld++;
			ticksSinceLastPress++;
			break;
		}
	}

	public String getName() {
		return name;
	}

	public int getKeycode() {
		return keycode;
	}
	
	public void setKeycode(int keycode) {
		this.keycode = keycode;
	}

	public KeyState getState() {
		return state;
	}

	public int getNumPresses() {
		return numPresses;
	}

	public int getTicksHeld() {
		return ticksHeld;
	}

	public int getTotalTicksHeld() {
		return totalTicksHeld;
	}

	public int getTicksSinceLastPress() {
		return ticksSinceLastPress;
	}

	public int getTicksSinceLastDown() {
		return ticksSinceLastDown;
	}

}