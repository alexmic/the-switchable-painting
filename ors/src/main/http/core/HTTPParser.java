package http.core;

import http.exception.HTTPParseErrorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import log.Log;

public class HTTPParser {

	private BufferedReader input;
	
	public HTTPParser(BufferedReader input){
		this.input = input;
	}
	
	public HTTPRequest getHTTPRequest() throws IOException, HTTPParseErrorException{
		return _parse(this.input);
	}
	
	
	/**
	 * Stripped down interpretation of the HTTP protocol. Since the server exposes
	 * a REST interface the only supported methods are GET, POST, PUT, DELETE.
	 *
	 * Exceptions during handling of the request are handled here so that a 500 request error can be sent to the client.
	 * Any errors during parsing the request, errors not being exceptions i.e parsing errors, 
	 * are logged and then the appropriate header is constructed.
	 * 
	 * Supported HTTP return codes:
	 * 200 OK
	 * 500 Internal server error
	 * 501 Not implemented
	 * 400 Bad request
	 * 
	 * @param input The input stream from the client.
	 * @param output The output stream to the client.
	 * @throws IOException
	 */
	private HTTPRequest _parse(BufferedReader input) throws IOException, HTTPParseErrorException{
		HTTPRequest request = new HTTPRequest();
		String requestLine = input.readLine();
		String[] requestLineTokens = requestLine.split(" ");
		
		Log.debug("Parsing HTTP request. Request line is: " + requestLine);
		
		if (requestLineTokens.length == 3
				&& requestLineTokens[2].toUpperCase().contains("HTTP")){
			
			String method = requestLineTokens[0].toUpperCase();
			String uri    = requestLineTokens[1];
			request.setMethod(method);
			request.setURI(uri);
			StringBuffer paramBuffer = new StringBuffer();
			
			if (method.equals("GET") || method.equals("POST") || method.equals("PUT") || method.equals("DELETE")){
				// Get the parameters. Depending on the type of the request we have to extract the
				// parameters in different ways.
				if ((method.equals("GET") || method.equals("DELETE"))){
					Log.debug("Parsing GET or DELETE request.");
					String[] tokens = uri.split("\\?");
					if (tokens.length == 2)
						paramBuffer.append(tokens[1]);
				} else {
					Log.debug("Parsing POST or PUT request.");
					int contentLength = 0;
					String temp;
					while(input.ready())
					{
						temp = input.readLine();
						if (temp.contains("Content-Length"))
						{
							contentLength = Integer.valueOf(temp.split(":")[1].trim());
							break;
						}
					}
					input.readLine();
					int i = 0;
					while (i < contentLength)
					{
						paramBuffer.append((char) input.read());
						i++;
					}
				}
				
				HashMap<String, String> params = null;
				if (paramBuffer.length() > 0)
				{
					params = new HashMap<String, String>();
					String[] tokens = paramBuffer.toString().split("&");
					for (String t : tokens){
						String[] subTokens = t.split("=");
						if (subTokens.length == 2){
							params.put(subTokens[0], subTokens[1]);
						} else {
							throw new HTTPParseErrorException("Error in HTTP request parameters format.");
						}
					}
				}
				request.setParams(params);
				return request;
			} else{
				throw new HTTPParseErrorException("HTTP method not recognized.");
			}
		} else {
			throw new HTTPParseErrorException("Error in HTTP Request-line format.");
		}
	}
}

