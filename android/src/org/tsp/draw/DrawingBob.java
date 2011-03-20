package org.tsp.draw;

import static com.googlecode.javacv.cpp.opencv_core.CV_WHOLE_SEQ;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSeqElem;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_CHAIN_APPROX_SIMPLE;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_POLY_APPROX_DP;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_RETR_LIST;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvApproxPoly;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCanny;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvContourArea;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvContourPerimeter;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvDilate;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvFindContours;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.tsp.activity.Prefs;
import org.tsp.comm.ImageDownloadThread;
import org.tsp.comm.ImageDownloadThreadResult;
import org.tsp.comm.ORSMatchThread;
import org.tsp.comm.ORSMatchThreadResult;
import org.tsp.cv.descriptor.FeatureVector;
import org.tsp.cv.descriptor.strategy.DescriptorContext;
import org.tsp.cv.detector.fast.Fast12;
import org.tsp.cv.detector.fast.FeaturePoint;
import org.tsp.model.Painting;
import org.tsp.stability.StabilityListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.cpp.opencv_core.CvContour;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 * DrawingBob is responsible for drawing stuff on top of the Camera preview.
 */
public class DrawingBob extends View implements StabilityListener
{
	private final int FAST_THRESHOLD = 18;
	private final int MAX_NUM_OF_FEATURES = 250;
	private final int WHITE_THRESHOLD = 120;
	private final int WHITE_INTENSITY = 255;
	
	private final int SHOWOFF_BEHAVIOUR = 0;
	private final int NORMAL_BEHAVIOUR = 1;

	private final int READY_FOR_SNAPSHOT = 2;
	private final int WAIT_FOR_MATCH_RESULTS = 3;
	private final int WAIT_FOR_IMAGE_DOWNLOAD = 4;
	private final int AUGMENTING_VIDEO = 5;
	private final int IDLE = 6;
	private final int MIN_AREA_THRESHOLD = 19000;  // (~1:6 ratio)
	
	private int currentBehaviour = NORMAL_BEHAVIOUR;
	private int currentState = IDLE; 
	private int currentImgIndex = 0;
	
	private boolean isPaused = false;
	private double[][] grayScale2D = null;

	private int imageWidth = 0;
	private int imageHeight = 0;
	
	private float parentWidth = 0f;
	private float parentHeight = 0f;
	
	private float aspectX = 0f;
	private float aspectY = 0f;
	
	private Context parentContext = null;
	
	private Paint paintRed = null;
	private Paint paintWhite = null;
	private Paint paintBlack = null;
	private Paint paintGreen = null;
	
	private IplImage grayImage = null;
    private CvMemStorage storage = null;
    
    private DescriptorContext descriptorContext = null;
    
	private ORSMatchThreadResult ORSMatchResult = new ORSMatchThreadResult();
	private ImageDownloadThreadResult imageDownloadResult = new ImageDownloadThreadResult();
	
	private List<Bitmap> imagesToDraw = null;
	
	public DrawingBob(Context context, int w, int h) 
	{
		super(context);
		
		parentContext = context;
		
		paintRed = new Paint();
		paintRed.setStyle(Paint.Style.FILL);
		paintRed.setColor(Color.RED);
		paintRed.setStrokeWidth(2.3f);
		
		paintGreen = new Paint();
		paintGreen.setStyle(Paint.Style.FILL);
		paintGreen.setColor(Color.GREEN);
		paintGreen.setStrokeWidth(3.3f);
		
		paintWhite = new Paint();
		paintWhite.setStyle(Paint.Style.FILL);
		paintWhite.setColor(Color.WHITE);
		paintWhite.setStrokeWidth(1.7f);
		
		paintBlack = new Paint();
		paintBlack.setStyle(Paint.Style.FILL);
		paintBlack.setColor(Color.BLACK);
		paintBlack.setStrokeWidth(1.7f);
		
		imageHeight = w;
		imageWidth = h;
		
		// We are going to deal with integers so we allocate 4x the size (int = 4 bytes).
		grayImage = IplImage.create(imageWidth * 4, imageHeight * 4, IPL_DEPTH_8U, 1);
		storage = CvMemStorage.create();
		
		grayScale2D = new double[imageHeight][imageWidth];
	}
	
