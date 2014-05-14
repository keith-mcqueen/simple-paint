package cs355.mcqueen.keith.shapes;

import java.util.*;

/**
 * The <code>Shapes</code> class is the model containing all the shapes.
 *
 * Created by keith on 5/3/14.
 */
public class Shapes implements Iterable<Shape> {
	private static Shapes ourInstance = new Shapes();

	public static Shapes getInstance() {
		return ourInstance;
	}

	private final Deque<Shape> shapeList = new ArrayDeque<>();

	private Shapes() {
	}

	public void addShape(Shape shape) {
		this.shapeList.add(shape);
	}

	@Override
	public Iterator<Shape> iterator() {
		return this.shapeList.iterator();
	}

	public Shape getShapeAt(int x, int y, double scaleFactor) {
		// check the selected shape first?

		// check the current shapes, in reverse order (front to back)
		for(Iterator<Shape> it = this.shapeList.descendingIterator(); it.hasNext();) {
			Shape shape = it.next();
			if (shape.contains(x, y, scaleFactor)) {
				return shape;
			}
		}

		return null;
	}
}
