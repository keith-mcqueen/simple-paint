package cs355.mcqueen.keith.shapes;

import java.awt.*;

/**
 * The <code>SelectedShape</code> class decorates a given instance of another {@link Shape}.
 *
 * Created by keith on 5/14/14.
 */
public class SelectedShape<S extends Shape> extends Shape {
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

	protected SelectedShape(S shape) {
		super(shape.getCenter());

		this.selected = shape;
	}

	public S getShape() {
		return this.selected;
	}

	@Override
	public void setCenter(Point center) {
		this.selected.setCenter(center);
	}

	@Override
	public double getRotation() {
		return this.selected.getRotation();
	}

	@Override
	public void setRotation(double rotation) {
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
		//  the drag/rotate handles
		return this.selected.contains(x, y, scaleFactor);
	}
}
