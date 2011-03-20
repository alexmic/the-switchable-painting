package org.tsp.cv.descriptor.strategy;

import java.util.List;

import org.tsp.cv.descriptor.FeatureVector;
import org.tsp.cv.detector.fast.FeaturePoint;

public interface Context {

	public List<FeatureVector> getFeatureVectors(List<FeaturePoint> featurePoints, double[][] pixels);
	public Strategy getStrategy();
	
}
