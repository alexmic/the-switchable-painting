package cv.detector;

import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cv.common.Filter;
import cv.detector.fast.*;

import javax.media.jai.ImagePyramid;
import javax.media.jai.Interpolation;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import com.sun.media.jai.codec.FileSeekableStream;


public class MultiScaleFast12 {

	private final float SCALE_DOWN_FACTOR = (float) ((double) 1 / Math.sqrt(2d)); 
	private final int FAST_THRESHOLD = 19;
	private final int MAX_NUM_OF_FEATURES = 150;
	
	private RenderedOp image;
    private RenderedOp downSampler;
    private Interpolation interp = new InterpolationNearest();
	private int levels = 0;
	private List<List<FeaturePoint>> featurePyramid = null;
	private List<double[][]> pixelPyramid = null;
	
	public MultiScaleFast12(String imgPath, int levels) throws IOException
	{
		FileSeekableStream stream = new FileSeekableStream(imgPath);
	    image = Filter.desaturate(JAI.create("stream", stream));
		this.levels = levels;
		this.featurePyramid = new ArrayList<List<FeaturePoint>>();
		this.pixelPyramid = new ArrayList<double[][]>();
		detectFeatures();
	}
	
	private void detectFeatures()
	{
	    downSampler = scaleDown(image, SCALE_DOWN_FACTOR);
        downSampler.removeSources();
	    
        // We only need down-sampling.
		ImagePyramid imgPyramid = new ImagePyramid(image, downSampler, downSampler, downSampler, downSampler);
		RenderedImage curr = imgPyramid.getCurrentImage();
		for (int i = 0; i < levels; ++i) {
		     Raster inputRaster = curr.getData();
		     int w = inputRaster.getWidth();
		     int h = inputRaster.getHeight();
		     int[] pixels = new int[w * h];
		     inputRaster.getPixels(inputRaster.getMinX(), inputRaster.getMinY(), w, h, pixels);
		     double[][] D2Pixels = get2DPixelData(pixels, w, h);
		     List<FeaturePoint> featurePoints = Fast12.detectWithNonMax(D2Pixels, w, h, FAST_THRESHOLD, MAX_NUM_OF_FEATURES);
		     featurePyramid.add(featurePoints);
		     pixelPyramid.add(D2Pixels);
		     curr = imgPyramid.getDownImage();
		}
	}
	
	private double[][] get2DPixelData(int[] pixels, int w, int h)
	{
		double[][] D2Pixels = new double[h][w];
		for (int y = 0; y < h; ++y) {
			for (int x = 0; x < w; ++x) {
				D2Pixels[y][x] = (double) pixels[y * w + x];
			}
		}
		return D2Pixels;
	}
	
	private RenderedOp scaleDown(RenderedOp img, float factor) {
		ParameterBlock pb = new ParameterBlock();
        pb.addSource(img);
        pb.add(factor);
        pb.add(factor);
        pb.add(1.0F);
        pb.add(1.0F);
        pb.add(interp);
        return JAI.create("scale", pb);
	}
	
	public List<FeaturePoint> getFeaturesAtLevel(int level)
	{
		return featurePyramid.get(level);
	}
	
	public double[][] getImageAtLevel(int level)
	{
		return pixelPyramid.get(level);
	}
	
}
