package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.ResizeHandle;
import cs355.mcqueen.keith.shapes.RotateHandle;
import cs355.mcqueen.keith.shapes.SelectedTriangle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

import static cs355.mcqueen.keith.Transformations.getWorldToViewTransform;
import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.RotateHandle.SIZE;
import static java.awt.BasicStroke.CAP_BUTT;
import static java.awt.BasicStroke.JOIN_MITER;

/**
 * The <code>SelectedTrianglePainter</code> class handles painting
 * {@link SelectedTriangle} instances.
 *
 * Created by keith on 5/27/14.
 */
public class SelectedTrianglePainter extends SelectedShapePainter<SelectedTriangle> {
	@Override
	protected AffineTransform getDrawingTransform(SelectedTriangle shape) {
		return getWorldToViewTransform();
	}

	@Override
	protected void paintOutline(SelectedTriangle shape, Graphics2D g2d) {
		// clear the transform
		g2d.setTransform(new AffineTransform());

		// use the resize handles as the vertices of the polygon
		List<ResizeHandle> handles = shape.getResizeHandles();
		int[] xCoords = new int[handles.size()];
		int[] yCoords = new int[handles.size()];
		for (int i = 0; i < yCoords.length; i++) {
			ResizeHandle handle = handles.get(i);
			Point handleLoc = transformPoint(getWorldToViewTransform(), handle.getLocation());
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
	protected void paintRotateHandle(SelectedTriangle shape, Graphics2D g2d) {
		RotateHandle handle = shape.getRotateHandle();
		// reset the transform (we will draw the handle without the aid of a transform)
		g2d.setTransform(new AffineTransform());

		// set the stroke
		g2d.setStroke(new BasicStroke(1.0f));

		// calculate the handle's X and Y
		Point handleLoc = transformPoint(getWorldToViewTransform(), handle.getLocation());
		int handle_x = (int) Math.floor(handleLoc.getCoordinate(X) - SIZE / 2);
		int handle_y = (int) Math.floor(handleLoc.getCoordinate(Y) - SIZE / 2);

		// fill a white rectangle
		g2d.setColor(Color.white);
		g2d.fillOval(handle_x, handle_y, SIZE, SIZE);

		// outline the rectangle in black
		g2d.setColor(Color.black);
		g2d.drawOval(handle_x, handle_y, SIZE, SIZE);
	}
}
