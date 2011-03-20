package org.tsp.comm;

import java.util.List;

import android.graphics.Bitmap;

public class ImageDownloadThreadResult extends ThreadResult{

	private List<Bitmap> downloadedImages = null;
	
	public ImageDownloadThreadResult()
	{
		super();
	}
	
	@Override
	protected void subReset() 
	{
		downloadedImages = null;
	}
	
	public void setDownloadedImages(List<Bitmap> images)
	{
		this.downloadedImages = images;
	}
	
	public List<Bitmap> getDownloadedImages()
	{
		return downloadedImages;
	}

}
