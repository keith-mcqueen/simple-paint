package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.RotateHandle;

import java.awt.*;

/**
 * The <code>RotateHandlePainter</code> class paints the specialized {@link RotateHandle} shape.
 * <p>
 * Created by keith on 5/14/14.
 */
public class RotateHandlePainter extends EllipsePainter {
	@Override
	protected void paintRectangle(Graphics2D g2d, int paintX, int paintY, int paintWidth, int paintHeight) {
		g2d.setColor(Color.white);
		g2d.fillOval(paintX, paintY, paintWidth, paintHeight);

		g2d.setStroke(new BasicStroke(1.0f));
		g2d.setColor(Color.black);
		g2d.drawOval(paintX, paintY, paintWidth, paintHeight);
	}
}
