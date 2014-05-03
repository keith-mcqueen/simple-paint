package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.*;
import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.shapes.Point.*;
import static java.lang.Math.*;

/**
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
			rectangle.setLocation(this.calculateRectangleLocation(e));
			rectangle.setSize(this.calculateRectangleSize(e));

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
