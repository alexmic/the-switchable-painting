package http.core;

import http.core.handler.ORSHandler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import logging.Log;

/**
 * The HTTPServer accepts connections from a server socket and dispatches a thread,
 * to serve the accepted request. 
 * 
 * It uses a threadpool of 25 threads. * http://httpd.apache.org/docs/2.0/mod/mpm_common.html#threadsperchild *
 * 
 * @author Alex Michael
 *
 */
public class HTTPServer 
{
	private Integer port;
	
	public HTTPServer(Integer port)
	{
		this.port = port;
	}
	
	/**
	 * Start the server. This method returns false immediately if there is an error in initializing the server
	 * socket, otherwise it will enter the server loop where any exceptions will be logged and then swallowed.
	 * @return boolean
	 * 				false, if the ServerSocket could not get initialized.
	 */
	public boolean start()
	{
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			Log.error("EXCEPTION: IOException occured when initializing server socket. Exception message is: " + e.getMessage());
			return false;
		} catch (Exception e) {
			Log.error("EXCEPTION: Unexpected error occured when initializing server socket. Exception message is: " + e.getMessage());
			return false;
		}
		
		//Server loop
		ExecutorService threadPool = Executors.newFixedThreadPool(25);
		Log.debug("Server started succesfully.");
		while (true) {
			Socket connectionSocket;
			try {
				connectionSocket = serverSocket.accept();
				final BufferedReader input = 
					new BufferedReader((new InputStreamReader(connectionSocket.getInputStream())));
				final DataOutputStream output = 
					new DataOutputStream(connectionSocket.getOutputStream());
				threadPool.submit(new HTTPJob(input, output).setHandler(new ORSHandler()));
			} catch (IOException e) {
				Log.error("EXCEPTION: IOException occured in server loop. Exception message is: " + e.getMessage());
			} catch (Exception e) {
				Log.error("EXCEPTION: Unexpected error occured in server loop. Exception message is: " + e.getMessage());
			}
		}
	}
}
