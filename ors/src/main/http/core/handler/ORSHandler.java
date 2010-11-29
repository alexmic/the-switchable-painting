package http.core.handler;

import http.core.HTTPRequest;
import http.exception.HTTPHandleErrorException;

public class ORSHandler implements HTTPHandler {

	@Override
	public String handle(HTTPRequest request) throws HTTPHandleErrorException {
		System.out.println(request.toString());
		return request.toString();
	}

}
