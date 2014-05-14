package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Shape;
import cs355.mcqueen.keith.shapes.Shapes;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The <code>SelectionTool</code> class is used to select a shape in order to transform it
 * (move it, rotate it, scale, change its color) in some way.
 *
 * Created by keith on 5/13/14.
 */
public class SelectionTool extends ShapeTool<Shape> {
	private double scaleFactor = 1.0;

	public SelectionTool(Color color) {
		super(color);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		// check if the click happened in the selected shape (do this here or do this in the model?)

		// have the model return the shape (if any) where the mouse clicked
		Shape selectedShape = Shapes.getInstance().getShapeAt(e.getX(), e.getY(), this.scaleFactor);
		System.out.println("selectedShape = " + selectedShape);

		// if a shape was selected, then update my selected shape
		//  - if a previous shape was selected, deselect
		//  - set the currently selected shape
		//  - update the current color
		// otherwise
		//  - reset the current color
		//  - clear the selected shape
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		// determine what to do based on where the mouse was pressed:
		//  - inside the selected shape --> prepare to move the shape
		//  - on a resize handle --> prepare to resize the shape
		//  - on the rotation handle --> prepare to rotate the shape
	}
}
