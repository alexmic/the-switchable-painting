package org.tsp.cv.descriptor;

import java.util.Arrays;

public abstract class FeatureVector {
	
	protected double x = -1;
	protected double y = -1;
	protected float[] descriptor = null;
	
	public FeatureVector()
	{
		
	}
	
	public FeatureVector(double x, double y, float[] descriptor) 
	{
		this.x = x;
		this.y = y;
		this.descriptor = descriptor;
	}
	
	public double x()
	{
		return x;
	}
	
	public double y()
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
		return this.type().equals(vector.type());
	}
	
	public boolean isEquivalentWith(FeatureVector vector) 
	{
		return x == vector.x()
			   && y == vector.y()
			   && Arrays.equals(this.descriptor, vector.descriptor());
	}

	public double getVectorDistance(FeatureVector vector) 
	{
		if (badVector(vector)) return Double.MAX_VALUE;
		float[] vDescriptor = vector.descriptor();
		double sum = 0;
		for (int i = 0; i < this.descriptor.length; ++i) {
			double diff = this.descriptor[i] - vDescriptor[i];
            sum += diff * diff; 
		}
		return Math.sqrt(sum);
	}
	
	public double getPointDistance(FeatureVector vector)
	{
		if (badVector(vector)) return Double.MAX_VALUE;
		return Math.sqrt(Math.pow((vector.x() - this.x), 2) + Math.pow((vector.y() - this.y), 2));
	}
	
	private boolean badVector(FeatureVector vector)
	{
		float[] vDescriptor = vector.descriptor();
		return !isCompatibleWith(vector) 
			|| vDescriptor == null
			|| vDescriptor.length == 0
			|| this.descriptor == null
			|| this.descriptor.length == 0;
	}

	public abstract FeatureVectorType type();
	
}
