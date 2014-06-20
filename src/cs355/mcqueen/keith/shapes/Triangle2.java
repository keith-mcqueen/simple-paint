package cs355.mcqueen.keith.shapes;

import static cs355.mcqueen.keith.Transformations.*;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * Another attempt at triangles, this time with the vertices stored relative
 * to the center.
 *
 * Created by keith on 5/27/14.
 */
public class Triangle2 extends Shape {
	public static enum Points {
		A, B, C
	}

	private Point[] points = new Point[3];

	public Triangle2(Point a, Point b, Point c) {
		super(calculateCenterPoint(a, b, c));

		Point center = this.getLocation();
		double center_x = center.getCoordinate(X);
		double center_y = center.getCoordinate(Y);

		this.setPointA(new Point(a.getCoordinate(X) - center_x,	a.getCoordinate(Y) - center_y));
		this.setPointB(new Point(b.getCoordinate(X) - center_x,	b.getCoordinate(Y) - center_y));
		this.setPointC(new Point(c.getCoordinate(X) - center_x,	c.getCoordinate(Y) - center_y));
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

	public void updateLocation(Point location) {
		// transform the vertices to world space
		Point a = transformPoint(shapeToWorld(this), this.getPointA());
		Point b = transformPoint(shapeToWorld(this), this.getPointB());
		Point c = transformPoint(shapeToWorld(this), this.getPointC());

		// set the new location
		super.setLocation(location);

		// transform the vertices back to shape space
		this.setPointA(transformPoint(worldToShape(this), a));
		this.setPointB(transformPoint(worldToShape(this), b));
		this.setPointC(transformPoint(worldToShape(this), c));
	}

	@Override
	protected boolean doesContain(Point p) {
		Point a = this.getPointA();
		Point b = this.getPointB();
		Point c = this.getPointC();

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
