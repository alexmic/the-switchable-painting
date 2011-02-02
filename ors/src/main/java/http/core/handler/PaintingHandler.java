package http.core.handler;


import java.util.Map;

import com.google.code.morphia.Datastore;

public class PaintingHandler implements Handler {

	private Datastore ds = null;
	
	public PaintingHandler(Datastore ds)
	{
		this.ds = ds;
	}
	
	@Override
	public String get(Map<String, String> requestParams) {
		return "GET handled.";
	}

	@Override
	public String post(Map<String, String> requestParams) {
		return "POST handled.";
	}

	@Override
	public String put(Map<String, String> requestParams) {
		return "PUT handled.";
	}

	@Override
	public String delete(Map<String, String> requestParams) {
		return "DELETE handled.";
	}

}
