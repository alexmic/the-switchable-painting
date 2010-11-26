#!/usr/bin/env python

import tornado.httpserver
import tornado.ioloop
import tornado.web
import os
import ConfigParser
import logging

from org.iproj.handler import base, api

########
# Boot #
########

# Application specific parameters
Settings = {
    "static_path": os.path.join("..","..","..","..","assets"),
    "cookie_secret": "exwtriaarxidia",
    "template_path": os.path.join("web", "templates")
}

# Configuration parameters
Config = ConfigParser.ConfigParser(allow_no_value=True)

# Application handlers
Handlers = [
    ("/api/", api.ApiHandler)
]

class Application(tornado.web.Application):
    def __init__(self, env):
        
        # Setup logging.
        LEVELS = {
            'debug': logging.DEBUG,
            'info': logging.INFO,
            'warning': logging.WARNING,
            'error': logging.ERROR,
            'critical': logging.CRITICAL }
        
        self.__log_level = LEVELS.get(Config.get(env, "log_level"), logging.NOTSET)
        frm = logging.Formatter("%(asctime)s : %(name)s : %(levelname)s : %(message)s")
        self.__file_handler = logging.FileHandler(os.path.join("..", "..", "..", "..", "log", Config.get(env, "log_file")))
        self.__file_handler.setLevel(logging.WARNING)
        self.__file_handler.setFormatter(frm)
        
        self.__stream_handler = logging.StreamHandler()
        self.__stream_handler.setLevel(logging.DEBUG)
        self.__stream_handler.setFormatter(frm)
        tornado.web.Application.__init__(self, Handlers, **Settings)

    def log(self, name):
        logger = logging.getLogger(name)
        logger.setLevel(self.__log_level)
        logger.addHandler(self.__file_handler)
        logger.addHandler(self.__stream_handler)
        return logger
        

if __name__ == "__main__":
    import sys
    if len(sys.argv) > 1:
        env = sys.argv[1]
    else:
        env = "dev"
    Config.read('config.cfg')
    http_server = tornado.httpserver.HTTPServer(Application(env))
    http_server.listen(Config.getint(env, "server_port"))
    tornado.ioloop.IOLoop.instance().start()