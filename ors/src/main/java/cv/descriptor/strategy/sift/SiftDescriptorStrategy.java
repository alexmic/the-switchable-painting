package cv.descriptor.strategy.sift;

import cv.descriptor.FeatureVector;
import cv.descriptor.strategy.Strategy;
import cv.detector.fast.FeaturePoint;

public class SiftDescriptorStrategy implements Strategy{

	public String toString() 
	{
		return "SIFT Descriptor Strategy";
	}

	@Override
	public FeatureVector[] calcFeatureVectors(FeaturePoint[] featurePoints, int[][] pixels) {
		// TODO Auto-generated method stub
		return null;
	}
}
