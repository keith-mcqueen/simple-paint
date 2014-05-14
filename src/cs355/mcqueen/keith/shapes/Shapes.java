package cs355.mcqueen.keith.shapes;

import java.util.*;

/**
 * The <code>Shapes</code> class is the model containing all the shapes.
 *
 * Created by keith on 5/3/14.
 */
public class Shapes implements Iterable<Shape> {
	private static Shapes ourInstance = new Shapes();

	private Shape selectedShape;

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
		List<Shape> allShapes = new ArrayList<>(this.shapeList);
		if (null != this.selectedShape) {
			allShapes.add(this.selectedShape);
		}

		return allShapes.iterator();
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public Shape getShapeAt(int x, int y, double scaleFactor) {
		// check the selected shape first?
		if (null != this.selectedShape && this.selectedShape.contains(x, y, scaleFactor)) {
			return this.selectedShape;
		}

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
