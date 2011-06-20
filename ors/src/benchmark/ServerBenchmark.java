import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerBenchmark {

	public static void main(String[] args) {
		try {
			new ServerBenchmark().run();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws MalformedURLException, IOException
	{
		URL url = new URL("http://localhost:9000/application/benchmark");
		long start  	   = System.currentTimeMillis();
		long finish 	   = start;
		int total_requests = 0;
		int requests       = 0;
		int runs           = 20;
		
		for (int i = 0; i < runs; i++) {			
			while(finish - start < 1000) {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("PUT");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write("q=" + finish + "\r\n");
				writer.flush();
				BufferedReader is = new BufferedReader((new InputStreamReader(conn.getInputStream())));
				finish = System.currentTimeMillis();
				int ret = 0;
				while ((ret = is.read()) > 0) {;}
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
