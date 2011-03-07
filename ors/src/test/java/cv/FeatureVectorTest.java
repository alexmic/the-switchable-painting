package cv;

import junit.framework.TestCase;
import org.junit.Test;
import cv.descriptor.FeatureVector;
import cv.descriptor.FeatureVectorType;
import cv.descriptor.IHFeatureVector;


public class FeatureVectorTest extends TestCase{

	@Test
	public void testIsEquivalent()
	{
		IHFeatureVector fv1 = new IHFeatureVector(1, 1, new float[]{1f, 1f});
		IHFeatureVector fv2 = new IHFeatureVector(1, 1, new float[]{1f, 1f});
		assertTrue(fv1.isEquivalentWith(fv2));
	}
	
	@Test
	public void testIsNotEquivalentDescriptor()
	{
		IHFeatureVector fv1 = new IHFeatureVector(1, 1, new float[]{1f, 1f});
		IHFeatureVector fv2 = new IHFeatureVector(1, 1, new float[]{2f, 1f});
		assertFalse(fv1.isEquivalentWith(fv2));
	}
	
	@Test
	public void testIsNotEquivalentCoords()
	{
		IHFeatureVector fv1 = new IHFeatureVector(1, 1, new float[]{1f, 1f});
		IHFeatureVector fv2 = new IHFeatureVector(1, 21, new float[]{1f, 1f});
		assertFalse(fv1.isEquivalentWith(fv2));
	}
	
	@Test
	public void testIsCompatible()
	{
		IHFeatureVector fv1 = new IHFeatureVector(1, 1, new float[]{1f, 1f});
		IHFeatureVector fv2 = new IHFeatureVector(1, 21, new float[]{1f, 1f});
		assertTrue(fv1.isCompatibleWith(fv2));
	}
	
	@Test
	public void testDistanceFunctionWhenCompatibleAndPopulated()
	{
		IHFeatureVector fv1 = new IHFeatureVector(1, 1, new float[]{0f, 0f, 0f});
		IHFeatureVector fv2 = new IHFeatureVector(1, 21, new float[]{4f, 0f, 3f});
		assertTrue(fv1.getVectorDistance(fv2) == 5);
	}
	
	@Test
	public void testDistanceFunctionWhenNotCompatible()
	{
		IHFeatureVector fv1 = new IHFeatureVector(1, 1, new float[]{1f, 1f});
		FeatureVector fv2 = new FeatureVector(1, 21, new float[]{1f, 1f}){

			@Override
			public FeatureVectorType type() {
				return FeatureVectorType.SURF;
			}

			@Override
			public double getVectorDistance(FeatureVector vector) {
				return 0;
			}
			
		};
		assertEquals((Double) fv1.getVectorDistance(fv2), (Double) Double.MAX_VALUE);
	}
	
	@Test
	public void testDistanceFunctionWhenNotCorrectlyPopulated()
	{
		IHFeatureVector fv1 = new IHFeatureVector(1, 1, null);
		IHFeatureVector fv2 = new IHFeatureVector(1, 21, new float[]{1f, 1f});
		assertEquals((Double) fv1.getVectorDistance(fv2), (Double) Double.MAX_VALUE);
	}
	
}
