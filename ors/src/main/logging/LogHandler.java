package logging;

public interface LogHandler {

	public void write(String msg, int callLevel);
	
	public void close();
	
}
