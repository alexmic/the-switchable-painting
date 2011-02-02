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

public class SimMatchHandler implements Handler {

	private Datastore ds = null;
	
	public SimMatchHandler(Datastore ds)
	{
		this.ds = ds;
	}
	
	@Override
	public String get(Map<String, String> requestParams)
			throws HttpHandlerErrorException {
		
		/*
		 * This is a simulated test for matching. It serves the GET method in order to be triggered by the browser.
		 * We fetch a painting from the database to serve as the painting coming from the mobile phone. We then perform
		 * the matching procedure and declare a match if any.
		 */
		Painting cameraPainting = ds.find(Painting.class, "title", "Son of Man").get();
		if (cameraPainting == null) {
			return "Painting does not exist.";
		}
		
		List<Painting> storedPaintings = ds.find(Painting.class).asList();
		Map<Painting, Float> rankings = new HashMap<Painting, Float>();
		
		// Init rankings to 0.
		for (Painting p : storedPaintings) {
			rankings.put(p, 0f);
		}
		
		// Calculate matching scores and create JSON response.
		JSONObject response = new JSONObject();
		JSONArray imgArray = new JSONArray();
		try {
			for (Painting p : storedPaintings) {
				if (p.getFeatureVectors() == null) continue;
				JSONObject img = new JSONObject();
				img.put("pid", p.getPaintingId())
				   .put("score", cameraPainting.getMatchScore(p, 12))
				   .put("title", p.getTitle())
				   .put("artist", p.getArtist());
				imgArray.put(img);
			}
			response.put("simid", cameraPainting.getPaintingId())
					.put("imgs", imgArray);
			/*StringBuffer rrs = new StringBuffer();
			for (Painting p : rankings.keySet()) {
				rrs.append(p.getTitle()).append(": ").append(rankings.get(p)).append("\n");
			}
			*/
			return response.toString();

		} catch (JSONException e) {
			throw new HttpHandlerErrorException(e);
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
