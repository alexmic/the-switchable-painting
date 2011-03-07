package org.tsp.activity;

import java.io.IOException;

import org.tsp.R;
import org.tsp.draw.DrawingBob;
import org.tsp.stability.StabilityListener;
import org.tsp.stability.StabilityMonitor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class MainScreen extends Activity implements SurfaceHolder.Callback,
		StabilityListener {

	private SurfaceView surfaceView = null;
	private SurfaceHolder surfaceHolder = null;
	private Camera camera = null;
	private StabilityMonitor stabilityMonitor = null;
	private Button stableButton = null;
	private DrawingBob bob = null;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		surfaceView = (SurfaceView) findViewById(R.id.camera_surface);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		stableButton = (Button) findViewById(R.id.testStableButton);
		stabilityMonitor = new StabilityMonitor(this, this.getBaseContext());
		bob = new DrawingBob(this);
		addContentView(bob, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));	
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) 
	{
		camera.stopPreview();
		Camera.Parameters parameters = camera.getParameters();
		parameters.setPreviewSize(480, 320);
		parameters.setPreviewFrameRate(15);
		camera.setParameters(parameters);
		camera.setDisplayOrientation(90);
		camera.startPreview();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		try {
			camera = Camera.open();
			camera.setPreviewCallback(new PreviewCallback() {
				public void onPreviewFrame(byte[] _data, Camera _camera) {
					// From Stanford's tutorial at:
					// http://www.doocu.com/pdf/read/31669.
					// Code can be found at:
					// http://ee368.stanford.edu/Android/ViewfinderEE368/ViewfinderEE368.java
					if (bob.getBitmap() == null) {
						// Init drawing surfaceView.
						Camera.Parameters params = camera.getParameters();
						int h = params.getPreviewSize().height;
						int w = params.getPreviewSize().width;
						bob.setImageWidth(w);
						bob.setImageHeight(h);
						bob.setBitmap(Bitmap.createBitmap(w, h,
								Bitmap.Config.RGB_565));
						bob.setRGBData(new double[h][w]);
						bob.setYUVData(new byte[_data.length]);
					}
					bob.copyYUVData(_data);
					bob.invalidate();
				}
			});
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
		stabilityMonitor.init();
	}

	@Override
	public void onPause() 
	{
		super.onPause();
		stabilityMonitor.shutDown();
	}

	@Override
	public void onBecomingStable() 
	{
		stableButton.setText("Stable");
	}

	@Override
	public void onBecomingUnstable() 
	{
		stableButton.setText("Unstable");
	}

}