	/**
	 * Once its parent container is created, this method needs
	 * to be called to set the dimensions inside Bob and also
	 * compute the aspect ratio of the captures preview frame with 
	 * respect to the View that's displaying the preview.
	 * 
	 * @param parentWidth
	 * 					The width of the parent View.
	 * @param parentHeight
	 * 					The height of the parent View.
	 */
	public DrawingBob setParentDimensions(float parentWidth, float parentHeight)
	{
		this.parentWidth = parentWidth;
		this.parentHeight = parentHeight;
		this.aspectY = this.parentWidth / this.imageHeight;
		this.aspectX = this.parentHeight / this.imageWidth;
		return this;
	}
	
	public int getImageWidth() 
	{
		return imageWidth;
	}

	public int getImageHeight() 
	{
		return imageHeight;
	}

	public DrawingBob pause()
	{
		isPaused = true;
		currentState = IDLE;
		imagesToDraw = null;
		currentImgIndex = 0;
		return this;
	}
	
	public DrawingBob resume()
	{
		isPaused = false;
		// If the activity was paused while a thread was running
		// the thread will continue to execute and populate the result objects.
		// We thus need to reset them on resume.
		ORSMatchResult.reset();
		imageDownloadResult.reset();
		return this;
	}
	
	public boolean isPaused()
	{
		return isPaused;
	}
	
	/**
	 * Extracts the luminance information from the YUV420SP byte
	 * array received from the onPreviewFrame callback. According to
	 * http://code.google.com/p/android/issues/detail?id=823 the first
	 * [width * height] bytes represent the luminance information in the
	 * captured frame. During extraction the bytes are converted to integers
	 * and are stored rotated by 90 degrees clockwise to get a portrait view.
	 
	 * @param yuv420sp
	 * 				 The byte[] data received from onPreviewFrame.
	 */
	public DrawingBob extractGrayScaleData(byte[] yuv420sp) 
	{
		int imageStride = grayImage.widthStep();
		ByteBuffer imageBuffer = grayImage.getByteBuffer();
		for (int i = 0; i < imageWidth; ++i) {
			int dataLine  = i * imageHeight;
    		for (int j = 0; j < imageHeight; ++j) {
    			int imageLine = j * imageStride;
    			int pixVal = (0xff & ((int) yuv420sp[dataLine + j])) - 16;
        		if (pixVal < 0) pixVal = 0;
        		if (pixVal > 255) pixVal = 255;
        		imageBuffer.putInt(imageLine + i, pixVal);
        		grayScale2D[j][imageWidth-1-i] = pixVal;
    		}
		}
		return this;
	}
	
	public DrawingBob update(byte[] yuv420sp)
	{
		extractGrayScaleData(yuv420sp);
		return this;
	}
	
