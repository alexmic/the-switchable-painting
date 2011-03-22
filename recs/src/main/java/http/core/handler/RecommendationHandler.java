package http.core.handler;

import http.exception.HttpHandlerErrorException;

import java.util.Map;

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
		JSONArray recommendations = new JSONArray();
		try {
			if (requestParams.containsKey("matched")) {
				System.out.println("MATCHED");
				JSONObject matched = new JSONObject(requestParams.get("matched"));
				JSONObject rec = new JSONObject();
				rec.put("pid", "a2test1");
				rec.put("title", "Test Recommended Painting");
				rec.put("artist", "Alex Michael");
				recommendations.put(rec);
			} else {
				System.out.println("NOT MATCHED");
			}
			return recommendations.toString();
		} catch (JSONException ex) {
			throw new HttpHandlerErrorException(ex.getMessage(), ex);
		}		
	}

	@Override
	public String post(Map<String, String> requestParams) throws HttpHandlerErrorException 
	{
		JSONArray recommendations = new JSONArray();
		try {
			if (requestParams.containsKey("matched")) {
				System.out.println("MATCHED");
				JSONObject matched = new JSONObject(requestParams.get("matched"));
				JSONObject rec = new JSONObject();
				rec.put("pid", "a2test1");
				rec.put("title", "Test Recommended Painting");
				rec.put("artist", "Alex Michael");
				recommendations.put(rec);
			} else {
				System.out.println("NOT MATCHED");
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

}
