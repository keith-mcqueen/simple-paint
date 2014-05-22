package cs355.mcqueen.keith.shapes;

import cs355.mcqueen.keith.shapes.tools.Handle;

import java.awt.event.MouseEvent;

import static cs355.GUIFunctions.refresh;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * The <code>RotateHandle</code> is a specialized ellipse that is used to draw handles
 * for rotating another shape.
 * <p>
 * Created by keith on 5/14/14.
 */
public class RotateHandle extends Ellipse implements Handle {
	private final Shape shapeToRotate;
	private Point oldLoc;

	public RotateHandle(Point location, Size size, Shape shape) {
		super(location, size);

		this.shapeToRotate = shape;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.oldLoc = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point newLoc = new Point(e.getX(), e.getY());

		Point vecA = this.shapeToRotate.transformPointToShape(this.oldLoc);
		Point vecB = this.shapeToRotate.transformPointToShape(newLoc);

		double a_dot_b = dot(vecA, vecB);
		double a_cross_b = cross(vecA, vecB);
		double theta = Math.atan2(a_cross_b, a_dot_b);

		this.shapeToRotate.setRotation(this.shapeToRotate.getRotation() + theta);
		this.shapeToRotate.changed();

		// save the new location for next time to do a differential angle measurement
		this.oldLoc = newLoc;

		refresh();
	}

	private static double dot(Point vecA, Point vecB) {
		double a_x = vecA.getCoordinate(X);
		double a_y = vecA.getCoordinate(Y);
		double b_x = vecB.getCoordinate(X);
		double b_y = vecB.getCoordinate(Y);

		return (a_x * b_x) + (a_y * b_y);
	}

	private static double cross(Point vecA, Point vecB) {
		double a_x = vecA.getCoordinate(X);
		double a_y = vecA.getCoordinate(Y);
		double b_x = vecB.getCoordinate(X);
		double b_y = vecB.getCoordinate(Y);

		return (a_x * b_y) - (a_y * b_x);
	}
}
