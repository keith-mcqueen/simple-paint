package cs355.mcqueen.keith.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by keith on 5/3/14.
 */
public class Shapes implements Iterable<Shape> {
	private static Shapes ourInstance = new Shapes();

	public static Shapes getInstance() {
		return ourInstance;
	}

	private final List<Shape> shapeList = new ArrayList<>();

	private Shapes() {
	}

	public void addShape(Shape shape) {
		this.shapeList.add(shape);
	}

	@Override
	public Iterator<Shape> iterator() {
		return this.shapeList.iterator();
	}
}
