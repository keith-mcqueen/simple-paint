package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Shapes;
import cs355.mcqueen.keith.shapes.Size;

/**
 * The <code>ImageShape</code> class wraps around image data to be stored in
 * the {@link Shapes} model.
 *
 * Created by keith on 6/13/14.
 */
public class ImageShape extends Rectangle {
	private final int[][] pixelData;

	public ImageShape(Point location, int[][] pixelData) {
		super(location,	new Size(pixelData[0].length, pixelData.length));

		this.pixelData = pixelData;
	}

	public int getPixelValue(int x, int y) {
		return this.pixelData[y][x];
	}

	@Override
	protected boolean doesContain(Point p) {
		return false;
	}
}
