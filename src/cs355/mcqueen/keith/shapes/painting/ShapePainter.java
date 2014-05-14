package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.*;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Shape;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The <code>ShapePainter</code> interface defines the contract for an object that can paint
 * a {@link Shape} instance in a 2-dimensional context.
 *
 * Created by keith on 5/3/14.
 */
public interface ShapePainter<S extends Shape> {
	/**
	 * Paint the given shape in the given graphics context
	 * @param shape the shape to be painted
	 * @param g2d the graphics context in which to paint the shape
	 */
	public void paint(S shape, Graphics2D g2d);

	public static final class Factory {
		private static final Map<Class<? extends Shape>, ShapePainter<? extends Shape>> PAINTERS_BY_SHAPE_CLASS =
				new HashMap<>();
		static {
			PAINTERS_BY_SHAPE_CLASS.put(Line.class, new LinePainter());
			PAINTERS_BY_SHAPE_CLASS.put(Rectangle.class, new RectanglePainter());
			PAINTERS_BY_SHAPE_CLASS.put(Ellipse.class, new EllipsePainter());
			PAINTERS_BY_SHAPE_CLASS.put(Triangle.class, new TrianglePainter());
			PAINTERS_BY_SHAPE_CLASS.put(SelectedRectangle.class, new SelectedShapePainter<>());
			PAINTERS_BY_SHAPE_CLASS.put(SelectedTriangle.class, new SelectedShapePainter<>());
			PAINTERS_BY_SHAPE_CLASS.put(SelectedLine.class, new SelectedLinePainter());
			PAINTERS_BY_SHAPE_CLASS.put(ResizeHandle.class, new ResizeHandlePainter());
		}

		public static ShapePainter<? extends Shape> getPainterForShape(Class<? extends Shape> shapeClass) {
			return PAINTERS_BY_SHAPE_CLASS.get(shapeClass);
		}
	}
}
