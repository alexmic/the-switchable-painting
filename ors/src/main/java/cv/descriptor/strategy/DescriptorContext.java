package cv.descriptor.strategy;

import cv.descriptor.FeatureVector;
import cv.descriptor.strategy.ihistogram.HistogramDescriptorStrategy;
import cv.descriptor.strategy.sift.SiftDescriptorStrategy;
import cv.descriptor.strategy.surf.SurfDescriptorStrategy;
import cv.detector.fast.FeaturePoint;

public class DescriptorContext implements Context {

	private Strategy strategy;
	private enum DESCRIPTOR_STRATEGIES {
		INTENSITY_HISTOGRAM,
		SIFT,
		SURF
	};
	
	public DescriptorContext(Strategy strategy) 
	{
		this.strategy = strategy;
	}
	
	public DescriptorContext(int keycode) 
	{
		if (keycode == DESCRIPTOR_STRATEGIES.INTENSITY_HISTOGRAM.ordinal()) {
			strategy = new HistogramDescriptorStrategy();
		} else if (keycode == DESCRIPTOR_STRATEGIES.SIFT.ordinal()) {
			strategy = new SiftDescriptorStrategy();
		} else if (keycode == DESCRIPTOR_STRATEGIES.SURF.ordinal()) {
			strategy = new SurfDescriptorStrategy();
		}
	}
	
	@Override
	public FeatureVector[] getFeatureVectors(FeaturePoint[] featurePoints, int[][] pixels) 
	{
		return strategy.calcFeatureVectors(featurePoints, pixels);
	}
	
	public Strategy getStrategy()
	{
		return this.strategy;
	}
	
}
