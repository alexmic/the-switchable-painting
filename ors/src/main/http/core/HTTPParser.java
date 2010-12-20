package http.core;

import http.exception.HTTPParseErrorException;

import java.io.BufferedReader;
import java.io.IOException;
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
public class HTTPParser 
{
	private BufferedReader input;
	
	public HTTPParser(BufferedReader input)
	{
		this.input = input;
	}
	
	public HTTPRequest getHTTPRequest() throws IOException, HTTPParseErrorException
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
	private HTTPRequest _parse(BufferedReader input) throws IOException, HTTPParseErrorException
	{
		HTTPRequest request = new HTTPRequest();
		String requestLine = input.readLine();
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
					int contentLength = 0;
					String temp;
					while (input.ready()) {
						temp = input.readLine();
						System.out.println(temp);
						if (temp.contains("Content-Length")) {
							contentLength = Integer.valueOf(temp.split(":")[1].trim());
						}
					}
					input.readLine();
					int i = 0;
					System.out.println(contentLength);
					while (i < contentLength) {
						paramBuffer.append((char) input.read());
						i++;
					}
				}
				
				HashMap<String, String> params = new HashMap<String, String>();
				if (paramBuffer.length() > 0) {
					String[] tokens = paramBuffer.toString().split("&");
					for (String t : tokens){
						String[] subTokens = t.split("=");
						if (subTokens.length == 2) {
							params.put(subTokens[0], subTokens[1]);
						} else {
							throw new HTTPParseErrorException("Error in HTTP request parameters format.");
						}
					}
				}
				request.setParams(params);
				return request;
			} else {
				throw new HTTPParseErrorException("HTTP method not recognized.");
			}
		} else {
			throw new HTTPParseErrorException("Error in HTTP Request-line format.");
		}
	}
}
