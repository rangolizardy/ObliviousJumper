package src.gfx;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import src.gfx.drawables.Drawable;

public class Screen extends Canvas {

	private static final long serialVersionUID = 1L;
	protected String name;
	protected int clearCol;
	protected BufferedImage img;
	protected int[] pixelData;

	public Screen(String name, int x, int y, int scale, int clearCol) {
		super();
		this.name = name;
		this.clearCol = clearCol;
		Dimension dim = new Dimension(x * scale, y * scale);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);
		img = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		pixelData = ((DataBufferInt) (img.getRaster().getDataBuffer())).getData();
	}

	public void draw(int position, int[] pixels) {
		if (position + pixels.length <= pixelData.length) {
			System.arraycopy(pixels, 0, pixelData, position, pixels.length);
		} else {
			throw new RuntimeException("Attempt to render caused pixel array overflow. ["
					+ (position + pixels.length) + "/" + pixelData.length + "]");
		}
	}

	public void draw(int x, int y, int[] pixels) {
		draw(x + y * img.getWidth(), pixels);
	}

	public void draw(Drawable drawable) {
		Graphics2D g2d = img.createGraphics();
		drawable.draw(g2d);
		g2d.dispose();
	}

	public void render(Graphics2D g2d) {
		//g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}

	public void clear() {
		for (int i = 0; i < pixelData.length; i++) {
			pixelData[i] = clearCol;
		}
	}
	
	public String getName() {
		return name;
	}

}
