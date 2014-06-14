package cs355.mcqueen.keith.shapes.tools;

import cs355.GUIFunctions;
import cs355.mcqueen.keith.shapes.Point;
import cs355.mcqueen.keith.shapes.Shapes;
import cs355.mcqueen.keith.shapes.painting.ImageShape;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * The <code>ImageTool</code> class performs operations on image.
 *
 * Created by keith on 6/13/14.
 */
public class ImageTool extends ShapeTool<ImageShape> {
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

	}

	public void doSharpen() {

	}

	public void doMedianBlur() {

	}

	public void doUniformBlur() {

	}

	public void doChangeContrast(int contrastAmount) {

	}

	public void doChangeBrightness(int brightnessAmount) {

	}
}
