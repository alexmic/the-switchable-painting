package logging;

import java.util.ArrayList;

import logging.handler.LogHandler;

/**
 * Convenient logging class. Inspired from the Python logging module.
 * The logger exposes four methods for logging messages, each of which
 * represents a log level (debug, info, warning, error). When such a method is called, 
 * the logger checks its severity against the logger level and if it is of equal or higher
 * severity the message is allowed to go through to the handlers.
 * 
 * The logger maintains a collection of handlers, each handler being responsible of
 * logging the filtered messages. This allows for a flexible configuration i.e having
 * a DB handler, a File handler or an Email handler. Each handler has its own logging
 * level so even if a message goes through the logger check, it will then be checked again in
 * the handler. This is to allow different handlers to handle different logging levels.
 * 
 * @author Alex Michael.
 *
 */
public class Log 
{
	public final static int DEBUG = 0;
	public final static int INFO = 10;
	public final static int WARNING = 20;
	public final static int ERROR = 30;
    private static int LOG_LEVEL = DEBUG;
    private static ArrayList<LogHandler> handlers = new ArrayList<LogHandler>();
    	
	public static void setLevel(int level)
	{
		LOG_LEVEL = level;
	}
	
	public static void addHandler(LogHandler handler)
	{
		handlers.add(handler);
	}
	
	public static void debug(String msg)
	{
		_filter(DEBUG, msg);
	}
	
	public static void info(String msg)
	{
		_filter(INFO, msg);
	}
	
	public static void error(String msg)
	{
		_filter(ERROR, msg);
	}
	
	public static void warning(String msg)
	{
		_filter(WARNING, msg);
	}
	
	private static void _filter(int callLevel, String msg)
	{
		if (LOG_LEVEL <= callLevel) {
			for (LogHandler handler: handlers) {
				if (handler.getLevel() <= callLevel)
					handler.write(msg, callLevel);
			}
		}
	}
	
	public static void close()
	{
		for (LogHandler handler: handlers) {
			handler.close();
		}
	}
}
