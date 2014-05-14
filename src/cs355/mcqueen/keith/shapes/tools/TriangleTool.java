package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Triangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * The <code>TriangleTool</code> class is used to draw a triangle in a 2-dimensional space.
 *
 * Created by keith on 5/3/14.
 */
public class TriangleTool extends ShapeTool<Triangle> {
	private List<Point> points = new ArrayList<>();

	public TriangleTool(Color color) {
		super(color);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.points.add(new Point(e.getX(), e.getY()));

		if (3 == this.points.size()) {
			Point pointA = this.points.get(0);
			Point pointB = this.points.get(1);
			Point pointC = this.points.get(2);

			Point centerPoint = new Point((pointA.getCoordinate(X) + pointB.getCoordinate(X) + pointC.getCoordinate(X))/3.0d,
					(pointA.getCoordinate(Y) + pointB.getCoordinate(Y) + pointC.getCoordinate(Y))/3.0d);

			Triangle triangle = new Triangle(centerPoint, pointA, pointB, pointC);

			this.setShape(triangle);
			this.points.clear();
		}
	}
}
