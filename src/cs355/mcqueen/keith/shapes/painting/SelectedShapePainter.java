package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.Transformations;
import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.ResizeHandle;
import cs355.mcqueen.keith.shapes.RotateHandle;
import cs355.mcqueen.keith.shapes.SelectedShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.Transformations.worldToView;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.painting.ShapePainter.Factory.getPainterForShape;
import static java.awt.BasicStroke.CAP_BUTT;
import static java.awt.BasicStroke.JOIN_MITER;

/**
 * The <code>SelectedShapePainter</code> class paints the UI decorations for a {@link SelectedShape}.
 * <p>
 * Created by keith on 5/14/14.
 */
public class SelectedShapePainter<S extends SelectedShape> extends AbstractBaseShapePainter<S> {
	@Override
	protected final void doPaint(S shape, Graphics2D g2d) {
		// paint the bounding box
		this.paintOutline(shape, g2d);

		// paint the resize handles
		this.paintResizeHandles(shape, g2d);

		// paint the rotate handle
		this.paintRotateHandle(shape, g2d);
	}

	protected void paintRotateHandle(S shape, Graphics2D g2d) {
		RotateHandle handle = shape.getRotateHandle();
		if (null != handle) {
			ShapePainter painter = getPainterForShape(handle.getClass());
			if (null != painter) {
				painter.paint(handle, g2d);
			}
		}
	}

	protected void paintResizeHandles(S shape, Graphics2D g2d) {
		List<ResizeHandle> handles = shape.getResizeHandles();
		for (ResizeHandle handle : handles) {
			ShapePainter painter = getPainterForShape(handle.getClass());
			if (null != painter) {
				painter.paint(handle, g2d);
			}
		}
	}

	protected void paintOutline(S shape, Graphics2D g2d) {
		// clear the transform
		g2d.setTransform(new AffineTransform());

		// use the resize handles as the vertices of the polygon
		List<ResizeHandle> handles = shape.getResizeHandles();
		int[] xCoords = new int[handles.size()];
		int[] yCoords = new int[handles.size()];
		for (int i = 0; i < yCoords.length; i++) {
			ResizeHandle handle = handles.get(i);
			Point handleLoc = transformPoint(worldToView(), handle.getLocation());
			xCoords[i] = (int) handleLoc.getCoordinate(X);
			yCoords[i] = (int) handleLoc.getCoordinate(Y);
		}

		// paint a polygon
		g2d.setStroke(new BasicStroke(2.0f, CAP_BUTT, JOIN_MITER, 10.0f, new float[]{5.0f}, 0.0f));
		g2d.setColor(Color.black);
		g2d.drawPolygon(xCoords, yCoords, xCoords.length);

		// paint a polygon
		g2d.setStroke(new BasicStroke(1.0f, CAP_BUTT, JOIN_MITER, 10.0f, new float[]{5.0f}, 0.0f));
		g2d.setColor(Color.white);
		g2d.drawPolygon(xCoords, yCoords, xCoords.length);
	}

	@Override
	protected AffineTransform getDrawingTransform(S shape) {
		return Transformations.shapeToView(shape.getShape());
	}
}
