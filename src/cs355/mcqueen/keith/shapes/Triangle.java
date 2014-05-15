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

	public Point getPointB() {
		return pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
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
}
