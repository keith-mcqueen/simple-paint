package cs355.mcqueen.keith.shapes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cs355.mcqueen.keith.shapes.Size.HEIGHT;
import static cs355.mcqueen.keith.shapes.Size.WIDTH;

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
	public Collection<? extends ResizeHandle> initResizeHandles(Rectangle shape) {
		// prepare the list
		List<ResizeHandle> handles = new ArrayList<>(4);

		// using the selected shape's size, calculate the location of the shape's NW corner
		Size size = shape.getSize();
		int width = (int) size.getLength(WIDTH);
		int height = (int) size.getLength(HEIGHT);

		int _NW_x = (int) Math.floor(-(width / 2.0d));
		int _NW_y = (int) Math.floor(-(height / 2.0d));

		// the handles will be offset and sized by the bounds offset
		int offset = this.getBoundsOffset();
		Size handleSize = new Size(offset * 4, offset * 4);

		// northwest corner
		Point handleLoc = new Point(_NW_x - offset, _NW_y - offset);
		ResizeHandle northwest = new ResizeHandle(this.transformPointToWorld(handleLoc), handleSize);
		northwest.setRotation(shape.getRotation());
		handles.add(northwest);

		// northeast corner
		handleLoc = new Point(_NW_x + width + offset, _NW_y - offset);
		ResizeHandle northeast = new ResizeHandle(this.transformPointToWorld(handleLoc), handleSize);
		northeast.setRotation(shape.getRotation());
		handles.add(northeast);

		// southeast corner
		handleLoc = new Point(_NW_x + width + offset, _NW_y + height + offset);
		ResizeHandle southeast = new ResizeHandle(this.transformPointToWorld(handleLoc), handleSize);
		southeast.setRotation(shape.getRotation());
		handles.add(southeast);

		// southwest corner
		handleLoc = new Point(_NW_x - offset, _NW_y + height + offset);
		ResizeHandle southwest = new ResizeHandle(this.transformPointToWorld(handleLoc), handleSize);
		southwest.setRotation(shape.getRotation());
		handles.add(southwest);

		return handles;
	}
}
