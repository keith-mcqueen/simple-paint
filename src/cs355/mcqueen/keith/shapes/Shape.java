package cs355.mcqueen.keith.shapes;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The <code>Shape</code> class is an abstract base class that defines the contract and
 * provides certain default behavior for 2-dimensional shapes.
 *
 * Created by keith on 5/2/14.
 */
public abstract class Shape {
	private final CopyOnWriteArrayList<ShapeListener> listeners = new CopyOnWriteArrayList<>();

	private Color color;
	private Point center;
	private double rotation;

	protected Shape(Point center) {
		this.center = center;
	}

	/**
	 * Get the location of the center of of this shape with respect to the "world"  The shape
	 * considers itself to centered at the origin.
	 *
	 * @return
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * Set the center of this shape with respect to the world.
	 *
	 * @param center
	 */
	public void setCenter(Point center) {
		this.center = center;
	}

	/**
	 * Get this shape's orientation (in radians) with respect to the world.  The shape considers
	 * itself to be axis-aligned at the origin.
	 *
	 * @return
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Set the orientation (in radians) with respect to the world.  The shape considers itself
	 * @param rotation
	 */
	public void setRotation(double rotation) {
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

	public void removeShapelistener(ShapeListener listener) {
		this.listeners.remove(listener);
	}

	protected void changed() {
		for (ShapeListener listener : this.listeners) {
			listener.shapeChanged(this);
		}
	}
}
