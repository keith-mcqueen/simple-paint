package cs355.mcqueen.keith.shapes.tools;

import cs355.GUIFunctions;
import cs355.mcqueen.keith.shapes.House;
import cs355.mcqueen.keith.shapes.Shapes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;

/**
 * The <code>HouseTool</code> works with a {@link House} instance.
 *
 * Created by keith on 6/7/14.
 */
public class HouseTool extends ShapeTool<House> implements KeyTool {
	private static final double WALK_DISTANCE = 1.0;
	private static final double ROTATION_ANGLE = Math.toRadians(1.0);

	public HouseTool() {
		super(null);

		this.setShape(new House());
	}

	@Override
	public void setColor(Color color) {
		// no op
	}

	@Override
	public KeyTool getKeyTool() {
		return this;
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) {
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			this.handleKey(key);
		}

		this.shapeChanged(this.getShape());
	}

	private void handleKey(Integer key) {
		House house = this.getShape();

		switch (key) {
			// forward
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				house.forward(WALK_DISTANCE);
				break;

			// backward
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				house.back(WALK_DISTANCE);
				break;

			// move left
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				house.left(WALK_DISTANCE);
				break;

			// move right
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				house.right(WALK_DISTANCE);
				break;

			// rotate left
			case KeyEvent.VK_Q:
				house.rotate(ROTATION_ANGLE);
				break;

			// rotate right
			case KeyEvent.VK_E:
				house.rotate(-ROTATION_ANGLE);
				break;

			// fly up
			case KeyEvent.VK_R:
				house.fly(WALK_DISTANCE);
				break;

			// fly down
			case KeyEvent.VK_F:
				house.fly(-WALK_DISTANCE);
				break;
		}
	}

	@Override
	protected void setActive(boolean activated) {
		if (activated) {
			// add the house shape to the shapes
			Shapes.getInstance().setSpecialShape(this.getShape());
		} else {
			// remove the house shape from the shapes
			Shapes.getInstance().setSpecialShape(null);
		}

		GUIFunctions.refresh();
	}

	@Override
	protected void shapeWasSet(House shape) {
		// no op
	}
}
