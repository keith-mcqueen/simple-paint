package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Triangle;
import cs355.mcqueen.keith.shapes.Point;

import java.awt.*;

import static cs355.mcqueen.keith.shapes.Point.*;

/**
 * Created by keith on 5/3/14.
 */
public class TrianglePainter implements ShapePainter<Triangle> {
	@Override
	public void paint(Triangle shape, Graphics2D g2d) {
		g2d.setColor(shape.getColor());

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
