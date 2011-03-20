package org.tsp.comm;

public abstract class ThreadResult {

	protected boolean success = false;
	protected boolean finished = false;
	
	public synchronized void setSuccess(boolean success)
	{
		this.success = success;
	}
	
	public synchronized void setFinished(boolean finished)
	{
		this.finished = finished;
	}
	
	public synchronized boolean isSuccessful()
	{
		return success;
	}
	
	public synchronized boolean isFinished()
	{
		return finished;
	}
	
	public synchronized void reset() 
	{
		success = false;
		finished = false;
		subReset();
	}
	
	protected abstract void subReset();
	
}
