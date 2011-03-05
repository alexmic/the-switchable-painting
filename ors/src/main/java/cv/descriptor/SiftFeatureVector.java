package cv.descriptor;

public class SiftFeatureVector extends FeatureVector{
	
	public SiftFeatureVector()
	{
		super();
	}
	
	public SiftFeatureVector(double x, double y, float[] descriptor) 
	{
		super(x, y, descriptor);
	}
	
	@Override
	public FeatureVectorType type() {
		return FeatureVectorType.SIFT;
	}

}
