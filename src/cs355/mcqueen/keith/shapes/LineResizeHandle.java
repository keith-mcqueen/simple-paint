package cs355.mcqueen.keith.shapes;

import cs355.mcqueen.keith.shapes.tools.LineTool;
import cs355.mcqueen.keith.shapes.tools.ShapeTool;

import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.Transformations.viewToWorld;
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
		System.out.println("******************** LineResizeHandle.udpateStartPoint ********************");
		System.out.println("e = " + e);

		Line line = this.getShape();
		System.out.println("line.getLocation() = " + line.getLocation());
		System.out.println("line.getRotation() = " + line.getRotation());
		System.out.println("line.getLength() = " + line.getLength());

		Point lineEnd = line.getEnd();
		System.out.println("lineEnd = " + lineEnd);

		Point mouseLoc = transformPoint(viewToWorld(), new Point(e.getX(), e.getY()));
		System.out.println("mouseLoc = " + mouseLoc);

		double deltaX = lineEnd.getCoordinate(X) - mouseLoc.getCoordinate(X);
		double deltaY = lineEnd.getCoordinate(Y) - mouseLoc.getCoordinate(Y);
		System.out.println("deltaX = " + deltaX);
		System.out.println("deltaY = " + deltaY);

		double length = sqrt(pow(deltaX, 2) + pow(deltaY, 2));
		double rotation = atan2(deltaY, deltaX);
		System.out.println("length = " + length);
		System.out.println("rotation = " + rotation);
		System.out.println();

		line.setLocation(mouseLoc);
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
