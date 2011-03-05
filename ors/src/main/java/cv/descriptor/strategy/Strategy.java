package cv.descriptor.strategy;

import java.util.List;

import cv.descriptor.FeatureVector;
import cv.detector.fast.FeaturePoint;

public interface Strategy {

	public List<FeatureVector> calcFeatureVectors(List<FeaturePoint> featurePoints, double[][] pixels);
	public String toString();
	
}
