package cs355.mcqueen.keith.shapes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cs355.mcqueen.keith.Transformations.shapeToWorld;
import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.Triangle2.Points.*;
import static java.lang.Math.*;

/**
 * Represents a selected {@link Triangle2}.
 *
 * Created by keith on 5/27/14.
 */
public class SelectedTriangle2 extends SelectedShape<Triangle2> {
	protected SelectedTriangle2(Triangle2 triangle) {
		super(triangle);
	}

	@Override
	protected Collection<? extends ResizeHandle> initResizeHandles(Triangle2 triangle) {
		List<ResizeHandle> handles = new ArrayList<>(3);

		double offset = this.getBoundsOffset();

		double x, y, theta;
		Point loc, handleLoc;
		ResizeHandle handle;

		// handle A
		loc = triangle.getPointA();
		x = loc.getCoordinate(X);
		y = loc.getCoordinate(Y);
		theta = atan2(y, x);
		handleLoc = new Point(x + (offset * cos(theta)), y + (offset * sin(theta)));
		handle = new Triangle2ResizeHandle(transformPoint(shapeToWorld(triangle), handleLoc),
				triangle, A);
		handle.setRotation(triangle.getRotation());
		handles.add(handle);

		// handle B
		loc = triangle.getPointB();
		x = loc.getCoordinate(X);
		y = loc.getCoordinate(Y);
		theta = atan2(y, x);
		handleLoc = new Point(x + (offset * cos(theta)), y + (offset * sin(theta)));
		handle = new Triangle2ResizeHandle(transformPoint(shapeToWorld(triangle), handleLoc),
				triangle, B);
		handle.setRotation(triangle.getRotation());
		handles.add(handle);

		// handle C
		loc = triangle.getPointC();
		x = loc.getCoordinate(X);
		y = loc.getCoordinate(Y);
		theta = atan2(y, x);
		handleLoc = new Point(x + (offset * cos(theta)), y + (offset * sin(theta)));
		handle = new Triangle2ResizeHandle(transformPoint(shapeToWorld(triangle), handleLoc),
				triangle, C);
		handle.setRotation(triangle.getRotation());
		handles.add(handle);

		return handles;
	}

	@Override
	protected RotateHandle initRotateHandle(Triangle2 triangle) {
		double offset = this.getRotateOffset();

		Point c = triangle.getPointC();
		double x = c.getCoordinate(X);
		double y = c.getCoordinate(Y);
		double theta = atan2(y, x);
		Point handleLoc = new Point(x + (offset * cos(theta)), y + (offset * sin(theta)));
		RotateHandle handle = new RotateHandle(transformPoint(shapeToWorld(triangle), handleLoc),
				triangle);
		handle.setRotation(triangle.getRotation());

		return handle;
	}
}
