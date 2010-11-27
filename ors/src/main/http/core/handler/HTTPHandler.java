package http.core.handler;

import http.core.HTTPRequest;
import http.exception.HTTPHandleErrorException;

public interface HTTPHandler {

	String handle(HTTPRequest request) throws HTTPHandleErrorException;
	
}
