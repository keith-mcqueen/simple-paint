package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.SelectedRectangle;

import java.awt.*;

import static java.awt.BasicStroke.CAP_BUTT;
import static java.awt.BasicStroke.JOIN_MITER;


/**
 * The <code>SelectedRectanglePainter</code> class paints the selection border around the selected shape's bounding
 * box.
 * <p>
 * Created by keith on 5/14/14.
 */
public class SelectedRectanglePainter extends AbstractBaseShapePainter<SelectedRectangle> {
	private final BoundingBoxPainter boundingBoxPainter = new BoundingBoxPainter();

	@Override
	protected void doPaint(SelectedRectangle shape, Graphics2D g2d) {
		this.boundingBoxPainter.paint(shape.getShape(), g2d);
	}

	private static class BoundingBoxPainter extends RectanglePainter {
		@Override
		protected void paintRectangle(Graphics2D g2d, int paintX, int paintY, int paintWidth, int paintHeight) {
			g2d.setStroke(new BasicStroke(1.0f,	CAP_BUTT,	JOIN_MITER,	10.0f, new float[]{5.0f}, 0.0f));

			int borderX = paintX - (paintX % 2 == 0 ? 2 : 2);
			int borderY = paintY - (paintY % 2 == 0 ? 2 : 2);
			int borderWidth = paintWidth + (paintWidth % 2 == 0 ? 4 : 4);
			int borderHeight = paintHeight + (paintHeight % 2 == 0 ? 4 : 4);

			g2d.setColor(Color.white);
			g2d.drawRect(borderX, borderY, borderWidth, borderHeight);

			g2d.setColor(Color.black);
			g2d.drawRect(borderX + 1, borderY + 1, borderWidth - 2, borderHeight - 2);
		}
	}
}
