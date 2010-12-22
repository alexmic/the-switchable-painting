package http.core.handler;

import java.util.Map;

public interface Handler {

	public String get(Map<String, String> requestParams);

	public String post(Map<String, String> requestParams);
	
	public String put(Map<String, String> requestParams);
	
	public String delete(Map<String, String> requestParams);
	
}
