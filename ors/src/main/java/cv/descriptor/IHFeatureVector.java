package cv.descriptor;


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

	@Override
	public double getDistance(FeatureVector vector) 
	{
		float[] vDescriptor = vector.descriptor();
		if (!isCompatibleWith(vector) 
			|| vDescriptor == null
			|| vDescriptor.length == 0
			|| this.descriptor == null
			|| this.descriptor.length == 0) return Double.MAX_VALUE;
		
		int sum = 0;
		for (int i = 0; i < this.descriptor.length; ++i) {
			double diff = this.descriptor[i] - vDescriptor[i];
            sum += diff * diff; 
		}
		return sum;
	}

}
