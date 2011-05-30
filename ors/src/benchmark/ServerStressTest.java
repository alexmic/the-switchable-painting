import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerStressTest {

	private static int reqs = 0;
	private static int errors = 0;
	private static int users = 100;
	
	public static void main(String[] args) 
	{
		ServerStressTest test = new ServerStressTest();
		test.concurrentUsers();
	}
	
	public static synchronized void addRequests(int toAdd)
	{
		reqs += toAdd;
	}
	
	public static synchronized void addErrors(int toAdd)
	{
		errors += toAdd;
	}
	
	public static synchronized void decUsers()
	{
		users--;
	}
	
	public static synchronized boolean isFinished()
	{
		return users == 0;
	}
	
	private void concurrentUsers() 
	{
		int _users = users;
		for (int i = 0; i < users; i++) {
			new Thread(new User()).start();
		}
		while(!isFinished()) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Average requests per user: " + (reqs / (float) _users));
		System.out.println("Average errors per user: " + (errors / (float) _users));
	}
	
	class User implements Runnable
	{
		@Override
		public void run() {
			URL url = null;
			try {
				url = new URL("http://localhost:4444/benchmark");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			long start   = System.currentTimeMillis();
			long finish  = start;
			int requests = 0;
			int errors = 0;
			while(finish - start < 1000) {
				try {
					HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
					//httpConn.setConnectTimeout(3000);
					int responseCode = httpConn.getResponseCode();
					if (responseCode != 200) errors++;
				} catch (Exception e) {
					errors++;
				} 
				finish = System.currentTimeMillis();
				requests++;
			}
			ServerStressTest.addRequests(requests);
			ServerStressTest.addErrors(errors);
			ServerStressTest.decUsers();
			//System.out.println("User done. Requests per second: " + requests +". Errors encountered: " + errors);
		}
	}
}
