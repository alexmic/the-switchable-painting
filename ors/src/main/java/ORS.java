
import http.core.HttpDispatcher;
import http.core.HttpServer;
import http.core.handler.DescribeHandler;
import http.core.handler.MatchHandler;

import java.io.IOException;
import java.net.UnknownHostException;

import logging.Log;
import logging.handler.base.ConsoleLogHandler;
import logging.handler.base.FileLogHandler;
import model.Painting;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * HTTP Server boot for Online Recognition Service (ORS). Default listens on port 4444.
 * @author Alex Michael
 *
 */

public class ORS
{
	public static void main(String[] args)
	{
		// Default to port 4444.
		Integer port = 4444;
		int logLevel = Log.DEBUG;
		if (args != null && args.length > 0){
			port = Integer.valueOf(args[0]);
			if (args.length > 1){
				if (args[1].equals("debug")) {
					logLevel = Log.DEBUG; 
				} else if (args[1].equals("info")) {
					logLevel = Log.INFO;
				} else if (args[1].equals("warning")) {
					logLevel = Log.WARNING;
				} else if (args[1].equals("error")) {
					logLevel = Log.ERROR;
				}
			}
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
		
		try {
			FileLogHandler flh = new FileLogHandler();
			flh.setLevel(Log.ERROR);
			ConsoleLogHandler clh = new ConsoleLogHandler();
			clh.setLevel(Log.DEBUG);
			Log.addHandler(flh);
			Log.addHandler(clh);
			Log.setLevel(logLevel);
			Log.debug("Logs initialized succesfully.");
			Log.info("Logs initialized succesfully.");
		} catch (IOException ex) {
			System.out.println("IOException. Unable to initialize logging.");
			ex.printStackTrace();
			System.exit(1);
		} catch (Exception ex) {
			System.out.println("Unexpected error. Unable to initialize logging.");
			ex.printStackTrace();
			System.exit(1);
		}
		
		// Mongo + Morphia
		Morphia morphia = new Morphia();
		morphia.map(Painting.class);
		
		// Change the hardcoded DB and url to match config file (which is not existent so far.).
		Datastore ds = null;
		try {
			ds = morphia.createDatastore(new Mongo("localhost"), "dev_tsp_db");
			ds.ensureCaps();
			ds.ensureIndexes();
		} catch (UnknownHostException e) {
			Log.error("Unknown host found when creating Mongo.", e.getStackTrace());
			System.exit(1);
		} catch (MongoException e) {
			Log.error("Mongo exception encountered.", e.getStackTrace());
			System.exit(1);
		} catch (Exception e) {
			Log.error("Unexpected exception encountered.", e.getStackTrace());
			System.exit(1);
		}
		
		// Wire up the routes.
		HttpDispatcher dispatcher = new HttpDispatcher();
		dispatcher.addRoute("/describe", new DescribeHandler(ds))
		  		  .addRoute("/match", new MatchHandler(ds))
 		          .ignore("/favicon.ico");
		
		// Returning false immediately means that this call has failed, otherwise this call will hang since the
		// server loop will be entered.
		if (new HttpServer(port, dispatcher).start() == false) {
			Log.error("Server is not able to start because server loop initialisation failed. Server exiting.");
			Log.close();
			return;
		}
	}
}
