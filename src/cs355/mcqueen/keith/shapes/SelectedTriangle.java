package cs355.mcqueen.keith.shapes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static java.lang.Math.*;

/**
 * The <code>SelectedTriangle</code> class decorates a {@link Triangle} instance.
 *
 * Created by keith on 5/14/14.
 */
public class SelectedTriangle extends SelectedShape<Triangle> {
	protected SelectedTriangle(Triangle shape) {
		super(shape);
	}

	@Override
	public Collection<? extends ResizeHandle> initResizeHandles(Triangle shape) {
		List<ResizeHandle> handles = new ArrayList<>(3);

		int offset = this.getBoundsOffset();
		Size handleSize = new Size(offset * 4, offset * 4);

		double x, y, theta;
		Point loc, handleLoc;
		ResizeHandle handle;

		// handle A
		loc = this.transformPointToShape(shape.getPointA());
		x = loc.getCoordinate(X);
		y = loc.getCoordinate(Y);
		theta = atan2(y, x);
		handleLoc = new Point(x + (offset * cos(theta)), y + (offset * sin(theta)));
		handle = new ResizeHandle(this.transformPointToWorld(handleLoc), handleSize);
		handle.setRotation(shape.getRotation());
		handles.add(handle);

		// handle B
		loc = this.transformPointToShape(shape.getPointB());
		x = loc.getCoordinate(X);
		y = loc.getCoordinate(Y);
		theta = atan2(y, x);
		handleLoc = new Point(x + (offset * cos(theta)), y + (offset * sin(theta)));
		handle = new ResizeHandle(this.transformPointToWorld(handleLoc), handleSize);
		handle.setRotation(shape.getRotation());
		handles.add(handle);

		// handle C
		loc = this.transformPointToShape(shape.getPointC());
		x = loc.getCoordinate(X);
		y = loc.getCoordinate(Y);
		theta = atan2(y, x);
		handleLoc = new Point(x + (offset * cos(theta)), y + (offset * sin(theta)));
		handle = new ResizeHandle(this.transformPointToWorld(handleLoc), handleSize);
		handle.setRotation(shape.getRotation());
		handles.add(handle);

		return handles;
	}

	@Override
	protected RotateHandle initRotateHandle(Triangle shape) {
		int offset = this.getRotateOffset();
		int boundsOffset = this.getBoundsOffset();
		Size handleSize = new Size(boundsOffset * 6, boundsOffset * 6);

		Point c = this.transformPointToShape(shape.getPointC());
		double x = c.getCoordinate(X);
		double y = c.getCoordinate(Y);
		double theta = atan2(y, x);
		Point handleLoc = new Point(x + (offset * cos(theta)), y + (offset * sin(theta)));
		RotateHandle handle = new RotateHandle(shape.transformPointToWorld(handleLoc), handleSize);
		handle.setRotation(shape.getRotation());

		return handle;
	}
}
