#!/usr/bin/env python

from org.iproj.handler import base
from org.iproj.file.uploader import Uploader, SizeLimitExceededError, WrongMimetypeError, WrongExtensionError, NullRequestError
from org.iproj.ors.service import ORSService
from tornado.escape import json_encode
import tornado.web

class MainPanelHandler(base.BaseHandler):
    
    def base_get(self):
        self.render("main.html")
    
    def base_post(self):
        pass

class UploadPanelHandler(base.BaseHandler):
    
    def on_service_response(self, http_response):
        if not http_response.code == 200:
            # write error
            return
        print str(http_response)
        self.write(json_encode({"success": True,
                                "next_msg": "Your painting was uploaded succesfully. :-)",
                                "last": True}))
        self.flush()
        self.finish()
    
    def base_get(self):
        self.render("upload.html")
        
    @tornado.web.asynchronous
    def base_post(self):
        allowed_extensions = ["png", "jpg", "gif", "bmp", "jpeg", "bm", "svg", "tiff"]
        allowed_mimetypes = ["image/png", "image/jpeg", "image/jpg",
                             "image/gif", "image/bmp", "image/x-windows-bmp",
                             "image/svg+xml", "image/tiff"]
        try:
            Uploader(self.request).size(614400).extensions(allowed_extensions).mimetypes(allowed_mimetypes).check(upload=True)
            self.write(json_encode({"success": True,
                                    "next_msg": "Analysing...",
                                    "last": False}))
            self.flush()
            ORSService().painting_put(1, self.async_callback(self.on_service_response))
            print "ret"
        except NullRequestError, (instance):
            self.log.debug(instance.err_msg)
            self.write(json_encode({"success": False, "next_msg": "Empty request received.", "last": True}))
            self.finish()
        except SizeLimitExceededError, (instance):
            self.log.debug(instance.err_msg)
            self.write(json_encode({"success": False, "next_msg": "You've exceeded the allowed file limit.", "last": True}))
            self.finish()
        except WrongMimetypeError, (instance):
            self.log.debug(instance.err_msg)
            self.write(json_encode({"success": False, "next_msg": "Non-permitted mimetype.", "last": True}))
            self.finish()
        except WrongExtensionError, (instance):
            self.log.debug(instance.err_msg)
            self.write(json_encode({"success": False, "next_msg": "Non-permitted extension.", "last": True}))
            self.finish()
        except:
            # Hmm not ideal. Fix it.
            import sys
            print "UNEXPECTED EXCEPTION: ", sys.exc_info()[0]
            raise