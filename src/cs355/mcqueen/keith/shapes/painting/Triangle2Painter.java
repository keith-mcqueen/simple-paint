package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Triangle2;

import java.awt.*;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * Paints a {@link Triangle2} instance.
 *
 * Created by keith on 5/27/14.
 */
public class Triangle2Painter extends AbstractBaseShapePainter<Triangle2> {
	@Override
	protected void doPaint(Triangle2 shape, Graphics2D g2d) {
		int[] xCoords = new int[3];
		int[] yCoords = new int[3];

		Point a = shape.getPointA();
		xCoords[0] = (int) a.getCoordinate(X);
		yCoords[0] = (int) a.getCoordinate(Y);

		Point b = shape.getPointB();
		xCoords[1] = (int) b.getCoordinate(X);
		yCoords[1] = (int) b.getCoordinate(Y);

		Point c = shape.getPointC();
		xCoords[2] = (int) c.getCoordinate(X);
		yCoords[2] = (int) c.getCoordinate(Y);

		g2d.fillPolygon(xCoords, yCoords, 3);
	}
}
