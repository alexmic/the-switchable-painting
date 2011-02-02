package http.core.handler;

import java.util.Map;
import com.google.code.morphia.Datastore;

public class MatchHandler implements Handler {

	private Datastore ds = null;
	
	public MatchHandler(Datastore ds)
	{
		this.ds = ds;
	}
	
	@Override
	public String get(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String post(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String put(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
