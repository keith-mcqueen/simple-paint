package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Line;

import java.awt.*;


/**
 * The <code>LinePainter</code> class paints a {@link Line} instance in a 2-dimensional
 * context.
 *
 * Created by keith on 5/3/14.
 */
public class LinePainter extends AbstractBaseShapePainter<Line> {
	@Override
	public void doPaint(Line shape, Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawLine(0, -3, (int) shape.getLength(), -3);
	}
}
