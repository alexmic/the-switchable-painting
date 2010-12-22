package logging.handler.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import logging.Log;

public class FileLogHandler extends BaseLogHandler {
	
	private BufferedWriter debug = null;
	private BufferedWriter info  = null;
	private BufferedWriter warning  = null;
    private BufferedWriter error = null;
	
    public FileLogHandler() throws IOException
	{
		if (debug == null) {
			File f = new File("log/debug.log");
			f.createNewFile();
			debug = new BufferedWriter(new FileWriter(f, true));
		}
		if (info == null) {
			File f = new File("log/info.log");
			f.createNewFile();
			info = new BufferedWriter(new FileWriter(f, true));
		}
		if (warning == null) {
			File f = new File("log/warning.log");
			f.createNewFile();
			warning = new BufferedWriter(new FileWriter(f, true));
		}
		if (error == null) {
			File f = new File("log/error.log");
			f.createNewFile();
			error = new BufferedWriter(new FileWriter(f, true));
		}
	}
	
	@Override
	public void write(String msg, int callLevel) {
		if (level <= callLevel) {
			switch (callLevel) {
				case Log.DEBUG:
					_write(debug, msg);
					break;
				case Log.INFO:
					_write(info, msg);
					break;
				case Log.WARNING:
					_write(warning, msg);
					break;
				case Log.ERROR:
					_write(error, msg);
					break;
				default:
					return;
			}
		}
	}
	
	private void _write(BufferedWriter writer, String msg)
	{
		try {
			writer.write(new Date().toString() + " - " + msg + "\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			debug.close();
			info.close();
			warning.close();
			error.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
