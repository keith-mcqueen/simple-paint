package cs355.mcqueen.keith;

import cs355.CS355Controller;
import cs355.GUIFunctions;
import cs355.ViewRefresher;
import cs355.mcqueen.keith.shapes.Shape;
import cs355.mcqueen.keith.shapes.Shapes;
import cs355.mcqueen.keith.shapes.tools.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import static cs355.GUIFunctions.changeSelectedColor;
import static cs355.GUIFunctions.refresh;
import static cs355.mcqueen.keith.Transformations.*;
import static cs355.solution.CS355.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * The {@code PaintController} class implements the {@link CS355Controller} interface. It also implements the {@link
 * MouseListener} and {@link MouseMotionListener} interfaces. One instance of this class should be created and passed to
 * the {@link GUIFunctions#createCS355Frame(CS355Controller, ViewRefresher, MouseListener, MouseMotionListener)}
 * method.
 * <p>
 * Created by mcqueen on 5/2/14.
 */
public class PaintController implements CS355Controller, MouseListener, MouseMotionListener {
	private ShapeTool activeTool;
	private Color activeColor;
	private final CopyOnWriteArrayList<KeyTool> keyTools = new CopyOnWriteArrayList<>();
	private HouseTool houseTool;

	@Override
	public void colorButtonHit(Color c) {
		// save the active color
		this.activeColor = c;

		// update the selected color in the UI
		changeSelectedColor(c);

		// if there is an already active tool, update its color
		if (null != this.activeTool) {
			this.activeTool.setColor(c);
		}

		Shape selectedShape = Shapes.getInstance().getSelectedShape();
		if (null != selectedShape) {
			selectedShape.setColor(this.activeColor);
			refresh();
		}
	}

	@Override
	public void triangleButtonHit() {
		this.activateTool(new TriangleTool(this.activeColor));
	}

	@Override
	public void squareButtonHit() {
		this.activateTool(new SquareTool(this.activeColor));
	}

	@Override
	public void rectangleButtonHit() {
		this.activateTool(new RectangleTool(this.activeColor));
	}

	@Override
	public void circleButtonHit() {
		this.activateTool(new CircleTool(this.activeColor));
	}

	@Override
	public void ellipseButtonHit() {
		this.activateTool(new EllipseTool(this.activeColor));
	}

	@Override
	public void lineButtonHit() {
		this.activateTool(new LineTool(this.activeColor));
	}

	@Override
	public void selectButtonHit() {
		this.activateTool(new SelectionTool(this.activeColor));
	}

	public void activateTool(ShapeTool<?> tool) {
		// if there is an active tool, then deactivate it
		if (null != this.activeTool) {
			this.deactivateTool(this.activeTool);
		}

		// save the new tool as the active tool
		this.activeTool = tool;

		// activate the tool
		this.activeTool.activate();

		// if the active tool has a KeyTool, then save it
		KeyTool keyTool = this.activeTool.getKeyTool();
		if (null != keyTool) {
			this.keyTools.addIfAbsent(keyTool);
		}
	}

	public void deactivateTool(ShapeTool<?> tool) {
		tool.deactivate();

		KeyTool keyTool = tool.getKeyTool();
		if (null != keyTool) {
			this.keyTools.remove(keyTool);
		}
	}

	@Override
	public void zoomInButtonHit() {
		double newFactor = min(getZoomFactor() * ZOOM_INCREMENT, ZOOM_MAX);

		setZoomFactor(newFactor);
	}

	@Override
	public void zoomOutButtonHit() {
		double newFactor = max(getZoomFactor() / ZOOM_INCREMENT, ZOOM_MIN);

		setZoomFactor(newFactor);
	}

	@Override
	public void hScrollbarChanged(int value) {
		setHorizontalViewPosition(value);
	}

	@Override
	public void vScrollbarChanged(int value) {
		setVerticalViewPosition(value);
	}

	@Override
	public void toggle3DModelDisplay() {
		if (null == this.houseTool) {
			this.houseTool = new HouseTool();
		}

		// do I activate or deactivate the house tool
		if (this.houseTool.isActivated()) {
			// deactivate the house tool
			this.houseTool.deactivate();

			// remove the HouseTool's KeyTool
			KeyTool keyTool = this.houseTool.getKeyTool();
			if (null != keyTool) {
				this.keyTools.remove(keyTool);
			}
		} else {
			// activate the house tool
			this.houseTool.activate();

			// add the HouseTool's KeyTool
			KeyTool keyTool = this.houseTool.getKeyTool();
			if (null != keyTool) {
				this.keyTools.addIfAbsent(keyTool);
			}
		}
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) {
		for (KeyTool tool : this.keyTools) {
			tool.keyPressed(iterator);
		}
	}

	@Override
	public void doEdgeDetection() {

	}

	@Override
	public void doSharpen() {

	}

	@Override
	public void doMedianBlur() {

	}

	@Override
	public void doUniformBlur() {

	}

	@Override
	public void doChangeContrast(int contrastAmountNum) {

	}

	@Override
	public void doChangeBrightness(int brightnessAmountNum) {

	}

	@Override
	public void doLoadImage(BufferedImage openImage) {

	}

	@Override
	public void toggleBackgroundDisplay() {

	}

	////////////////////////////////////////////////////////////
	// MouseListener methods
	////////////////////////////////////////////////////////////

	@Override
	public void mouseClicked(MouseEvent e) {
		if (null != this.activeTool) {
			this.activeTool.mouseClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (null != this.activeTool) {
			this.activeTool.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (null != this.activeTool) {
			this.activeTool.mouseReleased(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (null != this.activeTool) {
			this.activeTool.mouseEntered(e);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (null != this.activeTool) {
			this.activeTool.mouseExited(e);
		}
	}

	////////////////////////////////////////////////////////////
	// MouseMotionListener methods
	////////////////////////////////////////////////////////////

	@Override
	public void mouseDragged(MouseEvent e) {
		if (null != this.activeTool) {
			this.activeTool.mouseDragged(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (null != this.activeTool) {
			this.activeTool.mouseMoved(e);
		}
	}
}
