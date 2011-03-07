package org.tsp.draw;

import java.util.List;

import org.tsp.draw.cv.detector.fast.Fast12;
import org.tsp.draw.cv.detector.fast.FeaturePoint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class DrawingBob extends View{
	
	// Define a bunch of colors.
	private Paint paintGreen = null;
	private Paint paintRed = null;
	private Paint paintBlue = null;
	
	private double[][] grayscale2D = null;
	
	private int imageWidth = 0;
	private int imageHeight = 0;
	
	private float parentWidth = 0f;
	private float parentHeight = 0f;
	
	private float aspectX = 0f;
	private float aspectY = 0f;
	
	private final int FAST_THRESHOLD = 18;
	private final int MAX_NUM_OF_FEATURES = 250;
	
	public DrawingBob(Context context, int w, int h) 
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
		
		// Bob will hold the rotated image.
		this.imageHeight = w;
		this.imageWidth = h;
		
		this.grayscale2D = new double[this.imageHeight][this.imageWidth];
	}
	
	public void setParentDimensions(float parentWidth, float parentHeight)
	{
		this.parentWidth = parentWidth;
		this.parentHeight = parentHeight;
		this.aspectY = this.parentWidth / this.imageHeight;
		this.aspectX = this.parentHeight / this.imageWidth;
		Log.d("asag", aspectX + " " + aspectY);
	}
	
	public int getImageWidth() 
	{
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) 
	{
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() 
	{
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) 
	{
		this.imageHeight = imageHeight;
	}
	
	public void extractGrayscaleData(byte[] yuv420sp) 
	{
		int w = this.imageHeight;
		int h = this.imageWidth;
		for (int i = 0; i < h; ++i) {
    		for (int j = 0; j < w; ++j) {
    			int index = i * w + j;
    			int pixVal = (0xff & ((int) yuv420sp[index])) - 16;
        		if (pixVal < 0) pixVal = 0;
        		if (pixVal > 255) pixVal = 255;
        		grayscale2D[j][h-1-i] = pixVal;
    		}
    	}
	}
	
	@Override
    protected void onDraw(Canvas canvas) 
	{
	
        if (this.grayscale2D != null) {
        	List<FeaturePoint> featurePoints = Fast12.detect(grayscale2D, imageWidth, imageHeight, FAST_THRESHOLD, MAX_NUM_OF_FEATURES);
        	Rect rect = new Rect();
        	for (FeaturePoint fp : featurePoints) {
        		rect.top = (int) (fp.y() * aspectY);
        		rect.bottom = rect.top + 5;
        		rect.left = (int) (fp.x() * aspectX);
        		rect.right = rect.left + 5;
        		canvas.drawRect(rect, paintRed);
        	}
        }
        super.onDraw(canvas);
	}

}	
