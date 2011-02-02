package http.core;

import java.io.IOException;
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
public class HttpServer 
{
	private Integer port = null;
	private HttpDispatcher dispatcher = null;
	
	public HttpServer(Integer port, HttpDispatcher dispatcher)
	{
		this.port = port;
		this.dispatcher = dispatcher;
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
			Log.error("IOException occured when initializing server socket.", e.getStackTrace());
			return false;
		} catch (Exception e) {
			Log.error("Unexpected error occured when initializing server socket.", e.getStackTrace());
			return false;
		}
		
		//Server loop
		ExecutorService threadPool = Executors.newFixedThreadPool(25);
		Log.debug("Server started succesfully.");
		while (true) {
			Socket connectionSocket;
			try {
				connectionSocket = serverSocket.accept();
				threadPool.submit(new HttpJob(connectionSocket, dispatcher));
			} catch (IOException e) {
				Log.error("IOException occured in server loop.", e.getStackTrace());
			} catch (Exception e) {
				Log.error("Unexpected error occured in server loop.", e.getStackTrace());
			}
		}
	}
}
