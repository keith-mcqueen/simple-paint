package cs355.mcqueen.keith.shapes;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by keith on 5/2/14.
 */
public abstract class Shape {
	private final CopyOnWriteArrayList<ShapeListener> listeners = new CopyOnWriteArrayList<>();

	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void addShapeListener(ShapeListener listener) {
		this.listeners.addIfAbsent(listener);
	}

	public void removeShapelistener(ShapeListener listener) {
		this.listeners.remove(listener);
	}

	protected void changed() {
		for (ShapeListener listener : this.listeners) {
			listener.shapeChanged(this);
		}
	}
}
