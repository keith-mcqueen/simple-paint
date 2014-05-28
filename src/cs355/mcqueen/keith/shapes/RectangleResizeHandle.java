package cs355.mcqueen.keith.shapes;

import cs355.mcqueen.keith.shapes.tools.RectangleTool;
import cs355.mcqueen.keith.shapes.tools.ShapeTool;

import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.Transformations.*;
import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.Size.HEIGHT;
import static cs355.mcqueen.keith.shapes.Size.WIDTH;

/**
 * The <code>RectangleResizeHandle</code> resizes a given {@link Rectangle}.
 * <p>
 * Created by keith on 5/21/14.
 */
public class RectangleResizeHandle extends ResizeHandle<Rectangle> {
	private Corner corner;
	private Point fixedPoint;

	public RectangleResizeHandle(Point location, Rectangle shape, Corner corner) {
		super(location, shape);

		this.corner = corner;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Rectangle shape = this.getShape();
		this.fixedPoint = this.corner.getOpposite().getLocation(shape);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Rectangle rectangle = this.getShape();

		Object obj = rectangle.getUserObject(ShapeTool.class);
		if (!(obj instanceof RectangleTool)) {
			return;
		}

		((RectangleTool) obj).updateRectangle(e, this.fixedPoint, rectangle);
	}

	public static enum Corner {
		NORTHEAST(1, 1) {
			@Override
			protected Corner getOpposite() {
				return SOUTHWEST;
			}
		},
		SOUTHEAST(1, -1) {
			@Override
			protected Corner getOpposite() {
				return NORTHWEST;
			}
		},
		SOUTHWEST(-1, -1) {
			@Override
			protected Corner getOpposite() {
				return NORTHEAST;
			}
		},
		NORTHWEST(-1, 1) {
			@Override
			protected Corner getOpposite() {
				return SOUTHEAST;
			}
		};

		private int widthFactor;
		private int heightFactor;

		private Corner(int widthFactor, int heightFactor) {
			this.widthFactor = widthFactor;
			this.heightFactor = heightFactor;
		}

		private Point getLocation(Rectangle rectangle) {
			Point rectCenter = transformPoint(worldToShape(rectangle), rectangle.getLocation());
			Size rectSize = rectangle.getSize();

			double width = rectSize.getLength(WIDTH) / 2.0;
			double height = rectSize.getLength(HEIGHT) / 2.0;
			double x = rectCenter.getCoordinate(X);
			double y = rectCenter.getCoordinate(Y);
			Point p = new Point(x + (this.widthFactor * width),	y - (this.heightFactor * height));

			return transformPoint(shapeToWorld(rectangle), p);
		}

		protected Corner getOpposite() {
			return null;
		}
	}
}
