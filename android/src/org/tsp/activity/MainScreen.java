package org.tsp.activity;

import java.io.IOException;

import org.tsp.R;
import org.tsp.draw.DrawingBob;
import org.tsp.stability.StabilityListener;
import org.tsp.stability.StabilityMonitor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends Activity implements SurfaceHolder.Callback, StabilityListener 
{
	private SurfaceView surfaceView = null;
	private SurfaceHolder surfaceHolder = null;
	private Camera camera = null;
	private StabilityMonitor stabilityMonitor = null;
	private TextView stabilityLabel = null;
	private DrawingBob bob = null;
	
	/* We will only consider this phone since this is not
	 * a commercial app, hence we hardcode the preview size.
	 */
	private final int PREVIEW_WIDTH = 480;
	private final int PREVIEW_HEIGHT = 320;
	
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
		stabilityMonitor = new StabilityMonitor(this, this.getBaseContext());
		bob = new DrawingBob(this, PREVIEW_WIDTH, PREVIEW_HEIGHT);
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
		parameters.setPreviewFrameRate(24);
		parameters.setWhiteBalance(Parameters.WHITE_BALANCE_AUTO);
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
					if (bob != null) {
						bob.extractGrayscaleData(_data);
						bob.invalidate();
						
					}
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
		stabilityLabel.setText("Stable");
		stabilityLabel.setBackgroundColor(Color.GREEN);
	}

	@Override
	public void onBecomingUnstable() 
	{
		stabilityLabel.setText("Unstable");
		stabilityLabel.setBackgroundColor(Color.RED);
	}

}