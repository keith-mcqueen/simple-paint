package cs355.mcqueen.keith.shapes;

/**
 * The <code>SelectedLine</code> class decorates an instance of {@link Line}.
 *
 * Created by keith on 5/14/14.
 */
public class SelectedLine extends SelectedShape<Line> {
	protected SelectedLine(Line shape) {
		super(shape);
	}
}
