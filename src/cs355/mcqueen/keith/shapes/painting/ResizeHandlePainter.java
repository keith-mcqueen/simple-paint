package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.ResizeHandle;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.Transformations.worldToView;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.ResizeHandle.SIZE;

/**
 * The <code>ResizeHandlePainter</code> class paints the specialized {@link ResizeHandle}
 * shape.
 * <p>
 * Created by keith on 5/14/14.
 */
public class ResizeHandlePainter implements ShapePainter<ResizeHandle> {
	@Override
	public void paint(ResizeHandle handle, Graphics2D g2d) {
		// reset the transform (we will draw the handle without the aid of a transform)
		g2d.setTransform(new AffineTransform());

		// set the stroke
		g2d.setStroke(new BasicStroke(1.0f));

		// calculate the handle's X and Y
		Point handleLoc = transformPoint(worldToView(), handle.getLocation());
		int handle_x = (int) Math.floor(handleLoc.getCoordinate(X) - SIZE / 2);
		int handle_y = (int) Math.floor(handleLoc.getCoordinate(Y) - SIZE / 2);

		// fill a white rectangle
		g2d.setColor(Color.white);
		g2d.fillRect(handle_x, handle_y, SIZE, SIZE);

		// outline the rectangle in black
		g2d.setColor(Color.black);
		g2d.drawRect(handle_x, handle_y, SIZE, SIZE);
	}
}
