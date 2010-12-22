package logging.handler.base;


public class ConsoleLogHandler extends BaseLogHandler{

	@Override
	public void write(String msg, int callLevel) {
		if (level <= callLevel) {	
			System.out.println("Console Log: " + msg);
		}
	}

	@Override
	public void close() {
		// empty
	}

}
