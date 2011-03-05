package cv.common;

import java.awt.image.renderable.ParameterBlock;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

public class Filter {
	
	private static final int MAX_RGB = 255;
	private static final int MIN_RGB = 0;
	
	public static RenderedOp desaturate(RenderedOp ro) 
	{
		double[][] matrix = { {0.114D, 0.587D, 0.299D, 0.0D} };

		if (ro.getSampleModel().getNumBands() != 3) {
			throw new IllegalArgumentException("Image # bands <> 3");
		}

		ParameterBlock pb = new ParameterBlock();
		pb.addSource(ro);
		pb.add(matrix);
		return JAI.create("bandcombine", pb, null);
	} 
	
	public static double[][] gaussianBlur(double[][] pixels, int radius)
	{
		for (int i = 0; i < 3; ++i) {
			pixels = boxBlur(pixels, radius);
		}
		return pixels;
	}
	
	public static double[][] boxBlur(double[][] pixels, int radius)
	{
		float[] xKernel = getBlurKernel(radius, 0);
		float[] yKernel = getBlurKernel(0, radius);
		pixels = convolveX(pixels, xKernel);
		pixels = convolveY(pixels, yKernel);
		return pixels;
	}
	
	private static double[][] convolveX(double[][] pixels, float[] kernel)
	{
		return _convolve(pixels, kernel, 0);
	}
	
	private static double[][] convolveY(double[][] pixels, float[] kernel)
	{
		return _convolve(pixels, kernel, 1);
	}
	
	private static double[][] _convolve(double[][] pixels, float[] kernel, int dir)
	{
		if (pixels.length == 0) {
			return pixels;
		}
		int w = pixels[0].length;
		int h = pixels.length;
		int kw = kernel.length / 2;
		double[][] outPixels = new double[h][w];
		for (int y = 0; y < h; ++y) {
			for (int x = 0; x < w; ++x) {
				double value = 0;
				double sum = 0;
				for (int k = -kw; k <= kw; ++k) {
					if (dir == 0) {
						if ((x + k) < w && (x + k) > -1)
							value += pixels[y][x + k] * kernel[k + kw];
					} else if (dir == 1) {
						if ((y + k) < h && (y + k) > -1)
							value += pixels[y + k][x] * kernel[k + kw];	
					}
					sum += kernel[k + kw];
				}
				outPixels[y][x] = safe((double)value/sum);
			}
		}
		return outPixels;
	}
	
	private static float[] getBlurKernel(int hRadius, int vRadius) {
        int width = (hRadius == 0) ? 1 : hRadius;
        int height = (vRadius == 0) ? 1 : vRadius;

        float weight = 1.0f;
        float[] data = new float[width * height];
        
        for (int i = 0; i < data.length; i++) {
            data[i] = weight;
        }
        return data;
    }
	
	private static double safe(double value)
	{
		return Math.max(MIN_RGB, Math.min(MAX_RGB, value));
	}
	
	private static int safe(int value)
	{
		return (int) Math.floor(Math.max(MIN_RGB, Math.min(MAX_RGB, value)));
	}
	
}
