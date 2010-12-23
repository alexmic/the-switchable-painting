package http.core;

import http.core.handler.Handler;
import http.exception.HttpHandleErrorException;
import java.util.HashMap;
import java.util.Map;

/**
 * The HTTPDispatcher parses the Request URI and delegates to the 
 * appropriate handler for handling the request.
 * 
 * @author Alex Michael
 *
 */
public class HttpDispatcher {
	
	private Map<String, Handler> routes;
	
	public HttpDispatcher()
	{
		routes = new HashMap<String, Handler>();
	}
	
	public HttpDispatcher addRoute(String route, Handler handler)
	{
		routes.put(route, handler);
		return this;
	}
	
	public String route(HttpRequest request) throws HttpHandleErrorException
	{
		if (request == null)
			throw new HttpHandleErrorException("Received null HTTP request.");
		String baseURI = request.getURI().split("\\?")[0];
		if (routes.containsKey(baseURI)) {
			return _route(routes.get(baseURI), request);
		} else {
			throw new HttpHandleErrorException("Handler not recognized.");
		}
	}
	
	private String _route(Handler handler, HttpRequest request) throws HttpHandleErrorException
	{
		String method = request.getMethod();
		if (method.equals("GET")) {
			return handler.get(request.getParams());
		} else if (method.equals("POST")) {
			return handler.post(request.getParams());
		} else if (method.equals("PUT")) {
			return handler.put(request.getParams());
		} else if (method.equals("DELETE")) {
			return handler.delete(request.getParams());
		} else {
			throw new HttpHandleErrorException("Method not recognized.");
		}
	}

	
}
