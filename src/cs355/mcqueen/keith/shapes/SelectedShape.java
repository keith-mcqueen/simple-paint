package cs355.mcqueen.keith.shapes;

import cs355.mcqueen.keith.shapes.tools.Handle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cs355.GUIFunctions.refresh;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;

/**
 * The <code>SelectedShape</code> class decorates a given instance of another {@link Shape}.
 *
 * Created by keith on 5/14/14.
 */
public abstract class SelectedShape<S extends Shape> extends Shape implements Handle {
	private static final int BOUNDS_OFFSET = 2;
	private static final int ROTATE_OFFSET = 20;
	private Point mouseOffset;

	public static final class Factory {
		public static SelectedShape getSelectedShape(Shape shape) {
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
	private RotateHandle rotateHandle;


	protected SelectedShape(S shape) {
		super(shape.getLocation());

		super.setRotation(shape.getRotation());
		this.selected = shape;

		this.initHandles();
	}

	private void initHandles() {
		this.resizeHandles.addAll(this.initResizeHandles(this.selected));
		this.rotateHandle = this.initRotateHandle(this.selected);
	}

	public S getShape() {
		return this.selected;
	}

	@Override
	public void setLocation(Point center) {
		super.setLocation(center);
		this.selected.setLocation(center);

		// clear the handles and re-init them
		// TODO - If I could update the handles, that might be better
		this.clearHandles();
		this.initHandles();
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
	public boolean contains(Point p, double scaleFactor) {
		// check the handles

		if (null != this.rotateHandle && this.rotateHandle.contains(p, scaleFactor)) {
			return true;
		}

		List<ResizeHandle> resizeHandles = this.getResizeHandles();
		for (ResizeHandle handle : resizeHandles) {
			if (handle.contains(p, scaleFactor)) {
				return true;
			}
		}

		// check the selected shape

		return this.selected.contains(p, scaleFactor);
	}

	public Shape getShapeAt(Point p, double scaleFactor) {
		// check the handles

		if (null != this.rotateHandle && this.rotateHandle.contains(p, scaleFactor)) {
			return this.rotateHandle;
		}

		List<ResizeHandle> resizeHandles = this.getResizeHandles();
		for (ResizeHandle handle : resizeHandles) {
			if (handle.contains(p, scaleFactor)) {
				return handle;
			}
		}

		if (this.selected.contains(p, scaleFactor)) {
			return this;
		}

		return null;
	}

	public int getBoundsOffset() {
		return BOUNDS_OFFSET;
	}

	public int getRotateOffset() {
		return ROTATE_OFFSET;
	}

	@Override
	protected boolean doesContain(Point p, double scaleFactor) {
		return false;
	}

	public List<ResizeHandle> getResizeHandles() {
		return resizeHandles;
	}

	public RotateHandle getRotateHandle() {
		return this.rotateHandle;
	}

	private void clearHandles() {
		this.rotateHandle = null;
		this.resizeHandles.clear();
	}

	protected abstract Collection<? extends ResizeHandle> initResizeHandles(S shape);

	protected abstract RotateHandle initRotateHandle(S shape);

	////////////////////////////////////////
	// Handle implementation
	////////////////////////////////////////

	@Override
	public void mousePressed(MouseEvent e) {
		this.mouseOffset = this.transformPointToShape(new Point(e.getX(), e.getY()));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point mouseLoc = this.transformPointToShape(new Point(e.getX(), e.getY()));
		Point newLocation = this.transformPointToWorld(new Point(mouseLoc.getCoordinate(X) - this.mouseOffset.getCoordinate(X),
				mouseLoc.getCoordinate(Y) - this.mouseOffset.getCoordinate(Y)));

		this.setLocation(newLocation);

		refresh();
	}
}
