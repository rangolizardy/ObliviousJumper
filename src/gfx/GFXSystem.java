package src.gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import src.entities.Mob;
import src.entities.ai.*;
import src.gfx.drawables.*;
import src.util.controls.*;

public class GFXSystem {
	private JFrame frame;
	private Screen[] screens;
	private int currentScreen;
	private int x, y;
	private int scale;
	private int scaleX, scaleY;
	private Dimension frameDim;

	public GFXSystem(int x, int y, int scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.scaleX = scale * x;
		this.scaleY = scale * y;
		this.frameDim = new Dimension(scaleX, scaleY);
	}

	public void init(String name) {
		frame = new JFrame(name);
		screens = new Screen[1];
		screens[0] = new Screen("MAIN", x, y, scale, 0xFF000000);
		this.currentScreen = 0;

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// frame.setUndecorated(true);

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(screens[currentScreen]);
		frame.pack();
		frame.setVisible(true);

		screens[currentScreen].requestFocus();
	}

	public void render() {
		BufferStrategy bs = screens[currentScreen].getBufferStrategy();
		if (bs == null) {
			System.out.println("Attempting to create BufferStrategy.");
			screens[currentScreen].createBufferStrategy(3);
			if (screens[currentScreen].getBufferStrategy() != null) {
				System.out.println("BufferStrategy created.");
			}
			return;
		}

		Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
		screens[currentScreen].render(g2d);

		g2d.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		GFXSystem gfx = new GFXSystem(400, 300, 1);
		gfx.init("Test");
		gfx.run();
	}

	Keyboard keyboard;
	Rect rect;

	// TODO: Remove. Existed only as testing.
	public void run() {
		List<Key> keys = new ArrayList<>(5);
		keys.add(new Key("RIGHT", KeyEvent.VK_D));
		keys.add(new Key("LEFT", KeyEvent.VK_A));
		keys.add(new Key("UP", KeyEvent.VK_W));
		keys.add(new Key("DOWN", KeyEvent.VK_S));
		keys.add(new Key("ESCAPE", KeyEvent.VK_ESCAPE));

		keyboard = new Keyboard(keys);
		keyboard.attach(screens[currentScreen]);

		rect = new Rect(10, 10, 20, 20, Color.WHITE);
		int x = 100;
		int y = 100;

		BufferedImage[] img1 = { new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB) };
		int[] pix1 = (((DataBufferInt) (img1[0].getRaster().getDataBuffer())).getData());
		for (int i = 0; i < pix1.length; i++) {
			pix1[i] = 0xFF_FF_FF_FF;
		}
		BufferedImage[] img2 = { new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB) };
		int[] pix2 = (((DataBufferInt) (img2[0].getRaster().getDataBuffer())).getData());
		for (int i = 0; i < pix2.length; i++) {
			pix2[i] = 0xFF_FF_00_00;
		}
		BufferedImage[] img3 = { new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB) };
		int[] pix3 = (((DataBufferInt) (img3[0].getRaster().getDataBuffer())).getData());
		for (int i = 0; i < pix3.length; i++) {
			pix3[i] = 0xFF_00_FF_00;
		}
		BufferedImage[] img4 = { new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB) };
		int[] pix4 = (((DataBufferInt) (img4[0].getRaster().getDataBuffer())).getData());
		for (int i = 0; i < pix4.length; i++) {
			pix4[i] = 0xFF_00_00_FF;
		}

		List<Animation> animations = new ArrayList<>(5);
		animations.add(new Animation("IDLE", true, img1, 6));
		animations.add(new Animation("WALK LEFT", true, img2, 6));
		animations.add(new Animation("WALK RIGHT", true, img3, 6));
		animations.add(new Animation("WALK UP", true, img4, 6));
		animations.add(new Animation("WALK DOWN", true, img4, 6));

		Sprite sprite = new Sprite("test", animations, 0);

		Mob mob = new Mob(200.0, 200.0, 0.0, 0.0, 1.0, sprite);
		mob.addAI(new DumbAI(mob, new Random()));
		
		while (true) {
			screens[currentScreen].clear();
			screens[currentScreen].draw(mob);
			render();

			// controls
			double x1 = 0.0;
			double y1 = 0.0;
			if (keyboard.getState("UP") == KeyState.HELD
					|| keyboard.getState("UP") == KeyState.DOWN) {
				y1 -= 1.0;
			}
			if (keyboard.getState("DOWN") == KeyState.HELD
					|| keyboard.getState("DOWN") == KeyState.DOWN) {
				y1 += 1.0;
			}
			if (keyboard.getState("LEFT") == KeyState.HELD
					|| keyboard.getState("LEFT") == KeyState.DOWN) {
				x1 -= 1.0;
			}
			if (keyboard.getState("RIGHT") == KeyState.HELD
					|| keyboard.getState("RIGHT") == KeyState.DOWN) {
				x1 += 1.0;
			}
			if (keyboard.getState("ESCAPE") == KeyState.HELD
					|| keyboard.getState("ESCAPE") == KeyState.DOWN) {
				System.exit(0);
			}
			x += x1;
			y += y1;

			mob.tick();
			
			try {
				Thread.sleep(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
