package http.core.handler;

import http.exception.HttpHandlerErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.json.JSONArray;
import util.json.JSONException;
import util.json.JSONObject;
import model.Painting;
import com.google.code.morphia.Datastore;

import cv.descriptor.FeatureVectorType;

public class SimMatchHandler implements Handler {

	private Datastore ds = null;
	
	public SimMatchHandler(Datastore ds)
	{
		this.ds = ds;
	}
	
	@Override
	public String get(Map<String, String> requestParams) throws HttpHandlerErrorException 
	{
		/*
		 * MATCHING SIMULATION TEST.
		 * 
		 * This test will serve to explore the appropriability of the three methods of feature description,
		 * namely (1) Intensity histogram (2) SIFT.
		 * 
		 * We will always consider the same image (Son of Man) for now.
		 */
		
		// Descriptor strategies: Intensity Histogram -> 0, SIFT -> 1
		int s = FeatureVectorType.INTENSITY_HISTOGRAM.ordinal();
		if (requestParams.containsKey("s")) {
			s = Integer.valueOf(requestParams.get("s"));
		}
		
		String title = "Son of Man";
		if (requestParams.containsKey("title")) {
			title = requestParams.get("title");
		}
		
		try {
			JSONObject response = new JSONObject();
			response.put("success", true);
			JSONArray  imgs = new JSONArray();
			float D_THRESHOLD = 0.2f;
			Painting cameraPainting = ds.createQuery(Painting.class)
										.field("title").equal(title)
										.field("descriptorType").equal(s).get();
		
			if (cameraPainting == null) {
				response.put("success", false);
				response.put("err_msg", "Painting does not exist.");
				return response.toString();
			}
		
			List<Painting> storedPaintings = ds.find(Painting.class, "descriptorType", s).asList();
			
			// Calculate matching scores and create JSON response.
			for (Painting p : storedPaintings) {
				if (p.getFeatureVectors() == null) continue;
				JSONObject img = new JSONObject();
				img.put("pid", p.getPaintingId())
				   .put("score", cameraPainting.getMatchScore(p, D_THRESHOLD))
				   .put("title", p.getTitle())
				   .put("artist", p.getArtist());
				imgs.put(img);
			}
			response.put("simid", cameraPainting.getPaintingId())
					.put("imgs", imgs);
			
			return response.toString();

		} catch (JSONException e) {
			throw new HttpHandlerErrorException(e.getMessage(), e);
		}
		
	}

	@Override
	public String post(Map<String, String> requestParams)
			throws HttpHandlerErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String put(Map<String, String> requestParams)
			throws HttpHandlerErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Map<String, String> requestParams)
			throws HttpHandlerErrorException {
		// TODO Auto-generated method stub
		return null;
	}

}
