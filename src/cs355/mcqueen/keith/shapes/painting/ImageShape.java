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
	private final int width;
	private final int height;

	public ImageShape(Point location, int[][] pixelData) {
		super(location,	new Size(pixelData[0].length, pixelData.length));

		this.pixelData = pixelData;
		this.height = this.pixelData.length;
		this.width = this.pixelData[0].length;
	}

	@Override
	public Size getSize() {
		return new Size(this.width, this.height);
	}

	@Override
	protected boolean doesContain(Point p) {
		return false;
	}

	public void performPixelOperation(PixelOperator operator) {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				int pixelValue = this.getPixelValue(x, y);
				int newValue = operator.operateOn(this, x, y, pixelValue);

				if (newValue != pixelValue) {
					this.setPixelValue(x, y, newValue);
				}
			}
		}
	}

	public int getPixelValue(int x, int y) {
		return this.pixelData[max(0, min(y, this.height - 1))][max(0, min(x, this.width - 1))];
	}

	private void setPixelValue(int x, int y, int value) {
		this.pixelData[max(0, min(y, this.height - 1))][max(0, min(x, this.width - 1))] = max(MIN_VALUE, min(MAX_VALUE, value));
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
