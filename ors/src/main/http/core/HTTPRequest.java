package http.core;

import java.util.Map;

public class HTTPRequest {

	private String method = null;
	private String uri = null;
	private Map<String, String> params = null;
	
	public HTTPRequest(){
	}
	
	public HTTPRequest setURI(String uri){
		this.uri = uri;
		return this;
	}
	
	public HTTPRequest setMethod(String method){
		this.method = method;
		return this;
	}
	
	public HTTPRequest setParams(Map<String, String> params){
		this.params = params;
		return this;
	}
	
	public String getMethod(){
		return this.method;
	}
	
	public Map<String, String> getParams(){
		return this.params;
	}

	public String getURI(){
		return this.uri;
	}
}
