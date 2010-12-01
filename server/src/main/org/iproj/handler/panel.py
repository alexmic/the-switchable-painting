#!/usr/bin/env python

from org.iproj.handler import base
from org.iproj.file.uploader import Uploader, SizeLimitExceededError, WrongMimetypeError, WrongExtensionError, NullRequestError 

class MainPanelHandler(base.BaseHandler):
    
    def base_get(self):
        self.render("main.html")
    
    def base_post(self):
        pass

class UploadPanelHandler(base.BaseHandler):
    
    def base_get(self):
        self.render("upload.html")
        
    def base_post(self):
        allowed_extensions = ["png", "jpg", "gif", "bmp", "jpeg", "bm", "svg", "tiff"]
        allowed_mimetypes = ["image/png", "image/jpeg", "image/jpg",
                             "image/gif", "image/bmp", "image/x-windows-bmp",
                             "image/svg+xml", "image/tiff"]
        try:
            Uploader(self.request).size(614400).extensions(allowed_extensions).mimetypes(allowed_mimetypes).check(upload=True)
        except NullRequestError, (instance):
            self.log.debug(instance.err_msg)
        except SizeLimitExceededError, (instance):
            self.log.debug(instance.err_msg)
        except WrongMimetypeError, (instance):
            self.log.debug(instance.err_msg)
        except WrongExtensionError, (instance):
            self.log.debug(instance.err_msg)
        except:
            # Hmm not ideal. Fix it.
            import sys
            print "UNEXPECTED EXCEPTION: ", sys.exc_info()[0]
            raise