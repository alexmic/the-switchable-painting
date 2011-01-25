package http.exception;

public class HttpHandlerErrorException extends Exception {

	private static final long serialVersionUID = 1L;

	public HttpHandlerErrorException(Exception e){
		super(e);
	}
	
}
