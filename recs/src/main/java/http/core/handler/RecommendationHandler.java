package http.core.handler;

import http.exception.HttpHandlerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Painting;

import util.json.JSONArray;
import util.json.JSONException;
import util.json.JSONObject;

import com.google.code.morphia.Datastore;

public class RecommendationHandler implements Handler {

	private Datastore ds;
	
	public RecommendationHandler(Datastore ds)
	{
		this.ds = ds;
	}
	
	@Override
	public String get(Map<String, String> requestParams) throws HttpHandlerErrorException 
	{
		return null;
	}

	@Override
	public String post(Map<String, String> requestParams) throws HttpHandlerErrorException 
	{
		JSONArray recommendations = new JSONArray();
		try {
			if (requestParams.containsKey("matched")) {
				JSONObject matched = new JSONObject(requestParams.get("matched"));
				recommendations = getRecommendations(matched);
			} 
			return recommendations.toString();
		} catch (JSONException ex) {
			throw new HttpHandlerErrorException(ex.getMessage(), ex);
		}		
	}

	@Override
	public String put(Map<String, String> requestParams) throws HttpHandlerErrorException 
	{
		return null;
	}

	@Override
	public String delete(Map<String, String> requestParams) throws HttpHandlerErrorException 
	{
		return null;
	}
	
	private JSONArray getRecommendations(JSONObject matched) throws JSONException
	{
		JSONArray arrayRecommendations = new JSONArray();
		Painting painting = ds.find(Painting.class, "paintingId", matched.get("pid")).get();
		List<Painting> recommendedPaintings = ds.createQuery(Painting.class).field("tags").hasAnyOf(painting.getTags()).asList();
		for (Painting p : recommendedPaintings) {
			JSONObject o = new JSONObject();
			if (!p.getPaintingId().equals(painting.getPaintingId())) {
				o.put("pid", p.getPaintingId());
				o.put("title", p.getTitle());
				o.put("artist", p.getArtist());
				arrayRecommendations.put(o);
			}
		}
		return arrayRecommendations;
	}
	
}
