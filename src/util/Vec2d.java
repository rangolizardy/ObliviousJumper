package util;

public class Vec2d {
	public double x;
	public double y;

	public Vec2d() {
		this(0, 0);
	}

	public Vec2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vec2d normalize() {
		double magnitude = Math.sqrt(x * x + y * y);
		x /= magnitude;
		y /= magnitude;
		return this;
	}

	// As base operators + - * /

	public Vec2d add(Vec2d z) {
		return new Vec2d(x + z.x, y + z.y);
	}

	public Vec2d sub(Vec2d z) {
		return new Vec2d(x - z.x, y - z.y);
	}

	public Vec2d mult(double scale) {
		return new Vec2d(scale * x, scale * y);
	}

	public Vec2d div(double scale) {
		return new Vec2d(x / scale, y / scale);
	}

	// As equals operators += -= *= /=

	public void addEquals(Vec2d z) {
		x += z.x;
		y += z.y;
	}

	public void subEquals(Vec2d z) {
		x -= z.x;
		y -= z.y;
	}

	public void multEquals(double scale) {
		x *= scale;
		y *= scale;
	}

	public void divEquals(double scale) {
		x /= scale;
		y /= scale;
	}

}
