package cv.detector.fast;

public class FastStruct {
	
	private int[] xs = null;
	private int[] ys = null;
	private int[] scores = null;
	private int count = 0;
	
	public FastStruct(int[] xs, int[] ys, int[] scores, int count)
	{
		this.xs = xs;
		this.ys = ys;
		this.scores = scores;
		this.count = count;
	}
	
	public int count() 
	{
		return count;
	}
	
	public int[] xs()
	{
		return xs;
	}
	
	public int[] ys()
	{
		return ys;
	}
	
	public int[] scores()
	{
		return scores;
	}
	
}
