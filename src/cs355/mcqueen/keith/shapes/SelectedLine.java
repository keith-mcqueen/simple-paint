package cs355.mcqueen.keith.shapes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * The <code>SelectedLine</code> class decorates an instance of {@link Line}.
 *
 * Created by keith on 5/14/14.
 */
public class SelectedLine extends SelectedShape<Line> {
	protected SelectedLine(Line shape) {
		super(shape);
	}

	@Override
	public Collection<? extends ResizeHandle> initResizeHandles(Line shape) {
		List<ResizeHandle> handles = new ArrayList<>(2);

		// transform the shape's location from world space to shape space
		Point loc = this.transformPointToShape(shape.getLocation());

		// the handles will be offset and sized by the bounds offset
		int offset = this.getBoundsOffset();
		Size handleSize = new Size(offset * 4, offset * 4);

		// left handle
		// compute the location of the handle in shape space, then transform it back to world space
		Point handleLoc = new Point(loc.getCoordinate(X) - offset, loc.getCoordinate(Y));
		ResizeHandle handle = new ResizeHandle(this.transformPointToWorld(handleLoc), handleSize);
		handle.setRotation(shape.getRotation());
		handles.add(handle);

		// right handle
		// compute the location of the handle in shape space, then transform it back to world space
		handleLoc = new Point(loc.getCoordinate(X) + shape.getLength() + offset, loc.getCoordinate(Y));
		handle = new ResizeHandle(this.transformPointToWorld(handleLoc), handleSize);
		handle.setRotation(shape.getRotation());
		handles.add(handle);

		return handles;
	}

	@Override
	protected RotateHandle initRotateHandle(Line shape) {
		return null;
	}
}
