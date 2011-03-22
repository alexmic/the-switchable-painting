package http.exception;

public class HttpParseErrorException extends ChainedException{

	private static final long serialVersionUID = 1L;

	public HttpParseErrorException(String message){
		super(message);
	}
	
}
