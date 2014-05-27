package cs355.mcqueen.keith.shapes;

import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.shapes.Triangle.calculateCenterPoint;

/**
 * The <code>TriangleResizeHandle</code> class resizes a {@link Triangle}.
 * <p>
 * Created by keith on 5/21/14.
 */
public class TriangleResizeHandle extends ResizeHandle<Triangle> {
	private final Triangle.Points associatedPoint;

	public TriangleResizeHandle(Point location, Triangle shape, Triangle.Points point) {
		super(location, shape);

		this.associatedPoint = point;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// get the triangle
		Triangle triangle = this.getShape();

		// update the location of the point
		triangle.setPoint(this.associatedPoint, new Point(e.getX(), e.getY()));

		// recalculate the center
		triangle.updateLocation(calculateCenterPoint(triangle.getPointA(),
				triangle.getPointB(), triangle.getPointC()));

		// notify listeners that the triangle has changed
		triangle.changed();
	}
}
