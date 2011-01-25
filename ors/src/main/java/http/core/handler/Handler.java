package http.core.handler;

import http.exception.HttpHandlerErrorException;

import java.util.Map;

public interface Handler {

	public String get(Map<String, String> requestParams) throws HttpHandlerErrorException;

	public String post(Map<String, String> requestParams) throws HttpHandlerErrorException;
	
	public String put(Map<String, String> requestParams) throws HttpHandlerErrorException;
	
	public String delete(Map<String, String> requestParams) throws HttpHandlerErrorException;
	
}
