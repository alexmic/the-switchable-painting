#!/usr/bin/env python

from org.iproj.handler import base
from org.iproj.file.uploader import Uploader, SizeLimitExceededError, WrongMimetypeError, WrongExtensionError, NullRequestError
from org.iproj.ors.service import ORSService
from tornado.escape import json_encode, json_decode
import tornado.web
import tornado.ioloop

class SimPanelHandler(base.BaseHandler):
    
    def on_service_response(self, http_response):
        try:
            if http_response.error:
                http_response.rethrow()
            if http_response.code == 200:
                json_response = json_decode(http_response.body)
                if json_response["success"] == True:
                    self.render("sim.html", simid=json_response["simid"], imgs=json_response["imgs"])
                else:
                    self.finish(json_response["err_msg"])
            else:
                self.finish("An error occured.")
        except Exception, e:
            self.log.error(e)
            self.write(str(e))
            self.finish()
    
    @tornado.web.asynchronous
    def base_get(self, strategy, title):
        """ Renders landing page. """
        strategy = int(strategy or 0)
        title = self.cap(title)
        if strategy not in [0, 1]:
            strategy = 0
        service = ORSService().sim_match(title, strategy, self.on_service_response)

    def cap(self, word_with_underscores):
        parts = word_with_underscores.split("_")
        return " ".join(map(str.capitalize, parts))

    def base_post(self):
        """ Not implemented. """
        pass

class MainPanelHandler(base.BaseHandler):
    
    def base_get(self):
        """ Renders sim page. """
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
        try:
            if http_response.error:
                http_response.rethrow()   
            if success:
                json = json_decode(http_response.body)
                if json["s"]:
                    response["next_msg"] = "Your painting was uploaded succesfully. :-)"
                    response["id"] = json["id"]
                else:
                    response["next_msg"] = json["msg"]
            else:
                response["next_msg"] = "Server error. Please try again."
        except Exception, (instance):
            self.log.error(instance)
            response["next_msg"] = "Server error. Please try again."
        finally:       
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
            title = self.get_argument("painting-title")
            artist = self.get_argument("painting-artist")
            strategy = self.get_argument("painting-strategy")
            tags = json_encode(map(str.strip, (str(self.get_argument("painting-tags") or '')).split(",")))
            if not title:
                self.write(self.make_chunk({"success":False, "next_msg":"Please specify the painting's title.", "last":True}))
                self.finish()
            elif not artist:
                self.write(self.make_chunk({"success":False, "next_msg":"Please specify the artist.", "last":True}))
                self.finish()
            else:
                service = ORSService()
                if len(file) == 1:
                    id = file[0].split(".")[0]
                    service.describe_painting(id, title, artist, strategy, tags, self.async_callback(self.on_service_response))
                else:
                    self.write(self.make_chunk({"success":False, "next_msg":"No file selected for upload.", "last":True}))
                    self.finish()
        except (NullRequestError, SizeLimitExceededError, WrongMimetypeError, WrongExtensionError), e:
            self.log.error(e.err_msg)
            self.write(self.make_chunk({"success":False, "next_msg":e.err_msg, "last":True}))
            self.finish()
        except Exception, e:
            self.log.error(e)
            self.write(self.make_chunk({"success":False, "next_msg":"An error occured. Please try again.", "last":True}))
            self.finish()
            raise