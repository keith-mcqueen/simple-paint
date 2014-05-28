package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.*;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Shape;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The <code>ShapePainter</code> interface defines the contract for an object that can paint a {@link Shape} instance in
 * a 2-dimensional context.
 * <p>
 * Created by keith on 5/3/14.
 */
public interface ShapePainter<S extends Shape> {
	/**
	 * Paint the given shape in the given graphics context
	 *
	 * @param shape the shape to be painted
	 * @param g2d   the graphics context in which to paint the shape
	 */
	public void paint(S shape, Graphics2D g2d);

	public static final class Factory {
		private static final Map<Class<? extends Shape>, ShapePainter<? extends Shape>> PAINTERS_BY_SHAPE_CLASS =
				new HashMap<>();

		static {
			PAINTERS_BY_SHAPE_CLASS.put(Line.class, new LinePainter());
			PAINTERS_BY_SHAPE_CLASS.put(Rectangle.class, new RectanglePainter());
			PAINTERS_BY_SHAPE_CLASS.put(Ellipse.class, new EllipsePainter());
			PAINTERS_BY_SHAPE_CLASS.put(Triangle2.class, new Triangle2Painter());
			PAINTERS_BY_SHAPE_CLASS.put(SelectedRectangle.class, new SelectedShapePainter<>());
			PAINTERS_BY_SHAPE_CLASS.put(SelectedTriangle2.class, new SelectedShapePainter<>());
			PAINTERS_BY_SHAPE_CLASS.put(SelectedLine.class, new SelectedLinePainter());
			PAINTERS_BY_SHAPE_CLASS.put(ResizeHandle.class, new ResizeHandlePainter());
			PAINTERS_BY_SHAPE_CLASS.put(RotateHandle.class, new RotateHandlePainter());
		}

		public static ShapePainter<? extends Shape> getPainterForShape(Class<? extends Shape> shapeClass) {
			ShapePainter<? extends Shape> painter = PAINTERS_BY_SHAPE_CLASS.get(shapeClass);
			if (null != painter) {
				return painter;
			}

			Class<?> superclass = shapeClass.getSuperclass();
			if (null != superclass) {
				return getPainterForShape((Class<? extends Shape>) superclass);
			}

			return null;
		}
	}
}
