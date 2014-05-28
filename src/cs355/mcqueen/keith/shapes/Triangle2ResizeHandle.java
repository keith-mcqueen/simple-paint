package cs355.mcqueen.keith.shapes;

import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.Transformations.transformPointFromShapeCoordinates;
import static cs355.mcqueen.keith.Transformations.transformPointToShapeCoordinates;
import static cs355.mcqueen.keith.shapes.Triangle2.calculateCenterPoint;

/**
 * New <code>Triangle2ResizeHandle</code> class.
 *
 * Created by keith on 5/27/14.
 */
public class Triangle2ResizeHandle extends ResizeHandle<Triangle2> {
	private final Triangle2.Points associatedPoint;

	public Triangle2ResizeHandle(Point location, Triangle2 triangle, Triangle2.Points p) {
		super(location, triangle);

		this.associatedPoint = p;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// no op
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// get the triangle
		Triangle2 triangle = this.getShape();

		// update the location of the point
		triangle.setPoint(this.associatedPoint, transformPointToShapeCoordinates(new Point(e.getX(), e.getY()), triangle));

		// recalculate the center
		Point a = transformPointFromShapeCoordinates(triangle.getPointA(), triangle);
		Point b = transformPointFromShapeCoordinates(triangle.getPointB(), triangle);
		Point c = transformPointFromShapeCoordinates(triangle.getPointC(), triangle);

		triangle.updateLocation(calculateCenterPoint(a,
				b, c));

		// notify listeners that the triangle has changed
		triangle.changed();
	}
}
