package src.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class SpriteSheet {

	// TODO: Figure out what package this falls under if not here

	private final BufferedImage sheet;
	private final int[] pixels;
	public final int tileWidth;
	public final int tileHeight;
	private final int tilesX;
	private final int tilesY;
	private final int overflowX;
	private final int overflowY;

	//TODO: test all the things
	
	public SpriteSheet(BufferedImage sheet, int tileWidth, int tileHeight) {
		this.sheet = sheet;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;

		this.tilesX = this.sheet.getWidth() / tileWidth;
		this.overflowX = this.sheet.getWidth() % tileWidth;
		this.tilesY = this.sheet.getHeight() / tileHeight;
		this.overflowY = this.sheet.getHeight() % tileHeight;

		this.pixels = ((DataBufferInt) this.sheet.getRaster().getDataBuffer()).getData();
	}

	public BufferedImage get(int x, int y) {
		return this.sheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
	}

	public BufferedImage get(int pos) {
		return get(pos % tilesX, pos / tilesX);
	}

	public int[] getPix(int x, int y) {

		if (overflowX > 0 || overflowY > 0) {
			// TODO: Pixel array parsing when sheet size is not an integer
			// multiple of tile size.
			return null;
		} else {
			int[] pix = new int[tileWidth * tileHeight];
			int yCap = y * tileHeight + tileHeight;
			for (int ypix = y * tileHeight; ypix < yCap; y++) {
				int xCap = x * tileWidth + tileWidth;
				for (int xpix = x * tileWidth; xpix < xCap; xpix++) {
					int pos = ypix - y*tileHeight;
					pos *= tileWidth;
					pos += (xpix - x * tileWidth);
					pix[pos] = pixels[ypix * (sheet.getWidth()) + xpix];
				}
			}
			return pix;
		}
	}

	public int[] getPix(int pos) {
		return getPix(pos % tilesX, pos / tilesX);
	}

}
