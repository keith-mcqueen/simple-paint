package cs355.mcqueen.keith.shapes;

/**
 * Created by keith on 5/3/14.
 */
public class Rectangle extends Shape {
	private Point location;
	private Size size;

	public Rectangle(Point location, Size size) {
		this.location = location;
		this.size = size;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;

		this.changed();
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;

		this.changed();
	}
}
