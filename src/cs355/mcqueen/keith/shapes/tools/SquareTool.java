package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Size;

import java.awt.*;
import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.Transformations.viewToWorld;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.Size.HEIGHT;
import static cs355.mcqueen.keith.shapes.Size.WIDTH;
import static java.lang.Math.*;

/**
 * The <code>SquareTool</code> class creates squares in a 2-dimensional context.
 * <p>
 * Created by keith on 5/3/14.
 */
public class SquareTool extends RectangleTool {
	public SquareTool(Color color) {
		super(color);
	}

	@Override
	protected Size calculateRectangleSize(MouseEvent e, Point fixedPoint, Rectangle rectangle) {
		// let the super first calculate the rectangle size
		Size newSize = super.calculateRectangleSize(e, fixedPoint, rectangle);

		// pick the greater of the sides
		double greater = max(newSize.getLength(WIDTH), newSize.getLength(HEIGHT));

		return new Size(greater, greater);
	}

	protected Point calculateRectangleLocation(MouseEvent e, Point fixedPoint, Rectangle rectangle) {
		Point mouseLoc = transformPoint(viewToWorld(), new Point(e.getX(), e.getY()));

		// get the difference between the new point and the fixed point
		Size diff = new Size(mouseLoc.getCoordinate(X) - fixedPoint.getCoordinate(X),
				mouseLoc.getCoordinate(Y) - fixedPoint.getCoordinate(Y));

		double width = diff.getLength(WIDTH);
		double height = diff.getLength(HEIGHT);

		if (abs(width) > abs(height)) {
			height = copySign(width, height);
		} else {
			width = copySign(height, width);
		}

		// the center of the rectangle is located at the fixed point plus half the
		// difference to the new point

		return new Point(fixedPoint.getCoordinate(X) + width / 2,
				fixedPoint.getCoordinate(Y) + height / 2);
	}
}
