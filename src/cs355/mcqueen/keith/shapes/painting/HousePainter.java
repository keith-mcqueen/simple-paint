package cs355.mcqueen.keith.shapes.painting;

import cs355.Line3D;
import cs355.mcqueen.keith.shapes.House;
import cs355.mcqueen.keith.shapes.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static cs355.mcqueen.keith.Transformations.transformPoint;
import static cs355.mcqueen.keith.Transformations.worldToView;
import static cs355.mcqueen.keith.shapes.Point.*;
import static java.lang.Math.*;


/**
 * The <code>HousePainter</code> class paints the 3D {@link House} shape in the 2D viewing area.
 *
 * Created by keith on 6/7/14.
 */
public class HousePainter implements ShapePainter<House> {
	private static final double _0 = 0.0;
	private static final double _1 = 1.0;

	private static final double FOV = PI / 4.0;
	private static final double ZOOM_X = 1.0 / tan(FOV / 2.0);
	private static final double ZOOM_Y = 1.0 / tan(FOV / 2.0);
	private static final double Z_NEAR = 1.0;
	private static final double Z_FAR = 500.0;

	public static final double WIDTH = 1024.0;
	public static final double HEIGHT = 1024.0;
	public static final double[][] SCREEN_SPACE_MATRIX = new double[][]{
			{WIDTH,   _0,     WIDTH},
			{ _0,   -HEIGHT, HEIGHT},
			{ _0,     _0,     _1}
	};

	private double[][] translationMatrix;
	private double[][] rotationMatrix;

	@Override
	public void paint(House house, Graphics2D g2d) {
		// clear the graphics color
		g2d.setColor(Color.CYAN);

		// clear any transform
		g2d.setTransform(new AffineTransform());

		// get the world-to-camera transformation matrix (translate, rotate)
		this.translationMatrix = this.getTranslationMatrix(house);
		this.rotationMatrix = this.getRotationMatrix(house);

		// draw the lines
		for (Line3D line : house.getModel()) {
			this.drawLine(line, g2d);
		}
	}

	private double[][] getRotationMatrix(House house) {
		double theta = house.getRotation();

		return new double[][]{
				{ cos(theta), _0, sin(theta), _0},
				{    _0,      _1,    _0,      _0},
				{-sin(theta), _0, cos(theta), _0},
				{    _0,      _0,    _0,      _1}
		};
	}

	private double[][] getTranslationMatrix(House house) {
		Point location = house.getLocation();

		return new double[][] {
				{_1, _0, _0, -location.getCoordinate(X)},
				{_0, _1, _0, -location.getCoordinate(Y)},
				{_0, _0, _1, -location.getCoordinate(Z)},
				{_0, _0, _0,             _1}
		};
	}

