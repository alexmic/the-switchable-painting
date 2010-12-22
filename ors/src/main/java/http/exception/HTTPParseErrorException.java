package http.exception;

public class HTTPParseErrorException extends Exception{

	private static final long serialVersionUID = 1L;

	public HTTPParseErrorException(String message){
		super(message);
	}
	
}
