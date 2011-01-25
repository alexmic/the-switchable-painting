package cv.detector.fast;

public class FastCorner implements Comparable<FastCorner>{

	private int x = 0;
	private int y = 0;
	private int score = 0;
	
	public FastCorner(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public FastCorner(int x, int y, int score) 
	{
		this.x = x;
		this.y = y;
		this.score = score;
	}
	
	public int x()
	{
		return x;
	}
	
	public int y()
	{
		return y;
	}
	
	public int score()
	{
		return score;
	}
	
	public void score(int score)
	{
		this.score = score;
	}
	
	@Override
	public int compareTo(FastCorner fc) 
	{
		if (fc.score < this.score) return 1;
		else if (fc.score == this.score) return 0;
		else return -1;
	}

}
