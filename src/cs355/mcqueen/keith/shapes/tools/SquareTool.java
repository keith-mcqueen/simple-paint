package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Size;

import java.awt.*;
import java.awt.event.MouseEvent;

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
	public Size calculateRectangleSize(MouseEvent e, Point fixedPoint) {
		// let the super first calculate the rectangle size
		Size newSize = super.calculateRectangleSize(e, fixedPoint);

		// pick the greater of the sides
		double greater = max(newSize.getLength(WIDTH), newSize.getLength(HEIGHT));

		return new Size(greater, greater);
	}

	public Point calculateRectangleLocation(MouseEvent e, Point fixedPoint) {
		// get the difference between the new point and the fixed point
		Size diff = new Size(e.getX() - fixedPoint.getCoordinate(X),
				e.getY() - fixedPoint.getCoordinate(Y));

		double width = diff.getLength(WIDTH);
		double height = diff.getLength(HEIGHT);

		if (abs(width) > abs(height)) {
			height = copySign(width, height);
		} else {
			width = copySign(height, width);
		}

		return new Point(fixedPoint.getCoordinate(X) + width / 2,
				fixedPoint.getCoordinate(Y) + height / 2);
	}
}
