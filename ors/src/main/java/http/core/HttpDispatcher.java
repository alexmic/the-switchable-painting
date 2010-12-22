package http.core;

import http.core.handler.Handler;
import http.core.handler.ors.CollectionHandler;
import http.core.handler.ors.MatchHandler;
import http.core.handler.ors.PaintingHandler;
import http.exception.HTTPHandleErrorException;

/**
 * The HTTPDispatcher parses the Request URI and delegates to the 
 * appropriate handler for handling the request.
 * 
 * @author Alex Michael
 *
 */
public class HttpDispatcher {
	
	public String route(HttpRequest request) throws HTTPHandleErrorException
	{
		if (request == null)
			throw new HTTPHandleErrorException("Received null HTTP request.");
		String[] tokens = request.getURI().split("/");
		if (tokens.length != 2) 
			throw new HTTPHandleErrorException("URI format received is wrong. URIs should be of the format /<handler>.");
		/* If a need to use reflection arises i.e if the 
		 * exposed interface becomes big enough (more that the
		 * 2-3 methods it exposes right now), then the following code
		 * will be replaced. For now it does it's job.
		 */
		String handler = tokens[1];
		if (handler.equals("painting")) {
			return routeMethod(new PaintingHandler(), request);
		} else if (handler.equals("match")) {
			return routeMethod(new MatchHandler(), request);
		} else if (handler.equals("collection")) {
			return routeMethod(new CollectionHandler(), request);
		} else {
			throw new HTTPHandleErrorException("Handler not recognized.");
		}
	}
	
	private String routeMethod(Handler handler, HttpRequest request) throws HTTPHandleErrorException
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
			throw new HTTPHandleErrorException("Method not recognized.");
		}
	}

	
}
