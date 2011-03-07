package org.tsp.draw;

import java.util.List;

import org.tsp.draw.cv.detector.fast.Fast12;
import org.tsp.draw.cv.detector.fast.FeaturePoint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class DrawingBob extends View{
	
	// Define a bunch of colors.
	private Paint paintGreen = null;
	private Paint paintRed = null;
	private Paint paintBlue = null;
	
	private Bitmap bitmap = null;
	
	private byte[] YUVData = null;
	private double[][] RGBData = null;
	
	private int imageWidth = 0;
	private int imageHeight = 0;
	
	private final int FAST_THRESHOLD = 19;
	private final int MAX_NUM_OF_FEATURES = 150;
	
	public DrawingBob(Context context) 
	{
		super(context);
		
		paintGreen = new Paint();
		paintGreen.setStyle(Paint.Style.FILL);
		paintGreen.setColor(Color.BLACK);
		
		paintRed = new Paint();
		paintRed.setStyle(Paint.Style.FILL);
		paintRed.setColor(Color.RED);
		
		paintBlue = new Paint();
		paintBlue.setStyle(Paint.Style.FILL);
		paintBlue.setColor(Color.BLUE);
		
	}
	
	public Bitmap getBitmap() 
	{
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) 
	{
		this.bitmap = bitmap;
	}

	public int getImageWidth() 
	{
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) 
	{
		this.imageWidth = imageWidth;
		Log.d("asg", "========== HEIGHT"+ imageHeight);
	}

	public int getImageHeight() 
	{
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) 
	{
		this.imageHeight = imageHeight;
		Log.d("asg", "========== HEIGHT"+ imageHeight);
	}
	
	public byte[] getYUVData() 
	{
		return YUVData;
	}

	public void setYUVData(byte[] yUVData) 
	{
		this.YUVData = yUVData;
	}
	
	public void copyYUVData(byte[] yUVData) 
	{
		System.arraycopy(yUVData, 0, YUVData, 0, yUVData.length);
	}

	public double[][] getRGBData() 
	{
		return RGBData;
	}

	public void setRGBData(double[][] rGBData)
	{
		RGBData = rGBData;
	}
	
	@Override
    protected void onDraw(Canvas canvas) 
	{
        if (this.bitmap != null) {
        	decodeYUV420SPGrayscale(RGBData, YUVData, imageWidth, imageHeight);
        	List<FeaturePoint> featurePoints = Fast12.detect(RGBData, imageWidth, imageHeight, FAST_THRESHOLD, MAX_NUM_OF_FEATURES);
        	Rect rect = new Rect();
        	for (FeaturePoint fp : featurePoints) {
        		rect.top = fp.y();
        		rect.bottom = rect.top + 3;
        		rect.left = fp.x();
        		rect.right = fp.x() + 3;
        		canvas.drawRect(rect, paintRed);
        	}
        }
        super.onDraw(canvas);
	}

	private void decodeYUV420SPGrayscale(double[][] rgb, byte[] yuv420sp, int w, int h)
    {
    	for (int y = 0; y < h; ++y) {
    		for (int x = 0; x < w; ++x) {
    			int pixVal = (0xff & ((int) yuv420sp[y * w + x])) - 16;
        		if (pixVal < 0) pixVal = 0;
        		if (pixVal > 255) pixVal = 255;
        		rgb[y][x] = pixVal;
    		}
    	}
    }

}	
