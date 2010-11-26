#!/usr/bin/env python

from org.iproj.handler import base

class ApiHandler(base.BaseHandler):
    
    def base_get(self):
        self.write("Works.")
    
    def base_post(self):
        pass