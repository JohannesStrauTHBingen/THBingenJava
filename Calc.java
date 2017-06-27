import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Calc {
	public Color[][] colorList = new Color[2][];

	public Calc() {

		colorList[0] = new Color[] {Color.BLUEVIOLET, Color.BLUE, Color.DARKBLUE, Color.CADETBLUE, Color.AQUAMARINE };
		colorList[1] = new Color[] {Color.ORANGERED, Color.FUCHSIA,  Color.BEIGE, Color.AQUAMARINE, Color.NAVAJOWHITE };
	}
	
	private int isElement(double a, double b, double x, double y, int tmpColor) {
		double x2;
		for (int n = 0; n < 400; n++) {
			x2 = x * x - y * y + a;
			y = 2 * x * y + b;
			x = x2;
			if (x * x + y * y > 4.0) {
				return n % (colorList[tmpColor].length);
			}
		}
		return -1;
	}
	
	public void renderImage(WritableImage image, int colorSchema, Color def, double x, double y) {
		PixelWriter pix = image.getPixelWriter();
		int h = (int) image.getHeight();
		int w = (int) image.getWidth();
		
		double a, b;
		int c;
		
		for (int i = 0; i < h; i++) {
			b = (double) i * 3 / h - 1.5; // -1,5 <= b < 1,5
			for (int j = 0; j < w; j++) {
				a = (double) j * 3 / w - 2; // -2 <= a < 1
				c = isElement(a, b, 0, 0, colorSchema);
				if (c == -1)
					pix.setColor(j, i, def);
				else {
					pix.setColor(j, i, colorList[colorSchema][c]);
				}
			}
		}
	}
	
	public void renderImageJulia(WritableImage image, int colorSchema, Color def, double x, double y) {
		PixelWriter pix = image.getPixelWriter();
		int h = (int) image.getHeight();
		int w = (int) image.getWidth();
		
		double a, b;
		int c;
		
		for (int i = 0; i < h; i++) {
			b = (double) i * 3 / h - 1.5; // -1,5 <= b < 1,5
			for (int j = 0; j < w; j++) {
				a = (double) j * 3 / w - 1.5; // -2 <= a < 1
				c = isElement(x, y, a, b, colorSchema);
				if (c == -1)
					pix.setColor(j, i, def);
				else {
					pix.setColor(j, i, colorList[colorSchema][c]);
				}
			}
		}
	}
	
	
	
	/* Mandelbrot:
	 *  RealMin: -2
	 *  RealMax:  1
	 *  
	 *  ImagMin: -1
	 *  ImagMax:  1
	 *  private int isElement(double a, double b, double x, double y, int tmpColor) {
	double x2;
	for (int n = 0; n < 400; n++) {
		x2 = x * x - y * y + a;
		y = 2 * x * y + b;
		x = x2;
		if (x * x + y * y > 4.0) {
			return n % (colorList[tmpColor].length - 1);
		}
	}
	return -1;
}

public void renderImageMandel(WritableImage image, int colorSchema, Color def, double x, double y) {
		PixelWriter pix = image.getPixelWriter();
		int h = (int) image.getHeight();
		int w = (int) image.getWidth();
		
		double a, b;
		int c;
		
		for (int i = 0; i < h; i++) {
			b = (double) i * 3 / h - 1.5; // -1,5 <= b < 1,5
			for (int j = 0; j < w; j++) {
				a = (double) j * 3 / w - 2; // -2 <= a < 1
				c = isElement(a, b, 0, 0, colorSchema);
				if (c == -1)
					pix.setColor(j, i, def);
				else {
					pix.setColor(j, i, colorList[colorSchema][c]);
				}
			}
		}
	}


	 */
}
