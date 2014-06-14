package cs355.mcqueen.keith.shapes.painting;

import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Rectangle;
import cs355.mcqueen.keith.shapes.Shapes;
import cs355.mcqueen.keith.shapes.Size;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * The <code>ImageShape</code> class wraps around image data to be stored in
 * the {@link Shapes} model.
 *
 * Created by keith on 6/13/14.
 */
public class ImageShape extends Rectangle {
	public static final int MIN_VALUE = 0;
	public static final int MAX_VALUE = 255;
	private final int[][] pixelData;

	public ImageShape(Point location, int[][] pixelData) {
		super(location,	new Size(pixelData[0].length, pixelData.length));

		this.pixelData = pixelData;
	}

	@Override
	protected boolean doesContain(Point p) {
		return false;
	}

	public void performPixelOperation(PixelOperator operator) {
		for (int y = 0; y < this.pixelData.length; y++) {
			for (int x = 0; x < this.pixelData[y].length; x++) {
				int pixelValue = this.getPixelValue(x, y);
				int newValue = operator.operateOn(this, x, y, pixelValue);

				if (newValue != pixelValue) {
					this.setPixelValue(x, y, newValue);
				}
			}
		}
	}

	private int getPixelValue(int x, int y) {
		// TODO bounds safety
		return this.pixelData[y][x];
	}

	private void setPixelValue(int x, int y, int value) {
		int lower = min(MAX_VALUE, value);
		int higher = max(MIN_VALUE, lower);

		// TODO: bounds safety
		this.pixelData[y][x] = higher;
	}

	/**
	 * The <code>PixelOperator</code> interface defines the contract for an
	 * object that can operate on the pixes of an {@link ImageShape} instance.
	 */
	public static interface PixelOperator {
		/**
		 * Operate on the pixel defined by the given X and Y position with the
		 * given value for the given image.
		 * @param image the image owner of the pixel
		 * @param pixelX the x position of the pixel within the image
		 * @param pixelY the y position of the pixel within the image
		 * @param pixelValue the current value of the pixel
		 *
		 * @return the new value of the pixel
		 */
		int operateOn(ImageShape image, int pixelX, int pixelY, int pixelValue);
	}
}
