package logging.handler.base;

import logging.Log;


public class ConsoleLogHandler extends BaseLogHandler{

	@Override
	public void write(String msg, int callLevel) {
		if (callLevel == Log.ERROR)	
			System.err.println("Console Log: " + msg);
		else
			System.out.println("Console Log: " + msg);
	}

	@Override
	public void close() {
		// empty
	}

}
