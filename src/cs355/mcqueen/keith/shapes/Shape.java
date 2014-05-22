package cs355.mcqueen.keith.shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static java.lang.Math.PI;

/**
 * The <code>Shape</code> class is an abstract base class that defines the contract and
 * provides certain default behavior for 2-dimensional shapes.
 *
 * Created by keith on 5/2/14.
 */
public abstract class Shape {
	private static final double _2_PI = 2 * PI;
	private final CopyOnWriteArrayList<ShapeListener> listeners = new CopyOnWriteArrayList<>();

	private Color color;
	private Point location;
	private double rotation;
	private Map<Object, Object> userObjects = new HashMap<>();

	protected Shape(Point location) {
		this.location = location;
	}

	/**
	 * Get the location of this shape with respect to the "world"  The shape
	 * considers itself to centered at the origin.
	 *
	 * @return the point representing the location of this shape
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Set the location of this shape with respect to the world.
	 *
	 * @param location the location of the location of this shape
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * Get this shape's orientation (in radians) with respect to the world.  The shape considers
	 * itself to be axis-aligned at the origin.
	 *
	 * @return the angle (in radians) of rotation of this shape with respect to the world
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Set the orientation (in radians) with respect to the world.  The shape considers itself
	 * @param rotation the angle (in radians) of rotation of this shape with respect to the world
	 */
	public void setRotation(double rotation) {
		while (rotation > (_2_PI)) {
			rotation -= _2_PI;
		}

		while (rotation < -_2_PI) {
			rotation += _2_PI;
		}

		this.rotation = rotation;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void addShapeListener(ShapeListener listener) {
		this.listeners.addIfAbsent(listener);
	}

	public void removeShapeListener(ShapeListener listener) {
		this.listeners.remove(listener);
	}

	public void changed() {
		for (ShapeListener listener : this.listeners) {
			listener.shapeChanged(this);
		}
	}

	public boolean contains(Point point, double scaleFactor) {
		Point shapePoint = this.transformPointToShape(point);

		return this.doesContain(shapePoint, scaleFactor);
	}

	public AffineTransform getWorldToShapeTransform() {
		// create a new transformation (defaults to identity)
		AffineTransform worldToShape = new AffineTransform();

		// rotate back from its orientation (last transformation)
		worldToShape.rotate(-this.getRotation());

		// translate back from its position in the world (first transformation)
		Point center = this.getLocation();
		worldToShape.translate(-center.getCoordinate(X), -center.getCoordinate(Y));

		return worldToShape;
	}

	public AffineTransform getShapeToWorldTransform() {
		try {
			return this.getWorldToShapeTransform().createInverse();
		} catch (NoninvertibleTransformException e) {
			//return new AffineTransform();
			return null;
		}
	}

	public Point transformPointToShape(Point p) {
		AffineTransform w2s = this.getWorldToShapeTransform();

		Point2D world = new Point2D.Double(p.getCoordinate(X), p.getCoordinate(Y));
		Point2D shape = new Point2D.Double();

		w2s.transform(world, shape);

		return new Point(shape.getX(), shape.getY());
	}

	public Point transformPointToWorld(Point p) {
		AffineTransform s2w = this.getShapeToWorldTransform();

		Point2D shape = new Point2D.Double(p.getCoordinate(X), p.getCoordinate(Y));
		Point2D world = new Point2D.Double();

		s2w.transform(shape, world);

		return new Point(world.getX(), world.getY());
	}

	public void setUserObject(Object key, Object value) {
		if (null == value) {
			this.userObjects.remove(key);
		} else {
			this.userObjects.put(key, value);
		}
	}

	public Object getUserObject(Object key) {
		return this.userObjects.get(key);
	}

	protected abstract boolean doesContain(Point p, double scaleFactor);
}
