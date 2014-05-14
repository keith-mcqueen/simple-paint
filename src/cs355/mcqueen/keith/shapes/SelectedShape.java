package cs355.mcqueen.keith.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The <code>SelectedShape</code> class decorates a given instance of another {@link Shape}.
 *
 * Created by keith on 5/14/14.
 */
public abstract class SelectedShape<S extends Shape> extends Shape {
	private static final int BOUNDS_OFFSET = 2;

	public static final class Factory {
		public static final SelectedShape getSelectedShape(Shape shape) {
			if (null == shape) {
				return null;
			}

			if (shape instanceof SelectedShape) {
				return (SelectedShape) shape;
			}

			if (shape instanceof Rectangle) {
				return new SelectedRectangle((Rectangle) shape);
			}

			if (shape instanceof Triangle) {
				return new SelectedTriangle((Triangle) shape);
			}

			if (shape instanceof Line) {
				return new SelectedLine((Line) shape);
			}

			return null;
		}
	}

	private final S selected;
	private final List<ResizeHandle> resizeHandles = new ArrayList<>();

	protected SelectedShape(S shape) {
		super(shape.getLocation());

		super.setRotation(shape.getRotation());
		this.selected = shape;

		this.resizeHandles.addAll(this.initResizeHandles(this.selected));
	}

	public S getShape() {
		return this.selected;
	}

	@Override
	public void setLocation(Point center) {
		super.setLocation(center);
		this.selected.setLocation(center);
	}

	@Override
	public double getRotation() {
		return this.selected.getRotation();
	}

	@Override
	public void setRotation(double rotation) {
		super.setRotation(rotation);
		this.selected.setRotation(rotation);
	}

	@Override
	public Color getColor() {
		return this.selected.getColor();
	}

	@Override
	public void setColor(Color color) {
		this.selected.setColor(color);
	}

	@Override
	protected boolean doesContain(double x, double y, double scaleFactor) {
		// for now, just forward this to the internal shape; later we may want to also check
		//  the resize/rotate handles
		return this.selected.contains(x, y, scaleFactor);
	}

	public int getBoundsOffset() {
		return BOUNDS_OFFSET;
	}

	public List<ResizeHandle> getResizeHandles() {
		return resizeHandles;
	}

	public abstract Collection<? extends ResizeHandle> initResizeHandles(S shape);
}
