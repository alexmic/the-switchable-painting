#!/usr/bin/env python

class BaseError(Exception):
    """ Base class for custom exceptions, which offers error message and error code fields. """
    
    def __init__(self, err_msg, err_code = 0):
        self.err_msg = err_msg
        self.err_code = err_code
    
    def __str__(self):
        return self.err_msg + " [Error Code: " + str(self.err_code) + "]"
        
    def __repr__(self):
        return self.err_msg + " [Error Code: " + str(self.err_code) + "]"
