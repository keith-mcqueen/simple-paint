package cs355.mcqueen.keith.shapes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cs355.mcqueen.keith.Transformations.shapeToWorld;
import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.shapes.RectangleResizeHandle.Corner.*;
import static cs355.mcqueen.keith.shapes.Size.HEIGHT;
import static cs355.mcqueen.keith.shapes.Size.WIDTH;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * The <code>SelectedRectangle</code> class decorates an instance of {@link Rectangle}
 * (which includes rectangles, squares, ellipses and circles).
 *
 * Created by keith on 5/14/14.
 */
public class SelectedRectangle extends SelectedShape<Rectangle> {
	protected SelectedRectangle(Rectangle shape) {
		super(shape);
	}

	@Override
	public Collection<? extends ResizeHandle> initResizeHandles(Rectangle rect) {
		// prepare the list
		List<ResizeHandle> handles = new ArrayList<>(4);

		// using the selected rect's size, calculate the location of the rect's NW corner
		Size size = rect.getSize();
		int width = (int) size.getLength(WIDTH);
		int height = (int) size.getLength(HEIGHT);

		int _NW_x = (int) Math.floor(-(width / 2.0d));
		int _NW_y = (int) Math.floor(-(height / 2.0d));

		// the handles will be offset and sized by the bounds offset
		double offset = this.getBoundsOffset();
		Point handleLoc;
		ResizeHandle handle;

		// northwest corner
		handleLoc = new Point(_NW_x - offset, _NW_y - offset);
		handle = new RectangleResizeHandle(transformPoint(shapeToWorld(rect), handleLoc),
				rect, NORTHWEST);
		handles.add(handle);

		// northeast corner
		handleLoc = new Point(_NW_x + width + offset, _NW_y - offset);
		handle = new RectangleResizeHandle(transformPoint(shapeToWorld(rect), handleLoc),
				rect, NORTHEAST);
		handles.add(handle);

		// southeast corner
		handleLoc = new Point(_NW_x + width + offset, _NW_y + height + offset);
		handle = new RectangleResizeHandle(transformPoint(shapeToWorld(rect), handleLoc),
				rect, SOUTHEAST);
		handles.add(handle);

		// southwest corner
		handleLoc = new Point(_NW_x - offset, _NW_y + height + offset);
		handle = new RectangleResizeHandle(transformPoint(shapeToWorld(rect), handleLoc),
				rect, SOUTHWEST);
		handles.add(handle);

		return handles;
	}

	@Override
	protected RotateHandle initRotateHandle(Rectangle rect) {
		// using the selected rect's size, calculate the location of the rect's NW corner
		Size size = rect.getSize();
		int width = (int) size.getLength(WIDTH);
		int height = (int) size.getLength(HEIGHT);

		int _NW_x = (int) Math.floor(-(width / 2.0d));
		int _NW_y = (int) Math.floor(-(height / 2.0d));

		// the handles will be offset and sized by the bounds offset
		double offset = this.getRotateOffset();

		// northeast corner
		double theta = Math.PI / 4.0d;
		Point handleLoc = new Point(_NW_x + width + (offset * cos(theta)), _NW_y - (offset * sin(theta)));
		RotateHandle handle = new RotateHandle(transformPoint(shapeToWorld(rect), handleLoc), rect);

		return handle;
	}
}
