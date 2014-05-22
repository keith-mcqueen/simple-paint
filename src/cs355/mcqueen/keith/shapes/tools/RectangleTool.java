package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Shapes;
import cs355.mcqueen.keith.shapes.Size;

import java.awt.*;
import java.awt.event.MouseEvent;

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
		Point centerLoc = calculateRectangleLocation(e, fixedPoint);
		Size rectSize = calculateRectangleSize(e, fixedPoint);

		rectangle.setLocation(centerLoc);
		rectangle.setSize(rectSize);

		// notify that the shape has changed
		rectangle.changed();
	}

	public Size calculateRectangleSize(MouseEvent e, Point fixedPoint) {
		// the size of the rectangle is the difference between the X's and Y's
		return new Size(abs(fixedPoint.getCoordinate(X) - e.getX()),
				abs(fixedPoint.getCoordinate(Y) - e.getY()));
	}

	public Point calculateRectangleLocation(MouseEvent e, Point fixedPoint) {
		// get the difference between the new point and the fixed point
		Size diff = new Size(e.getX() - fixedPoint.getCoordinate(X),
				e.getY() - fixedPoint.getCoordinate(Y));

		// the center of the rectangle is located at the fixed point plus half the
		// difference to the new point
		return new Point(fixedPoint.getCoordinate(X) + diff.getLength(WIDTH) / 2,
				fixedPoint.getCoordinate(Y) + diff.getLength(HEIGHT) / 2);
	}
}
