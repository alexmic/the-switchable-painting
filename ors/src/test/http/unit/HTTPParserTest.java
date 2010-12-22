package http.unit;

import static org.junit.Assert.*;
import http.core.HttpParser;
import http.core.HttpRequest;
import http.exception.HTTPParseErrorException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class HttpParserTest {

	private HttpParser parser;
	private final String CRLF= "\r\n";
	private String constructRequest(String method, 
								    boolean okRequestLine, 
								    boolean okParameters)
	{
		method = method.toUpperCase();
		StringBuffer request = new StringBuffer();
		if (okRequestLine) {
			request.append(method);
			if ((method.equals("GET") || method.equals("DELETE")) && okParameters) {
				request.append(" /test?p1=2&p2=3 HTTP/1.1" + CRLF);
			} else if ((method.equals("GET") || method.equals("DELETE")) && !okParameters) {
				request.append(" /test?p1=2&p23 HTTP/1.1" + CRLF);
			} else {
				request.append(" /test HTTP/1.1" + CRLF);
			}
		} else {
			request.append(method);
			request.append("BADNOSPACEfffjf HTTP/2222" + CRLF);
		}
		
		if (method.equals("POST") || method.equals("PUT")) {
			request.append("Content-Length: 9" + CRLF);
			request.append(CRLF);
			if (okParameters)
				request.append("p1=2&p2=3");
			else
				request.append("p1=2&p23");
		}
		return request.toString();
	}
	
	@Test
	public void testOKGETRequest() throws IOException, HTTPParseErrorException
	{
		InputStream in = new ByteArrayInputStream(constructRequest("GET", true, true).getBytes("UTF-8"));		
		parser = new HttpParser(in);
		HttpRequest request = parser.getHttpRequest();
		assertEquals(request.getMethod(), "GET");
	}
	
	@Test
	public void testOKPUTRequest() throws IOException, HTTPParseErrorException
	{
		InputStream in = new ByteArrayInputStream(constructRequest("PUT", true, true).getBytes("UTF-8"));		
		parser = new HttpParser(in);
		HttpRequest request = parser.getHttpRequest();
		assertEquals(request.getMethod(), "PUT");
	}
	
	@Test
	public void testOKPOSTRequest() throws IOException, HTTPParseErrorException
	{
		InputStream in = new ByteArrayInputStream(constructRequest("POST", true, true).getBytes("UTF-8"));		
		parser = new HttpParser(in);
		HttpRequest request = parser.getHttpRequest();
		assertEquals(request.getMethod(), "POST");
	}
	
	@Test
	public void testOKDELETERequest() throws IOException, HTTPParseErrorException
	{
		InputStream in = new ByteArrayInputStream(constructRequest("DELETE", true, true).getBytes("UTF-8"));		
		parser = new HttpParser(in);
		HttpRequest request = parser.getHttpRequest();
		assertEquals(request.getMethod(), "DELETE");
	}
	
	@Test
	public void testOKGETParameterParse() throws IOException, HTTPParseErrorException
	{
		InputStream in = new ByteArrayInputStream(constructRequest("GET", true, true).getBytes("UTF-8"));		
		parser = new HttpParser(in);
		HttpRequest request = parser.getHttpRequest();
		assertEquals(request.getMethod(), "GET");
		assertEquals(request.getParams().get("p1"), "2");
		assertEquals(request.getParams().get("p2"), "3");
	}
	
	@Test
	public void testOKPOSTParameterParse() throws IOException, HTTPParseErrorException
	{
		InputStream in = new ByteArrayInputStream(constructRequest("POST", true, true).getBytes("UTF-8"));		
		parser = new HttpParser(in);
		HttpRequest request = parser.getHttpRequest();
		assertEquals(request.getMethod(), "POST");
		assertEquals(request.getParams().get("p1"), "2");
		assertEquals(request.getParams().get("p2"), "3");
	}
	
	@Test(expected=HTTPParseErrorException.class)
	public void testBadHTTPMethod() throws IOException, HTTPParseErrorException
	{
		InputStream in = new ByteArrayInputStream(constructRequest("POSTING", true, true).getBytes("UTF-8"));		
		new HttpParser(in).getHttpRequest();
	}
	
	@Test(expected=HTTPParseErrorException.class)
	public void testBadHTTPRequestLine() throws IOException, HTTPParseErrorException
	{
		InputStream in = new ByteArrayInputStream(constructRequest("POST", false, true).getBytes("UTF-8"));		
		new HttpParser(in).getHttpRequest();
	}
	
	@Test(expected=HTTPParseErrorException.class)
	public void testBadHTTPParameters() throws IOException, HTTPParseErrorException
	{
		InputStream in = new ByteArrayInputStream(constructRequest("POST", true, false).getBytes("UTF-8"));		
		parser = new HttpParser(in);
		HttpRequest request = parser.getHttpRequest();
		assertEquals(request.getMethod(), "POST");
		assertEquals(request.getParams().get("p1"), "2");
		assertEquals(request.getParams().get("p2"), "3");
	}
}
