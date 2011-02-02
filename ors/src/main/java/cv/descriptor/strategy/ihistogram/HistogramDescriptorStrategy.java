package cv.descriptor.strategy.ihistogram;

import cv.descriptor.FeatureVector;
import cv.descriptor.IHFeatureVector;
import cv.descriptor.strategy.Strategy;
import cv.detector.fast.FeaturePoint;

public class HistogramDescriptorStrategy implements Strategy{

	private int BUCKET_SIZE = 15;
	
	public String toString() 
	{
		return "Intensity Histogram Descriptor Strategy";
	}

	@Override
	public FeatureVector[] calcFeatureVectors(FeaturePoint[] featurePoints, int[][] pixels) {
		FeatureVector[] vectors = new FeatureVector[featurePoints.length];
		int numBuckets = 255 / BUCKET_SIZE;
		for (int i = 0; i < featurePoints.length; ++i) {
			float[] descriptor = new float[numBuckets];
			FeaturePoint p = featurePoints[i];
			int x = p.x();
			int y = p.y();
			//for (int j = -3; j < 4; ++j) {
			//	for (int k = -3; k < 4; ++k) {
			//		descriptor[pixels[y + j][x + k] / numBuckets]++; 
			//	}
			//}
			descriptor[pixels[y - 3][x] / numBuckets]++; 
			descriptor[pixels[y - 3][x + 1] / numBuckets]++;
			descriptor[pixels[y - 2][x + 2] / numBuckets]++;
			descriptor[pixels[y - 1][x + 3] / numBuckets]++;
			descriptor[pixels[y][x + 3] / numBuckets]++;
			descriptor[pixels[y + 1][x + 3] / numBuckets]++;
			descriptor[pixels[y + 2][x + 2] / numBuckets]++;
			descriptor[pixels[y + 3][x + 1] / numBuckets]++;
			descriptor[pixels[y + 3][x] / numBuckets]++;
			descriptor[pixels[y + 3][x - 1] / numBuckets]++;
			descriptor[pixels[y + 2][x - 2] / numBuckets]++;
			descriptor[pixels[y + 1][x - 3] / numBuckets]++;
			descriptor[pixels[y][x - 3] / numBuckets]++;
			descriptor[pixels[y - 1][x - 3] / numBuckets]++;
			descriptor[pixels[y - 2][x - 2] / numBuckets]++;
			descriptor[pixels[y - 3][x - 1] / numBuckets]++;
			vectors[i] = new IHFeatureVector(x, y, descriptor);
		}
		return vectors;
	}
}
