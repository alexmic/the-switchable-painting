package cv;

import java.awt.image.Raster;
import java.io.IOException;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import org.junit.Before;
import org.junit.Test;

import com.sun.media.jai.codec.FileSeekableStream;

import cv.common.Filter;
import cv.detector.fast.Fast12;
import cv.detector.fast.FeaturePoint;

public class FastBenchmark {

	// Simulate an 640W x 480H image with an array of pixels.
	private static double[][] pixels = null;
	private static int w;
	private static int h;
	
	private double[][] get2DPixelData(int[] pixels, int w, int h)
	{
		double[][] D2Pixels = new double[h][w];
		for (int y = 0; y < h; ++y) {
			for (int x = 0; x < w; ++x) {
				D2Pixels[y][x] = pixels[y * w + x];
			}
		}
		return D2Pixels;
	}
	
	@Before
	public void setUp() throws IOException
	{	
		// Yeah, the image needs to be on my desktop. :-)
		FileSeekableStream stream = new FileSeekableStream("/Users/alexis/Desktop/30pollock_lg.jpg");
		RenderedOp img = Filter.desaturate(JAI.create("stream", stream));
		w = img.getWidth();
		h = img.getHeight();
		pixels = new double[h][w];
		Raster inputRaster = img.getData();
		int[] ipixels = new int[w * h];
	    inputRaster.getPixels(inputRaster.getMinX(), inputRaster.getMinY(), w, h, ipixels);
	    pixels = get2DPixelData(ipixels, w, h);
	}
	
	@Test
	public void benchmarkFastWithNonMaxSuppression() 
	{
		long start = System.currentTimeMillis();
		List<FeaturePoint> f1 = Fast12.detectWithNonMax(pixels, w, h, 30, -1);
		long stop = System.currentTimeMillis();
		int sum = 0;
		for (FeaturePoint fp : f1)
			sum += fp.score();
		float avg = (float)sum/f1.size();
		sum = 0;
		System.out.println("B=30: A=" + avg + ": N=" + f1.size() + ": t=" + (stop - start));
		
		start = System.currentTimeMillis();
		List<FeaturePoint> f2 = Fast12.detectWithNonMax(pixels, w, h, 25, -1);
		stop = System.currentTimeMillis();
		for (FeaturePoint fp : f2)
			sum += fp.score();
		avg = (float)sum/f2.size();
		sum = 0;
		System.out.println("B=25: A=" + avg + ": N=" + f2.size() + ": t=" + (stop - start));
		
		start = System.currentTimeMillis();
		List<FeaturePoint> f3 = Fast12.detectWithNonMax(pixels, w, h, 22, -1);
		stop = System.currentTimeMillis();
		for (FeaturePoint fp : f3)
			sum += fp.score();
		avg = (float)sum/f3.size();
		sum = 0;
		System.out.println("B=22: A=" + avg + ": N=" + f3.size() + ": t=" + (stop - start));
		
		start = System.currentTimeMillis();
		List<FeaturePoint> f4 = Fast12.detectWithNonMax(pixels, w, h, 20, -1);
		stop = System.currentTimeMillis();
		for (FeaturePoint fp : f4)
			sum += fp.score();
		avg = (float)sum/f4.size();
		sum = 0;
		System.out.println("B=20: A=" + avg + ": N=" + f4.size() + ": t=" + (stop - start));

		start = System.currentTimeMillis();
		List<FeaturePoint> f5 = Fast12.detectWithNonMax(pixels, w, h, 19, -1);
		stop = System.currentTimeMillis();
		for (FeaturePoint fp : f5)
			sum += fp.score();
		avg = (float)sum/f5.size();
		sum = 0;
		System.out.println("B=19: A=" + avg + ": N=" + f5.size() + ": t=" + (stop - start));
		System.out.println("---------------");
	}
	
	@Test
	public void benchmarkFastWithoutNonMaxSuppression()
	{
		long start = System.currentTimeMillis();
		List<FeaturePoint> f1 = Fast12.detect(pixels, w, h, 30, -1);
		long stop = System.currentTimeMillis();
		int sum = 0;
		for (FeaturePoint fp : f1)
			sum += fp.score();
		float avg = (float)sum/f1.size();
		sum = 0;
		System.out.println("B=30: A=" + avg + ": N=" + f1.size() + ": t=" + (stop - start));
		
		start = System.currentTimeMillis();
		List<FeaturePoint> f2 = Fast12.detect(pixels, w, h, 25, -1);
		stop = System.currentTimeMillis();
		for (FeaturePoint fp : f2)
			sum += fp.score();
		avg = (float)sum/f2.size();
		sum = 0;
		System.out.println("B=25: A=" + avg + ": N=" + f2.size() + ": t=" + (stop - start));
		
		start = System.currentTimeMillis();
		List<FeaturePoint> f3 = Fast12.detect(pixels, w, h, 22, -1);
		stop = System.currentTimeMillis();
		for (FeaturePoint fp : f3)
			sum += fp.score();
		avg = (float)sum/f3.size();
		sum = 0;
		System.out.println("B=22: A=" + avg + ": N=" + f3.size() + ": t=" + (stop - start));
		
		start = System.currentTimeMillis();
		List<FeaturePoint> f4 = Fast12.detect(pixels, w, h, 20, -1);
		stop = System.currentTimeMillis();
		for (FeaturePoint fp : f4)
			sum += fp.score();
		avg = (float)sum/f4.size();
		sum = 0;
		System.out.println("B=20: A=" + avg + ": N=" + f4.size() + ": t=" + (stop - start));

		start = System.currentTimeMillis();
		List<FeaturePoint> f5 = Fast12.detect(pixels, w, h, 19, -1);
		stop = System.currentTimeMillis();
		for (FeaturePoint fp : f5)
			sum += fp.score();
		avg = (float)sum/f5.size();
		sum = 0;
		System.out.println("B=19: A=" + avg + ": N=" + f5.size() + ": t=" + (stop - start));
		System.out.println("---------------");
	}
	
}

