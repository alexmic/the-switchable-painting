package http.exception;

public class HttpRouteErrorException extends ChainedException {

	private static final long serialVersionUID = 1L;

	public HttpRouteErrorException(String message){
		super(message);
	}
	
}
