#!/usr/bin/env python

from org.iproj.handler import base
from org.iproj.file.uploader import Uploader, SizeLimitExceededError, WrongMimetypeError, WrongExtensionError, NullRequestError
from org.iproj.ors.service import ORSService
from tornado.escape import json_encode, json_decode
import tornado.web
import tornado.ioloop

class MainPanelHandler(base.BaseHandler):
    
    def base_get(self):
        """ Renders landing page. """
        self.render("main.html")
    
    def base_post(self):
        """ Not implemented. """
        pass

class UploadPanelHandler(base.BaseHandler):
    
    def make_chunk(self, data):
        junk = "This is some junk to make Safari and Chrome stream the packets. Junk, oh junk, you aaaaareee my juuuuuunkkkkkkkkkkkk.Junk, oh junk, you aaaaareee my juuuuuunkkkkkkkkkkkk.Junk, oh junk, you aaaaareee my juuuuuunkkkkkkkkkkkk.Junk, oh junk, you aaaaareee my juuuuuunkkkkkkkkkkkk."
        data.update({"junk" : junk})
        json = json_encode(data)
        return "<span>" + json + "</span>"
    
    def on_service_response(self, http_response):
        """ ORSService async callback. """
        success = http_response.code == 200
        response = {"success": success, "last": True}
        if success:
            json = json_decode(http_response.body)
            if json["s"]:
                response["next_msg"] = "Your painting was uploaded succesfully. :-)"
                response["id"] = json["id"]
            else:
                response["next_msg"] = json["msg"]
        else:
            response["next_msg"] = "Server error. Please try again."
        
        chunk = self.make_chunk(response)
        self.write(chunk)
        self.flush()
        self.finish()
    
    def base_get(self):
        """ Not implemented. """
        pass
    
    @tornado.web.asynchronous
    def base_post(self):
        """ Uploads & checks file, then delegates to ORSService for analysis. """
        allowed_extensions = ["png", "jpg", "gif", "bmp", "jpeg", "bm", "svg", "tiff"]
        allowed_mimetypes = ["image/png", "image/jpeg", "image/jpg",
                             "image/gif", "image/bmp", "image/x-windows-bmp",
                             "image/svg+xml", "image/tiff"]
        try:
            file = Uploader(self.request).size(614400).extensions(allowed_extensions).mimetypes(allowed_mimetypes).check(upload=True)
            chunk = self.make_chunk({"success":True,
                                   "next_msg":"Analysing..",
                                   "last":False})
            self.write(chunk)
            self.flush()
            service = ORSService()
            if len(file) == 1:
                id = file[0].split(".")[0]
                service.put_painting(id, self.async_callback(self.on_service_response))
            else:
                self.write(self.make_chunk({"success":False, "next_msg":"No file selected for upload.", "last":True}))
                self.finish()
        except NullRequestError, (instance):
            self.log.debug(instance.err_msg)
            self.write(self.make_chunk({"success":False, next_msg:"Empty request received.", "last":True}))
            self.finish()
        except SizeLimitExceededError, (instance):
            self.log.debug(instance.err_msg)
            self.write(self.make_chunk({"success":False, next_msg:"You've exceeded the allowed file limit.","last":True}))
            self.finish()
        except WrongMimetypeError, (instance):
            self.log.debug(instance.err_msg)
            self.write(self.make_chunk({"success":False, "next_msg":"Non-permitted mimetype.", "last":True}))
            self.finish()
        except WrongExtensionError, (instance):
            self.log.debug(instance.err_msg)
            self.write(self.make_chunk({"success":False, "next_msg":"Non-permitted extension.", "last":True}))
            self.finish()
        except:
            # Hmm not ideal. Fix it.
            import sys
            print "UNEXPECTED EXCEPTION: ", sys.exc_info()[0]
            raise