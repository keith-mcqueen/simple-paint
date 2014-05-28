package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Shapes;
import cs355.mcqueen.keith.shapes.Size;

import java.awt.*;
import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.Transformations.*;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.Size.HEIGHT;
import static cs355.mcqueen.keith.shapes.Size.WIDTH;
import static java.lang.Math.abs;

/**
 * The <code>RectangleTool</code> is used to create {@link Rectangle} instances drawn on a 2-dimensional canvas.
 * <p>
 * Created by keith on 5/3/14.
 */
public class RectangleTool extends ShapeTool<Rectangle> {
	private Point original;

	public RectangleTool(Color color) {
		super(color);
	}

	protected Point getOriginal() {
		return original;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// clear the current selection
		Shapes.getInstance().setSelectedShape(null);

		// save the current location as the original location
		this.original = new Point(e.getX(), e.getY());

		// create the rectangle at the original location with 0 size and save it
		this.setShape(this.createShape());
	}

	protected Rectangle createShape() {
		return new Rectangle(this.original, new Size(0, 0));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.updateRectangle(e, this.getOriginal(), this.getShape());
	}

	public void updateRectangle(MouseEvent e, Point fixedPoint, Rectangle rectangle) {
		Point rectLoc = calculateRectangleLocation(e, fixedPoint, rectangle);
		Size rectSize = calculateRectangleSize(e, fixedPoint, rectangle);

		rectangle.setLocation(rectLoc);
		rectangle.setSize(rectSize);

		rectangle.changed();
	}

	protected Size calculateRectangleSize(MouseEvent e, Point fixedPoint, Rectangle rectangle) {
		Point tFixedPoint = transformPoint(worldToShape(rectangle), fixedPoint);
		Point tMouseLoc = transformPoint(viewToShape(rectangle), new Point(e.getX(), e.getY()));

		// the size of the rectangle is the difference between the X's and Y's
		return new Size(abs(tFixedPoint.getCoordinate(X) - tMouseLoc.getCoordinate(X)),
				abs(tFixedPoint.getCoordinate(Y) - tMouseLoc.getCoordinate(Y)));
	}

	protected Point calculateRectangleLocation(MouseEvent e, Point fixedPoint, Rectangle rectangle) {
		Point tFixedPoint = transformPoint(worldToShape(rectangle), fixedPoint);
		Point tMouseLoc = transformPoint(viewToShape(rectangle), new Point(e.getX(), e.getY()));

		// get the difference between the new point and the fixed point
		Size diff = new Size(tMouseLoc.getCoordinate(X) - tFixedPoint.getCoordinate(X),
				tMouseLoc.getCoordinate(Y) - tFixedPoint.getCoordinate(Y));

		// the center of the rectangle is located at the fixed point plus half the
		// difference to the new point
		Point rectLoc = new Point(tFixedPoint.getCoordinate(X) + diff.getLength(WIDTH) / 2,
				tFixedPoint.getCoordinate(Y) + diff.getLength(HEIGHT) / 2);

		return transformPoint(shapeToWorld(rectangle), rectLoc);
	}
}
