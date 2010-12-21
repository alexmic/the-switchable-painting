#!/usr/bin/env python

import tornado.web
import os
import base64
from tornado.escape import json_decode

class BaseHandler(tornado.web.RequestHandler):
        
    @property
    def log(self):
        return self.application.log(self.__class__.__name__)
    
    def get(self, *args, **kwargs):
        """ Handles GET requests """
        cback = self.base_get
        self.__is_auth(cback, *args, **kwargs)
    
    def post(self, *args, **kwargs):
        """ Handles POST requests """
        cback = self.base_post
        self.__is_auth(cback, *args, **kwargs)

    # GET/POST methods for the subclasses to override.
    def base_get(self):
        pass
    
    def base_post(self):
        pass
    
    def __is_auth(self, callback, *args, **kwargs):
        """ Checks if the user is authenticated and then calls the passed callback. """
        def is_auth():
            # For the switchable painting prototype, we don't need any authentication.
            return True
            
        if is_auth():
            callback(*args, **kwargs)
        else:
            self.write("Unauthorized.")
    
    
    

    