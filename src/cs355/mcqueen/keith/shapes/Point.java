package cs355.mcqueen.keith.shapes;

/**
 * The {@code Point} class represents a location in some n-dimensional space.  The number of
 * dimensions for the point depends on the number of coordinates provided to the constructor
 * at the time of instantiation.
 *
 * To create a one-dimensional point, just provide one coordinate, two coordinates for a
 * point in 2D space, three coordinates for a point in 3D space, etc.
 *
 * To retrieve the coordinates, call the {@link #getCoordinate(int)} method passing the
 * index of the dimension.  The dimension indices are 0-based.
 *
 * Created by keith on 5/2/14.
 */
public class Point {
	/**
	 * Convenience constant to retrieve the coordinate in the X dimension, usually the 0th
	 * coordinate.
	 */
	public static final int X = 0;

	/**
	 * Convenience constant to retrieve the coordinate in the Y dimension, usually the 1st
	 * coordinate.
	 */
	public static final int Y = 1;

	/**
	 * Convenience constant to retrieve the coordinate in the Z dimension, usually the 2nd
	 * coordinate.
	 */
	public static final int Z = 2;

	private final double[] coordinates;

	public Point(double... coordinates) {
		this.coordinates = coordinates;
	}

	public int getDimensions() {
		return this.coordinates.length;
	}

	public double getCoordinate(int dimension) {
		return  this.getCoordinate(dimension, 0.0);
	}

	public double getCoordinate(int dimension, double defVal) {
		return this.coordinates.length > dimension ? this.coordinates[dimension] : defVal;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder("(");

		for (int i = 0; i < coordinates.length; i++) {
			if (i > 0) {
				out.append(", ");
			}

			double coordinate = coordinates[i];
			out.append(String.valueOf(coordinate));
		}

		out.append(")");

		return out.toString();
	}
}
