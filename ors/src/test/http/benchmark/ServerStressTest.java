package http.benchmark;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerStressTest {

	public static void main(String[] args) 
	{
		ServerStressTest test = new ServerStressTest();
		
		// Test 50 concurrent users issuing requests for 20 seconds each.
		//test.concurrentUsers();
		
		// Test how many exceptions = lost requests occur in 30 seconds.
		test.efficiency();
		
	}
	
	
	private void concurrentUsers() 
	{
		int users = 50;
		for (int i = 0; i < users; i++)
		{
			new Thread(new User()).start();
		}
	}
	
	private void efficiency()
	{
		URL url = null;
		try {
			url = new URL("http://localhost:4444?q=bravo");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		long start  	   = System.currentTimeMillis();
		long finish 	   = start;
		int good_requests  = 0;
		int bad_requests   = 0;
		int response_code = -1;
		
		while(finish - start < 30000)
		{
			try {
				response_code = ((HttpURLConnection) url.openConnection()).getResponseCode();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finish = System.currentTimeMillis();
			if (response_code == 200) good_requests++;
			else bad_requests++;
		}
		System.out.println("Good requests: " + good_requests);
		System.out.println("Bad requests: " + bad_requests);
		System.out.println("Efficiency: " + ((good_requests / (good_requests + bad_requests)) * 100) + "%");		
	}


	class User implements Runnable{

		@Override
		public void run() {
			URL url = null;
			try {
				url = new URL("http://localhost:4444");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long start  	   = System.currentTimeMillis();
			long finish 	   = start;
			int requests       = 0;
			
			while(finish - start < 20000)
			{
				try {
					((HttpURLConnection) url.openConnection()).getContent();
				} catch (IOException e) {
					e.printStackTrace();
				}
				finish = System.currentTimeMillis();
				requests++;
			}
			start = finish;
			System.out.println("User done. Total requests: " + requests);
		}
		
	}
}
