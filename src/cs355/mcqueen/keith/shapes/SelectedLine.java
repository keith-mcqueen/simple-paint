package cs355.mcqueen.keith.shapes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cs355.mcqueen.keith.Transformations.transformPointFromShapeCoordinates;
import static cs355.mcqueen.keith.shapes.LineResizeHandle.Endpoints.END;
import static cs355.mcqueen.keith.shapes.LineResizeHandle.Endpoints.START;
import static cs355.mcqueen.keith.shapes.Size.HEIGHT;

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
		int offset = this.getBoundsOffset();
		Size handleSize = new Size(offset * 4, offset * 4);

		Point handleLoc;
		ResizeHandle handle;

		// left handle
		// compute the location of the handle in line space, then transform it back to world space
		handleLoc = new Point(0 - offset,
				-(handleSize.getLength(HEIGHT) / 2));
		handle = new LineResizeHandle(transformPointFromShapeCoordinates(handleLoc, line),
				handleSize, line, START);
		handle.setRotation(line.getRotation());
		handles.add(handle);

		// right handle
		// compute the location of the handle in line space, then transform it back to world space
		handleLoc = new Point(0 + line.getLength() + offset,
				-(handleSize.getLength(HEIGHT) / 2));
		handle = new LineResizeHandle(transformPointFromShapeCoordinates(handleLoc, line),
				handleSize, line, END);
		handle.setRotation(line.getRotation());
		handles.add(handle);

		return handles;
	}

	@Override
	protected RotateHandle initRotateHandle(Line shape) {
		return null;
	}
}
