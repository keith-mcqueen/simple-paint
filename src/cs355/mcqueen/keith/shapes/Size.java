package cs355.mcqueen.keith.shapes;

/**
 * The {@code Size} class is used to describe the dimensions of some object in an
 * n-dimensional space.  The number of dimensions for the object depends on the number
 * of coordinates provided to the constructor at the time of instantiation.
 *
 * To retrieve the lengths, call the {@link #getLength(int)} method, passing the
 * index of the dimension.  The dimension indices are 0-based.
 *
 * Created by keith on 5/2/14.
 */
public class Size {
	/**
	 * Convenience constant to retrieve the length in the X dimension (width).
	 */
	public static final int WIDTH = 0;

	/**
	 * Convenience constant to retrieve the length in the Y dimension (height).
	 */
	public static final int HEIGHT = 1;

	/**
	 * Convenience constant to retrieve the length in the Z dimension (depth).
	 */
	public static final int DEPTH = 2;

	// use a Point instance to store the lengths
	private final Point lengths;

	public Size(double ... lengths) {
		this.lengths = new Point(lengths);
	}

	public double getLength(int dimension) {
		return this.lengths.getCoordinate(dimension);
	}
}
