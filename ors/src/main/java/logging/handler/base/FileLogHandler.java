package logging.handler.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileLogHandler extends BaseLogHandler {
	
    private BufferedWriter error = null;
	
    public FileLogHandler() throws IOException
	{
		if (error == null) {
			File dir = new File("log");
			if ((dir.exists() && !dir.isDirectory()) || !dir.exists()) {
				dir.mkdir();	
			}
			File f = new File("log/error.log");
			f.createNewFile();
			error = new BufferedWriter(new FileWriter(f, true));
		}
	}
	
	@Override
	public void write(String msg, int callLevel) {
		_write(error, msg);
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
			error.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
