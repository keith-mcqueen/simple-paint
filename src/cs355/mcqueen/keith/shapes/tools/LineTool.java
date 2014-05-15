package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Line;
import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Shapes;

import java.awt.*;
import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static java.lang.Math.*;

/**
 * The <code>LineTool</code> is used to create a {@link Line} instance from a 2-dimensional context.
 * <p>
 * Created by keith on 5/3/14.
 */
public class LineTool extends ShapeTool<Line> {
	public LineTool(Color color) {
		super(color);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// clear the current selection
		Shapes.getInstance().setSelectedShape(null);

		// create a new line with the same start and end points
		this.setShape(new Line(new Point(e.getX(), e.getY()), 0.0d, 0.0d));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Line line = this.getShape();
		if (null != line) {
			Point lineStart = line.getLocation();

			double deltaX = e.getX() - lineStart.getCoordinate(X);
			double deltaY = e.getY() - lineStart.getCoordinate(Y);

			double length = sqrt(pow(deltaX, 2) + pow(deltaY, 2));
			double rotation = atan2(deltaY, deltaX);

			line.setLength(length);
			line.setRotation(rotation);

			this.shapeChanged(line);
		}
	}
}
