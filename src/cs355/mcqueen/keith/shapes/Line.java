package cs355.mcqueen.keith.shapes;

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
	protected boolean doesContain(double x, double y, double scaleFactor) {
		return 0 <= x && x <= this.length && Math.abs(y) <= SELECTION_MARGIN;
	}
}
