package http.core;

import http.exception.HttpParseErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import logging.Log;

/**
 * Stripped down interpretation of the HTTP protocol. Since the server exposes
 * a REST interface the only supported methods are GET, POST, PUT, DELETE. 
 * Also, the only things actually recovered from a Request is the URI, the method and the params. 
 * We don't really need anything else now..
 * 
 * @author Alex Michael.
 *
 */
public class HttpParser 
{
	private InputStream input;
	private final String CRLF= "\r\n";
	
	public HttpParser(InputStream input) throws HttpParseErrorException
	{
		if (input == null) throw new HttpParseErrorException("Inputstream was null");
		this.input = input;
	}
	
	public HttpRequest getHttpRequest() throws IOException, HttpParseErrorException
	{
		return _parse(this.input);
	}
	
	/**
	 * The parsing method.
	 * 
	 * @param input The input stream from the client.
	 * @throws IOException, HTTPParseErrorException
	 * @return HTTPRequest A populated request object.
	 */
	private HttpRequest _parse(InputStream input) throws IOException, HttpParseErrorException
	{
		HttpRequest request = new HttpRequest();
		StringBuffer requestBuffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		
		String line;
		int contentLength = 0;
		while ((line = reader.readLine()) != null) {
			requestBuffer.append(line + CRLF);
			if (line.contains("Content-Length")) {
				contentLength = Integer.valueOf(line.split("\\s" )[1]);
			}
			if (line.equals("")) break;
		}
		
		char[] body = new char[contentLength];
		reader.read(body);
		requestBuffer.append(body);

		int currentIndex = requestBuffer.indexOf(CRLF);
		if (currentIndex < 0)
			throw new HttpParseErrorException("Error in HTTP Request-line format.");
		String requestLine = requestBuffer.substring(0, currentIndex);
		String[] requestLineTokens = requestLine.split("\\s");
		Log.debug("Parsing HTTP request. Request line is: " + requestLine);
		
		if (requestLineTokens.length == 3
				&& requestLineTokens[2].toUpperCase().contains("HTTP")) {
			String method = requestLineTokens[0].toUpperCase();
			String uri    = requestLineTokens[1];
			String protocol = requestLineTokens[2];
			request.setMethod(method);
			request.setURI(uri);
			request.setProtocol(protocol);
			StringBuffer paramBuffer = new StringBuffer();
			
			if (method.equals("GET") || method.equals("POST") || method.equals("PUT") || method.equals("DELETE")) {
				// Get the parameters. Depending on the type of the request we have to extract the
				// parameters in different ways.
				if ((method.equals("GET") || method.equals("DELETE"))) {
					Log.debug("Parsing GET or DELETE request.");
					String[] tokens = uri.split("\\?");
					if (tokens.length == 2)
						paramBuffer.append(tokens[1]);
				} else {
					Log.debug("Parsing POST or PUT request.");
					String temp;
					int nextIndex = -1;
					while (true){
						currentIndex +=2;
						nextIndex = requestBuffer.indexOf(CRLF, currentIndex); 
						temp = requestBuffer.substring(
								currentIndex, 
								nextIndex);
						currentIndex = nextIndex;
						if (temp.equals("")) {
							break;
						}
					}
					paramBuffer.append(requestBuffer.substring(currentIndex+2));
				}
				
				HashMap<String, String> params = new HashMap<String, String>();
				if (paramBuffer.length() > 0) {
					String[] tokens = paramBuffer.toString().split("&");
					for (String t : tokens){
						String[] subTokens = t.split("=");
						if (subTokens.length == 2) {
							params.put(subTokens[0], subTokens[1]);
						} else {
							throw new HttpParseErrorException("Error in HTTP request parameters format.");
						}
					}
				}
				request.setParams(params);
				return request;
			} else {
				throw new HttpParseErrorException("HTTP method not recognized.");
			}
		} else {
			throw new HttpParseErrorException("Error in HTTP Request-line format.");
		}
	}
}
