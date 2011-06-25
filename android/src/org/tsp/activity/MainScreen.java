package org.tsp.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.tsp.R;
import org.tsp.draw.DrawingBob;
import org.tsp.stability.StabilityListener;
import org.tsp.stability.StabilityMonitor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainScreen extends Activity implements SurfaceHolder.Callback, StabilityListener, OnClickListener
{

    /* We will only consider the Nexus One - since this is not
	 * a commercial app, we hardcode the preview size.
	 */
	private final int PREVIEW_WIDTH_GENERAL = 176;//480;
	private final int PREVIEW_HEIGHT_GENERAL = 144;//320;
	
	private boolean AR = false;
    
	private final int PREVIEW_WIDTH_AR = 176;
	private final int PREVIEW_HEIGHT_AR = 144;
	
	private SurfaceView surfaceView = null;
	private SurfaceHolder surfaceHolder = null;
	private Camera camera = null;
	private StabilityMonitor stabilityMonitor = null;
	private TextView stabilityLabel = null;
	
	long time = 0;
	float avg = 0;
	int frames = 0;
	long start = 0;
	
	private HashMap<Integer, Dialog> dialogs = null;
	
	protected DrawingBob bob = null;
	
	private String DIALOG_MESSAGE = null;
	private boolean isDirtyMessage = false;
	
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
		
		bob = new DrawingBob(this, PREVIEW_WIDTH_GENERAL, PREVIEW_HEIGHT_GENERAL);
		
		stabilityMonitor = new StabilityMonitor(this);
		stabilityMonitor.addListener(this).addListener(bob);
		
		dialogs = new HashMap<Integer, Dialog>();
		
		Button prefsButton = (Button) findViewById(R.id.prefsButton);
		prefsButton.setOnClickListener(this);
		addContentView(bob, new LayoutParams(480, 733));	
	}
	
	protected void onPrepareDialog(int id, Dialog dialog)
	{ 
		if (isDirtyMessage) {
			((AlertDialog) dialog).setMessage("You are viewing " + DIALOG_MESSAGE + ". Do you want to see relevant paintings?");
			isDirtyMessage = false;
		}
	} 
	
	protected Dialog onCreateDialog(int id) 
	{
		Dialog dialog = null;
		switch (id){
			case 0:
				dialog = createCustomDialog(id, "Please wait..", "Recognizing painting.");
				break;
			case 1:
				dialog = createCustomDialog(id, "You are viewing " + DIALOG_MESSAGE + ". Do you want to see relevant paintings?", null);
				break;
			case 2:
				dialog = createCustomDialog(id, "An error occured. Do you want to try again?", null);
				break;
			case 3:
				dialog = createCustomDialog(id, "No relevant paintings found. Do you want to try again?", null);
				break;
			case 4:
				dialog = createCustomDialog(id, "Could not recognize painting. Do you want to try again?", null);
				break;
			case 5:
				dialog = createCustomDialog(id, "Paintings downloaded. Do you wish to view them?", null);
				break;
			case 6:
				dialog = createCustomDialog(id, "Downloading paintings..", "Please wait..");
		}
		return dialog;
	}
	
	public void receiveDialogMessage(String message)
	{
		DIALOG_MESSAGE = message;
		isDirtyMessage = true;
	}
	
	public void onDismissDialog(int type) 
	{
		if (dialogs.containsKey(type)) {
			dismissDialog(type);
		}
	}
	
	public void onShowDialog(int type) 
	{
		if (dialogs.containsKey(type)) {
			Dialog dialog = dialogs.get(type);
			if (!dialog.isShowing()) {
				showDialog(type);
			}
		} else {
			showDialog(type);
		}
	}
	
	private Dialog createCustomDialog(int type, String message, String title)
	{
		Dialog dialog = null;
		if (type == 0 || type == 6) {
			dialog = new Dialog(this);
		    dialog.setContentView(R.layout.notification_layout);
		    TextView text = (TextView) dialog.findViewById(R.id.notification_text);
		    ImageView image = (ImageView) dialog.findViewById(R.id.notification_image);
			dialog.setTitle(title);
	    	text.setText(message);
		    image.setImageResource(R.drawable.loading);
		} else {
			final int nextState = (type == 5) ? DrawingBob.AUGMENTING_VIDEO : 
									(type == 1) ? DrawingBob.WAIT_FOR_IMAGE_DOWNLOAD : DrawingBob.READY_FOR_SNAPSHOT;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setMessage(message)
	    	       .setCancelable(false)
	    	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	               dialog.cancel();
	    	        	   MainScreen.this.bob.setState(nextState);
	    	           }
	    	       })
	    	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	               dialog.cancel();
	    	           }
	    	       });
	    	dialog = builder.create();
		}
		if (!dialogs.containsKey(type)) {
			dialogs.put(type, dialog);
		}
		return dialog;
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) 
	{
		bob.setParentDimensions((float) surfaceView.getMeasuredHeight(), 
							    (float) surfaceView.getMeasuredWidth());
		
		camera.stopPreview();
		
		Camera.Parameters parameters = camera.getParameters();
		parameters.setPreviewSize(PREVIEW_WIDTH_GENERAL, PREVIEW_HEIGHT_GENERAL);
		parameters.setWhiteBalance(Parameters.WHITE_BALANCE_DAYLIGHT);
		parameters.setExposureCompensation(1);
		parameters.setPreviewFrameRate(27);
		camera.setParameters(parameters);
		
		camera.setDisplayOrientation(90);
		
		camera.startPreview();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		try {
			camera = Camera.open();
			start = System.currentTimeMillis();
			camera.setPreviewCallback(
				new PreviewCallback() 
				{
					public void onPreviewFrame(byte[] _data, Camera _camera) 
					{
						frames++;
						long newStart = System.currentTimeMillis();
						time += newStart - start;
						start = newStart;
						avg = time / (float) frames;
						Log.d("AVG TIME PER FRAME", "" + avg);
						Log.d("DIMENSIONS", "" + AR);
						
						//Log.d("AVG TIME PER FRAME", "" + avg);
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
	
	public void setCameraPreviewAR()
	{
		AR = true;
		if (camera != null) {
			Camera.Parameters parameters = camera.getParameters();
			parameters.setPreviewSize(PREVIEW_WIDTH_AR, PREVIEW_HEIGHT_AR);
			camera.setParameters(parameters);
			//bob.setPreviewDimensions(PREVIEW_WIDTH_AR, PREVIEW_HEIGHT_AR);
		}
	}
	
	public void setCameraPreviewGeneral()
	{
		AR = false;
		if (camera != null) {
			Camera.Parameters parameters = camera.getParameters();
			parameters.setPreviewSize(PREVIEW_WIDTH_GENERAL, PREVIEW_HEIGHT_GENERAL);
			camera.setParameters(parameters);
			bob.setPreviewDimensions(PREVIEW_WIDTH_GENERAL, PREVIEW_HEIGHT_GENERAL);
		}
	}
	
	public void playSound()
	{
		MediaPlayer mp = MediaPlayer.create(this, R.raw.cork);
        mp.start();
        mp.setVolume(220, 220);
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