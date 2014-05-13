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
 * The <code>ShapeTool</code> class provides the base functionality for creating and
 * interacting with {@link Shape} instances.
 *
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

	final protected S getShape() {
		return this.shape;
	}

	final protected void setShape(S shape) {
		if (null != this.shape) {
			this.shape.removeShapelistener(this);
		}

		this.shape = shape;

		if (null != this.shape) {
			this.shape.addShapeListener(this);
			this.shape.setColor(this.color);

			Shapes.getInstance().addShape(this.shape);
			refresh();
		}
	}

	////////////////////////////////////////////////////////////
	// MouseListener methods
	////////////////////////////////////////////////////////////

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("AbstractShapeTool.mouseClicked");
		System.out.println("e = " + e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("AbstractShapeTool.mousePressed");
		System.out.println("e = " + e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("AbstractShapeTool.mouseReleased");
		System.out.println("e = " + e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("AbstractShapeTool.mouseEntered");
		System.out.println("e = " + e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("AbstractShapeTool.mouseExited");
		System.out.println("e = " + e);
	}

	////////////////////////////////////////////////////////////
	// MouseMotionListener methods
	////////////////////////////////////////////////////////////

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("AbstractShapeTool.mouseDragged");
		System.out.println("e = " + e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("AbstractShapeTool.mouseMoved");
		System.out.println("e = " + e);
	}

	////////////////////////////////////////////////////////////
	// ShapeListener methods
	////////////////////////////////////////////////////////////

	@Override
	public void shapeChanged(Shape shape) {
		refresh();
	}
}
