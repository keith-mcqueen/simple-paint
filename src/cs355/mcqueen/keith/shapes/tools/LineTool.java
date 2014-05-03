package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Line;
import cs355.mcqueen.keith.shapes.Point;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by keith on 5/3/14.
 */
public class LineTool extends ShapeTool<Line> {
	public LineTool(Color color) {
		super(color);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// create a new line with the same start and end points
		this.setShape(new Line(new Point(e.getX(), e.getY()), new Point(e.getX(), e.getY())));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Line line = this.getShape();
		if (null != line) {
			line.setEndPoint(new Point(e.getX(), e.getY()));
			this.shapeChanged(line);
		}
	}
}
