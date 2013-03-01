package src.util.controls;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import src.Tickable;

public class Keyboard implements Tickable, KeyListener {

	private List<Key> keys;
	private List<Component> attachedTo;
	
	public Keyboard(List<Key> keys) {
		this.keys = keys;
		attachedTo = new LinkedList<>();
	}

	public void attach(Component component) {
		component.addKeyListener(this);
		attachedTo.add(component);
	}

	public boolean detach(Component component) {
		for (Component comp : attachedTo) {
			if (component.equals(comp)) {
				comp.removeKeyListener(this);
				attachedTo.remove(comp);
				return true;
			}
		}
		return false;
	}

	public void detach() {
		for (Component comp : attachedTo) {
			comp.removeKeyListener(this);
		}
		attachedTo.clear();
	}

	public void tick() {
		for (Key key : keys) {
			key.tick();
		}
	}

	public KeyState getState(String keyName) {
		for (Key key : keys) {
			if (keyName.equalsIgnoreCase(key.name)) {
				return key.state;
			}
		}
		return null;
	}

	public Key getKey(String keyName) {
		for (Key key : keys) {
			if (key.name.equalsIgnoreCase(keyName)) {
				return key;
			}
		}
		return null;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for (Key key : keys) {
			if (e.getKeyCode() == key.keycode) {
				key.press();
				return;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (Key key : keys) {
			if (e.getKeyCode() == key.keycode) {
				key.release();
				return;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Do nothing, cause I don't even really.
	}
}
