package http.core.handler;

import http.exception.HttpHandlerErrorException;

import java.util.Map;

import util.json.JSONException;
import util.json.JSONObject;

import com.google.code.morphia.Datastore;

public class MatchHandler implements Handler {

	private Datastore ds = null;
	
	public MatchHandler(Datastore ds)
	{
		this.ds = ds;
	}
	
	@Override
	public String get(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String post(Map<String, String> requestParams) throws HttpHandlerErrorException {
		
		System.out.println(requestParams.get("payload"));
		JSONObject response = new JSONObject();
		JSONObject matched = new JSONObject();
		try {
			matched.put("pid", "1");
			matched.put("title", "alal");
			matched.put("artist", "a");
			response.put("matched", matched);
			response.put("relevant", JSONObject.NULL);
		} catch(JSONException ex) {
			throw new HttpHandlerErrorException(ex.getMessage(), ex);
		}
		return response.toString();
	}

	@Override
	public String put(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
