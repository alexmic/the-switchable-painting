package http;

import log.Log;
import http.core.HTTPServer;

/**
 * HTTP Server boot for Online Recognition Service (ORS). Default listens on port 4444.
 * @author Alex Michael
 *
 */

public class ServerMain {
	
	public static void main(String[] args)
	{
		// Default to port 4444.
		Integer port = 4444;
		int logLevel = Log.DEBUG;
		if (args != null && args.length > 0)
		{
			port = Integer.valueOf(args[0]);
			if (args.length > 1)
			{
				if (args[1].equals("debug")){
					logLevel = Log.DEBUG; 
				} else if (args[1].equals("info")){
					logLevel = Log.INFO;
				} else if (args[1].equals("warning")){
					logLevel = Log.WARNING;
				} else if (args[1].equals("error")){
					logLevel = Log.ERROR;
				}
			}
		}
		if (!Log.init(logLevel))
		{
			System.out.println("Unable to initialize logging. Server exiting.");
			Log.close();
			return;
		}
		else
		{
			Log.debug("Logs initialized succesfully.");
			Log.info("Logs initialized succesfully.");
		}
		
		// Add a shutdown hook to clean up when terminating with SIGTERM or SIGINT.
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() 
		    { 
		    	Log.info("Server terminating. Closing log streams.");
		    	Log.close();
		    	return;
		    }
		 });
		
		// Returning false immediately means that this call has failed, otherwise this call will hang since the
		// server loop will be entered.
		if (new HTTPServer(port).start() == false)
		{
			Log.error("Server not able to start. Server exiting.");
			Log.close();
			return;
		}
	}
}
