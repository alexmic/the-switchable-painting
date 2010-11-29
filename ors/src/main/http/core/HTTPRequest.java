package http.core;

import java.util.Map;

/**
 * An HTTP Request object. Contains only what's necessary to perform our functions,
 * which are the method, the URI, the protocol and the params.
 * 
 * @author Alex Michael.
 *
 */
public class HTTPRequest 
{
	private String method = null;
	private String uri = null;
	private String protocol = null;
	private Map<String, String> params = null;
	
	public HTTPRequest setURI(String uri)
	{
		this.uri = uri;
		return this;
	}
	
	public HTTPRequest setMethod(String method)
	{
		this.method = method;
		return this;
	}
	
	public HTTPRequest setParams(Map<String, String> params)
	{
		this.params = params;
		return this;
	}
	
	public HTTPRequest setProtocol(String protocol)
	{
		this.protocol = protocol;
		return this;
	}
	
	public String getProtocol()
	{
		return this.protocol;
	}
	
	public String getMethod()
	{
		return this.method;
	}
	
	public Map<String, String> getParams()
	{
		return this.params;
	}

	public String getURI()
	{
		return this.uri;
	}
	
	public String toString()
	{
		String request =  method + " " + uri + " " + protocol + "\n";
		for (String key : params.keySet()) {
			request += key + ": " + params.get(key) + "\n";
		}
		return request;
	}
}
