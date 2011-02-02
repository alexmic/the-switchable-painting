package cv.descriptor;

import java.util.Arrays;

import com.google.code.morphia.annotations.Embedded;

@Embedded
public abstract class FeatureVector {
	
	protected int x = -1;
	protected int y = -1;
	protected float[] descriptor = null;
	
	public FeatureVector()
	{
		
	}
	
	public FeatureVector(int x, int y, float[] descriptor) 
	{
		this.x = x;
		this.y = y;
		this.descriptor = descriptor;
	}
	
	public int x()
	{
		return x;
	}
	
	public int y()
	{
		return y;
	}
	
	public float[] descriptor()
	{
		return descriptor;
	}
	
	public FeatureVector setX(int x){
		this.x = x;
		return this;
	}
	
	public FeatureVector setY(int y){
		this.y = y;
		return this;
	}
	
	public FeatureVector setDescriptor(float[] descriptor){
		this.descriptor = descriptor;
		return this;
	}
	
	
	public boolean isCompatibleWith(FeatureVector vector)
	{
		return this.type() == vector.type();
	}
	
	public boolean isEquivalentWith(FeatureVector vector) 
	{
		return x == vector.x()
			   && y == vector.y()
			   && Arrays.equals(this.descriptor, vector.descriptor());
	}

	public abstract FeatureVectorType type();
	public abstract double getDistance(FeatureVector vector);
	
}
