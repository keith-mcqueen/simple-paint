package cs355.mcqueen.keith.shapes;

import static cs355.mcqueen.keith.Transformations.transformPointFromShapeCoordinates;
import static cs355.mcqueen.keith.Transformations.transformPointToShapeCoordinates;
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
		Point a = transformPointToShapeCoordinates(this.getPointA(), this);
		Point b = transformPointToShapeCoordinates(this.getPointB(), this);
		Point c = transformPointToShapeCoordinates(this.getPointC(), this);

		// set the location
		super.setLocation(location);

		// transform the vertex points back to world space
		this.setPointA(transformPointFromShapeCoordinates(a, this));
		this.setPointB(transformPointFromShapeCoordinates(b, this));
		this.setPointC(transformPointFromShapeCoordinates(c, this));
	}

	public void updateLocation(Point location) {
		super.setLocation(location);
	}

	@Override
	public void setRotation(double rotation) {
		// transform the vertex points to the shape space
		Point a = transformPointToShapeCoordinates(this.getPointA(), this);
		Point b = transformPointToShapeCoordinates(this.getPointB(), this);
		Point c = transformPointToShapeCoordinates(this.getPointC(), this);

		// set the rotation
		super.setRotation(rotation);

		// transform the vertex points back to world space
		this.setPointA(transformPointFromShapeCoordinates(a, this));
		this.setPointB(transformPointFromShapeCoordinates(b, this));
		this.setPointC(transformPointFromShapeCoordinates(c, this));
	}

	@Override
	protected boolean doesContain(Point p, double scaleFactor) {
		Point a = transformPointToShapeCoordinates(this.getPointA(), this);
		Point b = transformPointToShapeCoordinates(this.getPointB(), this);
		Point c = transformPointToShapeCoordinates(this.getPointC(), this);

		double dot_a_b = dotProduct(p, a, b);
		double dot_b_c = dotProduct(p, b, c);
		double dot_c_a = dotProduct(p, c, a);

		return (dot_a_b > 0 && dot_b_c > 0 && dot_c_a > 0) || (0 > dot_a_b && 0 > dot_b_c && 0 > dot_c_a);
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
