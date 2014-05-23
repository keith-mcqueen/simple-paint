package cs355.mcqueen.keith.shapes;

import cs355.mcqueen.keith.shapes.tools.RectangleTool;
import cs355.mcqueen.keith.shapes.tools.ShapeTool;

import java.awt.event.MouseEvent;

import static cs355.mcqueen.keith.Transformations.transformPointFromShapeCoordinates;
import static cs355.mcqueen.keith.Transformations.transformPointToShapeCoordinates;
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

	public RectangleResizeHandle(Point location, Size size, Rectangle shape, Corner corner) {
		super(location, size, shape);

		this.corner = corner;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Rectangle shape = this.getShape();
		this.fixedPoint = this.corner.getOpposite().getLocation(shape);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO I need to fix this for when the shape is rotated
		Object obj = this.getShape().getUserObject(ShapeTool.class);
		if (!(obj instanceof RectangleTool)) {
			return;
		}

		((RectangleTool) obj).updateRectangle(e, this.fixedPoint, this.getShape());
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
			Point rectCenter = transformPointToShapeCoordinates(rectangle.getLocation(), rectangle);
			Size rectSize = rectangle.getSize();

			Point p = new Point(rectCenter.getCoordinate(X) + (this.widthFactor * (rectSize.getLength(WIDTH) / 2.0)),
					rectCenter.getCoordinate(Y) - (this.heightFactor * (rectSize.getLength(HEIGHT) / 2.0)));

			return transformPointFromShapeCoordinates(p, rectangle);
		}

		protected Corner getOpposite() {
			return null;
		}
	}
}
