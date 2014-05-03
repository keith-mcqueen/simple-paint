/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.solution;

import cs355.ViewRefresher;
import cs355.mcqueen.keith.CanvasRefresher;
import cs355.mcqueen.keith.PaintController;

import static cs355.GUIFunctions.createCS355Frame;
import static cs355.GUIFunctions.refresh;

/**
 * @author [your name here]
 */
public class CS355 {

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

		refresh();
	}
}