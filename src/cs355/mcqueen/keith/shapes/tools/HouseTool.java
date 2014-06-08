package cs355.mcqueen.keith.shapes.tools;

import cs355.GUIFunctions;
import cs355.mcqueen.keith.shapes.House;
import cs355.mcqueen.keith.shapes.Shapes;

import java.awt.event.KeyEvent;
import java.util.Iterator;

/**
 * The <code>HouseTool</code> works with a {@link House} instance.
 *
 * Created by keith on 6/7/14.
 */
public class HouseTool extends ShapeTool<House> implements KeyTool {
	public HouseTool() {
		super(null);

		this.setShape(new House());
	}

	@Override
	public KeyTool getkeyTool() {
		return this;
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) {
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			this.handleKey(key);
		}
	}

	private void handleKey(Integer key) {
		switch (key) {
			case KeyEvent.VK_W:
				// forward
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
