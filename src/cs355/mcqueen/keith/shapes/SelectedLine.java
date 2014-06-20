package cs355.mcqueen.keith.shapes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cs355.mcqueen.keith.Transformations.shapeToWorld;
import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.shapes.LineResizeHandle.Endpoints.END;
import static cs355.mcqueen.keith.shapes.LineResizeHandle.Endpoints.START;

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
	public Collection<? extends ResizeHandle> initResizeHandles(Line line) {
		List<ResizeHandle> handles = new ArrayList<>(2);

		// the handles will be offset and sized by the bounds offset
		double offset = this.getBoundsOffset();

		Point handleLoc;
		ResizeHandle handle;

		// left handle
		// compute the location of the handle in line space, then transform it back to world space
		handleLoc = new Point(0 - offset,
				-(ResizeHandle.SIZE / 2));
		handle = new LineResizeHandle(transformPoint(shapeToWorld(line), handleLoc),
				line, START);
		handle.setRotation(line.getRotation());
		handles.add(handle);

		// right handle
		// compute the location of the handle in line space, then transform it back to world space
		handleLoc = new Point(0 + line.getLength() + offset,
				-(ResizeHandle.SIZE / 2));
		handle = new LineResizeHandle(transformPoint(shapeToWorld(line), handleLoc),
				line, END);
		handle.setRotation(line.getRotation());
		handles.add(handle);

		return handles;
	}

	@Override
	protected RotateHandle initRotateHandle(Line shape) {
		return null;
	}
}
