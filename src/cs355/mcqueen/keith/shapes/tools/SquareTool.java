package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Size;

import java.awt.*;
import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.Size.HEIGHT;
import static cs355.mcqueen.keith.shapes.Size.WIDTH;
import static java.lang.Math.max;

/**
 * Created by keith on 5/3/14.
 */
public class SquareTool extends RectangleTool {
	public SquareTool(Color color) {
		super(color);
	}

	@Override
	protected Size calculateRectangleSize(MouseEvent e) {
		Size newSize = super.calculateRectangleSize(e);

		double greater = max(newSize.getLength(WIDTH), newSize.getLength(HEIGHT));

		return new Size(greater, greater);
	}

	protected Point calculateRectangleLocation(MouseEvent e) {
		// get the location of the original click
		Point origin = this.getOriginal();
		double originX = origin.getCoordinate(X);
		double originY = origin.getCoordinate(Y);

		Size size = this.calculateRectangleSize(e);

		// if e.x >= origin.X, then keep origin.x
		double locX = e.getX() < originX ? originX - size.getLength(WIDTH) : originX;
		double locY = e.getY() < originY ? originY - size.getLength(WIDTH) : originY;

		return new Point(locX, locY);
	}
}
