package http.core.handler;

import http.exception.HttpHandlerErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import util.json.JSONArray;
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
			matched.put("title", "Test Matched Painting");
			matched.put("artist", "Alex Michael");
			JSONArray relevant = getRelevantPaintings(matched);
			response.put("matched", matched);
			response.put("relevant", relevant);
		} catch(JSONException ex) {
			throw new HttpHandlerErrorException(ex.getMessage(), ex);
		} catch(IOException ex) {
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
	
	private JSONArray getRelevantPaintings(JSONObject matched) throws JSONException, IOException
	{
		URL serverURL = new URL("http://localhost:4445/recommend");
		HttpURLConnection conn = (HttpURLConnection) serverURL.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		out.write("matched=" + matched.toString());
		out.flush();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer response = new StringBuffer();
		String temp;
		while ((temp = in.readLine()) != null) {
			response.append(temp);
		}
		System.out.println(response.toString());
		return new JSONArray(response.toString());
	}

}
