package cs355.mcqueen.keith;

import cs355.ViewRefresher;
import cs355.mcqueen.keith.shapes.Shape;
import cs355.mcqueen.keith.shapes.Shapes;
import cs355.mcqueen.keith.shapes.painting.ShapePainter;

import java.awt.*;

import static cs355.mcqueen.keith.shapes.painting.ShapePainter.Factory.getPainterForShape;


/**
 * The <code>CanvasRefresher</code> class causes all shapes to be repainted.
 *
 * Created by keith on 5/2/14.
 */
public class CanvasRefresher implements ViewRefresher {
	@Override
	public void refreshView(Graphics2D g2d) {
		for (Shape shape : Shapes.getInstance()) {
			ShapePainter painter = getPainterForShape(shape.getClass());
			if (null != painter) {
				painter.paint(shape, g2d);
			}
		}
	}
}
