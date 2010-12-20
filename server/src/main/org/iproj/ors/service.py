#!/usr/bin/env python
import time
import tornado.httpclient
import tornado.httputil

class ORSService():
    
    def __init__(self):
        self.base_uri = "http://localhost:4444/api/"
    
    def painting_put(self, id, cback):
        self.__do(self.__get_request(uri="painting", method="PUT", params={"id" : id}), cback)
    
    def painting_get(self, id, cback):
        self.__do(self.__get_request(uri="painting", method="GET", params={"id" : id}), cback)
    
    def painting_delete(self, id, cback):
        self.__do(self.__get_request(uri="painting", method="DELETE", params={"id" : id}), cback)
        
    def __do(self, http_request, cback):
        http = tornado.httpclient.AsyncHTTPClient()
        http.fetch(http_request, callback=cback)
    
    def __get_request(self, uri=None, method=None, params=None):
        # Separate between GET/DELETE and POST/PUT
        method = method or "GET"
        uri = uri or ""
        params = params or {}
        body = self.__get_request_body(params)
        headers = self.__get_request_headers(body)
        return tornado.httpclient.HTTPRequest(
                           self.base_uri + uri,
                           method=method,
                           body=body)
    
    def __get_http_version(self):
        return "HTTP/1.1"
    
    def __get_request_body(self, params):
        body = ""
        for key,value in params.iteritems():
            body += str(key) + "=" + str(value) + "&"
        return body[:-1]
    
    def __get_request_headers(self, body):
        l = len(body)
        header_dict = {
            "content-length" : l
        }
        return tornado.httputil.HTTPHeaders(header_dict)