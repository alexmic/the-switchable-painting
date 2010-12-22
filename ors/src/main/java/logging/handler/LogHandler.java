package logging.handler;

public interface LogHandler {

	public void write(String msg, int callLevel);
	
	public void close();
	
	public int getLevel();
	
	public void setLevel(int level);
	
}
