package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Ellipse;

import java.awt.*;

/**
 * The <code>EllipsePainter</code> class paints a given {@link Ellipse} instance.
 *
 * Created by keith on 5/3/14.
 */
public class EllipsePainter extends RectanglePainter<Ellipse>{
	@Override
	protected void paintRectangle(Graphics2D g2d, int paintX, int paintY, int paintWidth, int paintHeight) {
		g2d.fillOval(paintX, paintY, paintWidth, paintHeight);
	}
}
