package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Shapes;
import cs355.mcqueen.keith.shapes.Triangle2;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.Transformations.viewToWorld;

/**
 * The <code>TriangleTool</code> class is used to draw a triangle in a 2-dimensional space.
 * <p>
 * Created by keith on 5/3/14.
 */
public class TriangleTool extends ShapeTool<Triangle2> {
	private List<Point> points = new ArrayList<>();

	public TriangleTool(Color color) {
		super(color);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// clear the current selection
		Shapes.getInstance().setSelectedShape(null);

		this.points.add(transformPoint(viewToWorld(), new Point(e.getX(), e.getY())));

		if (3 == this.points.size()) {
			Point pointA = this.points.get(0);
			Point pointB = this.points.get(1);
			Point pointC = this.points.get(2);

			this.setShape(new Triangle2(pointA, pointB, pointC));
			this.points.clear();
		}
	}
}
