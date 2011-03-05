package http.core.handler;

import http.exception.HttpHandlerErrorException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Painting;
import util.json.JSONObject;
import com.google.code.morphia.Datastore;
import cv.descriptor.FeatureVector;
import cv.descriptor.strategy.Context;
import cv.descriptor.strategy.DescriptorContext;
import cv.detector.MultiScaleFast12;
import cv.detector.fast.FeaturePoint;

public class TestFDHandler implements Handler {
	
	private Datastore ds = null;
	
	public TestFDHandler(Datastore ds)
	{
		this.ds = ds;
	}
	
	@Override
	public String get(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String post(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String put(Map<String, String> requestParams) throws HttpHandlerErrorException {
		String path = "/Users/alexis/Desktop/Dev/IndividualProject/server/storage/img";
		if (requestParams.containsKey("id")) {
			String pID = requestParams.get("id");
			char[] pIDChars = pID.toCharArray(); 
			String imgPath = path + "/" + pIDChars[0] + "/" + pIDChars[1] + "/" + pID;
			String[] exts = {"png", "jpg", "jpeg", "gif"};
			String ext = "";
			for (int i = 0; i < exts.length; ++i) {
				if (new File(imgPath + "." + exts[i]).exists()) {
					ext = exts[i];
					break;
				}
			}
			//String drawnImgPath = path + "/" + pIDChars[0] + "/" + pIDChars[1] + "/" + pID + "_CORNERS." + ext;
			//BufferedImage img;
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
				System.out.println("Multiscale FAST: " + (stop - start) + "ms.");
				List<FeatureVector> multiScaleVectors = new ArrayList<FeatureVector>();
				int[] scaleIndices = new int[levels];
				int numVectors = 0;
				for (int i = 0; i < levels; ++i) {
					System.out.println("Pyramid level: " + i);
					List<FeaturePoint> featurePoints = msf.getFeaturesAtLevel(i);
					double[][] image = msf.getImageAtLevel(i);
					start = System.currentTimeMillis();
					List<FeatureVector> singleScaleVectors = descriptorContext.getFeatureVectors(featurePoints, image);
					stop = System.currentTimeMillis();
					System.out.println("SIFT for level " + i + ", " + singleScaleVectors.size() + " features: " + (stop - start) + "ms.");
					scaleIndices[i] = numVectors + singleScaleVectors.size();
					multiScaleVectors.addAll(singleScaleVectors);
					numVectors += singleScaleVectors.size();
				}
				//for (FeatureVector fv : multiScaleVectors) {
				//	for (float f : fv.descriptor()) 
				//		System.out.println(f);
				//}
				//Draw up some rectangles to have something to demonstrate.
				//Graphics2D g2 = img.createGraphics();
				//g2.setColor(new Color(250, 0 ,0));
				//int count = featurePoints.size();
				//for (int i = 0; i < count; ++i) {
				//	FeaturePoint p = featurePoints.get(i);
				//	g2.drawRect(p.x(), p.y(), 1, 1);
				//}
				
				Painting newPainting = new Painting()
									   .setDescriptorType(keycode)
									   .setPaintingId(pID)
									   .setArtist(requestParams.containsKey("artist")? requestParams.get("artist"): "Unknown")
									   .setTitle(requestParams.containsKey("title")? requestParams.get("title"): "Untitled")
									   .setFeatureVectors(multiScaleVectors)
									   .setScaleIndices(scaleIndices);
				ds.save(newPainting);
				//File out = new File(drawnImgPath);
				//ImageIO.write(img, "jpg", out);
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

	@Override
	public String delete(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
