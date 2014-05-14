package cs355.mcqueen.keith.shapes;

/**
 * The <code>SelectedTriangle</code> class decorates a {@link Triangle} instance.
 *
 * Created by keith on 5/14/14.
 */
public class SelectedTriangle extends SelectedShape<Triangle> {
	protected SelectedTriangle(Triangle shape) {
		super(shape);
	}
}
