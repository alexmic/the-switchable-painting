package logging;

public abstract class BaseLogHandler implements LogHandler {

    protected int level = Log.DEBUG;

	@Override
	public abstract void write(String msg, int callLevel);
	@Override
	public abstract void close();

	public void setLevel(int level) {
		this.level = level;
	}

	public int level() {
		return this.level;
	}

}
