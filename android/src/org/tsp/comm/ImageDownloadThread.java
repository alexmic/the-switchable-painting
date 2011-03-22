package org.tsp.comm;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.tsp.activity.Prefs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.util.Log;

public class ImageDownloadThread extends Thread
{
	private String serverHandler = "/storage";

	private String serverIP = null;
	private ImageDownloadThreadResult result = null;
	private List<String> imageUIDs = null;
	
	public ImageDownloadThread(String serverIP, List<String> imageUIDs, ImageDownloadThreadResult result) 
	{
		super();
		this.result = result;
		this.serverIP = serverIP;
		this.imageUIDs = imageUIDs;
	}
	
	@Override
	public void run()
	{
		List<Bitmap> downloadedImages = new ArrayList<Bitmap>();
		boolean atLeastOneSucceeded = false;
		for (String UID : imageUIDs) {
			try {
				Bitmap image = downloadImage(serverIP + serverHandler + "/" + UID);
				if (image != null) {
					downloadedImages.add(image);
					atLeastOneSucceeded = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (atLeastOneSucceeded) {
			result.setDownloadedImages(downloadedImages);
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		result.setFinished(true);
	}
	
	private Bitmap downloadImage(String imageURL) throws IOException 
	{
		URL serverURL = new URL("http://" + imageURL);
		HttpURLConnection conn = (HttpURLConnection) serverURL.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.connect();
		Bitmap image = BitmapFactory.decodeStream(conn.getInputStream());
		return image;
	}
	
}
