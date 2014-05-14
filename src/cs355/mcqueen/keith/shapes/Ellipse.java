package cs355.mcqueen.keith.shapes;

import static cs355.mcqueen.keith.shapes.Size.*;
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
	protected boolean doesContain(double x, double y, double scaleFactor) {
		// let the super check the bounding box, if that fails, then there is no need to
		// ckeck any further
		if (false == super.doesContain(x, y, scaleFactor)) {
			return false;
		}

		// the point is at least within the bounding box, so check to see if it is actually
		// within the ellipse
		Size mySize = this.getSize();
		double halfWidth = mySize.getLength(WIDTH) / 2.0d;
		double halfHeight = mySize.getLength(HEIGHT) / 2.0d;

		return  pow(x / halfWidth, 2) + pow(y / halfHeight, 2) <= 1;
	}
}
