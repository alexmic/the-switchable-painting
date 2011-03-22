package org.tsp.stability;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StabilityMonitor implements SensorEventListener 
{
	private final float HIGH_PASS_FILTER_THRESHOLD = 0.1f;
	private final int DELAY_COUNTER_MAX_VALUE = 1;
	private final long CONTINUOUS_STABILITY_THRESHOLD = 200; // in milliseconds.
	
	private SensorManager sensorManager = null;
	private Sensor accelerationSensor = null;
	private Sensor lightSensor = null;
	
	private List<StabilityListener> listeners = null;
	
	// Readings and thresholds.
	private float stabilityThreshold = 0.011f; // Through experimentation.
	private float xSensorValue = 0f;
	private float ySensorValue = 0f;
	private float zSensorValue = 0f;
	private float avgDisplacement = 0f;
	
	// Counter to slow down the UI updates - mainly for performance reasons.
	// After experimentation, the UI updates do not slow down the application
	// so we set DELAY_COUNTER_MAX_VALUE to 1 in order to update on every
	// reading.
	private int delayCounter = 0;
	
	// Variables to check for continuous stability.
	private long wasLastStableTimestamp = 0;
	private boolean wasLastStable = false;
	
	// The accelerometer values contain the acceleration from gravity (g),
	// so we need to filter that out.
	private float[] gravity = {0f, 0f, 0f};
	
	public StabilityMonitor(Context context)
	{
		this.listeners = new ArrayList<StabilityListener>();
		this.sensorManager = (SensorManager) context.getSystemService("sensor");
		this.accelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		this.lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
	}
	
	/**
	 * Registers (or re-registers) the necessary listeners.
	 * @return 
	 * 		  This.
	 */
	public StabilityMonitor resume()
	{
		sensorManager.registerListener(this, accelerationSensor, SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
		return this;
	}
	
	/**
	 * Unregisters 
	 * @return
	 */
	public StabilityMonitor pause()
	{
		sensorManager.unregisterListener(this);
		return this;
	}
	
	/**
	 * 
	 * @param threshold
	 * @return
	 */
	public StabilityMonitor setStabilityThreshold(float threshold)
	{
		this.stabilityThreshold = threshold;
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getStabilityThreshold()
	{
		return this.stabilityThreshold;
	}
	
	/**
	 * 
	 * @return
	 */
	public float[] getAccelerationSensorValue()
	{
		return new float[]{ this.xSensorValue, this.ySensorValue, this.zSensorValue };
	}
	
	/**
	 * 
	 * @return
	 */
	public float getAverageDisplacement()
	{
		return this.avgDisplacement;
	}
	
	/**
	 * 
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	/**
	 * 
	 */
	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		synchronized(this)
		{
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				float[] filteredValues = highPassFilter(event.values);
				float x = filteredValues[0];
				float y = filteredValues[1];
				float z = filteredValues[2];
				avgDisplacement = Math.abs(x + y + z) / 3;
				xSensorValue = x;
				ySensorValue = y;
				zSensorValue = z;
				
				if (delayCounter >= DELAY_COUNTER_MAX_VALUE) {
					for (StabilityListener sl : listeners) {
						if (isStable()) {
							sl.onBecomingStable();
							if (wasLastStable) {
								if (System.currentTimeMillis() - wasLastStableTimestamp >= CONTINUOUS_STABILITY_THRESHOLD) {
									sl.onBecomingContinuouslyStable();
								}
							} else {
								wasLastStableTimestamp = System.currentTimeMillis();
							}
							wasLastStable = true;
						} else {
							sl.onBecomingUnstable();
							wasLastStable = false;
						}
					}
					delayCounter = 0;
				} else {
					delayCounter++;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param listener
	 * @return
	 */
	public StabilityMonitor addListener(StabilityListener listener)
	{
		listeners.add(listener);
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<StabilityListener> getListeners()
	{
		return listeners;
	}
	
	/**
	 * 
	 * @param values
	 * @return
	 */
	private float[] highPassFilter(float[] values)
	{
		float[] newValues = new float[3];
		
		gravity[0] = HIGH_PASS_FILTER_THRESHOLD * gravity[0] + (1 - HIGH_PASS_FILTER_THRESHOLD) * values[0];
		gravity[1] = HIGH_PASS_FILTER_THRESHOLD * gravity[1] + (1 - HIGH_PASS_FILTER_THRESHOLD) * values[1];
		gravity[2] = HIGH_PASS_FILTER_THRESHOLD * gravity[2] + (1 - HIGH_PASS_FILTER_THRESHOLD) * values[2];
		
		newValues[0] = values[0] - gravity[0];
		newValues[1] = values[1] - gravity[1];
		newValues[2] = values[2] - gravity[2];
		
		return newValues;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean isStable()
	{
		return this.avgDisplacement <= this.stabilityThreshold;
	}
	
}