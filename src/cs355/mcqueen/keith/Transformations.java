package cs355.mcqueen.keith;

import cs355.GUIFunctions;
import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.SelectedShape;
import cs355.mcqueen.keith.shapes.Shape;
import cs355.mcqueen.keith.shapes.Shapes;
import cs355.solution.CS355;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import static cs355.GUIFunctions.setHScrollBarKnob;
import static cs355.GUIFunctions.setVScrollBarKnob;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.solution.CS355.SCROLL_BAR_MIN;
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
		AffineTransform w2s = getShapeToViewTransform(shape);

		Point2D pShape = new Point2D.Double(p.getCoordinate(X), p.getCoordinate(Y));
		Point2D pWorld = new Point2D.Double();

		w2s.transform(pShape, pWorld);

		return new Point(pWorld.getX(), pWorld.getY());
	}

	public static AffineTransform getViewToShapeTransform(Shape shape) {
		AffineTransform transform = new AffineTransform();
		transform.concatenate(getWorldToShapeTransform(shape));
		transform.concatenate(getViewToWorldTransform());

		return transform;
	}

	public static AffineTransform getShapeToViewTransform(Shape shape){
		AffineTransform transform = new AffineTransform();
		transform.concatenate(getWorldToViewTransform());
		transform.concatenate(getShapeToWorldTransform(shape));

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
		return new AffineTransform(         1 / zoomFactor,                        0.0,
																     					 0.0,                     1 / zoomFactor,
																(double) horizontalViewPosition, (double) verticalViewPosition);
	}

	private static  AffineTransform getWorldToViewTransform() {
		return new AffineTransform(            zoomFactor,                         0.0,
				                                      0.0,                          zoomFactor,
				                       (double) -horizontalViewPosition, (double) -verticalViewPosition);
	}

	////////////////////////////////////////
	// Zooming
	////////////////////////////////////////
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

		// update the selected shape (if any)
		Shape selectedShape = Shapes.getInstance().getSelectedShape();
		if (selectedShape instanceof SelectedShape) {
			((SelectedShape) selectedShape).getShape().changed();
		}

		// refresh the UI
		GUIFunctions.refresh();
	}

	////////////////////////////////////////
	// Scrolling
	////////////////////////////////////////
	private static int horizontalViewPosition = SCROLL_BAR_MIN;
	private static int verticalViewPosition = SCROLL_BAR_MIN;

	public static int getHorizontalViewPosition() {
		return horizontalViewPosition;
	}

	public static void setHorizontalViewPosition(int horizontalViewPosition) {
		Transformations.horizontalViewPosition = horizontalViewPosition;

		// update the selected shape (if any)
		Shape selectedShape = Shapes.getInstance().getSelectedShape();
		if (selectedShape instanceof SelectedShape) {
			((SelectedShape) selectedShape).getShape().changed();
		}

		// refresh the UI
		GUIFunctions.refresh();
	}

	public static int getVerticalViewPosition() {
		return verticalViewPosition;
	}

	public static void setVerticalViewPosition(int verticalViewPosition) {
		Transformations.verticalViewPosition = verticalViewPosition;

		// update the selected shape (if any)
		Shape selectedShape = Shapes.getInstance().getSelectedShape();
		if (selectedShape instanceof SelectedShape) {
			((SelectedShape) selectedShape).getShape().changed();
		}

		// refresh the UI
		GUIFunctions.refresh();
	}
}
