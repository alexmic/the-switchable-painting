#!/usr/bin/env python

from UserDict import UserDict
from org.iproj.error.udexception import BaseError
import re

class Property(UserDict):
    """ A property class to read properties from files or lists. """
    
    def __init__(self, properties = None):
        if properties == None:
            return
        if type(properties).__name__ == "string":
            f = open(properties)
            self.__parse(f.readlines())
        elif type(properties).__name__ == "list":
            self.__parse(properties)
        else:
            raise PropertyError("Properties supplied must be a filename or a list of properties.", 0)
            
    def __parse(self, lines):
        regex = re.compile("^(\s)*[a-zA-Z0-9]+(\s)*=(\s)*[a-zA-Z0-9\.]+(\s)*")
        for line in lines:
            if regex.match(line):
                tokens = map(str.strip, line.split("="))
                if len(tokens) != 2:
                    raise PropertyError("Illegal format of line '" + line + "' in property file.", 1)
                self.__dict__[tokens[0]] = tokens[1]
            elif line.startswith("#"):
                continue
            else:
                raise PropertyError("Illegal format of line '" + line + "' in property file.", 1)
        
class PropertyError(BaseError):
    def __init__(self, err_msg, err_code):
        super(PropertyError, self).__init__(err_msg, err_code)