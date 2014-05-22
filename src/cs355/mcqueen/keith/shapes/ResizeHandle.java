package cs355.mcqueen.keith.shapes;

import cs355.mcqueen.keith.shapes.tools.Handle;

/**
 * The <code>ResizeHandle</code> is a specialized rectangle that is used to draw handles for resizing another shape.
 * <p>
 * Created by keith on 5/14/14.
 */
public abstract class ResizeHandle<S extends Shape> extends Rectangle implements Handle {
	private final S shapeToResize;

	public ResizeHandle(Point location, Size size, S shape) {
		super(location, size);

		this.shapeToResize = shape;
	}

	public S getShape() {
		return shapeToResize;
	}
}
