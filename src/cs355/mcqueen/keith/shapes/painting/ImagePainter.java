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
		final WritableRaster imageRaster = outputImage.getRaster();

		// put the pixels into the output image
		image.performPixelOperation((image1, pixelX, pixelY, pixelValue) -> {
			imageRaster.setPixel(pixelX, pixelY, new int[] { pixelValue, 0, 0 });
			return pixelValue;
		});

		// draw the output image
		g2d.drawImage(outputImage, null, paintX, paintY);
	}
}
