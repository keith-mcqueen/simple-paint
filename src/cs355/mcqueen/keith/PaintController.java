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

import static cs355.GUIFunctions.changeSelectedColor;
import static cs355.GUIFunctions.refresh;

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
		this.activeTool = new TriangleTool(this.activeColor);
	}

	@Override
	public void squareButtonHit() {
		this.activeTool = new SquareTool(this.activeColor);
	}

	@Override
	public void rectangleButtonHit() {
		this.activeTool = new RectangleTool(this.activeColor);
	}

	@Override
	public void circleButtonHit() {
		this.activeTool = new CircleTool(this.activeColor);
	}

	@Override
	public void ellipseButtonHit() {
		this.activeTool = new EllipseTool(this.activeColor);
	}

	@Override
	public void lineButtonHit() {
		this.activeTool = new LineTool(this.activeColor);
	}

	@Override
	public void selectButtonHit() {
		this.activeTool = new SelectionTool(this.activeColor);
	}

	@Override
	public void zoomInButtonHit() {
//		double newFactor = min(getScaleFactor() * 2, MAX_ZOOM);
//
//		setScaleFactor(newFactor);
	}

	@Override
	public void zoomOutButtonHit() {
//		double newFactor = max(getScaleFactor() / 2.0, MIN_ZOOM);
//
//		setScaleFactor(newFactor);
	}

	@Override
	public void hScrollbarChanged(int value) {
//		setHorizontalPos(value);
	}

	@Override
	public void vScrollbarChanged(int value) {
//		setVerticalPos(value);
	}

	@Override
	public void toggle3DModelDisplay() {

	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) {

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
