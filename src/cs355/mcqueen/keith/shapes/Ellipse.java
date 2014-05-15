package cs355.mcqueen.keith.shapes;

import static cs355.mcqueen.keith.shapes.Point.X;
import static cs355.mcqueen.keith.shapes.Point.Y;
import static cs355.mcqueen.keith.shapes.Size.HEIGHT;
import static cs355.mcqueen.keith.shapes.Size.WIDTH;
import static java.lang.Math.pow;

/**
 * The <code>Ellipse</code> class is used to represent ellipses.
 *
 * Created by keith on 5/3/14.
 */
public class Ellipse extends Rectangle {
	public Ellipse(Point location, Size size) {
		super(location, size);
	}

	@Override
	protected boolean doesContain(Point p, double scaleFactor) {
		// let the super check the bounding box, if that fails, then there is no need to
		// check any further
		if (!super.doesContain(p, scaleFactor)) {
			return false;
		}

		// the point is at least within the bounding box, so check to see if it is actually
		// within the ellipse
		Size mySize = this.getSize();
		double halfWidth = mySize.getLength(WIDTH) / 2.0d;
		double halfHeight = mySize.getLength(HEIGHT) / 2.0d;

		return pow(p.getCoordinate(X) / halfWidth, 2) + pow(p.getCoordinate(Y) / halfHeight, 2) <= 1;
	}
}
