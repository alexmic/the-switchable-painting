package org.tsp.cv.descriptor.strategy.sift;

import java.util.ArrayList;
import java.util.List;

import org.tsp.cv.descriptor.FeatureVector;
import org.tsp.cv.descriptor.SiftPatch;
import org.tsp.cv.descriptor.strategy.Strategy;
import org.tsp.cv.detector.fast.FeaturePoint;

public class SiftDescriptorStrategy implements Strategy{

	public String toString() 
	{
		return "SIFT Descriptor Strategy";
	}

	@Override
	public List<FeatureVector> calcFeatureVectors(List<FeaturePoint> featurePoints, double[][] image) 
	{
		int kernelSize = 15;
		List<FeatureVector> featureVectors = new ArrayList<FeatureVector>();
		if (image.length == 0)
			return featureVectors;
		for (FeaturePoint fp : featurePoints) {
			if (SiftPatch.fitsKernel(fp.x(), fp.y(), kernelSize, image)) {
				SiftPatch patch = new SiftPatch(fp.x(), fp.y(), image, kernelSize);
				if (patch.getMainOrientations().size() <= 3) {
					featureVectors.addAll(patch.createFeatureVectors());
				}
			}
		}
		return featureVectors;
	}
	
}
