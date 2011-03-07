package org.tsp.stability;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StabilityMonitor implements SensorEventListener {

	private SensorManager sensorManager = null;
	private Sensor accelerationSensor = null;
	private Sensor lightSensor = null;
	private StabilityListener listener = null;
	
	private float stabilityThreshold = 0.011f; // Through experimentation.
	private float xSensorValue = 0f;
	private float ySensorValue = 0f;
	private float zSensorValue = 0f;
	private float avgDisplacement = 0f;
	
	private int delayCounter = 0;
	
	private float[] gravity = {0f, 0f, 0f};
	
	private final float HIGH_PASS_FILTER_THRESHOLD = 0.1f;
	private final int DELAY_VALUE = 2;
	
	public StabilityMonitor(StabilityListener listener, Context context)
	{
		this.listener = listener;
		this.sensorManager = (SensorManager) context.getSystemService("sensor");
		this.accelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		this.lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
	}
	
	public void init()
	{
		sensorManager.registerListener(this, accelerationSensor, SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
	}
	
	public void shutDown()
	{
		sensorManager.unregisterListener(this);
	}
	
	public void setStabilityThreshold(float threshold)
	{
		this.stabilityThreshold = threshold;
	}
	
	public float getStabilityThreshold()
	{
		return this.stabilityThreshold;
	}
	
	public float[] getAccelerationSensorValue()
	{
		return new float[]{this.xSensorValue, this.ySensorValue, this.zSensorValue};
	}
	
	public float getAverageDisplacement()
	{
		return this.avgDisplacement;
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

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
				this.avgDisplacement = Math.abs(x + y + z) / 3;
				this.xSensorValue = x;
				this.ySensorValue = y;
				this.zSensorValue = z;
			}	
			if (delayCounter >= DELAY_VALUE) {
				if (isStable()) {
					listener.onBecomingStable();
				} else {
					listener.onBecomingUnstable();
				}
				delayCounter = 0;
			} else {
				delayCounter++;
			}
		}
	}
	
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
	
	private boolean isStable()
	{
		return this.avgDisplacement <= this.stabilityThreshold;
	}
	
}