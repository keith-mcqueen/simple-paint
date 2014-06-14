package cs355.mcqueen.keith.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * The <code>Shapes</code> class is the model containing all the shapes.
 *
 * Created by keith on 5/3/14.
 */
public class Shapes implements Iterable<Shape> {
	private static Shapes ourInstance = new Shapes();

	private Shape selectedShape;
	private Shape foregroundShape;
	private Shape backgroundShape;

	public static Shapes getInstance() {
		return ourInstance;
	}

//	private final Deque<Shape> shapeList = new ArrayDeque<>();
	private final List<Shape> shapeList = new ArrayList<>();

	private Shapes() {
	}

	public void addShape(Shape shape) {
		this.shapeList.add(shape);
	}

	@Override
	public Iterator<Shape> iterator() {
		// create a list of all the shapes
		List<Shape> allShapes = new ArrayList<>();

		// add the background shape (if there is one)
		if (null != this.backgroundShape) {
			allShapes.add(this.backgroundShape);
		}

		allShapes.addAll(this.shapeList);

		// add the selected shape (if any) to the list
		if (null != this.selectedShape) {
			allShapes.add(this.selectedShape);
		}

		// add the foreground shape (if there is one)
		if (null != this.foregroundShape) {
			allShapes.add(this.foregroundShape);
		}

		return allShapes.iterator();
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public Shape getShapeAt(Point p) {
		// check the selected shape first?
		if (null != this.selectedShape && this.selectedShape.contains(p)) {
			return this.selectedShape;
		}

		// check the current shapes, in reverse order (front to back)
		for(ListIterator<Shape> it = this.shapeList.listIterator(this.shapeList.size()); it.hasPrevious();) {
			Shape shape = it.previous();
			if (shape.contains(p)) {
				return shape;
			}
		}

		return null;
	}

	public void setForegroundShape(Shape shape) {
		this.foregroundShape = shape;
	}

	public void removeShape(Shape shape) {
		this.shapeList.remove(shape);
	}

	public void setBackgroundShape(Shape shape) {
		this.backgroundShape = shape;
	}
}
