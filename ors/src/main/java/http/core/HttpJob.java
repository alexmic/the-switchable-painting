package http.core;

import http.exception.HTTPHandleErrorException;
import http.exception.HTTPParseErrorException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import logging.Log;

/**
 * New requests are servers in HTTPJobs which are submitted into the threadpool. 
 * This class is responsible for reading the input, delegating to the parser for
 * creating a request object, and then writing an HTTP response to the client. 
 * All exceptions are swallowed and the appropriate error headers and messages are
 * sent to the client.
 *  
 * @author Alex Michael.
 *
 */
public class HttpJob implements Runnable 
{
	private BufferedReader input = null;
	private DataOutputStream output = null;
	private HttpDispatcher dispatcher = null;
	
	public HttpJob(final BufferedReader input, final DataOutputStream output) 
	{
		this.input = input;
		this.output = output;
		dispatcher = new HttpDispatcher();
	}
	
	@Override
	public void run() 
	{
		try {
			HttpRequest request = new HttpParser(input).getHTTPRequest();
			String response = "";
			response = dispatcher.route(request);
			Log.debug("Request parsed and handled OK. Setting 200 header and response body.");
			String header = getHTTPHeader(200);
			output.writeBytes(header);
			output.writeBytes(response);
			output.close();
		} catch (IOException e) {
			Log.error("EXCEPTION: IOException occured when handling HTTP request. Exception message is: " + e.getMessage());
			handleError("IOException occured when handling HTTP request.", 500, output);
		} catch (HTTPParseErrorException e) {
			handleError(e.getMessage(), 400, output);
		} catch (HTTPHandleErrorException e) {
			handleError(e.getMessage(), 400, output);
		} catch (Exception e) {
			Log.error("EXCEPTION: Unexpected exception occured when handling HTTP request. Exception message is: " + e.getMessage());
			handleError("Unexpected exception occured when handling HTTP request.", 500, output);
		}
	}
	
	/**
	 * Handle errors while parsing requests. Errors do not mean exceptions. Writes the header and closes 
	 * the output stream.
	 * @param msg
	 * @param retCode
	 * @param out
	 */
	private void handleError(String msg, int retCode, DataOutputStream out)
	{
		Log.warning("REQUEST ERROR: " + msg);
		try {
			out.writeBytes(getHTTPHeader(retCode));
			out.close();
		} catch (IOException e) {
			Log.error("EXCEPTION: IOException occured while writing output in error handler. Exception message is " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Construct the HTTP response header. The server only supports application/json as content sent to the client
	 * since results are most likely going to get handled in Javascript code. 
	 * @param retCode The return code sent to the client.
	 * @return String The header.
	 */
	private String getHTTPHeader(int retCode)
	{
		String response = "HTTP/1.1 ";
		switch (retCode){
			case 500:{
				response += "500 Internal Server Error\r\n";
				break;
			}
			case 501:{
				response += "501 Not Implemented\r\n";
				break;
			}
			case 400:{
				response += "400 Bad Request\r\n";
				break;
			}
			default:{
				response += "200 OK\r\n";
			}
		}
		response += "Connection: keep-alive\r\n";
		response += "Server: ORSService on localhost\r\n";
		response += "Content-Type: application/json\r\n";
		response += "\r\n";
		return response;
	}
}
