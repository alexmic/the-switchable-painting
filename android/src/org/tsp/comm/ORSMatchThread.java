package org.tsp.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tsp.cv.descriptor.FeatureVector;
import org.tsp.model.Painting;

import android.util.Log;

public class ORSMatchThread extends Thread
{
	private final String serverHandler = "/api/match";
	
	private List<FeatureVector> featureVectors = null;
	private int strategy = 0;
	private String serverIP = null;
	private ORSMatchThreadResult result = null;
	
	public ORSMatchThread(String serverIP, List<FeatureVector> featureVectors,
						   int strategy, ORSMatchThreadResult result) 
	{
		super();
		this.strategy = strategy;
		this.featureVectors = featureVectors;
		this.result = result;
		this.serverIP = serverIP;
	}
	
	@Override
	public void run()
	{
		JSONObject payload = new JSONObject();
		JSONArray vectors = new JSONArray();
		try{
			for (FeatureVector fv : featureVectors) {
				JSONObject vector = new JSONObject();
				JSONArray descriptor = new JSONArray();
				for (float f : fv.descriptor()) {
					if (Float.isInfinite(f) || Float.isNaN(f)) {
						descriptor.put(0);
					} else {
						descriptor.put(f);
					}
				}
				vector.put("x", fv.x());
				vector.put("y", fv.y());
				vector.put("d", descriptor);
				vectors.put(vector);
			}
		payload.put("s", strategy);
		payload.put("v", vectors);
		} catch(JSONException ex) {
			onError();
			ex.printStackTrace();
			return;
		}
		
		try {
			JSONObject response = new JSONObject(sendPOST(serverIP + serverHandler, payload));
			if (!response.isNull("matched")) {
				JSONObject matched = response.getJSONObject("matched");
				if (matched.has("pid") && matched.has("title") && matched.has("artist")) {
					result.setMatchedPainting(new Painting(matched.getString("pid"), matched.getString("title"), matched.getString("artist")));
				}
			}
			if (!response.isNull("relevant")) {
				JSONArray relevant = response.getJSONArray("relevant");
				List<Painting> relevantPaintings = new ArrayList<Painting>();
				for (int i = 0; i < relevant.length(); ++i) {
					JSONObject painting = relevant.getJSONObject(i);
					relevantPaintings.add(new Painting(painting.getString("pid"), painting.getString("title"), painting.getString("artist")));
				}
				result.setRelevantPaintings(relevantPaintings);
			}
			result.setSuccess(true);
			result.setFinished(true);
		} catch (IOException e) {
			onError();
			e.printStackTrace();
			return;
		} catch (JSONException e) {
			onError();
			e.printStackTrace();
			return;
		}
	}
	
	private String sendPOST(String serverAddress, JSONObject payload) throws MalformedURLException, IOException
	{
		URL serverURL = new URL("http://"+serverAddress);
		HttpURLConnection conn = (HttpURLConnection) serverURL.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		out.write("payload=" + payload.toString());
		out.flush();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer response = new StringBuffer();
		String temp;
		while ((temp = in.readLine()) != null) {
			response.append(temp);
		}
		return response.toString();
	}
	
	private void onError()
	{
		result.setSuccess(false);
		result.setFinished(true);
	}
	
}
