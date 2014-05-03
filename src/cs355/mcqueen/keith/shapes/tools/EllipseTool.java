package cs355.mcqueen.keith.shapes.tools;

import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Size;
import cs355.mcqueen.keith.shapes.Ellipse;

import java.awt.*;

/**
 * Created by keith on 5/3/14.
 */
public class EllipseTool extends RectangleTool {
	public EllipseTool(Color color) {
		super(color);
	}

	@Override
	protected Rectangle createShape() {
		return new Ellipse(this.getOriginal(), new Size(0, 0));
	}
}
