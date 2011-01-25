#!/usr/bin/env python

import os
from tornado import web
from org.iproj.handler import base

class StorageHandler(base.BaseHandler):
    """ A very very simple static file server. It just serves the file (image)
        and the content type. I will probably fix this at some point, but it
        suffices for the time being."""
        
    def base_get(self, id):
        path = os.path.join("..","..","..","..","storage","img",id[0],id[1],"")
        exts = ["png", "jpg", "jpeg", "gif"]
        for e in exts:
            ext = e
            filepath = path + id + "." + e 
            if os.path.exists(filepath):
                break
        self.set_header("Content-Type", "image/" + ext)
        file = open(filepath, "rb")
        try:
            self.write(file.read())
        except:
            raise web.HTTPError(500)
        finally:
            file.close()
        
    def base_post(self):
        """ Not implemented. """
        pass