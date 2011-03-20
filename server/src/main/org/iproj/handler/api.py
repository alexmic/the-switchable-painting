#!/usr/bin/env python

import tornado.web
import os
import base64
from tornado.escape import json_decode
from org.iproj.ors.service import ORSService
from org.iproj.handler import base

class MatchHandler(base.BaseHandler):
    
    def on_service_response(self, http_response):
        try:
            if http_response.error:
                http_response.rethrow()
            if http_response.code == 200:
                self.finish(http_response.body)
            else:
                self.write("An error occured.")
                self.finish()
        except Exception, e:
            self.log.error(e)
            self.write(str(e))
            self.finish()
    
    @tornado.web.asynchronous
    def base_post(self):
        service = ORSService()
        payload = self.get_argument("payload")
        if not payload:
            self.finish("No payload detected.")
            return
        service.get_match(payload, self.async_callback(self.on_service_response))

    def base_get(self):
        raise tornado.web.HTTPError(403)