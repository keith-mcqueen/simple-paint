package cs355.mcqueen.keith;

import cs355.GUIFunctions;
import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Shape;
import cs355.solution.CS355;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import static cs355.GUIFunctions.setHScrollBarKnob;
import static cs355.GUIFunctions.setVScrollBarKnob;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.solution.CS355.VIEWPORT_WIDTH;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Contains utility methods for performing transformations.
 * <p>
 * Created by keith on 5/23/14.
 */
public class Transformations {
	/**
	 * Transform the given point to the coordinate space of the given shape.
	 *
	 * @param p     the point to be transformed
	 * @param shape the shape to whose space the point is to be transformed
	 *
	 * @return a new point in the same location relative to the space defined by the shape
	 */
	public static Point transformPointToShapeCoordinates(Point p, Shape shape) {
		//AffineTransform w2s = getWorldToShapeTransform(shape);
		AffineTransform w2s = getViewToShapeTransform(shape);

		Point2D pWorld = new Point2D.Double(p.getCoordinate(X), p.getCoordinate(Y));
		Point2D pShape = new Point2D.Double();

		w2s.transform(pWorld, pShape);

		return new Point(pShape.getX(), pShape.getY());
	}

	/**
	 * Transform the given point from the coordinate space of the given shape
	 *
	 * @param p     the point to be transformed
	 * @param shape the shape from whose coordinate space the point is to be transformed
	 *
	 * @return a new point in the same location relative to the space containing the shape
	 */
	public static Point transformPointFromShapeCoordinates(Point p, Shape shape) {
		//AffineTransform w2s = getShapeToWorldTransform(shape);
		AffineTransform w2s = getShapeToViewTransform(shape);

		Point2D pShape = new Point2D.Double(p.getCoordinate(X), p.getCoordinate(Y));
		Point2D pWorld = new Point2D.Double();

		w2s.transform(pShape, pWorld);

		return new Point(pWorld.getX(), pWorld.getY());
	}

	public static AffineTransform getViewToShapeTransform(Shape shape) {
		AffineTransform transform = new AffineTransform();
		transform.concatenate(getViewToWorldTransform());
		transform.concatenate(getWorldToShapeTransform(shape));

		return transform;
	}

	public static AffineTransform getShapeToViewTransform(Shape shape){
		AffineTransform transform = new AffineTransform();
		transform.concatenate(getShapeToWorldTransform(shape));
		transform.concatenate(getWorldToViewTransform());

		return transform;
	}

	private static AffineTransform getWorldToShapeTransform(Shape shape) {
		Point shapeLoc = shape.getLocation();
		double x = shapeLoc.getCoordinate(X);
		double y = shapeLoc.getCoordinate(Y);

		double theta = shape.getRotation();
		double cos_theta = cos(theta);
		double sin_theta = sin(theta);

		return new AffineTransform(              cos_theta,                        -sin_theta,
				                                     sin_theta,                         cos_theta,
				                       (-cos_theta * x) - (sin_theta * y), (sin_theta * x) - (cos_theta * y));
	}

	private static AffineTransform getShapeToWorldTransform(Shape shape) {
		Point shapeLoc = shape.getLocation();
		double x = shapeLoc.getCoordinate(X);
		double y = shapeLoc.getCoordinate(Y);

		double theta = shape.getRotation();
		double cos_theta = cos(theta);
		double sin_theta = sin(theta);

		return new AffineTransform( cos_theta, sin_theta,
				                       -sin_theta, cos_theta,
				                            x,         y);
	}

	private static AffineTransform getViewToWorldTransform() {
		return new AffineTransform();
	}

	private static  AffineTransform getWorldToViewTransform() {
		return new AffineTransform();
	}

	// Zooming
	private static double zoomFactor = CS355.ZOOM_DEFAULT;

	public static double getZoomFactor() {
		return zoomFactor;
	}

	public static void setZoomFactor(double zoomFactor) {
		Transformations.zoomFactor = zoomFactor;

		// changing the zoom should change the knob size on the scroll bars
		setHScrollBarKnob((int) (VIEWPORT_WIDTH / zoomFactor));
		setVScrollBarKnob((int) (VIEWPORT_WIDTH / zoomFactor));

		// does it change their position?

		// refresh the UI
		GUIFunctions.refresh();
	}
}
