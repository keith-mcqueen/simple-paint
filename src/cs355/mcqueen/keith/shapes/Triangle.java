package cs355.mcqueen.keith.shapes;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * The <code>Triangle</code> class represents a 3-sided figure in 2-dimensional space.
 *
 * Created by keith on 5/3/14.
 */
public class Triangle extends Shape {
	public static enum Points {
		A, B, C
	}

	private Point[] points = new Point[3];

	public Triangle(Point a, Point b, Point c) {
		super(calculateCenterPoint(a, b, c));

		// determine if points are clockwise or counterclockwise
		if (dotProduct(a, b, c) < 0) {
			this.points[Points.A.ordinal()] = b;
			this.points[Points.B.ordinal()] = a;
		} else {
			this.points[Points.A.ordinal()] = a;
			this.points[Points.B.ordinal()] = b;
		}

		this.setPointC(c);
	}

	public Point getPointA() {
		return this.getPoint(Points.A);
	}

	public Point getPointB() {
		return this.getPoint(Points.B);
	}

	public Point getPointC() {
		return this.getPoint(Points.C);
	}

	public Point getPoint(Points point) {
		return this.points[point.ordinal()];
	}

	public void setPointA(Point pointA) {
		this.setPoint(Points.A, pointA);
	}

	public void setPointB(Point pointB) {
		this.setPoint(Points.B, pointB);
	}

	public void setPointC(Point pointC) {
		this.setPoint(Points.C, pointC);
	}

	public void setPoint(Points point, Point p) {
		this.points[point.ordinal()] = p;
	}

	@Override
	public void setLocation(Point location) {
		// transform the vertex points to the shape space
		Point a = this.transformPointToShape(this.getPointA());
		Point b = this.transformPointToShape(this.getPointB());
		Point c = this.transformPointToShape(this.getPointC());

		// set the location
		super.setLocation(location);

		// transform the vertex points back to world space
		this.setPointA(this.transformPointToWorld(a));
		this.setPointB(this.transformPointToWorld(b));
		this.setPointC(this.transformPointToWorld(c));
	}

	public void updateLocation(Point location) {
		super.setLocation(location);
	}

	@Override
	public void setRotation(double rotation) {
		// transform the vertex points to the shape space
		Point a = this.transformPointToShape(this.getPointA());
		Point b = this.transformPointToShape(this.getPointB());
		Point c = this.transformPointToShape(this.getPointC());

		// set the rotation
		super.setRotation(rotation);

		// transform the vertex points back to world space
		this.setPointA(this.transformPointToWorld(a));
		this.setPointB(this.transformPointToWorld(b));
		this.setPointC(this.transformPointToWorld(c));
	}

	@Override
	protected boolean doesContain(Point p, double scaleFactor) {
		Point a = this.transformPointToShape(this.getPointA());
		Point b = this.transformPointToShape(this.getPointB());
		Point c = this.transformPointToShape(this.getPointC());

		return dotProduct(p, a, b) > 0
				&& dotProduct(p, b, c) > 0
				&& dotProduct(p, c, a) > 0;
	}

	private static double dotProduct(Point a, Point b, Point c) {
		return (b.getCoordinate(X) - a.getCoordinate(X)) * (c.getCoordinate(Y) - a.getCoordinate(Y)) -
				(b.getCoordinate(Y) - a.getCoordinate(Y)) * (c.getCoordinate(X) - a.getCoordinate(X));
	}

	public static Point calculateCenterPoint(Point a, Point b, Point c) {
		return new Point((a.getCoordinate(X) + b.getCoordinate(X) + c.getCoordinate(X)) / 3.0d,
				(a.getCoordinate(Y) + b.getCoordinate(Y) + c.getCoordinate(Y)) / 3.0d);
	}
}
