package cs355.mcqueen.keith.shapes;

import cs355.mcqueen.keith.shapes.tools.Handle;

import java.awt.event.MouseEvent;

import static cs355.GUIFunctions.refresh;
import static cs355.mcqueen.keith.Transformations.*;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * The <code>RotateHandle</code> is a specialized ellipse that is used to draw handles
 * for rotating another shape.
 * <p>
 * Created by keith on 5/14/14.
 */
public class RotateHandle extends Ellipse implements Handle {
	public static final int SIZE = 12;

	private final Shape shapeToRotate;
	private Point oldLoc;

	public RotateHandle(Point location, Shape shape) {
		super(location, new Size(SIZE, SIZE));

		this.shapeToRotate = shape;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.oldLoc = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point newLoc = new Point(e.getX(), e.getY());

		Point vecA = transformPoint(viewToShape(this.shapeToRotate), this.oldLoc);
		Point vecB = transformPoint(viewToShape(this.shapeToRotate), newLoc);

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

	@Override
	public boolean contains(Point point) {
		Point handleLoc = transformPoint(worldToView(), this.getLocation());

		double delta_x = point.getCoordinate(X) - handleLoc.getCoordinate(X);
		double delta_y = point.getCoordinate(Y) - handleLoc.getCoordinate(Y);

		return Math.abs(delta_x) <= SIZE && Math.abs(delta_y) <= SIZE;
	}
}
