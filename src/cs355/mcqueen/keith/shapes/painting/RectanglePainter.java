package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Size;

import java.awt.*;

import static cs355.mcqueen.keith.shapes.Size.HEIGHT;
import static cs355.mcqueen.keith.shapes.Size.WIDTH;

/**
 * The <code>RectanglePainter</code> class is used to paint a rectangle in a 2-dimensional
 * space.
 *
 * Created by keith on 5/3/14.
 */
public class RectanglePainter<S extends Rectangle> extends AbstractBaseShapePainter<S> {
	@Override
	public void doPaint(Rectangle shape, Graphics2D g2d) {
		Size size = shape.getSize();

		int paintX = (int) Math.floor(-size.getLength(WIDTH) / 2.0d);
		int paintY = (int) Math.floor(-size.getLength(HEIGHT) / 2.0d);
		int paintWidth = (int) size.getLength(WIDTH);
		int paintHeight = (int) size.getLength(HEIGHT);

		paintRectangle(g2d, paintX, paintY, paintWidth, paintHeight);
	}

	protected void paintRectangle(Graphics2D g2d, int paintX, int paintY, int paintWidth, int paintHeight) {
		g2d.fillRect(paintX, paintY, paintWidth, paintHeight);
	}


}
