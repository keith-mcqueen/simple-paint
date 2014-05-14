package cs355.mcqueen.keith.shapes;

/**
 * The <code>SelectedRectangle</code> class decorates an instance of {@link Rectangle}
 * (which includes rectangles, squares, ellipses and circles).
 *
 * Created by keith on 5/14/14.
 */
public class SelectedRectangle extends SelectedShape<Rectangle> {
	protected SelectedRectangle(Rectangle shape) {
		super(shape);
	}
}
