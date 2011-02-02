package cv.descriptor.strategy.surf;

import cv.descriptor.FeatureVector;
import cv.descriptor.strategy.Strategy;
import cv.detector.fast.FeaturePoint;

public class SurfDescriptorStrategy implements Strategy{

	public String toString() 
	{
		return "SURF Descriptor Strategy";
	}

	@Override
	public FeatureVector[] calcFeatureVectors(FeaturePoint[] featurePoints, int[][] pixels) {
		// TODO Auto-generated method stub
		return null;
	}

}
