#!/usr/bin/env python

import os
from tornado import web
from org.iproj.handler import base

class StorageHandler(base.BaseHandler):
    
    def base_get(self, filename):
        self.set_header("Content-Type", "image/png")
        path = os.path.join("..","..","..","..","storage","img",filename[0],filename[1],"")
        file = open(path + filename, "rb")
        try:
            self.write(file.read())
        finally:
            file.close()
        
    def base_post(self):
        """ Not implemented. """
        pass