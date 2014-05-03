package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Triangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
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
			this.setShape(new Triangle(this.points.get(0), this.points.get(1), this.points.get(2)));
			this.points.clear();
		}
	}
}
