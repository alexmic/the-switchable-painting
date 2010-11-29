package http.core.handler;

import http.core.HTTPRequest;
import http.exception.HTTPHandleErrorException;

/**
 * The HTTPHandler interface exposes a common interface that request handlers should
 * implement. This makes the architecture modular, and different types of requests can
 * be handled by different types of handlers.
 * 
 * @author Alex Michael.
 *
 */
public interface HTTPHandler {

	String handle(HTTPRequest request) throws HTTPHandleErrorException;
	
}
