package cs355.mcqueen.keith.shapes;

import cs355.mcqueen.keith.shapes.tools.LineTool;
import cs355.mcqueen.keith.shapes.tools.ShapeTool;

import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static java.lang.Math.*;

/**
 * The <code>LineResizeHandle</code> resizes a {@link Line}.
 * <p>
 * Created by keith on 5/21/14.
 */
public class LineResizeHandle extends ResizeHandle<Line> {
	public static enum Endpoints {
		START, END
	}

	private final Endpoints ep;

	public LineResizeHandle(Point location, Line shape, Endpoints ep) {
		super(location, shape);
		this.ep = ep;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		switch (this.ep) {
			case START:
				this.udpateStartPoint(e);
				break;

			case END:
				this.updateEndPoint(e);
				break;
		}
	}

	private void udpateStartPoint(MouseEvent e) {
		Line line = this.getShape();

		Point lineEnd = line.getEnd();

		double deltaX = lineEnd.getCoordinate(X) - e.getX();
		double deltaY = lineEnd.getCoordinate(Y) - e.getY();

		double length = sqrt(pow(deltaX, 2) + pow(deltaY, 2));
		double rotation = atan2(deltaY, deltaX);

		line.setLocation(new Point(e.getX(), e.getY()));
		line.setLength(length);
		line.setRotation(rotation);

		line.changed();
	}

	private void updateEndPoint(MouseEvent e) {
		Line line = this.getShape();
		Object obj = line.getUserObject(ShapeTool.class);
		if (obj instanceof LineTool) {
			((LineTool) obj).mouseDragged(e);
		}
	}
}
