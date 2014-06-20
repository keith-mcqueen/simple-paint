package cs355.mcqueen.keith.shapes.tools;

import cs355.CS355Controller;

import java.util.Iterator;

/**
 * The <code>KeyTool</code> interface defines the contract for an object that can respond to key events as defined by
 * the {@link CS355Controller} interface.
 *
 * Created by keith on 6/7/14.
 */
public interface KeyTool {
	void keyPressed(Iterator<Integer> iterator);
}