	private void drawLine(Line3D line3D, Graphics2D g2d) {
		// convert the 3D (X, Y, Z) coordinates to 4-element (X, Y, Z, 1) homogeneous coordinates
		Point lineStart = new Point(line3D.start.x, line3D.start.y, line3D.start.z, _1);
		Point lineEnd = new Point(line3D.end.x, line3D.end.y, line3D.end.z, _1);

		// get the matrix that converts from world to camera coordinates
		// (translate, rotate)
		double[][] worldToCamera = matrixMult(this.rotationMatrix, this.translationMatrix);

		// apply this matrix to the 3D homogeneous world-space  point to get a 3D
		// homogeneous camera-space point
		Point camLineStart = matrixMult(worldToCamera, lineStart);
		Point camLineEnd = matrixMult(worldToCamera, lineEnd);

		// build a clip matrix
		double[][] clipMatrix = getClipMatrix();

		// apply the clip matrix to the homogeneous camera-space point to get 3D
		// homogeneous points in clip space
		Point clipLineStart = matrixMult(clipMatrix, camLineStart);
		Point clipLineEnd = matrixMult(clipMatrix, camLineEnd);

		// apply the clipping tests (reject if *both* points fail the same test
		// *OR* if either point fails the near-plane test)
		if (shouldClip(clipLineStart, clipLineEnd)) {
			return;
		}

		// apply perspective by normalizing the 3D homogeneous clip-space coordi-
		// nate to get the (x/w, y/w) location of the point in canonical screen
		// space
		Point perspectiveLineStart = new Point(clipLineStart.getCoordinate(X) / clipLineStart.getCoordinate(3),
				clipLineStart.getCoordinate(Y) / clipLineStart.getCoordinate(3), _1);
		Point perspectiveLineEnd = new Point(clipLineEnd.getCoordinate(X) / clipLineEnd.getCoordinate(3),
				clipLineEnd.getCoordinate(Y) / clipLineEnd.getCoordinate(3), _1);

		// apply a viewport transformation to map the canonical screen space to
		// the actual drawing space
		Point screenLineStart = matrixMult(SCREEN_SPACE_MATRIX, perspectiveLineStart);
		Point screenLineEnd = matrixMult(SCREEN_SPACE_MATRIX, perspectiveLineEnd);

		// apply the same viewport transformation used to implement zooming and
		// scrolling to map from the 2048x2048 world to the view's 512x512 space
		Point p1 = transformPoint(worldToView(),
				new Point(screenLineStart.getCoordinate(X), screenLineStart.getCoordinate(Y)));
		Point p2 = transformPoint(worldToView(),
				new Point(screenLineEnd.getCoordinate(X), screenLineEnd.getCoordinate(Y)));

		// draw the line on the screen
		g2d.drawLine((int) p1.getCoordinate(X), (int) p1.getCoordinate(Y),
				(int) p2.getCoordinate(X), (int) p2.getCoordinate(Y));
	}

	private static boolean shouldClip(Point p1, Point p2) {
		double w1 = p1.getCoordinate(3);
		double w2 = p2.getCoordinate(3);

		// check if the x's are too far left or right
		double x1 = p1.getCoordinate(X);
		double x2 = p2.getCoordinate(X);
		if (x1 < -w1 && x2 < -w2) {
			return true;
		}
		if (x1 > w1 && x2 > w2) {
			return true;
		}

		// check if y's are too high or too low
		double y1 = p1.getCoordinate(Y);
		double y2 = p2.getCoordinate(Y);
		if (y1 < -w1 && y2 < -w2) {
			return true;
		}
		if (y1 > w1 && y2 > w2) {
			return true;
		}

		double z1 = p1.getCoordinate(Z);
		double z2 = p2.getCoordinate(Z);

		return z1 < -w1 || z2 < -w2;
	}

	private static double[][] getClipMatrix() {
		double Z_DIFF = Z_FAR - Z_NEAR;
		double A = (Z_FAR + Z_NEAR) / Z_DIFF;
		double B = (-2 * Z_NEAR * Z_FAR) / Z_DIFF;

		return new double[][] {
				{ZOOM_X,   _0,   _0, _0},
				{  _0,   ZOOM_Y, _0, _0},
				{  _0,     _0,    A,  B},
				{  _0,     _0,   _1, _0}
		};
	}

	private static Point matrixMult(double[][] A, Point p) {
		double[][] B = new double[A[0].length][1];
		for (int i = 0; i < B.length; i++) {
			B[i][0] = p.getCoordinate(i, _1);
		}

		double[][] C = matrixMult(A, B);

		double[] p2 = new double[C.length];
		for (int i = 0; i < p2.length; i++) {
			p2[i] = C[i][0];
		}

		return new Point(p2);
	}

	private static double[][] matrixMult(double[][] A, double[][] B) {
		int A_cols = A[0].length;
		int B_rows = B.length;

		if (A_cols != B_rows) {
			throw new IllegalArgumentException("Cannot multiply matrices: the " +
					"columns in A don't match the rows in B");
		}

		double[][] C = new double[A.length][B[0].length];

		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < C[0].length; j++) {
				for (int k = 0; k < A_cols; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		return C;
	}
}
