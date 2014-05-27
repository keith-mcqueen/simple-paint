package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.ResizeHandle;

import java.awt.*;

/**
 * The <code>ResizeHandlePainter</code> class paints the specialized {@link ResizeHandle}
 * shape.
 * <p>
 * Created by keith on 5/14/14.
 */
public class ResizeHandlePainter extends RectanglePainter<ResizeHandle> {
	@Override
	public void paint(ResizeHandle shape, Graphics2D g2d) {
		super.paint(shape, g2d);
	}

	@Override
	protected void paintRectangle(Graphics2D g2d, int paintX, int paintY, int paintWidth, int paintHeight) {
		g2d.setStroke(new BasicStroke(1.0f));

		g2d.setColor(Color.white);
		g2d.fillRect(paintX, paintY, paintWidth, paintHeight);

		g2d.setColor(Color.black);
		g2d.drawRect(paintX, paintY, paintWidth, paintHeight);
	}
}
