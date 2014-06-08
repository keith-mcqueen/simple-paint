/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.solution;

import cs355.GUIFunctions;
import cs355.ViewRefresher;
import cs355.mcqueen.keith.CanvasRefresher;
import cs355.mcqueen.keith.PaintController;
import cs355.mcqueen.keith.Transformations;

import static cs355.GUIFunctions.*;

/**
 * @author [your name here]
 */
public class CS355 {

	public static final int VIEWPORT_WIDTH = 512;
	public static final int SCROLL_BAR_MIN = 0;
	public static final int SCROLL_BAR_MAX = 2048;
	public static final int ZOOM_INCREMENT = 2;
	public static final double ZOOM_MAX = 4.0;
	public static final double ZOOM_MIN = 0.25;
//	public static final double ZOOM_DEFAULT = 1.0;
	public static final double ZOOM_DEFAULT = ZOOM_MIN;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		PaintController controller = new PaintController();
		ViewRefresher refresher = new CanvasRefresher();

		// Fill in the parameters below with your controller, view,
		//   mouse listener, and mouse motion listener

		// the controller implements MouseListener and MouseMotionListener
		createCS355Frame(controller, refresher, controller, controller);

//		setHScrollBarKnob(VIEWPORT_WIDTH);
//		setVScrollBarKnob(VIEWPORT_WIDTH);
		setHScrollBarMin(SCROLL_BAR_MIN);
		setHScrollBarMax(SCROLL_BAR_MAX);
		setVScrollBarMin(SCROLL_BAR_MIN);
		setVScrollBarMax(SCROLL_BAR_MAX);

		Transformations.setZoomFactor(ZOOM_DEFAULT);

		GUIFunctions.refresh();
	}


}