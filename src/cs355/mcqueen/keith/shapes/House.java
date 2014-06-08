package cs355.mcqueen.keith.shapes;

import cs355.HouseModel;

/**
 * The <code>House</code> class wraps the {@link HouseModel} into a {@link Shape}.
 *
 * Created by keith on 6/7/14.
 */
public class House extends Shape {
	private static final double INITIAL_X = 0.0;
	private static final double INITIAL_Y = 4.0;
	private static final double INITIAL_Z = -20.0;

	private final HouseModel houseModel = new HouseModel();

	public House() {
		super(new Point(INITIAL_X, INITIAL_Y, INITIAL_Z));
	}

	public HouseModel getModel() {
		return this.houseModel;
	}

	@Override
	protected boolean doesContain(Point p) {
		// always return false because we don't select the house
		return false;
	}
}
