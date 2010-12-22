package http.core;

import http.exception.HTTPHandleErrorException;
import http.exception.HTTPParseErrorException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import logging.Log;

/**
 * New requests are served in HTTPJobs which are submitted into the threadpool. 
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
	private InputStream input = null;
	private OutputStream output = null;
	private Socket socket = null;
	private HttpDispatcher dispatcher = null;
	private final String CRLF = "\r\n";
	
	public HttpJob(final Socket connectionSocket) throws IOException 
	{
		this.socket = connectionSocket;
		this.input  = connectionSocket.getInputStream();
		this.output = connectionSocket.getOutputStream();
		dispatcher  = new HttpDispatcher();
	}
	
	@Override
	public void run() 
	{
		try {
			HttpRequest request = new HttpParser(input).getHttpRequest();
			String response = dispatcher.route(request);
			Log.debug("Request parsed and handled OK. Setting 200 header and response body.");
			String header = getHTTPHeader(200, response);
			byte[] headerBytes = header.getBytes();
			byte[] responseBytes = response.getBytes();
 			for (int i = 0; i < headerBytes.length; i++) {
 				output.write(headerBytes[i]); 				
 			}
 			for (int i = 0; i < headerBytes.length; i++) {
 				output.write(responseBytes[i]);
 			}
			socket.close();
		} catch (IOException e) {
			Log.error("EXCEPTION: IOException occured when handling HTTP request. Exception message is: " + e.getMessage());
			sendError("IOException occured when handling HTTP request.", 500, output);
		} catch (HTTPParseErrorException e) {
			Log.warning("REQUEST ERROR: " + e.getMessage());
			sendError(e.getMessage(), 400, output);
		} catch (HTTPHandleErrorException e) {
			Log.warning("REQUEST ERROR: " + e.getMessage());
			sendError(e.getMessage(), 400, output);
		} catch (Exception e) {
			Log.error("EXCEPTION: Unexpected exception occured when handling HTTP request. Exception message is: " + e.getMessage());
			sendError("Unexpected exception occured when handling HTTP request.", 500, output);
		}
	}
	
	/**
	 * Handle errors while parsing requests. Errors do not mean exceptions. Writes the header and closes 
	 * the output stream.
	 * @param msg
	 * @param retCode
	 * @param out
	 */
	private void sendError(String msg, int retCode, OutputStream out)
	{
		try {
			byte[] headerBytes = getHTTPHeader(retCode, msg).getBytes();
			for (int i = 0; i < headerBytes.length; i++) {
 				output.write(headerBytes[i]); 				
 			}
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
	private String getHTTPHeader(int retCode, String response)
	{
		String header = "HTTP/1.1 ";
		switch (retCode){
			case 500:{
				header += "500 Internal Server Error" + CRLF;
				break;
			}
			case 501:{
				header += "501 Not Implemented" + CRLF;
				break;
			}
			case 400:{
				header += "400 Bad Request" + CRLF;
				break;
			}
			default:{
				header += "200 OK" + CRLF;
			}
		}
		header += "Connection: close" + CRLF;
		header += "Content-Length: " + response.length() + CRLF;
		header += "Server: ORSService on localhost" + CRLF;
		header += "Content-Type: application/json" + CRLF;
		header += CRLF;
		return response;
	}
}
