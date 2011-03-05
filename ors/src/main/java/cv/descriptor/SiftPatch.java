package cv.descriptor;

import java.util.ArrayList;
import java.util.List;

public class SiftPatch {
	
	private class Point {
		public double x = 0;
		public double y = 0;
	}

	private final float PEAK_THRESHOLD = 0.8f;
	private final float CUTOFF_THRESHOLD = 0.25f;
	private final int   SUBREGION_SIZE = 5;
	
	// The feature's centre coords.
	private int x;
	private int y;
	
	// The kernel size.
	private int kernelSize = 15;
	
	// The extracted SIFT patch.
	private double[][] imgPatch = null;
	
	// Orientation and magnitude patches.
	private double[][] magPatch = null;
	private double[][] oriPatch = null;
	
	// The patch's main orientation histogram. 
	private float[] mainOrientationHistogram = new float[36];
	
	// A list of all the peak orientations in the orientation histogram.
	// A bin is considered to be a peak if its value is 80%-100% of the maximum peak.
	private List<Integer> orientations = null;
	
	public SiftPatch(int x, int y, double[][] image, int kernelSize)
	{
		this.x = x;
		this.y = y;
		this.kernelSize = kernelSize;
		this.imgPatch = new double[kernelSize][kernelSize];
		this.oriPatch = new double[kernelSize][kernelSize];
		this.magPatch = new double[kernelSize][kernelSize];
		
		// Extract the image patch.
		createPatch(image, imgPatch, x-kernelSize/2, y-kernelSize/2, kernelSize);
		//imgPatch = Filter.gaussianBlur(imgPatch, 3);
		createOrientationAndMagnitudeImgs(imgPatch, magPatch, oriPatch);
										  		
		// Create the main orientation histogram.
		createHistogram(oriPatch, magPatch, mainOrientationHistogram, 10, 0, 0, 0, kernelSize, 0);
		// Get the main orientations of the patch.
		orientations = getMainOrientations();
	}
	
	public static boolean fitsKernel(int x, int y, int kernelSize, double[][] image) 
	{
		if (image == null) {
			throw new NullPointerException("Image supplied in fitsKernel() was null.");
		}
		int h = image.length;
		if (h == 0) {
			return false;
		}
		int w = image[0].length;
		return (x - kernelSize / 2 >= 0 && 
				y - kernelSize / 2 >= 0 &&
				x + kernelSize / 2 < w  && 
				y + kernelSize / 2 < h);
	}
	
	public List<FeatureVector> createFeatureVectors()
	{
		List<FeatureVector> featureVectors = new ArrayList<FeatureVector>();
		// Subregion side size.
		int numPerSide = kernelSize / SUBREGION_SIZE;
		int subRegionBins = 4;
		// Number of subregions in 15 x 15 patch.
		int numSubRegions = numPerSide * numPerSide;
		// Number of bins in descriptor histogram = number of subregions * 4 bins per region.
		int numDescriptorBins = numSubRegions * subRegionBins;
		// For each orientation..
		for (int orientation : this.orientations) {
			float[] descriptor = new float[numDescriptorBins];
			// For each subregion..
			for (int i = 0; i < numPerSide; ++i) {
				for (int j = 0; j < numPerSide; ++j) {
					// 4 bins for each subregion histogram => 90 degrees step.
					int step = 360 / 4;
					float[] subregionHistogram = new float[subRegionBins];
					float sigma = (float) kernelSize / 2f;
					createHistogram(this.oriPatch, this.magPatch, subregionHistogram, step, orientation,
									i * SUBREGION_SIZE, j * SUBREGION_SIZE, SUBREGION_SIZE, sigma);
					int offset = i * numPerSide * subRegionBins + j * subRegionBins;
					for (int k = 0; k < subRegionBins; ++k) {
						descriptor[offset + k] = subregionHistogram[k];
					}
				}
			}
			double length = length(descriptor);
			descriptor = suppressBins(normalize(descriptor, length), CUTOFF_THRESHOLD, length);
			length = length(descriptor);
			descriptor = normalize(descriptor, length);
			for (int i = 0; i < descriptor.length; ++i)
				System.out.println(descriptor[i]);
			Point rotated = rotate(x, y, orientation);
			featureVectors.add(new SiftFeatureVector(rotated.x, rotated.y, descriptor));
		}
		return featureVectors;
	}

