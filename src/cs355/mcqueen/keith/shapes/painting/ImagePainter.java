package cs355.mcqueen.keith.shapes.painting;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import static java.awt.image.BufferedImage.TYPE_BYTE_GRAY;

/**
 * The <code>ImagePainter</code> paints an image.
 *
 * Created by keith on 6/13/14.
 */
public class ImagePainter extends RectanglePainter<ImageShape> {
	@Override
	protected void paintRectangle(ImageShape image, Graphics2D g2d, int paintX, int paintY, int paintWidth, int paintHeight) {
		// prepare the image for drawing
		BufferedImage outputImage = new BufferedImage(paintWidth,	paintHeight, TYPE_BYTE_GRAY);

		// get the raster data for the image
		WritableRaster imageRaster = outputImage.getRaster();

		// put the pixels into the output image
		for (int y = 0; y < paintHeight; y++) {
			for (int x = 0; x < paintWidth; x++) {
				imageRaster.setPixel(x, y, new int[] { image.getPixelValue(x, y), 0, 0 });
			}
		}

		// draw the output image
		g2d.drawImage(outputImage, null, paintX, paintY);
	}
}
