package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Triangle;
import cs355.mcqueen.keith.shapes.Point;

import java.awt.*;

import static cs355.mcqueen.keith.shapes.Point.*;

/**
 * The <code>TrianglePainter</code> class is used to paint {@link Triangle} instances in a
 * 2-dimensional context.
 *
 * Created by keith on 5/3/14.
 */
public class TrianglePainter extends AbstractBaseShapePainter<Triangle> {
	@Override
	public void doPaint(Triangle shape, Graphics2D g2d) {
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
