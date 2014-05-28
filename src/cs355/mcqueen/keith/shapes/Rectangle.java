package cs355.mcqueen.keith.shapes;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.Size.HEIGHT;
import static cs355.mcqueen.keith.shapes.Size.WIDTH;
import static java.lang.Math.abs;

/**
 * The <code>Rectangle</code> class represents a right parallelogram.
 *
 * Created by keith on 5/3/14.
 */
public class Rectangle extends Shape {
	private Size size;

	public Rectangle(Point location, Size size) {
		super(location);

		this.size = size;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;

		this.changed();
	}

	@Override
	protected boolean doesContain(Point p) {
		Size mySize = this.getSize();
		double halfWidth = mySize.getLength(WIDTH) / 2.0d;
		double halfHeight = mySize.getLength(HEIGHT) / 2.0d;

		return abs(p.getCoordinate(X)) <= halfWidth && abs(p.getCoordinate(Y)) <= halfHeight;
	}
}