	/**
	 * On every drawing request we compute the FAST corners and display them.
	 * This will probably change since we want to compute corners only when 
	 * the frame is stable.
	 */
	@Override
    protected void onDraw(Canvas canvas) 
	{
        if (!isPaused()) {
        	// Read behaviour from preferences.
        	// Might need some synchronization here..
        	currentBehaviour = Prefs.getPref_isShowoffBehaviourEnabled(parentContext)? SHOWOFF_BEHAVIOUR : NORMAL_BEHAVIOUR;
        	switch (currentBehaviour) {
				case NORMAL_BEHAVIOUR:
					Log.d("DrawingBob", "--- NORMAL BEHAVIOUR State: " + currentState + " ---");
					switch (currentState) {
						case READY_FOR_SNAPSHOT:
							CvPoint paintingCorners = new CvPoint(4);
							double area = extractCorners(paintingCorners);
							if (area > MIN_AREA_THRESHOLD) {
								// Experiment with extracted corners area. If area is less than
								// a threshold we won't try to do any recognition at all.
								//Log.d("D", " ---- got corners ---- " + area);
								double[][] subregion = extractBoundingRect(paintingCorners, canvas);
								if (subregion.length > 0 && subregion[0].length > 0) {
									// This should take a couple of hundreds milliseconds. Remember
									// to time this.
									List<FeaturePoint> featurePoints 
										= Fast12.detect(subregion, subregion[0].length, subregion.length, 
														FAST_THRESHOLD, MAX_NUM_OF_FEATURES);
									//Log.d("D", " ---- performed FAST ---- " + area);
									int strategy = Integer.valueOf(Prefs.getPref_selectDescriptor(parentContext));
									descriptorContext = new DescriptorContext(strategy);
									List<FeatureVector> featureVectors = descriptorContext.getFeatureVectors(featurePoints, subregion);
									//Log.d("D", " ---- performed DESCRIPTION ---- " + area);
									new ORSMatchThread(parentContext, featureVectors, strategy, ORSMatchResult).start();
									synchronized (this) {
										//Log.d("D", " ---- Waiting for results... ---- ");
										currentState = WAIT_FOR_MATCH_RESULTS;
									}
								}
				        	}
							break;
						case WAIT_FOR_MATCH_RESULTS: 
							if (ORSMatchResult.isFinished()) {
								//Log.d("D", " ---- Match finished ---- ");
								if (ORSMatchResult.isSuccessful()) {
									//Log.d("D", " ---- Match was successful ---- ");
									Painting matchedPainting = ORSMatchResult.getMatchedPainting();
									if (matchedPainting == null) {
										// Show message that no painting was matched and return to IDLE state.
										synchronized (this) {
											currentState = IDLE;
											ORSMatchResult.reset();
										}
									} else {
										//Log.d("D", " ---- MatchedPainting was ---- " + matchedPainting.title());
										// Do a nice animation with the matched painting.
										// THIS IS PURELY FOR TESTING THE AUGMENTATION
										List<String> imageUIDs = new ArrayList<String>();
										imageUIDs.add("a2test");
										new ImageDownloadThread(parentContext, imageUIDs, imageDownloadResult).start();
										synchronized (this) {
											currentState = WAIT_FOR_IMAGE_DOWNLOAD;
											ORSMatchResult.reset();
										}
									}
									//if (threadResult.getRelevantPaintings() == null) {
										// Show message that no relevant paintings were found and return
										// to IDLE state.
									//	synchronized (this) {
									//		currentState = IDLE;
									//	}
									//} else {
									//	paintingsToDraw = threadResult.getRelevantPaintings();
									//	synchronized (this) {
									//		currentState = AUGMENTING_VIDEO;
									//	}
									//}
								} else {
									// Show message that an error occured and return to IDLE state.
									synchronized (this) {
										currentState = IDLE;
										ORSMatchResult.reset();
									}
								}
							}
							break;
						case WAIT_FOR_IMAGE_DOWNLOAD:
							if (imageDownloadResult.isFinished()) {
								//Log.d("D", " ---- Image Download finished ---- ");
								if (imageDownloadResult.isSuccessful()) {
								//	Log.d("D", " ---- Image Download was successful ---- ");
									synchronized (this) {
										imagesToDraw = imageDownloadResult.getDownloadedImages();
										currentState = AUGMENTING_VIDEO;
										imageDownloadResult.reset();
									}
								}
							}
							break;
						case AUGMENTING_VIDEO:
							Bitmap currentImage = imagesToDraw.get(currentImgIndex);
							canvas.drawBitmap(currentImage, 0, 0, null);
				        	// (6) Enable gesture recognition.
							//CvPoint trackedCorners = trackCorners();
							//synchronized (this) {
							//	drawCurrentImage();		
							//}
							break;
						case IDLE: 
							break;
						default:
							break;
					}
					break;
				case SHOWOFF_BEHAVIOUR:
					CvPoint paintingCorners = new CvPoint(4);
		        	double area  = extractCorners(paintingCorners);
		        	drawEdgeImage(canvas);
		        	if (area > MIN_AREA_THRESHOLD) {
		        		drawPaintingOutline(paintingCorners, canvas);
		        	}
	        		//List<FeaturePoint> fastCorners = Fast12.detect(grayScale2D, imageWidth, imageHeight, 
					//		   FAST_THRESHOLD, MAX_NUM_OF_FEATURES);
	        		//drawFastCorners(fastCorners, canvas);
				default:
					break;
        	}
        	
            cvClearMemStorage(storage);

        }
        super.onDraw(canvas);
	}

	private void drawCurrentImage() {
		// TODO Auto-generated method stub
		
	}

	private double[][] extractBoundingRect(CvPoint paintingCorners, Canvas canvas) {
		int minX = imageWidth;
		int maxX = 0;
		int minY = imageHeight;
		int maxY = 0;
		for (int i = 0; i< 4; i++) {
			CvPoint p = paintingCorners.position(i);
			minX = Math.min(minX, p.x());
			minY = Math.min(minY, p.y());
			maxX = Math.max(maxX, p.x());
			maxY = Math.max(maxY, p.y());
		}
		canvas.drawLine((imageWidth - minX) * aspectX, minY * aspectY, (imageWidth - maxX) * aspectX, minY * aspectY, paintGreen);
		canvas.drawLine((imageWidth - maxX) * aspectX, minY * aspectY, (imageWidth - maxX) * aspectX, maxY * aspectY, paintGreen);
		canvas.drawLine((imageWidth - maxX) * aspectX, maxY * aspectY, (imageWidth - minX) * aspectX, maxY * aspectY, paintGreen);
		canvas.drawLine((imageWidth - minX) * aspectX, maxY * aspectY, (imageWidth - minX) * aspectX, minY * aspectY, paintGreen);
		double[][] subregion = new double[maxY - minY][maxX - minX];
		for (int y = minY; y < maxY; y++) {
			for (int x = minX; x <maxX; x++) {
				subregion[y - minY][x - minX] = grayScale2D[y][x];
			}
		}
		return subregion;
	}

