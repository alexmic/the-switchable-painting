#!/usr/bin/env python
import time
import tornado.httpclient
import tornado.httputil

class ORSService:
    
    def __init__(self):
        self.base_uri = "http://localhost:4444/"
    
    def put_painting(self, id, cback):
        self.__do(self.__get_request(uri="painting", method="PUT", params={"id" : id}), cback)
    
    def get_painting(self, id, cback):
        self.__do(self.__get_request(uri="painting", method="GET", params={"id" : id}), cback)
    
    def get_associations(self, id, cback):
        self.__do(self.__get_request(uri="collection", method="GET", params={"id" : id}), cback)
    
    def add_association(self, id, new_assoc_id, cback):
        self.__do(self.__get_request(uri="collection", method="POST", params={"id" : id, "na_id" : id}), cback)
    
    def get_match(self, id, feature_vectors, cback):
        self.__do(self.__get_request(uri="match", method="POST", params={"id" : id, "fvectors" : feature_vectors}), cback)

    def __do(self, http_request, cback):
        http = tornado.httpclient.AsyncHTTPClient()
        http.fetch(http_request, callback=cback)
    
    def __get_request(self, uri=None, method=None, params=None):
        method = method or "GET"
        uri = uri or ""
        params = params or {}
        if method == "PUT" or method == "POST":
            body = self.__get_request_body(params)
        else:
            body = ""
            uri += self.__get_qs(params)
        return tornado.httpclient.HTTPRequest(
                           self.base_uri + uri,
                           method=method,
                           body=body)
    
    def __get_http_version(self):
        return "HTTP/1.1"
    
    def __get_request_body(self, params):
        if len(params) == 0:
            return ""
        return self.__format_params(params)
        
    def __format_params(self, params):
        if len(params) == 0:
            return ""
        body = ""
        for key,value in params.iteritems():
            body += str(key) + "=" + str(value) + "&"
        return body[:-1]
    
    def __get_qs(self, params):
        if len(params) == 0:
            return ""
        return "?" + self.__format_params(params)