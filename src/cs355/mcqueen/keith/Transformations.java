package cs355.mcqueen.keith;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Shape;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

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
		AffineTransform w2s = getWorldToShapeTransform(shape);

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
		AffineTransform w2s = getShapeToWorldTransform(shape);

		Point2D pShape = new Point2D.Double(p.getCoordinate(X), p.getCoordinate(Y));
		Point2D pWorld = new Point2D.Double();

		w2s.transform(pShape, pWorld);

		return new Point(pWorld.getX(), pWorld.getY());
	}

	public static AffineTransform getWorldToShapeTransform(Shape shape) {
		// create the new (empty) transform
		AffineTransform toShape = new AffineTransform();

		// add the rotation
		toShape.rotate(0 - shape.getRotation());

		// add the translation
		Point shapeLoc = shape.getLocation();
		toShape.translate(0 - shapeLoc.getCoordinate(X), 0 - shapeLoc.getCoordinate(Y));

		return toShape;
	}

	public static AffineTransform getShapeToWorldTransform(Shape shape) {
		try {
			return getWorldToShapeTransform(shape).createInverse();
		} catch (NoninvertibleTransformException e) {
			return new AffineTransform();
		}
	}
}
