package cv;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import cv.common.Filter;
import cv.descriptor.SiftPatch;

public class SiftPatchTest {

	@Test(expected=NullPointerException.class)
	public void testFitsKernel() 
	{
		int x = 2;
		int y = 1;
		double[][] image = new double[][] {
				{ 1,2,3,4,5,35,12,22,27,56},
				{ 6,71,81,91,16,12,111,78,99,45},
				{ 21,12,33,14,15,18,67,23,45,145},
				{ 16,17,18,19,20,123,126,222,111,210}
		};
		assertTrue(SiftPatch.fitsKernel(x, y, 2, image));
		assertFalse(SiftPatch.fitsKernel(x, y, 15, image));
		assertFalse(SiftPatch.fitsKernel(x, y, 2, new double[][] {{}}));
		// Supposed to throw null pointer exception when passed a null image.
		SiftPatch.fitsKernel(x, y, 2, null);
	}
	
	@Test
	public void testExtractPatch()
	{
		int x = 2;
		int y = 1;
		double[][] image = new double[][] {
				{ 1,2,3,4,5,35,12,22,27,56},
				{ 6,71,81,91,16,12,111,78,99,45},
				{ 21,12,33,14,15,18,67,23,45,145},
				{ 16,17,18,19,20,123,126,222,111,210}
		};
		double[][] expected = new double[][] {
				{2, 3, 4},
				{71, 81, 91},
				{12, 33, 14}
		};
		//expected = Filter.gaussianBlur(expected, 3);
		boolean equal = true;
		SiftPatch patch = new SiftPatch(x, y, image, 3);
		double[][] p = patch.getPatch();
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				equal &= p[i][j] == expected[i][j];
			}
		}
		assertTrue(equal);
	}
	
	@Test
	public void testOrientations() 
	{
		int x = 4;
		int y = 2;
		double[][] image = new double[][] {
				{ 1,2,3,4,5,35,12,22,27,56},
				{ 6,71,81,91,16,12,111,78,99,45},
				{ 21,12,33,14,15,18,67,23,45,145},
				{ 241,12,53,94,25,88,57,13,25,145},
				{ 16,17,18,19,20,123,126,222,111,210}
		};
		SiftPatch patch = new SiftPatch(x, y, image, 5);
		List<Integer> orientations = patch.getMainOrientations();
		patch.createFeatureVectors();
		Collections.sort(orientations);
		boolean noDuplicates = true;
		for (int i = 0; i < orientations.size() - 1; ++i) {
			noDuplicates = orientations.get(i) != orientations.get(i + 1);
		}
		
		assertTrue(noDuplicates);
	}
}
