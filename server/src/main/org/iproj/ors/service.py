#!/usr/bin/env python
import time
import tornado.httpclient
import tornado.httputil
import urllib

class ORSService:
    
    def __init__(self):
        self.base_uri = "http://localhost:4444/"
    
    def describe_painting(self, id, title, artist, strategy, tags, cback):
        self.__do(self.__get_request(uri="describe", method="POST",
                                     params={"id" : id, "title" : title, "artist" : artist, "s" : strategy, "tags" : tags}),
                                     cback)
    
    def sim_match(self, title, strategy, cback):
        self.__do(self.__get_request(uri="sim_match", method="GET", params={"title": title,"s": strategy}), cback)
    
    def get_match(self, payload, cback):
        self.__do(self.__get_request(uri="match", method="POST", params={"payload" : payload}), cback)

    def __do(self, http_request, cback):
        http = tornado.httpclient.AsyncHTTPClient()
        http.fetch(http_request, callback=cback)
    
    def __get_request(self, uri=None, method=None, params=None):
        method = method or "GET"
        uri = uri or ""
        params = params or {}
        if method == "PUT" or method == "POST":
            body = urllib.urlencode(params)
        else:
            body = ""
            uri += "?" + urllib.urlencode(params)
        return tornado.httpclient.HTTPRequest(
                           self.base_uri + uri,
                           method=method,
                           body=body)
    
    def __get_http_version(self):
        return "HTTP/1.1"