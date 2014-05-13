package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Line;
import cs355.mcqueen.keith.shapes.Point;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The <code>LineTool</code> is used to create a {@link Line} instance from a 2-dimensional
 * context.
 *
 * Created by keith on 5/3/14.
 */
public class LineTool extends ShapeTool<Line> {
	public LineTool(Color color) {
		super(color);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// create a new line with the same start and end points
		this.setShape(new Line(new Point(e.getX(), e.getY()), 0.0d, 0.0d));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Line line = this.getShape();
		if (null != line) {

			Point lineStart = line.getCenter();

			double deltaX = e.getX() - lineStart.getCoordinate(Point.X);
			double deltaY = e.getY() - lineStart.getCoordinate(Point.Y);

			double length = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
			double rotation = Math.atan2(deltaY, deltaX);

			line.setLength(length);
			line.setRotation(rotation);

			this.shapeChanged(line);
		}
	}
}
