package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.SelectedShape;
import cs355.mcqueen.keith.shapes.Shape;
import cs355.mcqueen.keith.shapes.Shapes;

import java.awt.*;
import java.awt.event.MouseEvent;

import static cs355.GUIFunctions.changeSelectedColor;
import static cs355.GUIFunctions.refresh;
import static cs355.mcqueen.keith.shapes.SelectedShape.Factory.getSelectedShape;

/**
 * The <code>SelectionTool</code> class is used to select a shape in order to transform it
 * (move it, rotate it, scale, change its color) in some way.
 *
 * Created by keith on 5/13/14.
 */
public class SelectionTool extends ShapeTool<SelectedShape> /*implements KeyTool*/ {

	private Handle activeHandle;

	public SelectionTool(Color color) {
		super(color);
	}

	@Override
	protected void shapeWasSet(SelectedShape shape) {
		Shapes.getInstance().setSelectedShape(shape);

		if (null != shape) {
			changeSelectedColor(shape.getColor());
		} else {
			changeSelectedColor(this.getColor());
		}

		// force a repaint of the shapes to update the display of the selected (or not) shape
		refresh();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// clear the active handle
		this.activeHandle = null;

		// have the model return the shape (if any) where the mouse was clicked
		Shape shapeAtLocation =
				Shapes.getInstance().getShapeAt(new Point(e.getX(), e.getY()));
		SelectedShape selectedShape = getSelectedShape(shapeAtLocation);

		this.setShape(selectedShape);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (null != this.activeHandle) {
			this.activeHandle.mouseDragged(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (null != this.getShape()) {
			Shape shape = this.getShape().getShapeAt(new Point(e.getX(), e.getY()));
			if (shape instanceof Handle) {
				this.activeHandle = (Handle) shape;
				this.activeHandle.mousePressed(e);
			} else {
				this.activeHandle = null;
			}
		} else {
			this.activeHandle = null;
		}
	}

//	@Override
//	public KeyTool getKeyTool() {
//		return this;
//	}
//
//	@Override
//	public void keyPressed(Iterator<Integer> iterator) {
//		while (iterator.hasNext()) {
//			Integer key = iterator.next();
//			switch (key) {
//				case KeyEvent.VK_DELETE:
//				case KeyEvent.VK_BACK_SPACE:
//					// remove the selected shape from the model
//					Shapes.getInstance().removeShape(this.getShape().getShape());
//					Shapes.getInstance().setSelectedShape(null);
//
//					GUIFunctions.refresh();
//			}
//		}
//	}
}
