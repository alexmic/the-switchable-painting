package http.core.handler;


import http.exception.HttpHandlerErrorException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Painting;
import util.json.JSONArray;
import util.json.JSONException;
import util.json.JSONObject;

import com.google.code.morphia.Datastore;

import cv.descriptor.FeatureVector;
import cv.descriptor.strategy.Context;
import cv.descriptor.strategy.DescriptorContext;
import cv.detector.MultiScaleFast12;
import cv.detector.fast.FeaturePoint;

public class DescribeHandler implements Handler {

	private Datastore ds = null;
	
	public DescribeHandler(Datastore ds)
	{
		this.ds = ds;
	}
	
	@Override
	public String get(Map<String, String> requestParams) 
	{
		return "GET handled.";
	}

	@Override
	public String put(Map<String, String> requestParams) 
	{
		return "PUT handled.";
	}

	@Override
	public String post(Map<String, String> requestParams) throws HttpHandlerErrorException
	{
		String path = "/Users/alexis/Desktop/Dev/IndividualProject/server/storage/img";
		if (requestParams.containsKey("id")) {
			String pID = requestParams.get("id");
			char[] pIDChars = pID.toCharArray(); 
			String imgPath = path + "/" + pIDChars[0] + "/" + pIDChars[1] + "/" + pID;
			String[] exts = {"png", "jpg", "jpeg", "gif"};
			String ext = "";
			
			// Get image from storage.
			for (int i = 0; i < exts.length; ++i) {
				if (new File(imgPath + "." + exts[i]).exists()) {
					ext = exts[i];
					break;
				}
			}
			
			// Describe image using the appropriate strategy.
			try {
				
				// Create the descriptor strategy context.
				Context descriptorContext = null;
				int keycode = 0;
				if (requestParams.containsKey("s")) {
					keycode = Integer.valueOf(requestParams.get("s"));
				} 
				descriptorContext = new DescriptorContext(keycode);
				
				// Detect feature points at various scales and extract
				// descriptors at each scale.
				int levels = 7;
				long start = System.currentTimeMillis();
				MultiScaleFast12 msf = new MultiScaleFast12(imgPath + "." + ext, levels);
				long stop = System.currentTimeMillis();
				System.out.println(imgPath + "="+"Multiscale FAST: " + (stop - start) + "ms.");
				List<FeatureVector> multiScaleVectors = new ArrayList<FeatureVector>();
				int[] scaleIndices = new int[levels];
				int numVectors = 0;
				start = System.currentTimeMillis();
				for (int i = 0; i < levels; ++i) {
					List<FeaturePoint> featurePoints = msf.getFeaturesAtLevel(i);
					double[][] image = msf.getImageAtLevel(i);
					List<FeatureVector> singleScaleVectors = descriptorContext.getFeatureVectors(featurePoints, image);
					scaleIndices[i] = numVectors + singleScaleVectors.size();
					multiScaleVectors.addAll(singleScaleVectors);
					numVectors += singleScaleVectors.size();
				}
				stop = System.currentTimeMillis();
				System.out.println(imgPath + "="+"SIFT " + numVectors + " features: " + (stop - start) + "ms.");

				// Get tags if any.
				List<String> tags = new ArrayList<String>();
				if (requestParams.containsKey("tags")) {
					tags = toStringList(new JSONArray(requestParams.get("tags")));
				}
				
				// Save painting.
				Painting newPainting = new Painting()
									   .setDescriptorType(keycode)
									   .setPaintingId(pID)
									   .setArtist(requestParams.containsKey("artist")? requestParams.get("artist"): "Unknown")
									   .setTitle(requestParams.containsKey("title")? requestParams.get("title"): "Untitled")
									   .setTags(tags)
									   .setFeatureVectors(multiScaleVectors)
									   .setScaleIndices(scaleIndices);
				ds.save(newPainting);
				return new JSONObject().put("s", true).put("id", pID).put("msg", "").toString();
			
			} catch (Exception e) {
				// Wrap up any exceptions to be handled in the HttpJob. Exceptions are errors
				// which will lead to an error response (i.e not 200) and will not contain
				// a response body.
				e.printStackTrace();
				throw new HttpHandlerErrorException(e.getMessage(), e);
			}
		} else {
			// If not ID was found, then return an error msg in an 200 response,
			// so that the user knows what they omitted.
			try {
				return new JSONObject().put("s", false).put("msg", "No id was found in request.").toString();
			} catch(Exception e) {
				throw new HttpHandlerErrorException(e.getMessage(), e);
			}
		}
	}

	private List<String> toStringList(JSONArray jsonTags) throws JSONException 
	{
		List<String> tags = new ArrayList<String>();
		for (int i = 0; i < jsonTags.length(); ++i) {
			tags.add(jsonTags.getString(i)); 
		}
		return tags;
	}

	@Override
	public String delete(Map<String, String> requestParams) 
	{
		return "DELETE handled.";
	}

}
