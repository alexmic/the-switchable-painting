package http.core;

import http.core.handler.Handler;
import http.exception.HttpHandlerErrorException;
import http.exception.HttpRouteErrorException;
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
	
	public String route(HttpRequest request) throws HttpRouteErrorException, HttpHandlerErrorException
	{
		if (request == null)
			throw new HttpRouteErrorException("Received null HTTP request.");
		String baseURI = request.getURI().split("\\?")[0];
		if (routes.containsKey(baseURI)) {
			return _route(routes.get(baseURI), request);
		} else {
			throw new HttpRouteErrorException("Handler not recognized.");
		}
	}
	
	private String _route(Handler handler, HttpRequest request) throws HttpRouteErrorException, HttpHandlerErrorException
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
			throw new HttpRouteErrorException("Method not recognized.");
		}
	}

	
}
