package src.gfx.drawables;

import java.awt.Color;
import java.awt.Graphics2D;


public class Rect implements Drawable {

	public int x;
	public int y;
	public int w;
	public int h;
	public Color col;

	public Rect(int x, int y, int w, int h, Color col) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.col = col;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(col);
		g2d.fillRect(x, y, w, h);
	}

}
