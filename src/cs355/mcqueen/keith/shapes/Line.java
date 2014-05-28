package cs355.mcqueen.keith.shapes;

import static cs355.mcqueen.keith.Transformations.*;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static java.lang.Math.abs;

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

	public Point getEnd() {
		Point start = transformPoint(worldToShape(this), this.getLocation());
		Point end = new Point(start.getCoordinate(X) + this.getLength(), 0);

		return transformPoint(shapeToWorld(this), end);
	}

	@Override
	protected boolean doesContain(Point p) {
		double x = p.getCoordinate(X);
		double y = p.getCoordinate(Y);

		return 0 <= x && x <= this.length &&
				abs(y) <= SELECTION_MARGIN / getZoomFactor();
	}
}
