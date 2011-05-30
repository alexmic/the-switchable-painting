package http.core.handler;
import java.util.Map;

import com.google.code.morphia.Datastore;

import http.exception.HttpHandlerErrorException;


public class BenchmarkHandler implements Handler {

	private Datastore ds;
	
	public BenchmarkHandler(Datastore ds)
	{
		this.ds = ds;
	}
	
	@Override
	public String get(Map<String, String> requestParams)
			throws HttpHandlerErrorException {
		// TODO Auto-generated method stub
		return "Ping";
	}

	@Override
	public String post(Map<String, String> requestParams)
			throws HttpHandlerErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String put(Map<String, String> requestParams)
			throws HttpHandlerErrorException {
		return "Ping";
	}

	@Override
	public String delete(Map<String, String> requestParams)
			throws HttpHandlerErrorException {
		// TODO Auto-generated method stub
		return null;
	}

}
