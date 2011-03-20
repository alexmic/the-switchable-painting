package org.tsp.cv.descriptor;


public class IHFeatureVector extends FeatureVector{
	
	public IHFeatureVector()
	{
		super();
	}
	
	public IHFeatureVector(int x, int y, float[] descriptor) 
	{
		super(x, y, descriptor);
	}
	
	@Override
	public FeatureVectorType type() 
	{
		return FeatureVectorType.INTENSITY_HISTOGRAM;	
	}

}
