package http.core.handler;

import http.exception.HttpHandlerErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Painting;

import util.json.JSONArray;
import util.json.JSONException;
import util.json.JSONObject;

import com.google.code.morphia.Datastore;

import cv.descriptor.FeatureVector;
import cv.descriptor.FeatureVectorType;
import cv.descriptor.SiftFeatureVector;

public class MatchHandler implements Handler {

	private final float D_THRESHOLD = 0.2f;
	
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
		
		JSONObject response = new JSONObject();
		JSONObject matched = new JSONObject();
		
		try {
			if (requestParams.containsKey("payload")) {
				JSONObject payload = new JSONObject(requestParams.get("payload"));
				if (payload.has("s") && payload.has("v")) {
					int strategy = payload.getInt("s");
					JSONArray vectors = payload.getJSONArray("v");
					Painting receivedPainting = new Painting();
					receivedPainting.setFeatureVectors(toFVList(vectors));
					Painting bestMatch = getBestMatch(strategy, receivedPainting);
					JSONArray relevant = new JSONArray();
					if (bestMatch != null) {
						matched.put("pid", bestMatch.getPaintingId());
						matched.put("title", bestMatch.getTitle());
						matched.put("artist", bestMatch.getArtist());
						relevant = getRelevantPaintings(matched);
					}
					response.put("matched", matched);
					response.put("relevant", relevant);
				} else {
					throw new Exception("Request does not contain both strategy and vectors.");
				}
			} else {
				throw new Exception("Request does not contain a payload.");
			}
		} catch(Exception ex) {
			throw new HttpHandlerErrorException(ex.getMessage(), ex);
		}
		System.out.println(response.toString());
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
		return new JSONArray(response.toString());
	}

	private Painting getBestMatch(int strategy, Painting receivedPainting)
	{
		List<Painting> storedPaintings = ds.find(Painting.class, "descriptorType", strategy).asList();
		HashMap<Painting, Float> scores = new HashMap<Painting, Float>();
		for (Painting p : storedPaintings) {
			if (p.getFeatureVectors() == null) continue;
			scores.put(p, receivedPainting.getMatchScore(p, D_THRESHOLD));
		}
		float max = 0;
		Painting bestMatch = null;
		for (Painting p : scores.keySet()) {
			if (scores.get(p) > max) {
				max = scores.get(p);
				bestMatch = p;
			}
		}
		if (max > 0.2) {
			return bestMatch;
		}
		return null;
	}
	
	private List<FeatureVector> toFVList(JSONArray jsonVectors) throws JSONException
	{
		List<FeatureVector> fvList = new ArrayList<FeatureVector>();
		for (int i = 0; i < jsonVectors.length(); ++i) {
			JSONObject obj = jsonVectors.getJSONObject(i);
			JSONArray descriptor = obj.getJSONArray("d");
			float[] fDescriptor = new float[descriptor.length()];
			for (int j = 0; j < descriptor.length(); ++j) {
				fDescriptor[j] = (float) descriptor.getLong(j);
			}
			fvList.add(new SiftFeatureVector(obj.getInt("x"), obj.getInt("y"), fDescriptor)); 
		}
		return fvList;
	}
}
