package cs355.mcqueen.keith.shapes;

import cs355.mcqueen.keith.shapes.tools.Handle;

import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.Transformations.worldToView;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * The <code>ResizeHandle</code> is a specialized rectangle that is used to draw handles for resizing another shape.
 * <p>
 * Created by keith on 5/14/14.
 */
public abstract class ResizeHandle<S extends Shape> extends Rectangle implements Handle {
	public static final int SIZE = 8;

	private final S shapeToResize;

	public ResizeHandle(Point location, S shape) {
		super(location, new Size(SIZE, SIZE));

		this.shapeToResize = shape;
	}

	public S getShape() {
		return shapeToResize;
	}

	@Override
	public boolean contains(Point point) {
		Point handleLoc = transformPoint(worldToView(), this.getLocation());

		double delta_x = point.getCoordinate(X) - handleLoc.getCoordinate(X);
		double delta_y = point.getCoordinate(Y) - handleLoc.getCoordinate(Y);

		return Math.abs(delta_x) <= SIZE && Math.abs(delta_y) <= SIZE;
	}
}
