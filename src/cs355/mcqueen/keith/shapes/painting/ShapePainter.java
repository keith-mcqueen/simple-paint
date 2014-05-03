package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.*;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Shape;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by keith on 5/3/14.
 */
public interface ShapePainter<S extends Shape> {
	public void paint(S shape, Graphics2D g2d);

	public static final class Factory {
		private static final Map<Class<? extends Shape>, ShapePainter<? extends Shape>> PAINTERS_BY_SHAPE_CLASS =
				new HashMap<>();
		static {
			PAINTERS_BY_SHAPE_CLASS.put(Line.class, new LinePainter());
			PAINTERS_BY_SHAPE_CLASS.put(Rectangle.class, new RectanglePainter());
			PAINTERS_BY_SHAPE_CLASS.put(Ellipse.class, new EllipsePainter());
			PAINTERS_BY_SHAPE_CLASS.put(Triangle.class, new TrianglePainter());
		}

		public static ShapePainter<? extends Shape> getPainterForShape(Class<? extends Shape> shapeClass) {
			return PAINTERS_BY_SHAPE_CLASS.get(shapeClass);
		}
	}
}
