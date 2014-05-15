package cs355.mcqueen.keith.shapes;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * The {@code Line} class represents a line segment in some dimensional space.  The line is
 * defined by a start point and an end point.
 *
 * Created by keith on 5/2/14.
 */
public class Line extends Shape {
	public static final int SELECTION_MARGIN = 4;
	private double length;

	public Line(Point start, double length, double angle) {
		super(start);

		super.setRotation(angle);
		this.length = length;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	@Override
	public boolean contains(Point p, double scaleFactor) {
		return super.contains(p, scaleFactor);
	}

	@Override
	protected boolean doesContain(Point p, double scaleFactor) {
		double x = p.getCoordinate(X);
		return 0 <= x && x <= this.length && Math.abs(p.getCoordinate(Y)) <= SELECTION_MARGIN;
	}
}
