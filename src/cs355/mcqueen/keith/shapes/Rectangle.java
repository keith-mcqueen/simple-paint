package cs355.mcqueen.keith.shapes;

/**
 * The <code>Rectangle</code> class represents a right parallelogram.
 *
 * Created by keith on 5/3/14.
 */
public class Rectangle extends Shape {
	private Size size;

	public Rectangle(Point location, Size size) {
		super(location);

		this.size = size;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;

		this.changed();
	}
}
