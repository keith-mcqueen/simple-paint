package cs355.mcqueen.keith.shapes.tools;

import cs355.GUIFunctions;
import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Shapes;
import cs355.mcqueen.keith.shapes.painting.ImageShape;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import static java.lang.Math.*;
import static java.util.Arrays.sort;

/**
 * The <code>ImageTool</code> class performs operations on image.
 * <p>
 * Created by keith on 6/13/14.
 */
public class ImageTool extends ShapeTool<ImageShape> {
	private static final int[] DX_COEFFICIENTS = {-1, 0, 1, -2, 0, 2, -1, 0, 1};
	private static final int[] DY_COEFFICIENTS = {-1, -2, -1, 0, 0, 0, 1, 2, 1};

	public ImageTool() {
		super(null);
	}

	public void loadImage(BufferedImage image) {
		// get the raster data from the image
		WritableRaster imageRaster = image.getRaster();

		// prepare an array for the pixel data
		int[][] pixelData = new int[imageRaster.getHeight()][imageRaster.getWidth()];

		// load the pixel data into our array
		for (int y = 0; y < pixelData.length; y++) {
			int[] pixelRow = pixelData[y];

			for (int x = 0; x < pixelRow.length; x++) {
				pixelRow[x] = imageRaster.getSample(x, y, 0);
			}
		}

		// create (and save) the image shape with the loaded pixel data
		this.setShape(new ImageShape(new Point(1024, 1024), pixelData));
	}

	@Override
	protected void setActive(boolean activated) {
		if (activated) {
			// set our shape as the background shape
			Shapes.getInstance().setBackgroundShape(this.getShape());
		} else {
			// clear the background shape
			Shapes.getInstance().setBackgroundShape(null);
		}

		GUIFunctions.refresh();
	}

	@Override
	protected void shapeWasSet(ImageShape shape) {
		// no op
	}

	public void doEdgeDetection() {
		this.transformImage((image, pixelX, pixelY, pixelValue) -> {
			float dx = 0.0f;
			float dy = 0.0f;

			int i = 0;
			for (int y = pixelY - 1; y < pixelY + 2; y++) {
				for (int x = pixelX - 1; x < pixelX + 2; x++) {
					int value = image.getPixelValue(x, y);

					dx += value * DX_COEFFICIENTS[i];
					dy += value * DY_COEFFICIENTS[i];

					i++;
				}
			}

			return (int) rint(sqrt(pow(dx, 2) + pow(dy, 2)) / 8.0);
		});
	}

	public void doSharpen() {
		this.transformImage((image, pixelX, pixelY, pixelValue) -> {
			int n = image.getPixelValue(pixelX, pixelY - 1);
			int s = image.getPixelValue(pixelX, pixelY + 1);
			int e = image.getPixelValue(pixelX + 1, pixelY);
			int w = image.getPixelValue(pixelX - 1, pixelY);

			return round(((6 * pixelValue) - n - s - e - w) / 2.0f);
		});
	}

	public void doMedianBlur() {
		this.transformImage((image, pixelX, pixelY, pixelValue) -> {
			int[] pixelValues = new int[9];
			int i = 0;

			// collect all the neighborhood pixel values
			for (int y = pixelY - 1; y < pixelY + 2; y++) {
				for (int x = pixelX - 1; x < pixelX + 2; x++) {
					// note that i++ is a POST increment
					pixelValues[i++] = image.getPixelValue(x, y);
				}
			}

			// now sort the array
			sort(pixelValues);

			// the median value is always in position 4
			return pixelValues[4];
		});
	}

	public void doUniformBlur() {
		this.transformImage((image, pixelX, pixelY, pixelValue) -> {
			int total = 0;

			// add up all the neighborhood pixel values
			for (int y = pixelY - 1; y < pixelY + 2; y++) {
				for (int x = pixelX - 1; x < pixelX + 2; x++) {
					total += image.getPixelValue(x, y);
				}
			}

			// return the average of the values (rounded)
			return round(total / 9.0f);
		});
	}

	public void doChangeContrast(int contrastAmount) {
		this.transformImage((image, pixelX, pixelY, pixelValue) ->
				(int) ((pow((contrastAmount + 100.0) / 100.0, 4.0) * (pixelValue - 128)) + 128));
	}

	public void doChangeBrightness(int brightnessAmount) {
		this.transformImage((image, pixelX, pixelY, pixelValue) -> pixelValue + brightnessAmount);
	}

	public void transformImage(ImageShape.PixelOperator operator) {
		// get the image
		ImageShape imageShape = this.getShape();

		// perform the operation
		imageShape.performPixelOperation(operator);

		// notify that the shape has changed
		this.shapeChanged(imageShape);
	}
}
