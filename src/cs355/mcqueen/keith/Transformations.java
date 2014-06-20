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
import static cs355.solution.CS355.*;
import static java.lang.Math.*;

/**
 * Contains utility methods for performing transformations.
 * <p>
 * Created by keith on 5/23/14.
 */
public class Transformations {
	public static Point transformPoint(AffineTransform transform, Point point) {
		Point2D pShape = new Point2D.Double(point.getCoordinate(X), point.getCoordinate(Y));
		Point2D pWorld = new Point2D.Double();

		transform.transform(pShape, pWorld);

		return new Point(pWorld.getX(), pWorld.getY());
	}

	public static AffineTransform viewToShape(Shape shape) {
		AffineTransform transform = new AffineTransform();
		transform.concatenate(worldToShape(shape));
		transform.concatenate(viewToWorld());

		return transform;
	}

	public static AffineTransform shapeToView(Shape shape){
		AffineTransform transform = new AffineTransform();
		transform.concatenate(worldToView());
		transform.concatenate(shapeToWorld(shape));

		return transform;
	}

	public static AffineTransform worldToShape(Shape shape) {
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

	public static AffineTransform shapeToWorld(Shape shape) {
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

	public static AffineTransform viewToWorld() {
		AffineTransform transform = new AffineTransform();

		// translate
		transform.concatenate(new AffineTransform(                  1.0,                          0.0,
                                                        				0.0,                          1.0,
				                                      (double) horizontalViewPosition, (double) verticalViewPosition));

		// scale
		transform.concatenate(new AffineTransform(1 / zoomFactor,        0.0,
				                                             0.0,     1 / zoomFactor,
				                                             0.0,            0.0));


		return transform;
	}

	public static  AffineTransform worldToView() {
		AffineTransform transform = new AffineTransform();

		// scale
		transform.concatenate(new AffineTransform(zoomFactor,    0.0,
				                                         0.0,     zoomFactor,
				                                         0.0,        0.0));

		// translate
		transform.concatenate(new AffineTransform(                   1.0,                           0.0,
				                                                         0.0,                           1.0,
				                                      (double) -horizontalViewPosition, (double) -verticalViewPosition));

		return transform;
	}

	////////////////////////////////////////
	// Zooming
	////////////////////////////////////////
	private static double zoomFactor = CS355.ZOOM_DEFAULT;

	public static double getZoomFactor() {
		return zoomFactor;
	}

	public static void setZoomFactor(double factor) {
		// changing the zoom should change the knob size on the scroll bars
		int knobWidth = (int) (VIEWPORT_WIDTH / factor);
		setHScrollBarKnob(knobWidth);
		setVScrollBarKnob(knobWidth);

		// update scrollbar positions

		// get the current center of the viewport
		int halfViewportWidth = VIEWPORT_WIDTH / 2;

		double scaledWidth = halfViewportWidth / zoomFactor;
		double center_x = horizontalViewPosition + scaledWidth;
		double center_y = verticalViewPosition + scaledWidth;

		horizontalViewPosition = max(min((int) (center_x - halfViewportWidth / factor), SCROLL_BAR_MAX - knobWidth), 0);
		verticalViewPosition   = max(min((int) (center_y - halfViewportWidth / factor), SCROLL_BAR_MAX - knobWidth), 0);

		zoomFactor = factor;

		viewportMoved();

		GUIFunctions.setHScrollBarPosit(horizontalViewPosition);
		GUIFunctions.setVScrollBarPosit(verticalViewPosition);
	}

	////////////////////////////////////////
	// Scrolling
	////////////////////////////////////////
	private static int horizontalViewPosition = SCROLL_BAR_MIN;
	private static int verticalViewPosition = SCROLL_BAR_MIN;

	public static void setHorizontalViewPosition(int horizontalViewPosition) {
		Transformations.horizontalViewPosition = horizontalViewPosition;
		viewportMoved();
	}

	public static void setVerticalViewPosition(int verticalViewPosition) {
		Transformations.verticalViewPosition = verticalViewPosition;
		viewportMoved();
	}

	private static void viewportMoved() {
		// update the selected shape (if any)
		Shape selectedShape = Shapes.getInstance().getSelectedShape();
		if (selectedShape instanceof SelectedShape) {
			((SelectedShape) selectedShape).getShape().changed();
		}

		// refresh the UI
		GUIFunctions.refresh();
	}
}
