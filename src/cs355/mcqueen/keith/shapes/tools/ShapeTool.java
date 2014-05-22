package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Shape;
import cs355.mcqueen.keith.shapes.ShapeListener;
import cs355.mcqueen.keith.shapes.Shapes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static cs355.GUIFunctions.refresh;

/**
 * The <code>ShapeTool</code> class provides the base functionality for creating and interacting with {@link Shape}
 * instances.
 * <p>
 * Created by keith on 5/3/14.
 */
public class ShapeTool<S extends Shape> implements ShapeListener, MouseListener, MouseMotionListener {
	private S shape;

	private Color color;

	public ShapeTool(Color color) {
		this.setColor(color);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	final protected S getShape() {
		return this.shape;
	}

	final protected void setShape(S shape) {
		if (null != this.shape) {
			this.shape.removeShapeListener(this);
			this.shape.setUserObject(ShapeTool.class, null);
		}

		this.shape = shape;

		if (null != this.shape) {
			this.shape.addShapeListener(this);
			this.shape.setUserObject(ShapeTool.class, this);
		}

		this.shapeWasSet(shape);
	}

	protected void shapeWasSet(S shape) {
		Shapes.getInstance().addShape(shape);
		shape.setColor(this.getColor());
		refresh();
	}

	public void deactivate() {
		// no op
	}

	////////////////////////////////////////////////////////////
	// MouseListener methods
	////////////////////////////////////////////////////////////

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	////////////////////////////////////////////////////////////
	// MouseMotionListener methods
	////////////////////////////////////////////////////////////

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	////////////////////////////////////////////////////////////
	// ShapeListener methods
	////////////////////////////////////////////////////////////

	@Override
	public void shapeChanged(Shape shape) {
		refresh();
	}
}
