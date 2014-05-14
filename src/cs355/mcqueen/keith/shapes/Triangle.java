package cs355.mcqueen.keith.shapes;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * The <code>Triangle</code> class represents a 3-sided figure in 2-dimensional space.
 *
 * Created by keith on 5/3/14.
 */
public class Triangle extends Shape {
	private Point pointA;
	private Point pointB;
	private Point pointC;

	public Triangle(Point center, Point a, Point b, Point c) {
		super(center);

		// determine if points are clockwise or counterclockwise
		if (dotProduct(a, b, c) < 0) {
			this.pointA = b;
			this.pointB = a;
		} else {
			this.pointA = a;
			this.pointB = b;
		}

		this.pointC = c;
	}

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	@Override
	protected boolean doesContain(double x, double y, double scaleFactor) {
		Point loc = new Point(x, y);

		Point a = this.getPointA();
		Point b = this.getPointB();
		Point c = this.getPointC();

		return dotProduct(loc, a, b) > 0
				&& dotProduct(loc, b, c) > 0
				&& dotProduct(loc, c, a) > 0;
	}

	private static double dotProduct(Point a, Point b, Point c) {
		double result = (b.getCoordinate(X) - a.getCoordinate(X)) * (c.getCoordinate(Y) - a.getCoordinate(Y)) -
				(b.getCoordinate(Y) - a.getCoordinate(Y)) * (c.getCoordinate(X) - a.getCoordinate(X));

		return result;
	}
}
