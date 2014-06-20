package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Ellipse;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Size;

import java.awt.*;

/**
 * The <code>CircleTool</code> creates circles in a 2-dimensional context.
 *
 * Created by keith on 5/3/14.
 */
public class CircleTool extends SquareTool {
	public CircleTool(Color color) {
		super(color);
	}

	@Override
	protected Rectangle createShape() {
		return new Ellipse(this.getOriginal(), new Size(0, 0));
	}
}
