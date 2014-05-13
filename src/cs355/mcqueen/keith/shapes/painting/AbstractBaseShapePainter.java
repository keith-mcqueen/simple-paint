package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Shape;
import cs355.mcqueen.keith.shapes.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static cs355.mcqueen.keith.shapes.Point.*;

/**
 * The <code>AbstractBaseShapePainter</code> class is an abstract base class for any
 * {@link ShapePainter} implementation that can transform a shape between its own object
 * space and the "world" space of the drawing context.
 *
 * Created by keith on 5/13/14.
 */
public abstract class AbstractBaseShapePainter<S extends Shape> implements ShapePainter<S> {
	@Override
	final public void paint(S shape, Graphics2D g2d) {
		// create a new transformation
		AffineTransform objToWorld = new AffineTransform();

		// translate to its position in the world (last transformation)
		Point center = shape.getCenter();
		objToWorld.translate(center.getCoordinate(X),	center.getCoordinate(Y));

		// rotate to its orientation (first transformation)
		objToWorld.rotate(shape.getRotation());

		// set the drawing transformation
		g2d.setTransform(objToWorld);

		// set the painting color from the shape's color
		g2d.setColor(shape.getColor());

		// and finally draw
		this.doPaint(shape, g2d);
	}

	/**
	 * Paint the given shape in the given graphics context.  At the point that this method
	 * is called, all transformations have been executed, and the current color has been set.
	 * It is left to the subclass to implement the final drawing of the shape.
	 *
	 * @param shape
	 * @param g2d
	 */
	protected abstract void doPaint(S shape, Graphics2D g2d);
}
