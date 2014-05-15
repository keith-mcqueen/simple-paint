package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Shape;
import cs355.mcqueen.keith.shapes.*;

import java.awt.*;
import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static java.lang.Math.abs;
import static java.lang.Math.min;

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
		Rectangle rectangle = this.getShape();
		if (null != rectangle) {
			Point cornerLoc = this.calculateRectangleLocation(e);
			Size rectSize = this.calculateRectangleSize(e);

			Point centerLoc = new Point(cornerLoc.getCoordinate(X) + rectSize.getLength(Size.WIDTH) / 2.0d,
					cornerLoc.getCoordinate(Y) + rectSize.getLength(Size.HEIGHT) / 2.0d);

			rectangle.setLocation(centerLoc);
			rectangle.setSize(rectSize);

			// notify that the shape has changed
			super.shapeChanged(rectangle);
		}
	}

	protected Size calculateRectangleSize(MouseEvent e) {
		Point origin = this.getOriginal();

		// the size of the rectangle is the difference between the X's and Y's
		return new Size(abs(origin.getCoordinate(X) - e.getX()),
				abs(origin.getCoordinate(Y) - e.getY()));
	}

	protected Point calculateRectangleLocation(MouseEvent e) {
		Point origin = this.getOriginal();

		// the (upper left corner of the) rectangle will be located at the point with the lower X and Y values
		return new Point(min(origin.getCoordinate(X), e.getX()),
				min(origin.getCoordinate(Y), e.getY()));
	}

	@Override
	public void shapeChanged(Shape shape) {
		// do nothing
	}
}
