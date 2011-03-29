package org.tsp.activity;

import java.io.IOException;

import org.tsp.R;
import org.tsp.draw.DrawingBob;
import org.tsp.stability.StabilityListener;
import org.tsp.stability.StabilityMonitor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreen extends Activity implements SurfaceHolder.Callback, StabilityListener, OnClickListener 
{

    /* We will only consider the Nexus One - since this is not
	 * a commercial app, we hardcode the preview size.
	 */
	private final int PREVIEW_WIDTH = 480;
	private final int PREVIEW_HEIGHT = 320;
    
	private SurfaceView surfaceView = null;
	private SurfaceHolder surfaceHolder = null;
	private Camera camera = null;
	private StabilityMonitor stabilityMonitor = null;
	private TextView stabilityLabel = null;
	private DrawingBob bob = null;
	
	private boolean hasPlayedStabilitySound = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		surfaceView = (SurfaceView) findViewById(R.id.camera_surface);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		stabilityLabel = (TextView) findViewById(R.id.stabilityLabel);
		
		bob = new DrawingBob(this, PREVIEW_WIDTH, PREVIEW_HEIGHT);
		
		stabilityMonitor = new StabilityMonitor(this);
		stabilityMonitor.addListener(this).addListener(bob);
		
		Button prefsButton = (Button) findViewById(R.id.prefsButton);
		prefsButton.setOnClickListener(this);
		addContentView(bob, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));	
        
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) 
	{
		bob.setParentDimensions((float) surfaceView.getMeasuredHeight(), 
							    (float) surfaceView.getMeasuredWidth());
		
		camera.stopPreview();
		
		Camera.Parameters parameters = camera.getParameters();
		parameters.setPreviewSize(PREVIEW_WIDTH, PREVIEW_HEIGHT);
		parameters.setWhiteBalance(Parameters.WHITE_BALANCE_DAYLIGHT);
		parameters.setExposureCompensation(1);
		camera.setParameters(parameters);
		
		camera.setDisplayOrientation(90);
		
		camera.startPreview();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		try {
			camera = Camera.open();
			camera.setPreviewCallback(
				new PreviewCallback() 
				{
					public void onPreviewFrame(byte[] _data, Camera _camera) 
					{
						if (bob != null) {
							bob.update(_data);
							bob.invalidate();
						}
					}
				}
			);
			camera.setPreviewDisplay(holder);
		} catch (IOException exception) {
			camera.release();
			camera = null;
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		// http://code.google.com/p/android/issues/detail?id=6201
		camera.setPreviewCallback(null);
		camera.stopPreview();
		camera.release();
		camera = null;
	}

	@Override
	public void onResume() 
	{
		super.onResume();
		stabilityMonitor.resume();
		bob.resume();
	}

	@Override
	public void onPause() 
	{
		super.onPause();
		stabilityMonitor.pause();
		bob.pause();
	}

	@Override
	public void onBecomingStable() 
	{
		stabilityLabel.setText("Stable");
		stabilityLabel.setBackgroundColor(Color.GREEN);
	}

	@Override
	public void onBecomingUnstable() 
	{
		stabilityLabel.setText("Unstable");
		stabilityLabel.setBackgroundColor(Color.RED);
		hasPlayedStabilitySound = false;
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) {
		case R.id.prefsButton:
			Intent i = new Intent(this, Prefs.class);
			startActivity(i);
			break;
		default:
			return;
		}
	}

	@Override
	public void onBecomingContinuouslyStable() 
	{
		if (!hasPlayedStabilitySound) {
			//playSound();
			hasPlayedStabilitySound = true;
		}
	}
	
	private void playSound()
	{
		MediaPlayer mp = MediaPlayer.create(this, R.raw.beep);
        mp.start();
        mp.setOnCompletionListener(
        	new OnCompletionListener() 
        	{
        		@Override
        		public void onCompletion(MediaPlayer mp) 
        		{
        			mp.release();
        		}
        	}
        );
	}
	
}