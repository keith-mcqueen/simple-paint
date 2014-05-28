package cs355.mcqueen.keith.shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static cs355.mcqueen.keith.Transformations.transformPointToShapeCoordinates;
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
		Point shapePoint = transformPointToShapeCoordinates(point, this);

		return this.doesContain(shapePoint, scaleFactor);
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
