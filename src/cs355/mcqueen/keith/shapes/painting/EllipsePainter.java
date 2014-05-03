package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Ellipse;
import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Size;

import java.awt.*;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.Size.HEIGHT;
import static cs355.mcqueen.keith.shapes.Size.WIDTH;

/**
 * Created by keith on 5/3/14.
 */
public class EllipsePainter implements ShapePainter<Ellipse>{
	@Override
	public void paint(Ellipse shape, Graphics2D g2d) {
		g2d.setColor(shape.getColor());

		Point location = shape.getLocation();
		Size size = shape.getSize();

		g2d.fillOval((int) location.getCoordinate(X),
				(int) location.getCoordinate(Y),
				(int) size.getLength(WIDTH),
				(int) size.getLength(HEIGHT));
	}
}
