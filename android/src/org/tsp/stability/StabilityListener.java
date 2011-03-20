package org.tsp.stability;

public interface StabilityListener 
{
	public void onBecomingStable();
	public void onBecomingUnstable();
	public void onBecomingContinuouslyStable();
}
