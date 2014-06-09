package cs355.mcqueen.keith.shapes;

import cs355.HouseModel;

import static cs355.mcqueen.keith.shapes.Point.*;
import static java.lang.Math.*;

/**
 * The <code>House</code> class wraps the {@link HouseModel} into a {@link Shape}.
 *
 * Created by keith on 6/7/14.
 */
public class House extends Shape {
	private static final double INITIAL_X = 0.0;
	private static final double INITIAL_Y = 4.0;
	private static final double INITIAL_Z = -20.0;
	private static final double _90_DEGREES = PI / 2.0;

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

	public void forward(double distance) {
		Point oldLoc = this.getLocation();
		double theta = this.getRotation();

		double x = oldLoc.getCoordinate(X);
		double y = oldLoc.getCoordinate(Y);
		double z = oldLoc.getCoordinate(Z);

		x -= distance * sin(theta);
		z += distance * cos(theta);

		this.setLocation(new Point(x, y, z));
	}

	public void back(double distance) {
		Point oldLoc = this.getLocation();
		double theta = this.getRotation();

		double x = oldLoc.getCoordinate(X);
		double y = oldLoc.getCoordinate(Y);
		double z = oldLoc.getCoordinate(Z);

		x += distance * sin(theta);
		z -= distance * cos(theta);

		this.setLocation(new Point(x, y, z));
	}

	public void left(double distance) {
		Point oldLoc = this.getLocation();
		double theta = this.getRotation();

		double x = oldLoc.getCoordinate(X);
		double y = oldLoc.getCoordinate(Y);
		double z = oldLoc.getCoordinate(Z);

		x += distance * sin(theta - _90_DEGREES);
		z -= distance * cos(theta - _90_DEGREES);

		this.setLocation(new Point(x, y, z));
	}

	public void right(double distance) {
		Point oldLoc = this.getLocation();
		double theta = this.getRotation();

		double x = oldLoc.getCoordinate(X);
		double y = oldLoc.getCoordinate(Y);
		double z = oldLoc.getCoordinate(Z);

		x += distance * (float) sin(theta + _90_DEGREES);
		z -= distance * (float) cos(theta + _90_DEGREES);

		this.setLocation(new Point(x, y, z));
	}

	public void rotate(double d_theta) {
		this.setRotation(this.getRotation() + d_theta);
	}

	public void fly(double distance) {
		Point oldLoc = this.getLocation();

		double x = oldLoc.getCoordinate(X);
		double y = oldLoc.getCoordinate(Y);
		double z = oldLoc.getCoordinate(Z);

		y += distance;

		this.setLocation(new Point(x, y, z));
	}
}
