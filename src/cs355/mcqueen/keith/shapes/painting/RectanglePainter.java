package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.*;
import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Rectangle;

import java.awt.*;

import static cs355.mcqueen.keith.shapes.Point.*;
import static cs355.mcqueen.keith.shapes.Size.*;

/**
 * Created by keith on 5/3/14.
 */
public class RectanglePainter implements ShapePainter<Rectangle> {
	@Override
	public void paint(Rectangle shape, Graphics2D g2d) {
		g2d.setColor(shape.getColor());

		Point location = shape.getLocation();
		Size size = shape.getSize();

		g2d.fillRect((int) location.getCoordinate(X),
				(int) location.getCoordinate(Y),
				(int) size.getLength(WIDTH),
				(int) size.getLength(HEIGHT));
	}
}
