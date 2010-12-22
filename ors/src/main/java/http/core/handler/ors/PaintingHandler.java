package http.core.handler.ors;

import http.core.handler.Handler;

import java.util.Map;

public class PaintingHandler implements Handler {

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
