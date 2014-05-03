package cs355.mcqueen.keith.shapes;

/**
 * Created by keith on 5/3/14.
 */
public class Triangle extends Shape {
	private Point pointA;
	private Point pointB;
	private Point pointC;

	public Triangle(Point a, Point b, Point c) {
		this.pointA = a;
		this.pointB = b;
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
}
