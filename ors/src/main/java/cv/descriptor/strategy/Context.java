package cv.descriptor.strategy;

import cv.descriptor.FeatureVector;
import cv.detector.fast.FeaturePoint;

public interface Context {

	public FeatureVector[] getFeatureVectors(FeaturePoint[] featurePoints, int[][] pixels);
	public Strategy getStrategy();
	
}
