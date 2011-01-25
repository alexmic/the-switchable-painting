package http;

import static org.junit.Assert.*;

import java.util.Map;

import http.core.HttpDispatcher;
import http.core.HttpRequest;
import http.core.handler.Handler;
import http.exception.HttpHandlerErrorException;
import http.exception.HttpRouteErrorException;

import org.junit.Before;
import org.junit.Test;

public class HttpDispatcherTest {

	private HttpDispatcher dispatcher;

	class NiceRouteHandler implements Handler{

		@Override
		public String get(Map<String, String> requestParams) {
			return "niceget";
		}

		@Override
		public String post(Map<String, String> requestParams) {
			return "nicepost";
		}

		@Override
		public String put(Map<String, String> requestParams) {
			return "niceput";
		}

		@Override
		public String delete(Map<String, String> requestParams) {
			return "nicedelete";
		}
		
	}
	
	class BadRouteHandler implements Handler{

		@Override
		public String get(Map<String, String> requestParams) {
			return "badget";
		}

		@Override
		public String post(Map<String, String> requestParams) {
			return "badpost";
		}

		@Override
		public String put(Map<String, String> requestParams) {
			return "badput";
		}

		@Override
		public String delete(Map<String, String> requestParams) {
			return "baddelete";
		}
		
	}
	
	@Before
	public void setUp()
	{
		dispatcher = new HttpDispatcher();
		dispatcher.addRoute("/niceRoute", new NiceRouteHandler())
				  .addRoute("/badRoute", new BadRouteHandler());
	}
	
	@Test
	public void testExistingGETHandlerDispatch() throws HttpRouteErrorException, HttpHandlerErrorException
	{
		assertEquals(dispatcher.route(
						new HttpRequest()
							.setMethod("GET")
							.setURI("/niceRoute?p=1")
						),
						"niceget");
		assertEquals(dispatcher.route(
						new HttpRequest()
							.setMethod("GET")
							.setURI("/badRoute?p=1")
						),
						"badget");	
	}
	
	@Test
	public void testExistingPOSTHandlerDispatch() throws HttpRouteErrorException, HttpHandlerErrorException
	{
		assertEquals(dispatcher.route(
				new HttpRequest()
					.setMethod("POST")
					.setURI("/niceRoute")
				),
				"nicepost");
		assertEquals(dispatcher.route(
				new HttpRequest()
					.setMethod("POST")
					.setURI("/badRoute")
				),
				"badpost");	
	}
	
	@Test(expected=HttpRouteErrorException.class)
	public void testNotExistingGETHandlerDispatch() throws HttpRouteErrorException, HttpHandlerErrorException
	{
		dispatcher.route(
				new HttpRequest()
					.setMethod("GET")
					.setURI("/quiteNiceRoute?p=1")
				);
	}
	
	@Test(expected=HttpRouteErrorException.class)
	public void testNotExistingPOSTHandlerDispatch() throws HttpRouteErrorException, HttpHandlerErrorException
	{
		dispatcher.route(
				new HttpRequest()
					.setMethod("POST")
					.setURI("/quiteNiceRoute")
				);
	}
	
	@Test(expected=HttpRouteErrorException.class)
	public void testNullRequest() throws HttpRouteErrorException, HttpHandlerErrorException
	{
		dispatcher.route(null);
	}
	
	@Test
	public void testExistingMethodDispatch() throws HttpRouteErrorException, HttpHandlerErrorException
	{
		assertEquals(dispatcher.route(
				new HttpRequest()
					.setMethod("GET")
					.setURI("/niceRoute?p=1")
				),
				"niceget");
		assertEquals(dispatcher.route(
				new HttpRequest()
					.setMethod("PUT")
					.setURI("/niceRoute")
				),
				"niceput");
		assertEquals(dispatcher.route(
				new HttpRequest()
					.setMethod("POST")
					.setURI("/niceRoute")
				),
				"nicepost");
		assertEquals(dispatcher.route(
				new HttpRequest()
					.setMethod("DELETE")
					.setURI("/niceRoute?p=1")
				),
				"nicedelete");
	}
	
	@Test(expected=HttpRouteErrorException.class)
	public void testNotExistingMethodDispatch() throws HttpRouteErrorException, HttpHandlerErrorException
	{
		dispatcher.route(
				new HttpRequest()
					.setMethod("GETTING")
					.setURI("/niceRoute?p=1")
				);
	}
	
}
