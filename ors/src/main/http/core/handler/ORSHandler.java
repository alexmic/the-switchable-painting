package http.core.handler;

import http.core.HTTPRequest;
import http.exception.HTTPHandleErrorException;

/**
 * Request handler of the Online Recognition Service. Still a stub, supposed to perform
 * feature point detection and description, and storing those in our database. 
 * 
 * @author Alex Michael.
 *
 */
public class ORSHandler implements HTTPHandler {

	@Override
	public String handle(HTTPRequest request) throws HTTPHandleErrorException {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request.toString();
	}

}
