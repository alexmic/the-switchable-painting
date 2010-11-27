package http.benchmark;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerBenchmark {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
		{
			new ServerBenchmark().run();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws MalformedURLException, IOException
	{
		URL url = new URL("http://localhost:4444");
		long start  	   = System.currentTimeMillis();
		long finish 	   = start;
		int total_requests = 0;
		int requests       = 0;
		int runs           = 20;
		
		for (int i = 0; i < runs; i++){			
			while(finish - start < 1000)
			{
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("PUT");
				conn.setDoOutput(true);
				OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write("q=" + finish);
				writer.flush();
				conn.getContent();
				finish = System.currentTimeMillis();
				requests++;
			}
			System.out.println();
			System.out.println("RUN: " + (i + 1));
			System.out.println("Requests per second:" + requests);
			total_requests += requests;
			requests = 0;
			start = finish;
		}
		System.out.println();
		System.out.println("Average requests per second: " + total_requests / runs);
	}

}
