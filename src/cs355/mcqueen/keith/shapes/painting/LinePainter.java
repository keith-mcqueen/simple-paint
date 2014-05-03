package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Line;
import cs355.mcqueen.keith.shapes.Point;

import java.awt.*;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;


/**
 * Created by keith on 5/3/14.
 */
public class LinePainter implements ShapePainter<Line> {
	@Override
	public void paint(Line shape, Graphics2D g2d) {
		g2d.setColor(shape.getColor());

		Point startPoint = shape.getStartPoint();
		Point endPoint = shape.getEndPoint();

		g2d.drawLine((int) startPoint.getCoordinate(X),
				(int) startPoint.getCoordinate(Y),
				(int) endPoint.getCoordinate(X),
				(int) endPoint.getCoordinate(Y));
	}
}
