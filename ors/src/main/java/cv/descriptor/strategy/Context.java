package cv.descriptor.strategy;

import java.util.List;

import cv.descriptor.FeatureVector;
import cv.detector.fast.FeaturePoint;

public interface Context {

	public List<FeatureVector> getFeatureVectors(List<FeaturePoint> featurePoints, double[][] pixels);
	public Strategy getStrategy();
	
}
