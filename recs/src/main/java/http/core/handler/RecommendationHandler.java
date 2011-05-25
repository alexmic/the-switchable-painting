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
		return null;
	}

	@Override
	public String post(Map<String, String> requestParams) throws HttpHandlerErrorException 
	{
		System.out.println("ASFASASGA");
		JSONArray recommendations = new JSONArray();
		try {
			if (requestParams.containsKey("matched")) {
				System.out.println("MATCHED");
				JSONObject matched = new JSONObject(requestParams.get("matched"));
				JSONObject rec = new JSONObject();
				rec.put("pid", "a2test");
				rec.put("title", "Test Recommended Painting 1");
				rec.put("artist", "Alex Michael");
				JSONObject rec2 = new JSONObject();
				rec2.put("pid", "a2test1");
				rec2.put("title", "Test Recommended Painting 2");
				rec2.put("artist", "Alex Michael");
				JSONObject rec3 = new JSONObject();
				rec3.put("pid", "a2test2");
				rec3.put("title", "Test Recommended Painting 3");
				rec3.put("artist", "Alex Michael");
				recommendations.put(rec);
				recommendations.put(rec2);
				recommendations.put(rec3);
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
