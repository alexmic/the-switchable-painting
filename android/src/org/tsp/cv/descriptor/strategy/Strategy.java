package org.tsp.cv.descriptor.strategy;

import java.util.List;

import org.tsp.cv.descriptor.FeatureVector;
import org.tsp.cv.detector.fast.FeaturePoint;

public interface Strategy {

	public List<FeatureVector> calcFeatureVectors(List<FeaturePoint> featurePoints, double[][] pixels);
	public String toString();
	
}