	public List<Integer> getMainOrientations()
	{
		if (orientations == null) {
			List<Integer> orientations = new ArrayList<Integer>();
			float maxBinValue = 0;
			for (int i = 0; i < mainOrientationHistogram.length; ++i) {
				if (mainOrientationHistogram[i] > maxBinValue) {
					maxBinValue = mainOrientationHistogram[i];
				}
			}
			for (int i = 0; i < mainOrientationHistogram.length; ++i) {
				if (mainOrientationHistogram[i] >= maxBinValue * PEAK_THRESHOLD) {
					int angle = i * 10;
					if (!orientations.contains(angle))
						orientations.add(angle);
				}
			}
			return orientations;
		} else {
			return this.orientations;
		}
	}
	
	public double[][] getPatch()
	{
		return this.imgPatch;
	}
	
	public int x()
	{
		return this.x;
	}
	
	public int y()
	{
		return this.y;
	}
	
	public void createPatch(double[][] image, double[][] imgPatch, int sX, int sY, int side)
	{
		for (int i = 0; i < side; ++i) {
			for (int j = 0; j < side; ++j) {
				imgPatch[i][j] = image[sY + i][sX + j];
			}
		}
	}
	
	public void createOrientationAndMagnitudeImgs(double[][] img, double[][] ori, double[][] mag)
	{
		int side = kernelSize;
		for (int y = 1; y < side - 1; ++y) {
			for (int x = 1; x < side - 1; ++x) {
				double dx = img[y][x+1] - img[y][x-1];
				double dy = img[y+1][x] - img[y-1][x];
				double angle = degrees(direction(dx, dy));
				if (angle < 0) {
					angle += 360;
				}
				double magnitude = magnitude(dx, dy);
				ori[y][x] = angle;
				mag[y][x] = magnitude;
			}
		}
	}
	
	public void createHistogram(double[][] ori, double[][] mag, float[] histogram, 
								int step, int relOrientation, int startX, int startY,
								int side, float sigma)
	{
		for (int y = startY; y < startY + side; ++y) {
			for (int x = startX; x < startX + side; ++x) {
				double angle = ori[y][x] - relOrientation;
				double magn = mag[y][x];
				if (angle < 0) 
					angle += 360;
				if (sigma > 0)
					magn *= gaussianWeight(this.x, this.y, x, y, sigma);
				histogram[quantize(angle, step)] += magn;
			}
		}
		//System.out.println("--- " + histogram.length);
		//for (int i = 0; i < histogram.length; ++i)
		//	System.out.println(histogram[i]);
		//System.out.println("---");
	}
	
	private int quantize(double value, int step)
	{
		return (int) Math.floor( (double) value / step);
	}
	
	private double magnitude(double dx, double dy)
	{
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	private double direction(double dx, double dy)
	{
		double angle = Math.atan2(dy, dx);
		if (angle < 0) {
			angle += 2 * Math.PI;
		}
		//System.out.println(angle);
		return angle;
	}
	
	private double degrees(double rads) 
	{
		return rads * (180 / Math.PI);
	}
	
	private double length(float[] histogram)
	{
		double sum = 0;
		for (float binValue : histogram) {
			sum += binValue * binValue;
		}
		return Math.sqrt(sum);
	}
	
	private float[] normalize(float[] histogram, double length)
	{
		if (length == 0)
			length = Double.MIN_VALUE;
		for (int i = 0; i < histogram.length; ++i) {
			histogram[i] /= length;
		}
		return histogram;
	}
	
	private float[] suppressBins(float[] histogram, double cutoff, double length) 
	{
		for (int i = 0; i < histogram.length; ++i) {
			histogram[i] = (float) Math.min(histogram[i], length * cutoff);
		}
		return histogram;
	}
	
	private double gaussianWeight(float cX, float cY, float x, float y, float sigma)
	{
		double exp = -(Math.pow((x - cX) - (y - cY), 2) / (2 * sigma * sigma));
		return Math.pow(Math.E, exp);
	}
	
	private Point rotate(int x, int y, int orientation) {
		Point rotated = new Point();
		rotated.x = x * Math.cos((double) orientation) - y * Math.sin((double) orientation);
		rotated.y = x * Math.sin((double) orientation) + y * Math.cos((double) orientation);
		return rotated;
	}
	
}
