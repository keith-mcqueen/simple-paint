package cs355.mcqueen.keith.shapes;

/**
 * The {@code Line} class represents a line segment in some dimensional space.  The line is
 * defined by a start point and an end point.
 *
 * Created by keith on 5/2/14.
 */
public class Line extends Shape {
	private Point startPoint;
	private Point endPoint;

	public Line(Point start, Point end) {
		this.startPoint = start;
		this.endPoint = end;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public String toString() {
		return String.format("Line: start: %s; end: %s", this.getStartPoint(), this.getEndPoint());
	}
}
