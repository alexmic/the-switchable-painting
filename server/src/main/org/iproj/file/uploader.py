#!/usr/bin/env python

from org.iproj.error.udexception import BaseError
import string
import os
import uuid
from org.iproj.etc import fn
import mimetypes

class Uploader:
    
    def __init__(self, request = None):
        self.__active_validators = []
        self.uploaded_files = []
        if request == None:
            raise NullRequestError()
        self.__files = request.files
        
    ##############
    # VALIDATORS #
    ##############
    def size(self, size):
        def _size(file):
            print len(file["body"])
            if len(file["body"]) > size:
                raise SizeLimitExceededError()
        self.__active_validators.append(_size)
        return self
    
    def extensions(self, allowed = None):
        def _extensions(file):
            tokens = file["filename"].split(".")
            ext = tokens[len(tokens) - 1].lower()
            if allowed != None and ext not in allowed:
                raise WrongExtensionError(ext)
        self.__active_validators.append(_extensions)
        return self
    
    def mimetypes(self, allowed = None):
        mimetypes.init()
        def _mimetypes(file):
            given_mtype = file["content_type"]
            derived_mtype = mimetypes.guess_type(file["filename"])
            if allowed == None:
                return
            if derived_mtype[0] != None and given_mtype != derived_mtype[0]:
                raise WrongMimetypeError(given_mtype)
            if given_mtype not in allowed:
                raise WrongMimetypeError(given_mtype)
        self.__active_validators.append(_mimetypes)
        return self
    
    # Performs validation on the current request.
    # If a validator's constraints are not met, throw the appropriate exception.
    def check(self, upload=False):
        for filekey, files in self.__files.iteritems():
            for file in files:
                for validate in self.__active_validators:
                    validate(file)
                if upload:
                    filename = self.upload(file)
                    self.uploaded_files.append(filename)
        return self.uploaded_files
            
    # Upload a file. Should be called only after the file to be uploaded
    # has passed validation.
    def upload(self, file):
        tokens = file["filename"].split(".")
        ext = tokens[len(tokens) - 1].lower()
        filename = str(uuid.uuid4()) + "." + ext
        path = self.create_path(filename)
        self.write(file, path)
        return filename
    
    # Creates and returns path from filename. The images are evenly distributed by deriving
    # the path from the filename:
    # The first two digits of the filename correspond to the two levels
    # of the directory tree structure. That gives 256 dirs for a UUID
    # of 32 hex digits.
    def create_path(self, filename):
        fst_lvl_path = os.path.join(fn.find_abs_path("server", __file__), "storage", "img", filename[0])
        snd_lvl_path = os.path.join(fst_lvl_path, filename[1])
        if not os.path.exists(fst_lvl_path):
            os.mkdir(fst_lvl_path)    
        if not os.path.exists(snd_lvl_path):
            os.mkdir(snd_lvl_path)
        return os.path.join(snd_lvl_path, filename)
    
    # Writes the file to the file system.
    def write(self, file, path):
        try:
            f = open(path, 'wb')
            f.write(file['body'])
        finally:
            f.close()
            
class SizeLimitExceededError(BaseError):
    def __init__(self):
        super(SizeLimitExceededError, self).__init__("Exceeded file size limit.", 0)

class WrongMimetypeError(BaseError):
    def __init__(self, mimetype):
        super(WrongMimetypeError, self).__init__("Wrong mimetype -> " + mimetype, 1)

class WrongExtensionError(BaseError):
    def __init__(self, extension):
        super(WrongExtensionError, self).__init__("Wrong extension -> " + extension, 2)    

class NullRequestError(BaseError):
    def __init__(self):
        super(NullRequestError, self).__init__("Null request received in Uploader.", 3)
