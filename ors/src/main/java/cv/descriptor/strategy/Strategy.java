package cv.descriptor.strategy;

import cv.descriptor.FeatureVector;
import cv.detector.fast.FeaturePoint;

public interface Strategy {

	public FeatureVector[] calcFeatureVectors(FeaturePoint[] featurePoints, int[][] pixels);
	public String toString();
	
}