	private CvPoint trackCorners() {
		// TODO Auto-generated method stub
		return null;
	}

	private void drawFastCorners(List<FeaturePoint> corners, Canvas canvas) 
	{
		Rect rect = new Rect();
		for (FeaturePoint fp : corners) {
			rect.top = (int) (fp.y() * aspectY);
			rect.bottom = rect.top + 5;
			rect.left = (int) (fp.x() * aspectX);
			rect.right = rect.left + 5;
			canvas.drawRect(rect, paintRed);
		}
	}

	private void drawPaintingOutline(CvPoint corners, Canvas canvas) 
	{
		int prevX = -1;
		int prevY = -1;
		int currX = -1;
		int currY = -1;
		for (int i = 0; i < 5; i++) {
			if (currX != -1) {
				prevX = currX;
				prevY = currY;
				currX = corners.position(i % 4).x();
				currY = corners.position(i % 4).y();
				canvas.drawLine((imageWidth - prevX) * aspectX, prevY * aspectY, (imageWidth - currX) * aspectX, currY * aspectY, paintGreen);
			} else {
				currX = corners.position(i % 4).x();
				currY = corners.position(i % 4).y();
			}
		}
		
	}

	private void drawEdgeImage(Canvas canvas) 
	{
		Paint currPaint = new Paint();
		currPaint.setStrokeWidth(1.7f);
		currPaint.setStyle(Paint.Style.FILL);
		ByteBuffer imageBuffer = grayImage.getByteBuffer();
		int imageStride = grayImage.widthStep();
		for (int i = 0; i < imageWidth; ++i) {
    		for (int j = 0; j < imageHeight; ++j) {
    			int imageLine = j * imageStride;
    			int pixVal = imageBuffer.getInt(imageLine + i);
    			currPaint.setARGB(255, pixVal, pixVal, pixVal);
    			canvas.drawPoint((imageWidth - 1 - i)*aspectX, j*aspectY, currPaint);
    		}
		}
	}

	@Override
	public void onBecomingStable() {;}

	@Override
	public void onBecomingUnstable() 
	{
		synchronized (this) {
			if (currentState != WAIT_FOR_MATCH_RESULTS 
				&& currentState != AUGMENTING_VIDEO
				&& currentState != WAIT_FOR_IMAGE_DOWNLOAD ) {
				currentState = IDLE;
			}
		}
	}
	
	@Override
	public void onBecomingContinuouslyStable() 
	{
		synchronized(this) {
			if (currentState != WAIT_FOR_MATCH_RESULTS 
				&& currentState != AUGMENTING_VIDEO
				&& currentState != WAIT_FOR_IMAGE_DOWNLOAD ) {				
				currentState = READY_FOR_SNAPSHOT;
			}
		}
	}
	
	private double extractCorners(CvPoint corners)
	{
		cvCanny(grayImage, grayImage, 1, 254, 3);
		cvDilate(grayImage, grayImage, null, 1);
		CvSeq contour = new CvSeq(null);
        cvFindContours(grayImage, storage, contour, Loader.sizeof(CvContour.class),
                 CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
        double maxArea = 0;
        while (contour != null && !contour.isNull()) {
        	if (contour.elem_size() > 0) {
        		CvSeq points = cvApproxPoly(contour, Loader.sizeof(CvContour.class),
                         storage, CV_POLY_APPROX_DP, cvContourPerimeter(contour)*0.02, 0);
        		double area = Math.abs(cvContourArea(contour, CV_WHOLE_SEQ, 0));
        		if (points.total() == 4) {
        			if (area > maxArea) {
        				maxArea = area;
        				for (int i = 0; i < 4; ++i) {
        					CvPoint point = new CvPoint(cvGetSeqElem(points, i));
        					corners.position(i).x(point.x());
        					corners.position(i).y(point.y());
        				}
        			}
        		}
            }
            contour = contour.h_next();
        }
        return maxArea;
	}

}	
