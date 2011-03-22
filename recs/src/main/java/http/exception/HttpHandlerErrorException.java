package http.exception;

public class HttpHandlerErrorException extends ChainedException {

	private static final long serialVersionUID = 1L;

	public HttpHandlerErrorException(String msg, Exception e){
		super(msg, e);
	}
	
}
